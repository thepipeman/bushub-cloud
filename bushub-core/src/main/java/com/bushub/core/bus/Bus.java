package com.bushub.core.bus;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity
@Table(schema = "vh", name = "bus")
public class Bus {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Enumerated(EnumType.STRING)
  private BusType type;

  @NotNull
  private String plateNumber;

  @NotNull
  private String number;
}
