package org.example.testtaskmaksimgavriliuk.controllers;


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


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CandidateTestDTO> getCandidateTests(Pageable pageable) {
        return candidateTestService.getCandidateTests(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CandidateTestDTO getCandidateTestById(@PathVariable long id) {
        return candidateTestService.getCandidateTestById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CandidateTestDTO saveCandidateTest(@RequestBody CandidateTestDTO candidateTestDTO) {
        return candidateTestService.saveCandidateTest(candidateTestDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CandidateTestDTO updateCandidateTest(@PathVariable long id, @RequestBody CandidateTestDTO candidateTestDTO) {
        return candidateTestService.updateCandidateTest(id, candidateTestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidateTest(@PathVariable long id) {
        candidateTestService.deleteCandidateTest(id);
    }

    @GetMapping("/score-greater-than/{score}")
    @ResponseStatus(HttpStatus.OK)
    public Page<CandidateTestDTO> getCandidateTestsWhereScoreGreaterThan(@PathVariable Integer score, Pageable pageable) {
        return candidateTestService.getCandidateTestsWhereScoreGreaterThan(score, pageable);
    }

}