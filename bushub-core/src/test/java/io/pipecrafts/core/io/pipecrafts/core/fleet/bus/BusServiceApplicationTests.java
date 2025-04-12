package io.pipecrafts.core.io.pipecrafts.core.fleet.bus;

import io.pipecrafts.commons.core.flt.bus.Bus;
import io.pipecrafts.commons.core.flt.bus.BusType;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;


@Ignore
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class BusServiceApplicationTests {

  @Autowired
  private WebTestClient webTestClient;

//  @Test
  public void whenPostRequestThenBookCreated() {
    final var bus = new Bus(1L, BusType.FIRST_CLASS, "ABC123458", "123453");

    // I need to change this to H2 because hey - problem with actual db creation :)
    webTestClient
      .post()
      .uri("/buses")
      .bodyValue(bus)
      .exchange()
      .expectStatus().isCreated()
      .expectBody(Long.class).consumeWith(System.out::println)
      .value(busResponse -> assertThat(busResponse).isNotNull());
  }
}
