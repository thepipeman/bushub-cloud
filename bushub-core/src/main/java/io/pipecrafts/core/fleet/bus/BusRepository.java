//package io.pipecrafts.core.fleet.bus;
//
//import io.pipecrafts.core.jooq.vh.tables.Bus;
//import lombok.RequiredArgsConstructor;
//import org.jooq.DSLContext;
//import org.springframework.stereotype.Repository;
//
//@Repository
//@RequiredArgsConstructor
//public class BusRepository {
//
//  private final DSLContext dsl;
//
//
////  public Bus create(Bus bus) {
////    return dsl.insertInto(BUS)
////      .set(BUS.NUMBER, "12356")
////      .set(BUS.PLATE_NUMBER, "ABCD1234")
////      .returningResult(BUS.ID, BUS.NUMBER, BUS.PLATE_NUMBER)
////      .fetchAny(mapping(Bus::new));
//    return null;
//  }
//
//}
