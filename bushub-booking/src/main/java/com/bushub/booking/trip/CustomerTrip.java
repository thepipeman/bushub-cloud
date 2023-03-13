package com.bushub.booking.trip;

import com.bushub.booking.customer.Customer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

// this should be cached
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_trip")
public class CustomerTrip implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @NonNull
  @NotBlank
  private String referenceNumber;
}
