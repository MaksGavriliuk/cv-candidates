package org.example.testtaskmaksimgavriliuk.services.impl;

import lombok.AllArgsConstructor;
import org.example.testtaskmaksimgavriliuk.dtos.TestDTO;
import org.example.testtaskmaksimgavriliuk.entities.Test;
import org.example.testtaskmaksimgavriliuk.exceptions.NotFoundException;
import org.example.testtaskmaksimgavriliuk.mappers.TestMapper;
import org.example.testtaskmaksimgavriliuk.repositories.TestRepository;
import org.example.testtaskmaksimgavriliuk.services.TestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;


    @Override
    public Page<TestDTO> getTests(Pageable pageable) {
        return testRepository.findAll(pageable)
                .map(TestMapper.INSTANCE::toTestDTO);
    }

    @Override
    public TestDTO getTestById(long id) {
        return testRepository.findById(id)
                .map(TestMapper.INSTANCE::toTestDTO)
                .orElseThrow(() -> new NotFoundException("Не удалось найти тест с id = " + id));
    }

    @Override
    public TestDTO saveTest(TestDTO testDTO) {
        Test test = TestMapper.INSTANCE.toTest(testDTO);
        testRepository.save(test);
        return TestMapper.INSTANCE.toTestDTO(test);
    }

    @Override
    public TestDTO updateTest(long id, TestDTO testDTO) {
        if (!testRepository.existsById(id)) {
            throw new NotFoundException("Не удалось найти тест с id = " + id);
        }
        Test test = TestMapper.INSTANCE.toTest(testDTO).setId(id);
        testRepository.save(test);
        return TestMapper.INSTANCE.toTestDTO(test);
    }

    @Override
    public void deleteTest(long id) {
        testRepository.deleteById(id);
    }

    @Override
    public Page<Test> getFilteredTests(String filter, Pageable pageable) {
        return testRepository.findByNameContainingIgnoreCase(filter, pageable);
    }

}