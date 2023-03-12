package com.bushub.core.reservation.booking;

import org.springframework.data.repository.CrudRepository;

import java.awt.print.Book;
import java.util.Collection;
import java.util.Optional;

interface BookingRepository extends CrudRepository<Booking, Long> {

  Collection<Booking> findByTripId(long tripId);

  Optional<Booking> findByReferenceNumber(String referenceNumber);

  Collection<Booking> findCollectionByReferenceNumber(String referenceNumber);
}
