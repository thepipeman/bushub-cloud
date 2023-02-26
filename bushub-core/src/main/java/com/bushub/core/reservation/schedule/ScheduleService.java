package com.bushub.core.reservation.schedule;

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
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;

  public Long create(Schedule schedule) {
    final var created = scheduleRepository.save(schedule);
    log.trace("Schedule created [id=<{}>]", created.getId());
    return created.getId();
  }

  @Transactional(readOnly = true)
  public List<Schedule> readAll() {
    return Lists.newArrayList(scheduleRepository.findAll());
  }

  @Transactional(readOnly = true)
  public List<Schedule> readByRouteId(long routeId) {
    return Lists.newArrayList(scheduleRepository.findByRouteId(routeId));
  }
}