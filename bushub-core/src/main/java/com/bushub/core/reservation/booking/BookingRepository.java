package com.bushub.core.reservation.booking;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

interface BookingRepository extends CrudRepository<Booking, Long> {

  Collection<Booking> findByTripId(long tripId);
}
