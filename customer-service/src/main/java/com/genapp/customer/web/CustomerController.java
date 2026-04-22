package com.genapp.customer.web;

import com.genapp.customer.domain.Customer;
import com.genapp.customer.service.CustomerAppService;
import com.genapp.customer.web.dto.CreateCustomerRequest;
import com.genapp.customer.web.dto.CustomerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  private final CustomerAppService service;

  public CustomerController(CustomerAppService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<CustomerResponse> create(@RequestBody CreateCustomerRequest request) {
    Customer c = new Customer();
    c.setExternalId(request.externalId);
    c.setFirstName(request.firstName);
    c.setLastName(request.lastName);
    c.setDateOfBirth(request.dateOfBirth);
    c.setEmail(request.email);
    c.setPhone(request.phone);
    c.setAddressLine1(request.addressLine1);
    c.setAddressLine2(request.addressLine2);
    c.setCity(request.city);
    c.setPostalCode(request.postalCode);
    c.setCountry(request.country);
    Customer saved = service.create(c);
    return ResponseEntity.created(URI.create("/api/customers/" + saved.getId())).body(toResponse(saved));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerResponse> get(@PathVariable UUID id) {
    Customer c = service.get(id);
    if (c == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(toResponse(c));
  }

  @GetMapping
  public List<CustomerResponse> search(@RequestParam(name = "lastName", required = false) String lastName) {
    return service.searchByLastName(lastName).stream().map(this::toResponse).collect(Collectors.toList());
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerResponse> update(@PathVariable UUID id, @RequestBody CreateCustomerRequest request) {
    Customer c = new Customer();
    c.setFirstName(request.firstName);
    c.setLastName(request.lastName);
    c.setDateOfBirth(request.dateOfBirth);
    c.setEmail(request.email);
    c.setPhone(request.phone);
    c.setAddressLine1(request.addressLine1);
    c.setAddressLine2(request.addressLine2);
    c.setCity(request.city);
    c.setPostalCode(request.postalCode);
    c.setCountry(request.country);
    Customer updated = service.update(id, c);
    if (updated == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(toResponse(updated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  private CustomerResponse toResponse(Customer c) {
    CustomerResponse r = new CustomerResponse();
    r.id = c.getId();
    r.externalId = c.getExternalId();
    r.firstName = c.getFirstName();
    r.lastName = c.getLastName();
    r.dateOfBirth = c.getDateOfBirth();
    r.email = c.getEmail();
    r.phone = c.getPhone();
    r.addressLine1 = c.getAddressLine1();
    r.addressLine2 = c.getAddressLine2();
    r.city = c.getCity();
    r.postalCode = c.getPostalCode();
    r.country = c.getCountry();
    r.createdAt = c.getCreatedAt();
    r.updatedAt = c.getUpdatedAt();
    return r;
  }
}
