package com.bushub.booking.trip;

import com.bushub.commons.trip.CustomerBookedTrip;
import com.google.common.collect.Lists;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeoutException;

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

  @CircuitBreaker(name = "customerTripByRef", fallbackMethod = "customerTripByRefFallback")
  @Transactional(readOnly = true)
  public CustomerBookedTrip readByReferenceNumber(String refNumber) throws TimeoutException {
    final var customerTrip = customerTripRepository.findByReferenceNumber(refNumber).orElse(null);
    if (customerTrip == null) {
      return null;
    }

    randomlyRunLong();
    final var customerBookedTrip = customerBookingClient.getTripByReferenceNumber(refNumber);
    log.info("CustomerBookedTrip {}", customerBookedTrip);
    return customerBookedTrip;
  }

  private CustomerBookedTrip customerTripByRefFallback(String refNumber, Throwable t) {
    log.warn("Unable to fetch customer trip for reference number {}", refNumber);
    return null;
  }

  @Transactional(readOnly = true)
  public List<CustomerTrip> readCustomerTrips(long customerId) {
    return Lists.newArrayList(customerTripRepository.findByCustomerId(customerId));
  }

  private void randomlyRunLong() throws TimeoutException {
    Random rand = new Random();
    int randomNum = rand.nextInt((3 - 1) + 1) + 1;
    if (randomNum == 3) {
      sleep();
    }
  }

  private void sleep() throws TimeoutException {
    try {
      System.out.println("Sleep");
      Thread.sleep(5000);
      throw new java.util.concurrent.TimeoutException();
    } catch (InterruptedException e) {
      log.error(e.getMessage());
    }
  }
}
