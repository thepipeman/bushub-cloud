package com.bushub.core.reservation.trip;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

interface TripRepository extends CrudRepository<Trip, Long> {

  Collection<Trip> findByScheduleId(long scheduleId);

  Optional<Trip> findByTripCode(String tripCode);
}
