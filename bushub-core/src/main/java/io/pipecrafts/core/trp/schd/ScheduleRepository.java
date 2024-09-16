package io.pipecrafts.core.trp.schd;

import io.pipecrafts.commons.core.flt.bus.BusType;
import io.pipecrafts.commons.core.trp.route.Route;
import io.pipecrafts.commons.core.trp.schd.Schedule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

import static io.pipecrafts.core.jooq.trp.tables.BHRoute.ROUTE;
import static io.pipecrafts.core.jooq.trp.tables.BHSchedule.SCHEDULE;
import static org.jooq.Records.mapping;
import static org.jooq.impl.DSL.row;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class ScheduleRepository {

  private final DSLContext dsl;

  public Long create(Schedule schedule) {
    final var id = dsl.insertInto(SCHEDULE)
      .set(SCHEDULE.ROUTE_ID, schedule.routeId())
      .set(SCHEDULE.DEPARTURE_TIME, schedule.departureTime())
      .set(SCHEDULE.BUS_TYPE, schedule.busType())
      .returning(SCHEDULE.ID)
      .fetchAny(SCHEDULE.ID);

    // improve with logging AOP.
    log.trace("Schedule created {}", id);
    return id;
  }

  @Transactional(readOnly = true)
  public List<Schedule> selectAll() {

    return dsl.select(SCHEDULE.ID, SCHEDULE.ROUTE_ID, SCHEDULE.DEPARTURE_TIME, SCHEDULE.BUS_TYPE,
        row(ROUTE.ID, ROUTE.ORIGIN, ROUTE.DESTINATION, ROUTE.DISTANCE, ROUTE.CODE).mapping(Route::new)
      )
      .from(SCHEDULE)
      .join(ROUTE)
      .on(SCHEDULE.field(SCHEDULE.ROUTE_ID).eq(ROUTE.field(ROUTE.ID)))
      .fetch(mapping(Schedule::new));
  }

  // TODO: Check how to use nested objects properly
  private Schedule convert(
    long id, long routeId, LocalTime departureTime, BusType busType,
    String origin, String destination, Integer distance, String code
  ) {
    final var route = new Route(routeId, origin, destination, distance, code);
    return new Schedule(id, routeId, departureTime, busType, route);
  }
}
