package io.pipecrafts.commons.core.trp.bkg;

import io.pipecrafts.commons.core.trp.domain.Trip;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

// TODO: use CQRS~ish approach.
public record Booking(
  Long id,

  @NotNull
  Long tripId,

  @NotNull
  @Min(1)
  Integer seatNumber,

  // calculated in service layer
  BigDecimal fare,

  String customerName,

  @NotNull
  BookingStatus status,

  // generated on serviceLayer
  String referenceNumber,

  Trip trip
) {
}
