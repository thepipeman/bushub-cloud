package com.bushub.core.reservation.booking;

import com.bushub.commons.trip.CustomerBookedTrip;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookingService {

  private final BookingRepository bookingRepository;

  public Long create(Booking booking) {
    final var bookingToPersist = booking.toBuilder()
      .referenceNumber(UUID.randomUUID().toString())
      .build();

    final var created = bookingRepository.save(bookingToPersist);
    log.trace("Booking created [id=<{}>]", created.getId());
    return created.getId();
  }

  @Transactional(readOnly = true)
  public Booking readById(long id) {
    return bookingRepository.findById(id)
      .orElse(null);
  }

  @Transactional(readOnly = true)
  public List<Booking> readByTripId(long tripId) {
    return Lists.newArrayList(bookingRepository.findByTripId(tripId));
  }

  @Transactional(readOnly = true)
  public CustomerBookedTrip readCustomerTripByRefNumber(String refNumber) {
    return bookingRepository.findByReferenceNumber(refNumber)
      .map(this::convertBooking)
      .orElse(null);
  }

  @Transactional(readOnly = true)
  public List<CustomerBookedTrip> readCustomerTripCollectionByRefNumber(String refNumber) {
//    return Lists.newArrayList(bookingRepository.findCollectionByReferenceNumber(refNumber));
    return bookingRepository.findCollectionByReferenceNumber(refNumber)
      .stream()
      .map(this::convertBooking)
      .collect(Collectors.toList());
  }

  private CustomerBookedTrip convertBooking(Booking booking) {
    String busNumber = booking.getTrip().getBus() != null ? booking.getTrip().getBus().getNumber() : "TBD";
    return new CustomerBookedTrip(
      booking.getReferenceNumber(),
      booking.getStatus().name(),
      booking.getFare(),
      booking.getTrip().getDepartureDate(),
      booking.getTrip().getSchedule().getDepartureTime(),
      booking.getTrip().getStatus().name(),
      booking.getTrip().getSchedule().getBusType().name(),
      booking.getTrip().getSchedule().getRoute().getOrigin(),
      booking.getTrip().getSchedule().getRoute().getDestination(),
      booking.getTrip().getSchedule().getRoute().getDistance(),
      busNumber
    );
  }

}
