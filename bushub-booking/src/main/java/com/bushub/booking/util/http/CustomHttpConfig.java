package com.bushub.booking.util.http;

import com.bushub.commons.user.context.UserContext;
import com.bushub.commons.user.context.UserContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

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
      setAuthorizationHeader(requestTemplate);
    };
  }

  private void setAuthorizationHeader(RequestTemplate requestTemplate) {
    final var authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      // guard clause -> LEAVE!
      return;
    }
    final var jwtAuthentication = (JwtAuthenticationToken) authentication;
    requestTemplate.header(
      HttpHeaders.AUTHORIZATION,
      String.format("Bearer %s", jwtAuthentication.getToken().getTokenValue())
    );
  }
}
