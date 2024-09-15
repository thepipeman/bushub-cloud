package io.pipecrafts.core.fleet;

import io.pipecrafts.commons.core.flt.bus.BusType;
import io.pipecrafts.core.jooq.vh.enums.BHBusType;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BusJooqUtil {

  public static BHBusType convertToBhBusType(BusType busType) {
    // improve with validation and exception handling
    return BHBusType.valueOf(busType.name());
  }

  public static BusType convertFromBhBusType(BHBusType bhBusType) {
    return BusType.valueOf(bhBusType.name());
  }

}
