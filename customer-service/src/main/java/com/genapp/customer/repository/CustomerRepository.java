package com.genapp.customer.repository;

import com.genapp.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
  List<Customer> findByLastNameContainingIgnoreCase(String lastName);
}
