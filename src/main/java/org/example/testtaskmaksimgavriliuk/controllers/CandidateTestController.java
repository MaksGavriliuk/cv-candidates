package org.example.testtaskmaksimgavriliuk.controllers;


import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.example.testtaskmaksimgavriliuk.dtos.CandidateTestDTO;
import org.example.testtaskmaksimgavriliuk.services.CandidateTestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/candidate-tests")
@AllArgsConstructor
public class CandidateTestController {

    private final CandidateTestService candidateTestService;


    @Operation(
            summary = "Get candidate tests",
            description = "Allows to get candidate tests page by specifying the page number and size. The default page size is 20"
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CandidateTestDTO> getCandidateTests(Pageable pageable) {
        return candidateTestService.getCandidateTests(pageable);
    }

    @Operation(summary = "Get candidate test by id", description = "Allows to get candidate test by id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CandidateTestDTO getCandidateTestById(@PathVariable long id) {
        return candidateTestService.getCandidateTestById(id);
    }

    @Operation(summary = "Create new candidate test", description = "Allows to create new candidate test and return it")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CandidateTestDTO saveCandidateTest(@RequestBody CandidateTestDTO candidateTestDTO) {
        return candidateTestService.saveCandidateTest(candidateTestDTO);
    }

    @Operation(summary = "Update an existing candidate test", description = "Allows to update an existing candidate test and return it")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CandidateTestDTO updateCandidateTest(@PathVariable long id, @RequestBody CandidateTestDTO candidateTestDTO) {
        return candidateTestService.updateCandidateTest(id, candidateTestDTO);
    }

    @Operation(summary = "Delete candidate test", description = "Allows to delete a candidate test")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidateTest(@PathVariable long id) {
        candidateTestService.deleteCandidateTest(id);
    }

    @Operation(summary = "Get candidate tests by score", description = "Allows to get candidate tests where the score is higher than given one")
    @GetMapping("/score-greater-than/{score}")
    @ResponseStatus(HttpStatus.OK)
    public Page<CandidateTestDTO> getCandidateTestsWhereScoreGreaterThan(@PathVariable Integer score, Pageable pageable) {
        return candidateTestService.getCandidateTestsWhereScoreGreaterThan(score, pageable);
    }

}