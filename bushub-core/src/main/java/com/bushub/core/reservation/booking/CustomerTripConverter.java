package com.bushub.core.reservation.booking;

import com.bushub.commons.trip.CustomerBookedTrip;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
class CustomerTripConverter implements Function<Booking, CustomerBookedTrip> {

  @Override
  public CustomerBookedTrip apply(Booking booking) {
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
