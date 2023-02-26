package com.bushub.core.reservation.schedule;

import com.bushub.core.bus.BusType;
import com.bushub.core.reservation.route.Route;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@Entity
@Table(schema = "trp", name = "schedule")
public class Schedule implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Enumerated(EnumType.STRING)
  private BusType busType;

  @NotNull
  @JsonFormat(pattern = "HH:mm:ss")
  private LocalTime departureTime;

//  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "route_id", nullable = false)
  private Route route;
}
