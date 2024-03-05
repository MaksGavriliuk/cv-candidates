package org.example.testtaskmaksimgavriliuk.services.impl;

import lombok.AllArgsConstructor;
import org.example.testtaskmaksimgavriliuk.dtos.TestDTO;
import org.example.testtaskmaksimgavriliuk.entities.Direction;
import org.example.testtaskmaksimgavriliuk.entities.Test;
import org.example.testtaskmaksimgavriliuk.exceptions.NotFoundException;
import org.example.testtaskmaksimgavriliuk.mappers.TestMapper;
import org.example.testtaskmaksimgavriliuk.repositories.DirectionRepository;
import org.example.testtaskmaksimgavriliuk.repositories.TestRepository;
import org.example.testtaskmaksimgavriliuk.services.TestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final DirectionRepository directionRepository;


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
        Test test = TestMapper.INSTANCE.toTest(testDTO)
                .setDirections(mapDirectionsIdToDirections(testDTO.directionsId()));
        testRepository.save(test);
        return TestMapper.INSTANCE.toTestDTO(test);
    }

    @Override
    public TestDTO updateTest(long id, TestDTO testDTO) {
        if (!testRepository.existsById(id)) {
            throw new NotFoundException("Не удалось найти тест с id = " + id);
        }
        Test test = TestMapper.INSTANCE.toTest(testDTO)
                .setId(id)
                .setDirections(mapDirectionsIdToDirections(testDTO.directionsId()));
        testRepository.save(test);
        return TestMapper.INSTANCE.toTestDTO(test);
    }

    @Override
    public void deleteTest(long id) {
        testRepository.deleteById(id);
    }

    @Override
    public Page<TestDTO> getFilteredTests(String filter, Pageable pageable) {
        return testRepository.findByNameContainingIgnoreCase(filter, pageable)
                .map(TestMapper.INSTANCE::toTestDTO);
    }

    private List<Direction> mapDirectionsIdToDirections(List<Long> directionsId) {
        return directionsId
                .stream()
                .map(id -> directionRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Не удалось найти направления с id = " + id)))
                .toList();
    }

}