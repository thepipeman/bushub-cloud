package com.bushub.booking.util.http;

import com.bushub.commons.user.context.UserContext;
import com.bushub.commons.user.context.UserContextHolder;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CustomHttpConfig {

  // Inject JWT authentication here motherfucker
  @Bean
  public RequestInterceptor requestInterceptor() {
    return requestTemplate -> {
      requestTemplate.header(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
      requestTemplate.header(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());
      log.debug("Request headers: {}", UserContextHolder.getContext().getCorrelationId());
    };
  }
}
