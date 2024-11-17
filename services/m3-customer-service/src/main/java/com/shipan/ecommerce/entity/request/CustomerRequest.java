package com.shipan.ecommerce.entity.request;

/*
 * record provides a concise way to define immutable data carriers.
 * It automatically generates a constructor, getters, equals(), hashCode(), and toString() methods, reducing boilerplate code.
 *
 * Using record instead of a regular class in Java provides several benefits,
 *  especially for data transfer objects (DTOs) like CustomerRequest
 * */

import com.shipan.ecommerce.entity.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(

        String id,
        @NotBlank(message = "First name is required")
        String firstName,
        @NotBlank(message = "Last name is required")
        String lastName,
        @NotBlank(message = "Email is required")
        @Email(message = "The email addess shoudl be valid")
        String email,

        // The Address class was already calidated using @Validated, so no extra validation needed
        @Valid // this allows Spring’s validation to cascade into the Address object, so any validation annotations applied within Address will be evaluated.
        Address address


) {
}
