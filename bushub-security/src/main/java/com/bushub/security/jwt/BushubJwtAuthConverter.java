package com.bushub.security.jwt;

import com.bushub.security.auth.UserJwtAuthentication;
import com.bushub.security.common.user.UserRole;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2TokenIntrospectionClaimNames;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class BushubJwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

  private static final String AUTHORITIES_CLAIM = "authorities";

  private final Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

  @Override
  public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
    // TODO:
    // extend JwtGrantedAuthoritiesConverter  -> make use of ROLES as well. or;
    // append the roles to the authorities
    Collection<GrantedAuthority> authorities = jwtGrantedAuthoritiesConverter.convert(jwt);
    log.debug("Security - authorities detected {}", authorities);
    final String principalClaimValue = jwt.getClaimAsString(OAuth2TokenIntrospectionClaimNames.USERNAME);
    final String subClaimValue = jwt.getClaimAsString(OAuth2TokenIntrospectionClaimNames.SUB);
    final UserRole role = getRole(jwt);

    return new UserJwtAuthentication(
      jwt,
      authorities,
      principalClaimValue,
      subClaimValue,
      role
    );
  }


  private UserRole getRole(Jwt jwt) {
    final Set<String> roles = Sets.newHashSet(jwt.getClaimAsStringList(AUTHORITIES_CLAIM));

    // todo optimize with static initialization
    final Set<String> userRoles = Arrays.stream(UserRole.values())
      .map(UserRole::name)
      .collect(Collectors.toSet());

    final List<String> commonRoles = Lists.newArrayList(CollectionUtils.intersection(roles, userRoles));
    if (commonRoles.size() != 1) {
      throw new RuntimeException("Invalid roles setup, exactly one user role definition is required per user.");
    }

    return UserRole.valueOf(commonRoles.get(0));
  }

}
