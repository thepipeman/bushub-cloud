package io.pipecrafts.commons.core.flt.bus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record Bus(

  @Schema(
    description = "Primary ID of the bus",
    example = "1L",
    accessMode = Schema.AccessMode.READ_ONLY
  )
  Long id,

  @Schema(
    description = "Type of the bus",
    example = "FIRST_CLASS",
    requiredMode = Schema.RequiredMode.AUTO,
    accessMode = Schema.AccessMode.READ_WRITE
  )
  @NotNull
  BusType type,

  @Schema(
    description = "Plate number of the bus",
    example = "ABC12345",
    requiredMode = Schema.RequiredMode.REQUIRED,
    accessMode = Schema.AccessMode.READ_WRITE
  )
  @NotBlank
  @Size(min = 6, max = 12, message = "Invalid plate number length")
  String plateNumber,

  @Schema(
    description = "Unique number of the bus. Null values will result to automatically created values based on format configured",
    example = "BAA1234",
    accessMode = Schema.AccessMode.READ_WRITE
  )
  @Size(max = 25)
  String number
) {
}
