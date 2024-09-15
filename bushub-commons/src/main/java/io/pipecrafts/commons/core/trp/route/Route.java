package io.pipecrafts.commons.core.trp.route;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record Route(

  Long id,

  @NotBlank
  String origin,

  @NotBlank
  String destination,

  @Min(1)
  Integer distance,

  String code

) implements Serializable {
}
