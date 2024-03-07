package org.example.testtaskmaksimgavriliuk.services;

import org.example.testtaskmaksimgavriliuk.dtos.CandidateTestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CandidateTestService {

    Page<CandidateTestDTO> getCandidateTests(Pageable pageable);

    CandidateTestDTO getCandidateTestById(long id);

    CandidateTestDTO saveCandidateTest(CandidateTestDTO candidateTestDTO);

    CandidateTestDTO updateCandidateTest(long id, CandidateTestDTO candidateTestDTO);

    void deleteCandidateTest(long id);

    Page<CandidateTestDTO> getCandidateTestsWhereScoreGreaterThan(Integer score, Pageable pageable);

}
