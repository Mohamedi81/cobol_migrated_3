package com.genapp.customer.web.dto;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public class CustomerResponse {
  public UUID id;
  public String externalId;
  public String firstName;
  public String lastName;
  public LocalDate dateOfBirth;
  public String email;
  public String phone;
  public String addressLine1;
  public String addressLine2;
  public String city;
  public String postalCode;
  public String country;
  public OffsetDateTime createdAt;
  public OffsetDateTime updatedAt;
}
