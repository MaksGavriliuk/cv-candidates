package org.example.testtaskmaksimgavriliuk.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Test", description = "Controller for manage tests")
public class TestController {

    private final TestService testService;


    @Operation(
            summary = "Getting tests",
            description = "Allows to get tests page by specifying the page number and size. The default page size is 20"
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<TestDTO> getTests(Pageable pageable) {
        return testService.getTests(pageable);
    }

    @Operation(summary = "Getting filtered tests", description = "Allows to get tests page filtered by name by specifying the page number and size. The default page size is 20")
    @GetMapping("/filtered")
    @ResponseStatus(HttpStatus.OK)
    public Page<TestDTO> getTests(@RequestParam(name = "name") String filter, Pageable pageable) {
        return testService.getFilteredTests(filter, pageable);
    }

    @Operation(summary = "Getting test by id", description = "Allows to get test by id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TestDTO getTest(@PathVariable Long id) {
        return testService.getTestById(id);
    }

    @Operation(summary = "Create new test", description = "Allows to create new test and return it")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TestDTO saveTest(@RequestBody TestDTO testDTO) {
        return testService.saveTest(testDTO);
    }

    @Operation(summary = "Update an existing test", description = "Allows to update an existing test and return it")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TestDTO updateTest(@PathVariable Long id, @RequestBody TestDTO testDTO) {
        return testService.updateTest(id, testDTO);
    }

    @Operation(summary = "Delete test", description = "Allows to delete a test")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTest(@PathVariable Long id) {
        testService.deleteTest(id);
    }

}