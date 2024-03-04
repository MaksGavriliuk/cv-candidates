package org.example.testtaskmaksimgavriliuk.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.antlr.v4.runtime.misc.NotNull;


public record DirectionDTO (
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) @NotNull Long id,
        @NotBlank String name,
        String description
) {
}
