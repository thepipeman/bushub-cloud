package io.pipecrafts.core.io.pipecrafts.core.fleet.bus;

import io.pipecrafts.commons.tools.error.BhResourceNotFoundException;
import io.pipecrafts.core.fleet.bus.BusController;
import io.pipecrafts.core.fleet.bus.BusRepository;
import org.jooq.DSLContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BusController.class)
public class BusControllerMvcTests {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private BusRepository busRepository;

  @MockitoBean
  private DSLContext dslContext;

  @Test
  public void getBusByIdDoesNotExistThenShouldReturnNotFound() throws Exception {
    given(busRepository.readById(1))
      .willThrow(BhResourceNotFoundException.class);

    mockMvc
      .perform(get("/buses/1"))
      .andExpect(status().isNotFound());
  }
}
