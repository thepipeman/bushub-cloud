package io.pipecrafts.core.trp.route;

import io.pipecrafts.commons.core.trp.route.Route;
import io.pipecrafts.commons.core.trp.route.RouteCriteria;
import io.pipecrafts.commons.data.page.PageData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RouteController {

  private final RouteRepository routeRepository;

  @PostMapping
  public Long create(@RequestBody @Valid Route route) {
    return routeRepository.create(route);
  }


  @GetMapping
  public PageData<Route> getRoutes(@Valid RouteCriteria routeCriteria) {
    return routeRepository.selectByCriteria(routeCriteria);
  }

}
