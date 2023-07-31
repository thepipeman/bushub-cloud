package com.bushub.booking.customer.trip;

import com.bushub.commons.trip.CustomerBookedTrip;
import com.bushub.security.common.method.OnlyCustomerEndpoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeoutException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/customer/trips")
@OnlyCustomerEndpoint
public class CustomerTripController {

  private final CustomerTripService customerTripService;

  @GetMapping("/{refNumber}")
  public CustomerBookedTrip readByRefNumber(@PathVariable("refNumber") String refNumber) throws TimeoutException {
    return customerTripService.readByReferenceNumber(refNumber);
  }

  // TODO API to book TRIP
  // Book the trip in Core service
  // Create the customer_trip entry


}
