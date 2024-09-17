package io.pipecrafts.core.trp.domain;

import io.pipecrafts.commons.core.trp.domain.Trip;
import io.pipecrafts.commons.core.trp.domain.TripSearchCriteria;
import io.pipecrafts.commons.core.trp.domain.TripStatus;
import io.pipecrafts.commons.core.trp.route.Route;
import io.pipecrafts.commons.core.trp.schd.Schedule;
import io.pipecrafts.commons.tools.error.BhResourceNotFoundException;
import io.pipecrafts.core.jooq.trp.tables.BHSchedule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record6;
import org.jooq.SelectField;
import org.jooq.SelectOnConditionStep;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static io.pipecrafts.core.jooq.trp.tables.BHRoute.ROUTE;
import static io.pipecrafts.core.jooq.trp.tables.BHSchedule.SCHEDULE;
import static io.pipecrafts.core.jooq.trp.tables.BHTrip.TRIP;
import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.row;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class TripRepository {

  private final DSLContext dsl;

  public Long create(Trip trip) {
    final var id = dsl.insertInto(TRIP)
      .set(TRIP.SCHEDULE_ID, trip.scheduleId())
      .set(TRIP.DEPARTURE_DATE, trip.departureDate())
      .set(TRIP.STATUS, trip.status())
      .set(TRIP.BUS_ID, trip.busId())
      .returning(TRIP.ID)
      .fetchAny(TRIP.ID);

    log.trace("Trip created {}", id);
    return id;
  }

  @Transactional(readOnly = true)
  public List<Trip> selectTripsByCriteria(TripSearchCriteria criteria) {
    var condition = DSL.noCondition();

    if (criteria.scheduleId() != null) {
      condition = condition.and(TRIP.SCHEDULE_ID.eq(criteria.scheduleId()));
    }

    if (criteria.departureDate() != null) {
      condition = condition.and(TRIP.DEPARTURE_DATE.eq(criteria.departureDate()));
    }

    return selectTrips()
      .where(condition)
      .fetch(mapping(Trip::new));
  }

  @Transactional(readOnly = true)
  public Trip selectById(Long id) {
    final var optionalTrip = selectTrips()
      .where(TRIP.field(TRIP.ID).eq(id))
      .fetchOptional(mapping(Trip::new));

    return optionalTrip.orElseThrow(() -> BhResourceNotFoundException.ofId(Trip.class, id));
  }

  private SelectOnConditionStep<Record6<Long, Long, LocalDate, TripStatus, Long, Schedule>> selectTrips() {
    return dsl.select(TRIP.ID, TRIP.SCHEDULE_ID, TRIP.DEPARTURE_DATE, TRIP.STATUS, TRIP.BUS_ID, scheduleRow())
      .from(TRIP)
      .join(SCHEDULE)
      .on(SCHEDULE.field(SCHEDULE.ID).eq(TRIP.field(TRIP.SCHEDULE_ID)))
      .join(ROUTE)
      .on(ROUTE.field(ROUTE.ID).eq(SCHEDULE.field(SCHEDULE.ROUTE_ID)));
  }

  private SelectField<Schedule> scheduleRow() {
    return row(SCHEDULE.ID, SCHEDULE.ROUTE_ID, SCHEDULE.DEPARTURE_TIME, SCHEDULE.BUS_TYPE,
      row(ROUTE.ID, ROUTE.ORIGIN, ROUTE.DESTINATION, ROUTE.DISTANCE, ROUTE.CODE).mapping(Route::new))
      .mapping(Schedule::new);
  }

}
