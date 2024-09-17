package io.pipecrafts.commons.core.trp.domain;

import io.pipecrafts.commons.core.trp.schd.Schedule;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record Trip(
  Long id,

  @NotNull
  Long scheduleId,

  @NotNull
  @Future
  LocalDate departureDate,

  @NotNull
  TripStatus status,

  Long busId,

  Schedule schedule

) {
}
