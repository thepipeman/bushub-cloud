package com.bushub.commons.user.context;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class UserContextFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    throws ServletException, IOException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) request;

    UserContextHolder.getContext().setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID));
    UserContextHolder.getContext().setUserId(httpServletRequest.getHeader(UserContext.USER_ID));
    UserContextHolder.getContext().setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));
    UserContextHolder.getContext().setOrganizationId(httpServletRequest.getHeader(UserContext.ORGANIZATION_ID));

    log.debug("UserContextFilter Correlation id: {}", UserContextHolder.getContext().getCorrelationId());

    chain.doFilter(httpServletRequest, response);
  }
}
