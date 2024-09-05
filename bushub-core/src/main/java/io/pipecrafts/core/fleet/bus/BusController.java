package io.pipecrafts.core.fleet.bus;

import io.pipecrafts.commons.core.flt.bus.Bus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buses")
@RequiredArgsConstructor
public class BusController {

  private final BusRepository busRepository;

  @PostMapping
  public Long createBus(@Valid @RequestBody Bus bus) {
    return busRepository.create(bus);
  }

  @GetMapping
  public List<Bus> readAll() {
    return busRepository.selectAll();
  }

  @GetMapping("/{id}")
  public Bus readById(@PathVariable long id) {
    return busRepository.readById(id);
  }
}
