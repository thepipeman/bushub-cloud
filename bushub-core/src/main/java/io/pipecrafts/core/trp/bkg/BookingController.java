package io.pipecrafts.core.trp.bkg;

import io.pipecrafts.commons.core.trp.bkg.Booking;
import io.pipecrafts.commons.core.trp.bkg.BookingInput;
import io.pipecrafts.commons.core.trp.bkg.BookingSearchCriteria;
import io.pipecrafts.commons.data.page.PageData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bookings")
@RestController
@RequiredArgsConstructor
public class BookingController {

  private final BookingRepository bookingRepository;

  @PostMapping
  public long create(@RequestBody @Valid BookingInput booking) {
    return bookingRepository.create(booking);
  }

  @GetMapping
  public PageData<Booking> readByCriteria(@Valid BookingSearchCriteria criteria) {
    return bookingRepository.selectByCriteria(criteria);
  }

  @GetMapping("/{id}")
  public Booking readById(@PathVariable("id") long id) {
    return bookingRepository.readById(id);
  }
}
