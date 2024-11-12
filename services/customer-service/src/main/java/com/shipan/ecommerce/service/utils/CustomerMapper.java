package com.shipan.ecommerce.service.utils;

import com.shipan.ecommerce.entity.Customer;
import com.shipan.ecommerce.entity.request.CustomerRequest;
import com.shipan.ecommerce.entity.response.CustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerMapper {
    public Customer requestToCustomerEntity(CustomerRequest request) {
        // Map the request obj to a Customer Entity
        if(request == null) {
            log.warn("Received null CustomerRequest. Returning null Customer entity.");
            throw new IllegalArgumentException("CustomerRequest cannot be null when handling Customer");
        }
        return Customer.builder()
                .id(request.id())
                .firstname(request.firstName())
                .lastname(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse customerEntityToCustomerResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
