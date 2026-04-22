package com.genapp.customer.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class Customer {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(name = "external_id")
  private String externalId;

  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private String email;
  private String phone;
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String postalCode;
  private String country;

  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

  @PrePersist
  public void prePersist() {
    createdAt = OffsetDateTime.now();
    updatedAt = createdAt;
  }

  @PreUpdate
  public void preUpdate() {
    updatedAt = OffsetDateTime.now();
  }

  // getters and setters omitted for brevity
}
