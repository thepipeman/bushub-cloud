package com.bushub.core.reservation.trip;

import com.bushub.core.bus.Bus;
import com.bushub.core.reservation.schedule.Schedule;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(schema = "trp", name = "trip")
public class Trip {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "schedule_id", nullable = false)
  private Schedule schedule;

  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private LocalDate departureDate;

  @NotNull
  @Enumerated(EnumType.STRING)
  private TripStatus status;

  @NotNull
  private BigDecimal fare;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "bus_id", nullable = false)
  private Bus bus;
}
