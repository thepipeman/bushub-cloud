package com.bushub.core.bus;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/buses")
@RequiredArgsConstructor
public class BusController {

  private final BusService busService;

  @PostMapping
  private ResponseEntity<Long> create(@RequestBody @Valid Bus bus) {
    final var id = busService.create(bus);
    return new ResponseEntity<>(id, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Bus>> readAll() {
    final var buses = busService.readAll();
    if (CollectionUtils.isEmpty(buses)) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(buses);
  }

}
