package io.pipecrafts.commons.tools.spring.request;

import io.pipecrafts.commons.tools.logging.MDCParam;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BhRequestFilter implements Filter {

  @Override
  @SneakyThrows
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {

    // TODO Change this later with sleuth or whatever tracing is available in Spring Boot 3
    try {
      final var requestId = UUID.randomUUID().toString();
      RequestContext.setRequestId(requestId);
      MDC.put(MDCParam.REQUEST_ID.getMdcName(), requestId.substring(0, 8));
      filterChain.doFilter(servletRequest, servletResponse);
    } finally {
      MDC.clear();
      RequestContext.clear();
    }
  }
}
