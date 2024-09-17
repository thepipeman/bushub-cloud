package io.pipecrafts.core.trp;

import io.pipecrafts.commons.core.trp.domain.Trip;
import io.pipecrafts.core.trp.domain.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TripService {

  private final TripRepository tripRepository;

  public Trip readById(long id) {
    return tripRepository.selectById(id);
  }
}
