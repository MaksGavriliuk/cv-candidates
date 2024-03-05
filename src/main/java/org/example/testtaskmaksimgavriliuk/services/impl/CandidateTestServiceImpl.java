package org.example.testtaskmaksimgavriliuk.services.impl;


import lombok.AllArgsConstructor;
import org.example.testtaskmaksimgavriliuk.dtos.CandidateTestDTO;
import org.example.testtaskmaksimgavriliuk.entities.CandidateTest;
import org.example.testtaskmaksimgavriliuk.exceptions.NotFoundException;
import org.example.testtaskmaksimgavriliuk.mappers.CandidateTestMapper;
import org.example.testtaskmaksimgavriliuk.repositories.CandidateTestRepository;
import org.example.testtaskmaksimgavriliuk.services.CandidateTestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CandidateTestServiceImpl implements CandidateTestService {

    private final CandidateTestRepository candidateTestRepository;


    @Override
    public Page<CandidateTestDTO> getCandidateTests(Pageable pageable) {
        return candidateTestRepository.findAll(pageable)
                .map(CandidateTestMapper.INSTANCE::toCandidateTestDTO);
    }

    @Override
    public CandidateTestDTO getCandidateTestById(long id) {
        return candidateTestRepository.findById(id)
                .map(CandidateTestMapper.INSTANCE::toCandidateTestDTO)
                .orElseThrow(() -> new NotFoundException("Не удалось найти тест кандидата с id = " + id));
    }

    @Override
    public CandidateTestDTO saveCandidateTest(CandidateTestDTO candidateTestDTO) {
        CandidateTest candidateTest = CandidateTestMapper.INSTANCE.toCandidateTest(candidateTestDTO);
        candidateTestRepository.save(candidateTest);
        return CandidateTestMapper.INSTANCE.toCandidateTestDTO(candidateTest);
    }

    @Override
    public CandidateTestDTO updateCandidateTest(long id, CandidateTestDTO candidateTestDTO) {
        if (!candidateTestRepository.existsById(id)) {
            throw new NotFoundException("Не удалось найти тест кандидата с id = " + id);
        }
        CandidateTest candidateTest = CandidateTestMapper.INSTANCE.toCandidateTest(candidateTestDTO).setId(id);
        candidateTestRepository.save(candidateTest);
        return CandidateTestMapper.INSTANCE.toCandidateTestDTO(candidateTest);
    }

    @Override
    public void deleteCandidateTest(long id) {
        candidateTestRepository.deleteById(id);
    }

    @Override
    public Page<CandidateTestDTO> getCandidateTestsWhereScoreGreaterThan(Integer score, Pageable pageable) {
        return candidateTestRepository.findByScoreGreaterThan(score, pageable)
                .map(CandidateTestMapper.INSTANCE::toCandidateTestDTO);
    }

}