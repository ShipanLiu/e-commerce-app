package com.shipan.ecommerce.service;


import com.shipan.ecommerce.entity.Customer;
import com.shipan.ecommerce.entity.exception.CustomerNotFoundException;
import com.shipan.ecommerce.entity.request.CustomerRequest;
import com.shipan.ecommerce.entity.response.CustomerResponse;
import com.shipan.ecommerce.repository.CustomerRepository;
import com.shipan.ecommerce.service.utils.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // Map request to Customer entity
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = customerRepository.save(mapper.requestToCustomerEntity(request));
        return customer.getId();
    }


    public void updateCustomer(CustomerRequest request) {
        // find
        var customer = customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update customer: No customer found with the provided ID: %s", request.id())
                ));
        // merge but not overwrite
        mergeCustomer(customer, request);
        // save
        customerRepository.save(customer);

    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstname(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())) {
            customer.setLastname(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> getAllCustomer() {
        return customerRepository.findAll().stream()
                .map(mapper::customerEntityToCustomerResponse)
                .collect(Collectors.toList());
    }

    public Boolean checkIfACustomerExistsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();

    }


    public CustomerResponse getACustomerById(String customerId) {
        return customerRepository.findById(customerId)
                // map is for Optional
                .map(mapper::customerEntityToCustomerResponse)
                .orElseThrow(() -> new CustomerNotFoundException(format("No Customer found with id::%s", customerId)));
    }

    public void deleteACustomer(String customerId) {

        if (!checkIfACustomerExistsById(customerId)) {
            throw new CustomerNotFoundException(format("The to be deleted Customer with id:: %s does not exist", customerId));
        }

        customerRepository.deleteById(customerId);

    }


}
