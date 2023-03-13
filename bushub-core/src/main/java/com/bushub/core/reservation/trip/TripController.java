package com.bushub.core.reservation.trip;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/trips")
@RestController
@RequiredArgsConstructor
public class TripController {

  private final TripService tripService;

  @GetMapping
  public List<Trip> readTrips(@RequestParam(name = "scheduleId", required = false) Long scheduleId) {
    if (scheduleId != null) {
      return tripService.readBySchedule(scheduleId);
    }

    return tripService.readAll();
  }

  @GetMapping("/{tripCode}")
  public Trip readByTripCode(@PathVariable("tripCode") String tripCode) {
    return tripService.readByTripCode(tripCode);
  }
}
