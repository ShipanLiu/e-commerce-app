package com.shipan.ecommerce.entity;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Validated
public class Address {
    @NotBlank(message = "Street cannot be empty")
    @Size(max = 100, message = "Street name should not exceed 100 characters")
    private String street;

    @NotBlank(message = "House number cannot be empty")
    @Size(max = 10, message = "House number should not exceed 10 characters")
    private String houseNumber;

    @NotBlank(message = "ZIP code cannot be empty")
    @Pattern(regexp = "\\d{5}", message = "ZIP code must be a 5-digit number")
    private String zipCode;

    @NotBlank(message = "Country cannot be empty")
    @Size(max = 50, message = "Country name should not exceed 50 characters")
    private String country;
}
