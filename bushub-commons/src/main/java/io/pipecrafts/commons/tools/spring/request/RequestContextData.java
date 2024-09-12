package io.pipecrafts.commons.tools.spring.request;

import lombok.Builder;

import java.io.Serializable;

@Builder(toBuilder = true)
public record RequestContextData(
  String requestId
)  implements Serializable {

  public static RequestContextData empty() {
    return RequestContextData.builder().build();
  }
}
