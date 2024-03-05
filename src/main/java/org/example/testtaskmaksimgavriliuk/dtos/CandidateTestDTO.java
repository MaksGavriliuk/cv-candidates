package org.example.testtaskmaksimgavriliuk.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

import java.util.Date;


public record CandidateTestDTO(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY) @NotNull Long id,
        @NotNull Long candidateId,
        @NotNull Long testId,
        @NotNull Integer score,
        @NotNull @Temporal(TemporalType.DATE) Date testDate
) {
}
