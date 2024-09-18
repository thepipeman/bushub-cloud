package io.pipecrafts.core.trp.domain;


import io.pipecrafts.commons.core.trp.domain.Trip;
import io.pipecrafts.commons.core.trp.domain.TripSearchCriteria;
import io.pipecrafts.commons.data.page.PageData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {

  private final TripRepository tripRepository;

  @PostMapping
  public Long create(@RequestBody @Valid Trip trip) {
    return tripRepository.create(trip);
  }

  // eventually convert to a basic data to minimize exposing data.
  @GetMapping
  public PageData<Trip> readTripsByCriteria(@Valid TripSearchCriteria tripSearchCriteria) {
    return tripRepository.selectTripsByCriteria(tripSearchCriteria);
  }

  @GetMapping("/{id}")
  public Trip readById(@PathVariable("id") Long id) {
    return tripRepository.selectById(id);
  }

}
