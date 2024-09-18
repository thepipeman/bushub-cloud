package io.pipecrafts.core.fleet.bus;

import io.pipecrafts.commons.core.flt.bus.Bus;
import io.pipecrafts.commons.tools.error.BhResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.pipecrafts.core.jooq.vh.tables.BHBus.BUS;
import static org.jooq.Records.mapping;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class BusRepository {

  private final DSLContext dsl;

  // reference: https://www.sivalabs.in/spring-boot-jooq-tutorial-crud-operations/#implementing-finduserbyid

  public Long create(Bus bus) {
    final var id = dsl.insertInto(BUS)
      .set(BUS.BUS_TYPE, bus.type())
      .set(BUS.NUMBER, bus.number())
      .set(BUS.PLATE_NUMBER, bus.plateNumber())
      .returning(BUS.ID)
      .fetchAny(BUS.ID);

    log.trace("Bus created {}", id);
    return id;
  }

  @Transactional(readOnly = true)
  public List<Bus> selectAll() {
    return dsl.select(BUS.ID, BUS.BUS_TYPE, BUS.NUMBER, BUS.PLATE_NUMBER)
      .from(BUS)
      .fetch(mapping(Bus::new));
  }

  @Transactional(readOnly = true)
  public Bus readById(long id) {
    final var optionalBus = dsl.select(BUS.ID, BUS.BUS_TYPE, BUS.NUMBER, BUS.PLATE_NUMBER)
      .from(BUS)
      .where(BUS.ID.equal(id))
      .fetchOptional(mapping(Bus::new));

    return optionalBus.orElseThrow(() -> BhResourceNotFoundException.ofId(Bus.class, id));
  }

}
