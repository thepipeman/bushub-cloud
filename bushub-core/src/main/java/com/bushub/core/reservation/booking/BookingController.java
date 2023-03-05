package com.bushub.core.reservation.booking;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    return bookingService.readById(id);
  }
}
