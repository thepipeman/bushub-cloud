package com.bushub.core.reservation.booking;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

interface BookingRepository extends CrudRepository<Booking, Long> {

  Collection<Booking> findByTripId(long tripId);

  @Query(name = "find_booking_view_by_reference_number", nativeQuery = true)
  Optional<BookingView> findByReferenceNumber(@Param("referenceNumber") String referenceNumber);

  Collection<Booking> findCollectionByReferenceNumber(String referenceNumber);
}
