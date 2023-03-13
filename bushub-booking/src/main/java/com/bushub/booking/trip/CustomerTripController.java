package com.bushub.booking.trip;

import com.bushub.commons.trip.CustomerBookedTrip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/customer/trips")
public class CustomerTripController {

  private final CustomerTripService customerTripService;

  @GetMapping("/{refNumber}")
  public CustomerBookedTrip readByRefNumber(@PathVariable("refNumber") String refNumber) {
    return customerTripService.readByReferenceNumber(refNumber);
  }

}
