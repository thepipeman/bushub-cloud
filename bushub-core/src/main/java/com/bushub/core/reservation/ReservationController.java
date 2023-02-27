package com.bushub.core.reservation;

import com.bushub.core.reservation.booking.Booking;
import com.bushub.core.reservation.booking.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

  private final BookingService bookingService;

  @PostMapping(value = "/bookings", produces = MediaType.APPLICATION_JSON_VALUE)
  public long reserveBooking(@Valid @RequestBody Booking booking) {
    return bookingService.create(booking);
  }

  @GetMapping("/bookings/{id}")
  public Booking readBookingById(@PathVariable("id") long id) {
    return bookingService.readById(id);
  }
}
