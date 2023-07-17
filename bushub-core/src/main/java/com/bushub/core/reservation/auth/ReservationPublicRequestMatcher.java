package com.bushub.core.reservation.auth;

import com.bushub.core.config.security.PublicRequestMatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

@Component
public class ReservationPublicRequestMatcher implements PublicRequestMatcher {

  private final RequestMatcher matchers = new OrRequestMatcher(
    antMatcher("/trips/**", HttpMethod.GET),
    antMatcher("/schedules/**", HttpMethod.GET),
    antMatcher("/routes/**", HttpMethod.GET)
  );

  @Override
  public boolean matches(HttpServletRequest request) {
    return matchers.matches(request);
  }
}
