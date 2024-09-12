package io.pipecrafts.core.trp.route;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class RouteRepository {

  private final DSLContext dsl;

//  public Long create()
}
