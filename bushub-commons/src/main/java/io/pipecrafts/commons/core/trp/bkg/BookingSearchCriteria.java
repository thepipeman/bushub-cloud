package io.pipecrafts.commons.core.trp.bkg;

import io.pipecrafts.commons.data.page.PageCriteria;

public record BookingSearchCriteria(
  Long tripId,
  BookingStatus status

) implements PageCriteria {
}
