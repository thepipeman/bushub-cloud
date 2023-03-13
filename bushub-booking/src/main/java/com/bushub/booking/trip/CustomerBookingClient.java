package com.bushub.booking.trip;

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

}
