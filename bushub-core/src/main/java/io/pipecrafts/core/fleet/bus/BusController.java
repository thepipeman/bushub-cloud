package io.pipecrafts.core.fleet.bus;

import io.pipecrafts.commons.core.flt.bus.Bus;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buses")
@RequiredArgsConstructor
@Tag(name = "Core / Bus")
public class BusController {

  private final BusRepository busRepository;

  /**
   * Creates a new bus record
   *
   * @return ID of the bus created
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
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
