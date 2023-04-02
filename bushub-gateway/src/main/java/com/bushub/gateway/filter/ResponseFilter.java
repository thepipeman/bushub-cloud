package com.bushub.gateway.filter;

import com.bushub.commons.filter.CorrelationFilterUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ResponseFilter {

  private final CorrelationFilterUtils correlationFilterUtils;

  @Bean
  public GlobalFilter postGlobalCorrelationIdFilter() {
    return ((exchange, chain) -> chain.filter(exchange).then(Mono.fromRunnable(() -> {
      final var reqHeaders = exchange.getRequest().getHeaders();
      final var correlationId = correlationFilterUtils.getCorrelationId(reqHeaders);
      log.debug("Adding correlation ID to response headers. {}", correlationId);
      exchange.getResponse().getHeaders().add(CorrelationFilterUtils.CORRELATION_ID, correlationId);
      log.debug("Completing outgoing request for {}", exchange.getRequest().getURI());
    })));
  }

}
