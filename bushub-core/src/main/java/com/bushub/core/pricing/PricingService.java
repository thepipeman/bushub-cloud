package com.bushub.core.pricing;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
public class PricingService {

  private final PricingRepository pricingRepository;

  public Long create(Pricing pricing) {
    final var pricingCreated = pricingRepository.save(pricing);
    log.trace("Pricing created [id=<{}>]", pricingCreated.getId());
    return pricingCreated.getId();
  }

  @Transactional(readOnly = true)
  public List<Pricing> readAll() {
    return Lists.newArrayList(pricingRepository.findAll());
  }
}
