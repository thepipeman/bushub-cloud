package com.bushub.booking.customer.trip.model;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CustomerBooking(
  Trip trip,
  int seatNumber,
  BigDecimal fare,
  String customerName
) {
}
