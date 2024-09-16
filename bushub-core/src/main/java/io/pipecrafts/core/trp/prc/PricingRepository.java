package io.pipecrafts.core.trp.prc;

import io.pipecrafts.commons.core.flt.bus.Bus;
import io.pipecrafts.commons.core.flt.bus.BusType;
import io.pipecrafts.commons.core.trp.prc.Pricing;
import io.pipecrafts.commons.tools.error.BhResourceNotFoundException;
import io.pipecrafts.core.jooq.vh.enums.BHBusType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static io.pipecrafts.core.fleet.BusJooqUtil.convertFromBhBusType;
import static io.pipecrafts.core.fleet.BusJooqUtil.convertToBhBusType;
import static io.pipecrafts.core.jooq.trp.tables.BHPricing.PRICING;
import static org.jooq.Records.mapping;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class PricingRepository {

  private final DSLContext dsl;

  public Long create(Pricing pricing) {
    final var id = dsl.insertInto(PRICING)
      .set(PRICING.BASE_FARE, pricing.baseFare())
      .set(PRICING.PER_KM_FARE, pricing.perKmFare())
      .set(PRICING.BUS_TYPE, pricing.busType())
      .returning(PRICING.ID)
      .fetchAny(PRICING.ID);

    log.trace("Pricing created {}", id);
    return id;
  }

  @Transactional(readOnly = true)
  public List<Pricing> selectAll() {
    return dsl.select(PRICING.ID, PRICING.BASE_FARE, PRICING.PER_KM_FARE, PRICING.BUS_TYPE)
      .from(PRICING)
      .fetch(mapping(Pricing::new));
  }

  @Transactional(readOnly = true)
  public Pricing selectByBusType(BusType busType) {
    final var optionalPricing = dsl.select(PRICING.ID, PRICING.BASE_FARE, PRICING.PER_KM_FARE, PRICING.BUS_TYPE)
      .from(PRICING)
      .where(PRICING.BUS_TYPE.equal(busType))
      .fetchOptional(mapping(Pricing::new));

    return optionalPricing.orElseThrow(() -> new BhResourceNotFoundException(Pricing.class, "busType", busType.name()));

  }

}
