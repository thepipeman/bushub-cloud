package io.pipecrafts.core.trp;

import io.pipecrafts.commons.core.flt.bus.BusType;
import io.pipecrafts.commons.core.trp.prc.Pricing;
import io.pipecrafts.core.trp.prc.PricingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PricingService {

  private final PricingRepository pricingRepository;

  @Transactional(readOnly = true)
  public Pricing readByBusType(BusType busType) {
    return pricingRepository.selectByBusType(busType);
  }
}
