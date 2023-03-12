package com.bushub.core.reservation.booking;

import com.bushub.commons.trip.CustomerBookedTrip;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

  private final BookingService bookingService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public long reserveBooking(@Valid @RequestBody Booking booking) {
    return bookingService.create(booking);
  }

  @GetMapping("/{id}")
  public Booking readBookingById(@PathVariable("id") long id) {
    final var booking = bookingService.readById(id);
    log.info("Booking {}", booking);
    return booking;
  }

//  @GetMapping("/ref/{refNumber}")
//  public CustomerBookedTrip readByRefNumber(@PathVariable("refNumber") String referenceNumber) {
//    return bookingService.radCustomerTripByRefNumber(referenceNumber);
//  }

  @GetMapping("/ref/{refNumber}")
  public List<CustomerBookedTrip> readByRefNumber(@PathVariable("refNumber") String referenceNumber) {
    return bookingService.readCustomerTripCollectionByRefNumber(referenceNumber);
  }
}
