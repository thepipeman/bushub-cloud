package com.bushub.booking.customer.trip;

import com.bushub.booking.customer.info.Customer;
import com.bushub.booking.customer.trip.model.CustomerBooking;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerBookingService {

  private final CustomerTripService customerTripService;
  private final CustomerBookingClient customerBookingClient;

  public String bookTrip(CustomerBooking customerBooking) {
    String refNumber = customerBookingClient.bookTrip(customerBooking);
    log.info("Successfully booked trip refNumber=<{}>", refNumber);
    customerTripService.create(CustomerTrip.builder()
        .referenceNumber(refNumber)
        .customer(Customer.builder().id(1L).build())
      .build());
    return refNumber;
  }
}
