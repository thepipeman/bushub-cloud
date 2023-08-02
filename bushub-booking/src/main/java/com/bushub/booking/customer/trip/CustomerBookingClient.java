package com.bushub.booking.customer.trip;

import com.bushub.booking.customer.trip.model.CustomerBooking;
import com.bushub.commons.trip.CustomerBookedTrip;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient("bushub-core")
public interface CustomerBookingClient {

  @RequestMapping(
    method = RequestMethod.GET,
    value = "/api/bookings/ref/{refNumber}",
    consumes = MediaType.APPLICATION_JSON_VALUE
  )
  CustomerBookedTrip getTripByReferenceNumber(@PathVariable("refNumber") String referenceNumber);


  @RequestMapping(
    method = RequestMethod.GET,
    value = "/api/bookings",
    consumes = MediaType.APPLICATION_JSON_VALUE
  )
  String bookTrip(@RequestBody CustomerBooking booking);
}
