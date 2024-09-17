package io.pipecrafts.core.trp;

import io.pipecrafts.commons.core.trp.schd.Schedule;
import io.pipecrafts.core.trp.schd.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

  private final ScheduleRepository scheduleRepository;

  public Schedule readById(long id) {
    return scheduleRepository.selectById(id);
  }
}
