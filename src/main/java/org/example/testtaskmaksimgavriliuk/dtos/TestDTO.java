package org.example.testtaskmaksimgavriliuk.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.List;


public record TestDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) Long id,
        @NotBlank String name,
        String description,
        List<Long> directionsId
) {
}