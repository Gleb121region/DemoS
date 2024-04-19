package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FactorialRequest(
        @NotBlank
        @NotNull
        @JsonProperty("factorial_num")
        Number factorialNum
) {
}
