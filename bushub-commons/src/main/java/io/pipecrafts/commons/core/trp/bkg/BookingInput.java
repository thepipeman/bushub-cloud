package io.pipecrafts.commons.core.trp.bkg;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

// TODO: use CQRS~ish approach.
public record BookingInput(

  @NotNull
  Long tripId,

  @NotNull
  @Min(1)
  Integer seatNumber,

  String customerName,

  @NotNull
  BookingStatus status

) {
}
