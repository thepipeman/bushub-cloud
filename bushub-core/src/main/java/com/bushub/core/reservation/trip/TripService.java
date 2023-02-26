package com.bushub.core.reservation.trip;

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
public class TripService {

  private final TripRepository repository;

  public Long create(Trip trip) {
    final var created = repository.save(trip);
    log.trace("Trip created [id=<{}>]", created.getId());
    return created.getId();
  }

  @Transactional(readOnly = true)
  public List<Trip> readAll() {
    return Lists.newArrayList(repository.findAll());
  }

  @Transactional(readOnly = true)
  public List<Trip> readBySchedule(Long scheduleId) {
    return Lists.newArrayList(repository.findByScheduleId(scheduleId));
  }
}
