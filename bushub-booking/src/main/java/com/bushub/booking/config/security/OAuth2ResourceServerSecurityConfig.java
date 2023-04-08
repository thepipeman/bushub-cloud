package com.bushub.booking.config.security;

import com.bushub.commons.security.jwt.BushubJwtAuthConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
public class OAuth2ResourceServerSecurityConfig {

  private final BushubJwtAuthConverter bushubJwtAuthConverter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .authorizeHttpRequests()
      .anyRequest()
      .hasAnyAuthority("ROLE_CUSTOMER", "SCOPE_booking")
      .and()
      .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer ->
          jwtConfigurer.jwtAuthenticationConverter(bushubJwtAuthConverter)
        )
      );

    return httpSecurity.build();
  }

}
