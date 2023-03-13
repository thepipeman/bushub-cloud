package com.bushub.booking.trip;

import com.bushub.commons.trip.CustomerBookedTrip;
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
public class CustomerTripService {

  private final CustomerTripRepository customerTripRepository;
  private final CustomerBookingClient customerBookingClient;

  public Long create(CustomerTrip customerTrip) {
    final var customerTripCreated = customerTripRepository.save(customerTrip);
    log.trace("CustomerTrip created [id=<{}>]", customerTripCreated.getId());
    return customerTripCreated.getId();
  }

  @Transactional(readOnly = true)
  public CustomerTrip readById(long id) {
    return customerTripRepository.findById(id).orElse(null);
  }

  @Transactional(readOnly = true)
  public CustomerBookedTrip readByReferenceNumber(String referenceNumber) {
//    return customerTripRepository.findByReferenceNumber(referenceNumber).orElse(null)];
    final var customerTrip = customerTripRepository.findByReferenceNumber(referenceNumber).orElse(null);
    if (customerTrip == null) {
      return null;
    }

    final var customerBookedTrip = customerBookingClient.getTripByReferenceNumber(referenceNumber);
    log.info("CustomerBookedTrip {}", customerBookedTrip);
    return customerBookedTrip;
  }

  @Transactional(readOnly = true)
  public List<CustomerTrip> readCustomerTrips(long customerId) {
    return Lists.newArrayList(customerTripRepository.findByCustomerId(customerId));
  }
}
