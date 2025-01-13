package com.sid.gl.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
    @NotNull(message = "Product name cannot be null")
    @NotEmpty(message = "Product name cannot be empty")
    @NotBlank(message = "Product name cannot be blank")
    String name,
    String description,
    @NotNull(message = "Product price cannot be null")
    double price,
    @NotEmpty(message = "Product quantity cannot be empty")
    @Positive(message = "Product quantity cannot be negative")
    int quantity
) {

}
