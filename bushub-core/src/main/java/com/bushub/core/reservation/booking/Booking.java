package com.bushub.core.reservation.booking;


import com.bushub.core.reservation.trip.Trip;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "trp", name = "booking")
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "trip_id", nullable = false)
  private Trip trip;

  private int seatNumber;

  @NotNull
  private BigDecimal fare;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Builder.Default
  private BookingStatus status = BookingStatus.PENDING_PAYMENT;

  private String customerName;

  private String referenceNumber;
}
