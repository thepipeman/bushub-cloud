package io.pipecrafts.commons.core.trp.schd;

import io.pipecrafts.commons.core.flt.bus.BusType;
import io.pipecrafts.commons.core.trp.route.Route;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalTime;

public record Schedule(

  Long id,

  @NotNull
  Long routeId,

  @NotNull
  LocalTime departureTime,

  BusType busType,

  Route route

) implements Serializable {
}
