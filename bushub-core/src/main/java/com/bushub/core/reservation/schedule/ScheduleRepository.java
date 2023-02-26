package com.bushub.core.reservation.schedule;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

interface ScheduleRepository extends CrudRepository<Schedule, Long> {

  Collection<Schedule> findByRouteId(long routeId);
}
