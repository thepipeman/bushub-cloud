package com.bushub.booking.customer.trip.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerBookingRequest {

  @NotNull
  private Long tripId;
  @NotNull
  private Integer seatNumber;
  @NotNull
  private BigDecimal fare;
}
