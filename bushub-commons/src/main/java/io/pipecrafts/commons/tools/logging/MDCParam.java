package io.pipecrafts.commons.tools.logging;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MDCParam {
  
  REQUEST_ID("requestId");
  
  @Getter
  private final String mdcName;
}
