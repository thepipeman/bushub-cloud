package com.bushub.gateway.filter;

import com.bushub.commons.filter.CorrelationFilterUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Order(1)
@Component
@RequiredArgsConstructor
public class TrackingFilter implements GlobalFilter {

  private final CorrelationFilterUtils correlationFilterUtils;

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    HttpHeaders headers = exchange.getRequest().getHeaders();
    if (isCorrelationIdPresent(headers)) {
      log.info("tmx-correlation-id found in tracking filter: {}", correlationFilterUtils.getCorrelationId(headers));
    } else {
      final var correlationId = generateCorrelationId();
      // mutation watch out
      exchange = correlationFilterUtils.setCorrelationId(exchange, correlationId);
      log.info("Injecting tmx-correlation-id in tracking filter: {}", correlationId);
    }

    return chain.filter(exchange);
  }

  private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
    if (correlationFilterUtils.getCorrelationId(requestHeaders) != null) {
      return true;
    } else {
      return false;
    }
  }

  private String generateCorrelationId() {
    return java.util.UUID.randomUUID().toString();
  }
}
