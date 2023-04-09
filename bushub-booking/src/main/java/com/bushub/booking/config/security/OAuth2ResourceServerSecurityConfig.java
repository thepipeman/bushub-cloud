package com.bushub.booking.config.security;

import com.bushub.security.jwt.BushubJwtAuthConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
public class OAuth2ResourceServerSecurityConfig {

  private static final String SCOPE_BOOKING = "SCOPE_booking";

  private final BushubJwtAuthConverter bushubJwtAuthConverter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .authorizeHttpRequests()
      .anyRequest()
      // initial version is only customer can access this
      // TODO: ADMIN access for customer entities
      .hasAuthority(SCOPE_BOOKING)
      .and()
      .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer ->
          jwtConfigurer.jwtAuthenticationConverter(bushubJwtAuthConverter)
        )
      );

    return httpSecurity.build();
  }

}
