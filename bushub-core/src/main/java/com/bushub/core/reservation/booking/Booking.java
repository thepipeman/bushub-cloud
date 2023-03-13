package com.bushub.core.reservation.booking;


import com.bushub.core.bus.BusType;
import com.bushub.core.reservation.trip.Trip;
import com.bushub.core.reservation.trip.TripStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@SqlResultSetMapping(
  name = "booking_view",
  classes = @ConstructorResult(
    targetClass = BookingView.class,
    columns = {
      @ColumnResult(name = "reference_number", type = String.class),
      @ColumnResult(name = "booking_status", type = BookingStatus.class),
      @ColumnResult(name = "fare", type = BigDecimal.class),
      @ColumnResult(name = "departure_date", type = LocalDate.class),
      @ColumnResult(name = "departure_time", type = LocalTime.class),
      @ColumnResult(name = "trip_status", type = TripStatus.class),
      @ColumnResult(name = "bus_type", type = BusType.class),
      @ColumnResult(name = "origin", type = String.class),
      @ColumnResult(name = "destination", type = String.class),
      @ColumnResult(name = "distance", type = Integer.class),
      @ColumnResult(name = "trip_code", type = String.class)
    }
  )
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "trp", name = "booking")
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //  @JsonBackReference
  @ManyToOne(fetch = FetchType.LAZY)
  @NotFound(action = NotFoundAction.IGNORE)
  @JoinColumn(name = "trip_id", nullable = false)
  private Trip trip;

  private int seatNumber;

  @NotNull
  private BigDecimal fare;

  @NotNull
  @Enumerated(EnumType.STRING)
  private BookingStatus status = BookingStatus.PENDING_PAYMENT;

  private String customerName;

  private String referenceNumber;
}
