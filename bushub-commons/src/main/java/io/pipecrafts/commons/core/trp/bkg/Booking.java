package io.pipecrafts.commons.core.trp.bkg;

import io.pipecrafts.commons.core.flt.bus.BusType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record Booking(
  Long id,
  String referenceNumber,
  int seatNumber,
  BigDecimal fare,
  String customerName,
  BookingStatus status,
  LocalDate departureDate,
  LocalTime departureTime,
  String origin,
  String destination,
  int distance,
  BusType busType,
  String busNumber
) {
}
