package io.pipecrafts.commons.core.flt.bus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Bus(
  Long id,
  @NotNull
  BusType type,

  @NotBlank
  String plateNumber,

  String number
) {
}
