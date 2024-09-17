package io.pipecrafts.commons.core.trp.domain;

import java.time.LocalDate;

public record TripSearchCriteria(
  Long scheduleId,

  LocalDate departureDate
) {
}
