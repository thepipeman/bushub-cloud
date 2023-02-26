package com.bushub.core.reservation.route;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RouteService {

  private final RouteRepository routeRepository;

  public Long create(Route route) {
    final var created = routeRepository.save(route);
    log.trace("Route created [id=<{}>]", route.getId());
    return created.getId();
  }

  @Transactional(readOnly = true)
  public List<Route> readAll() {
    return Lists.newArrayList(routeRepository.findAll());
  }
}
