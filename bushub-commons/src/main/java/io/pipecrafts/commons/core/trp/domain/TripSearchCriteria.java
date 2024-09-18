package io.pipecrafts.commons.core.trp.domain;

import io.pipecrafts.commons.data.page.PageCriteria;

import java.time.LocalDate;


public record TripSearchCriteria(

  Long scheduleId,

  LocalDate departureDate

) implements PageCriteria {

}