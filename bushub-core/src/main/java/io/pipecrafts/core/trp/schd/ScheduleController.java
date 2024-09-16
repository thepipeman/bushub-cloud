package io.pipecrafts.core.trp.schd;

import io.pipecrafts.commons.core.trp.schd.Schedule;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

  private final ScheduleRepository scheduleRepository;

  @PostMapping
  public long create(@RequestBody @Valid Schedule schedule) {
    return scheduleRepository.create(schedule);
  }

  @GetMapping
  public List<Schedule> getAll() {
    return scheduleRepository.selectAll();
  }
}
