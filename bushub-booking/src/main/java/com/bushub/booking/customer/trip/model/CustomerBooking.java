package com.bushub.booking.customer.trip.model;

import java.math.BigDecimal;

public record CustomerBooking(
  Long tripId,
  int seatNumber,
  BigDecimal fare,
  String customerName
) {
}
