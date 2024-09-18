package io.pipecrafts.commons.core.trp.domain;

import io.pipecrafts.commons.data.page.PageCriteria;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TripSearchCriteria extends PageCriteria {

  private Long scheduleId;

  private LocalDate departureDate;
}
