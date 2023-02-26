package com.bushub.core.reservation.route;

import com.bushub.core.reservation.schedule.Schedule;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(schema = "trp", name = "route")
public class Route implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String origin;

  @NotBlank
  private String destination;

  @Min(1)
  private int distance;

  @JsonManagedReference
  @OneToMany(mappedBy = "route")
  private Set<Schedule> schedules;
}
