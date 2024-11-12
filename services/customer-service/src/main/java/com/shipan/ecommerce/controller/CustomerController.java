package com.shipan.ecommerce.controller;


import com.shipan.ecommerce.entity.Customer;
import com.shipan.ecommerce.entity.request.CustomerRequest;
import com.shipan.ecommerce.entity.response.CustomerResponse;
import com.shipan.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer/")
@RequiredArgsConstructor // no @Autowired required if you only have one constructor
public class CustomerController {
    private final CustomerService customerService;

    // Create a customer
    @PostMapping
    public ResponseEntity<String>  createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    // update Customer
    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request) {
        customerService.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    //Get customer
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomer() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    //Check if a Customer exists
    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> checkIfACustomerExistsById(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.checkIfACustomerExistsById(customerId));
    }

    // Find a customer bu id
    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> getACustomerById(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.getACustomerById(customerId));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteACustomer(@PathVariable("customer-id") String customerId) {
        // We throw Exceptions in customerService
        customerService.deleteACustomer(customerId);
        return ResponseEntity.noContent().build();
    }
}
