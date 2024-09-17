package io.pipecrafts.core.trp.bkg;

import io.pipecrafts.commons.core.trp.bkg.Booking;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/bookings")
@RestController
@RequiredArgsConstructor
public class BookingController {

  private final BookingRepository bookingRepository;

  @PostMapping
  public long create(@RequestBody @Valid Booking booking) {
    return bookingRepository.create(booking);
  }
}
