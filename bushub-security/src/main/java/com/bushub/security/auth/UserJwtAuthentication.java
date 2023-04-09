package com.bushub.security.auth;

import com.bushub.security.common.user.UserRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

public class UserJwtAuthentication extends JwtAuthenticationToken {

  /**
   * Mapped to "sub" claim
   */
  @Getter
  private final String authId;

  @Getter
  private final UserRole userRole;

  public UserJwtAuthentication(
    Jwt jwt,
    Collection<? extends GrantedAuthority> authorities,
    String name,
    String authId,
    UserRole role
  ) {
    super(jwt, authorities, name);
    this.authId = authId;
    this.userRole = role;
  }
}
