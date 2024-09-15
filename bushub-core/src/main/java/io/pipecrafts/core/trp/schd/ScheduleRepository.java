package io.pipecrafts.core.trp.schd;

import io.pipecrafts.commons.core.trp.schd.Schedule;
import io.pipecrafts.core.fleet.BusJooqUtil;
import io.pipecrafts.core.jooq.trp.tables.records.BHScheduleRecord;
import io.pipecrafts.core.jooq.vh.enums.BHBusType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

import static io.pipecrafts.core.fleet.BusJooqUtil.convertFromBhBusType;
import static io.pipecrafts.core.jooq.trp.tables.BHSchedule.SCHEDULE;
import static org.jooq.Records.mapping;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class ScheduleRepository {

  private DSLContext dsl;

  public Long create(Schedule schedule) {
    final var id = dsl.insertInto(SCHEDULE)
      .set(SCHEDULE.ROUTE_ID, schedule.routeId())
      .set(SCHEDULE.DEPARTURE_TIME, schedule.departureTime())
      .set(SCHEDULE.BUS_TYPE, BusJooqUtil.convertToBhBusType(schedule.busType()))
      .returning(SCHEDULE.ID)
      .fetchAny(SCHEDULE.ID);

    // improve with logging AOP.
    log.trace("Schedule created {}", id);
    return id;
  }

  @Transactional(readOnly = true)
  public List<Schedule> selectAll() {
    return dsl.select(SCHEDULE.ID, SCHEDULE.ROUTE_ID, SCHEDULE.DEPARTURE_TIME, SCHEDULE.BUS_TYPE)
      .from(SCHEDULE)
      .fetch(mapping(this::convert));
  }

  private Schedule convert(long id, long routeId, LocalTime departureTime, BHBusType bhBusType) {
    return new Schedule(id, routeId, departureTime, convertFromBhBusType(bhBusType));
  }
}
