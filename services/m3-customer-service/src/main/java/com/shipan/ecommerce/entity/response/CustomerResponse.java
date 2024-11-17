package com.shipan.ecommerce.entity.response;

import com.shipan.ecommerce.entity.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {}
