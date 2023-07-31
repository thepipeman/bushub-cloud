package com.bushub.booking.customer.trip;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

interface CustomerTripRepository extends CrudRepository<CustomerTrip, Long> {

  Optional<CustomerTrip> findByReferenceNumber(String referenceNumber);

  Collection<CustomerTrip> findByCustomerId(long customerId);
}
