package com.bushub.core.pricing;

import com.bushub.core.bus.BusType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(schema = "trp", name = "pricing")
public class Pricing implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Enumerated(EnumType.STRING)
  private BusType type;

  @NotNull
  BigDecimal baseFare;

  @NotNull
  BigDecimal perKmFare;
}
