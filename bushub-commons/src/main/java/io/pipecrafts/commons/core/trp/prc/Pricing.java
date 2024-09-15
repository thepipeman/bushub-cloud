package io.pipecrafts.commons.core.trp.prc;

import io.pipecrafts.commons.core.flt.bus.BusType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

public record Pricing(

  Long id,

  @NotNull
  @Min(1)
  BigDecimal baseFare,

  @NotNull
  @Min(0)
  BigDecimal perKmFare,

  BusType busType
) implements Serializable {
}
