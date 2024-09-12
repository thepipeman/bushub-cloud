package io.pipecrafts.commons.tools.spring.request;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestContext {

  private static final ThreadLocal<RequestContextData> HOLDER = new InheritableThreadLocal<>();

  public static RequestContextData get() {
    RequestContextData contextData = HOLDER.get();
    if (contextData == null) {
      contextData = RequestContextData.empty();
      HOLDER.set(contextData);
    }

    return contextData;
  }

  public static void set(RequestContextData contextData) {
    if (contextData == null) {
       contextData = RequestContextData.empty();
    }
    HOLDER.set(contextData);
  }

  public static void setRequestId(String requestId) {
    RequestContextData contextData = get()
      .toBuilder()
      .requestId(requestId)
      .build();

    HOLDER.set(contextData);
  }

  public static void clear() {
    RequestContextData contextData = get();
    HOLDER.remove();
    log.trace("Request context cleared [{}]", contextData);
  }

}
