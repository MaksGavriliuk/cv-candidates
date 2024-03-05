package org.example.testtaskmaksimgavriliuk.controllers;


import lombok.AllArgsConstructor;
import org.example.testtaskmaksimgavriliuk.dtos.TestDTO;
import org.example.testtaskmaksimgavriliuk.services.TestService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tests")
@AllArgsConstructor
public class TestController {

    private final TestService testService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<TestDTO> getTests(Pageable pageable) {
        return testService.getTests(pageable);
    }

    @GetMapping("/filtered")
    @ResponseStatus(HttpStatus.OK)
    public Page<TestDTO> getTests(@RequestParam(name = "name") String filter, Pageable pageable) {
        return testService.getFilteredTests(filter, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TestDTO getTest(@PathVariable Long id) {
        return testService.getTestById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestDTO saveTest(@RequestBody TestDTO testDTO) {
        return testService.saveTest(testDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TestDTO updateTest(
            @PathVariable Long id,
            @RequestBody TestDTO testDTO
    ) {
        return testService.updateTest(id, testDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTest(@PathVariable Long id) {
        testService.deleteTest(id);
    }

}