package io.pipecrafts.core.trp.bkg;

import io.pipecrafts.commons.core.flt.bus.BusType;
import io.pipecrafts.commons.core.trp.bkg.Booking;
import io.pipecrafts.commons.core.trp.bkg.BookingInput;
import io.pipecrafts.commons.core.trp.bkg.BookingSearchCriteria;
import io.pipecrafts.commons.core.trp.bkg.BookingStatus;
import io.pipecrafts.commons.data.page.PageData;
import io.pipecrafts.commons.tools.error.BhResourceNotFoundException;
import io.pipecrafts.core.trp.PricingService;
import io.pipecrafts.core.trp.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record14;
import org.jooq.SelectOnConditionStep;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import static io.pipecrafts.core.jooq.trp.tables.BHBooking.BOOKING;
import static io.pipecrafts.core.jooq.trp.tables.BHRoute.ROUTE;
import static io.pipecrafts.core.jooq.trp.tables.BHSchedule.SCHEDULE;
import static io.pipecrafts.core.jooq.trp.tables.BHTrip.TRIP;
import static io.pipecrafts.core.jooq.vh.tables.BHBus.BUS;
import static org.jooq.Records.mapping;

@Slf4j
@Transactional
@Repository
@RequiredArgsConstructor
public class BookingRepository {

  private static final int BASE_KM = 100;

  private final DSLContext dsl;
  private final PricingService pricingService;
  private final TripService tripService;

  public Long create(BookingInput booking) {
    final var id = dsl.insertInto(BOOKING)
      .set(BOOKING.TRIP_ID, booking.tripId())
      .set(BOOKING.FARE, calculateFare(booking.tripId()))
      .set(BOOKING.SEAT_NUMBER, booking.seatNumber())
      .set(BOOKING.CUSTOMER_NAME, booking.customerName())
      .set(BOOKING.STATUS, booking.status())
      .set(BOOKING.REFERENCE_NUMBER, UUID.randomUUID().toString())
      .returning(BOOKING.ID)
      .fetchAny(BOOKING.ID);

    log.trace("Booking created {}", id);
    return id;
  }

  @Transactional(readOnly = true)
  public Booking readById(long id) {
    final var optionalBooking = selectBooking()
      .where(BOOKING.field(BOOKING.ID).eq(id))
      .fetchOptional(mapping(Booking::new));

    return optionalBooking.orElseThrow(() -> BhResourceNotFoundException.ofId(Booking.class, id));
  }

  @Transactional(readOnly = true)
  public PageData<Booking> selectByCriteria(BookingSearchCriteria criteria) {
    var condition = DSL.noCondition();

    if (criteria.status() != null) {
      condition = condition.and(BOOKING.STATUS.eq(criteria.status()));
    }

    if (criteria.tripId() != null) {
      condition = condition.and(BOOKING.TRIP_ID.eq(criteria.tripId()));
    }

    final var data = selectBooking()
      .where(condition)
      .orderBy(BOOKING.ID.desc())
      .limit(criteria.pageSize())
      .offset(criteria.offSet())
      .fetch(mapping(Booking::new));

    return PageData.of(criteria.pageNumber(), data);
  }

  private SelectOnConditionStep<Record14<Long, Long, String, Integer, BigDecimal, String, BookingStatus, LocalDate, LocalTime, String, String, Integer, BusType, String>> selectBooking() {
    return dsl.select(BOOKING.ID, TRIP.ID, BOOKING.REFERENCE_NUMBER,
        BOOKING.SEAT_NUMBER, BOOKING.FARE, BOOKING.CUSTOMER_NAME,
        BOOKING.STATUS, TRIP.DEPARTURE_DATE, SCHEDULE.DEPARTURE_TIME,
        ROUTE.ORIGIN, ROUTE.DESTINATION, ROUTE.DISTANCE,
        SCHEDULE.BUS_TYPE, BUS.NUMBER)
      .from(BOOKING)
      .join(TRIP)
      .on(TRIP.field(TRIP.ID).eq(BOOKING.ID))
      .join(SCHEDULE)
      .on(SCHEDULE.field(SCHEDULE.ID).eq(TRIP.field(TRIP.SCHEDULE_ID)))
      .join(ROUTE)
      .on(ROUTE.field(ROUTE.ID).eq(SCHEDULE.field(SCHEDULE.ROUTE_ID)))
      .leftJoin(BUS)
      .on(BUS.field(BUS.ID).eq(TRIP.BUS_ID));
  }

  private BigDecimal calculateFare(long tripId) {
    final var trip = tripService.readById(tripId);
    final var schedule = trip.schedule();
    final var pricing = pricingService.readByBusType(schedule.busType());

    final var distance = schedule.route().distance();
    final var exceedingKm = distance - BASE_KM;

    if (exceedingKm <= BASE_KM) {
      return pricing.baseFare();
    }

    final var calculatedPerKmFare = pricing.perKmFare().multiply(BigDecimal.valueOf(exceedingKm));
    return pricing.baseFare().add(calculatedPerKmFare);
  }
}
