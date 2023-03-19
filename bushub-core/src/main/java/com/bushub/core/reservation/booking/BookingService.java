package com.bushub.core.reservation.booking;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookingService {

  private final BookingRepository bookingRepository;

  public Long create(Booking booking) {
    booking.setReferenceNumber(UUID.randomUUID().toString());
    final var created = bookingRepository.save(booking);
    log.trace("Booking created [id=<{}>]", created.getId());
    return created.getId();
  }

  @Transactional(readOnly = true)
  public Booking readById(long id) {
    final var booking = bookingRepository.findById(id)
      .orElse(null);
    log.trace("Booking {}", booking);
    return booking;
  }

  @Transactional(readOnly = true)
  public List<Booking> readByTripId(long tripId) {
    return Lists.newArrayList(bookingRepository.findByTripId(tripId));
  }

  @Transactional(readOnly = true)
  public BookingView readCustomerTripByRefNumber(String refNumber) {
    return bookingRepository.findByReferenceNumber(refNumber).orElse(null);
  }
}
