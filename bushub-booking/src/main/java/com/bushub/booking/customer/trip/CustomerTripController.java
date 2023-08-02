package com.bushub.booking.customer.trip;

import com.bushub.booking.customer.trip.model.CustomerBooking;
import com.bushub.booking.customer.trip.model.CustomerBookingRequest;
import com.bushub.booking.customer.trip.model.Trip;
import com.bushub.commons.trip.CustomerBookedTrip;
import com.bushub.security.auth.UserJwtAuthentication;
import com.bushub.security.common.method.OnlyCustomerEndpoint;
import jakarta.validation.Valid;
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
  private final CustomerBookingService customerBookingService;

  @GetMapping("/{refNumber}")
  public CustomerBookedTrip readByRefNumber(@PathVariable("refNumber") String refNumber) throws TimeoutException {
    // TODO implement synching of custome data
    return customerTripService.readByReferenceNumber(refNumber);
  }

  @PostMapping
  public String bookTrip(@Valid @RequestBody CustomerBookingRequest request, UserJwtAuthentication authentication) {
    final var booking = CustomerBooking.builder()
      .trip(new Trip(request.getTripId()))
      .customerName(authentication.getName())
      .seatNumber(request.getSeatNumber())
      .fare(request.getFare())
      .build();
    return customerBookingService.bookTrip(booking);
  }

}
