package com.bushub.booking.customer;

import com.bushub.booking.trip.CustomerTrip;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String username;

  @NotNull
  private String firstName;

  @NotNull
  private String middleName;

  @NotNull
  private String lastName;

  @NotNull
  private String mobileNumber;

  @NotNull
  @Email
  private String email;

  @NotNull
  private LocalDate birthDate;

  @JsonManagedReference
  @OneToMany(mappedBy = "customer")
  private Set<CustomerTrip> trips;
}
