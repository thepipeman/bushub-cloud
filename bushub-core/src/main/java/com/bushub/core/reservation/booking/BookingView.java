package com.bushub.core.reservation.booking;

import com.bushub.core.bus.BusType;
import com.bushub.core.reservation.trip.TripStatus;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Value
@Builder
public class BookingView {

  String referenceNumber;

  BookingStatus bookingStatus;

  BigDecimal fare;

  LocalDate departureDate;

  LocalTime departureTime;

  TripStatus tripStatus;

  BusType busType;

  String origin;

  String destination;

  int distance;

  String tripCode;
}
