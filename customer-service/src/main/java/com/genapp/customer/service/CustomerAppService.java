package com.genapp.customer.service;

import com.genapp.customer.domain.Customer;
import com.genapp.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerAppService {

  private final CustomerRepository repository;

  public CustomerAppService(CustomerRepository repository) {
    this.repository = repository;
  }

  public Customer create(Customer c) {
    return repository.save(c);
  }

  public Customer get(UUID id) {
    return repository.findById(id).orElse(null);
  }

  public List<Customer> searchByLastName(String lastName) {
    if (lastName == null || lastName.isBlank()) {
      return repository.findAll();
    }
    return repository.findByLastNameContainingIgnoreCase(lastName);
  }

  public Customer update(UUID id, Customer updated) {
    Customer existing = get(id);
    if (existing == null) {
      return null;
    }
    existing.setFirstName(updated.getFirstName());
    existing.setLastName(updated.getLastName());
    existing.setDateOfBirth(updated.getDateOfBirth());
    existing.setEmail(updated.getEmail());
    existing.setPhone(updated.getPhone());
    existing.setAddressLine1(updated.getAddressLine1());
    existing.setAddressLine2(updated.getAddressLine2());
    existing.setCity(updated.getCity());
    existing.setPostalCode(updated.getPostalCode());
    existing.setCountry(updated.getCountry());
    return repository.save(existing);
  }

  public void delete(UUID id) {
    repository.deleteById(id);
  }
}
