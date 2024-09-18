package io.pipecrafts.core.trp.route;

import io.pipecrafts.commons.core.trp.route.Route;
import io.pipecrafts.commons.core.trp.route.RouteCriteria;
import io.pipecrafts.commons.data.page.PageData;
import io.pipecrafts.commons.tools.error.BhResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static io.pipecrafts.core.jooq.trp.tables.BHRoute.ROUTE;
import static org.jooq.Records.mapping;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class RouteRepository {

  private final DSLContext dsl;

  // ref: https://github.com/pkainulainen/jooq-with-spring-examples/blob/master/jooq-only/src/main/java/net/petrikainulainen/spring/jooq/todo/repository/JOOQTodoRepository.java
  public Long create(Route route) {
    final var code = StringUtils.isNotBlank(route.code()) ? route.code() : UUID.randomUUID().toString();
    final var id = dsl.insertInto(ROUTE)
      .set(ROUTE.ORIGIN, route.origin())
      .set(ROUTE.DESTINATION, route.destination())
      .set(ROUTE.DISTANCE, route.distance())
      .set(ROUTE.CODE, code)
      .returning(ROUTE.ID)
      .fetchAny(ROUTE.ID);

    log.trace("Route created {}", id);
    return id;
  }

  @Transactional(readOnly = true)
  public PageData<Route> selectByCriteria(RouteCriteria criteria) {
    var condition = DSL.noCondition();

    if (StringUtils.isNotBlank(criteria.origin())) {
      condition = condition.and(ROUTE.ORIGIN.eq(criteria.origin()));
    }

    if (StringUtils.isNotBlank(criteria.destination())) {
      condition = condition.and(ROUTE.DESTINATION.eq(criteria.destination()));
    }

    final var data = dsl.select(ROUTE.ID, ROUTE.ORIGIN, ROUTE.DESTINATION, ROUTE.DISTANCE, ROUTE.CODE)
      .from(ROUTE)
      .where(condition)
      .orderBy(ROUTE.ID.desc())
      .limit(criteria.pageSize())
      .offset(criteria.offSet())
      .fetch(mapping(Route::new));

    return PageData.of(criteria.pageNumber(), data);
  }

  @Transactional(readOnly = true)
  public Route readById(long id) {
    final var optionalRoute = dsl.select(ROUTE.ID, ROUTE.ORIGIN, ROUTE.DESTINATION, ROUTE.DISTANCE, ROUTE.CODE)
      .from(ROUTE)
      .where(ROUTE.ID.eq(id))
      .fetchOptional(mapping(Route::new));

    return optionalRoute.orElseThrow(() -> BhResourceNotFoundException.ofId(Route.class, id));
  }

}
