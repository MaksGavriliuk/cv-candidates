package org.example.testtaskmaksimgavriliuk.services;

import org.example.testtaskmaksimgavriliuk.dtos.TestDTO;
import org.example.testtaskmaksimgavriliuk.entities.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TestService {

    Page<TestDTO> getTests(Pageable pageable);

    TestDTO getTestById(long id);

    TestDTO saveTest(TestDTO testDTO);

    TestDTO updateTest(long id, TestDTO testDTO);

    void deleteTest(long id);

    Page<Test> getFilteredTests(String filter, Pageable pageable);

}