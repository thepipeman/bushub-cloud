package com.bushub.core.reservation.booking;


import com.bushub.core.reservation.trip.Trip;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(schema = "trp", name = "booking")
public class Booking implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "trip_id", nullable = false)
  private Trip trip;

  private int seatNumber;

  @NotNull
  private BigDecimal fare;

  @NotNull
  @Enumerated(EnumType.STRING)
  private BookingStatus status;

  private String customerName;
}
