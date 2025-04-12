package io.pipecrafts.core.io.pipecrafts.core.fleet.bus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.pipecrafts.commons.core.flt.bus.Bus;
import io.pipecrafts.commons.core.flt.bus.BusType;
import io.pipecrafts.commons.tools.error.BhDuplicateResourceException;
import io.pipecrafts.commons.tools.error.BhErrorCode;
import io.pipecrafts.commons.tools.error.BhResourceNotFoundException;
import io.pipecrafts.commons.tools.error.ErrorResponse;
import io.pipecrafts.core.fleet.bus.BusController;
import io.pipecrafts.core.fleet.bus.BusRepository;
import org.jooq.DSLContext;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

  @Test
  public void whenBusNumberIsDuplicateThenShouldReturnConflict() throws Exception {
    given(busRepository.create(createTestBus()))
      .willThrow(BhDuplicateResourceException.class);

    MvcResult result = mockMvc
      .perform(post("/buses")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(asJsonString(createTestBus()))
      )
      .andExpect(status().isConflict())
      .andReturn();

    var errResponse = deserialize(result.getResponse().getContentAsString(), ErrorResponse.class);
    assertThat(errResponse.errorCode()).isEqualTo(BhErrorCode.DUPLICATE_RESOURCE.name());
  }

  private String asJsonString(final Bus bus) {
    try {
      return new ObjectMapper().writeValueAsString(bus);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private <T> T deserialize(String stringResponse, Class<T> clazz) throws JsonProcessingException {
    final var objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    return objectMapper.readValue(stringResponse, clazz);
  }

  private Bus createTestBus() {
    return new Bus(1L, BusType.FIRST_CLASS, "ABC12345", "123456");
  }
}
