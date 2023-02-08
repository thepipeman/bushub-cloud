package com.bushub.core.bus;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BusService {

  private final BusRepository busRepository;

  public Long create(Bus bus) {
    final var busCreated = busRepository.save(bus);
    log.trace("Bus created [id=<{}>]", busCreated.getId());
    return busCreated.getId();
  }

  public List<Bus> readAll() {
    return Lists.newArrayList(busRepository.findAll());
  }

}
