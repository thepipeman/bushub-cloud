package com.bushub.core.config.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public interface PublicRequestMatcher extends RequestMatcher {

  default RequestMatcher antMatcher(String url) {
    return antMatcher(url, null);
  }

  default RequestMatcher antMatcher(String url, HttpMethod method) {
    String methodName = method == null ? null : method.name();
    return new AntPathRequestMatcher(url, methodName);
  }
}
