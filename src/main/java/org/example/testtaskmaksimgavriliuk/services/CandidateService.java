package org.example.testtaskmaksimgavriliuk.services;

import org.example.testtaskmaksimgavriliuk.dtos.CandidateDTO;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;


public interface CandidateService {

    Page<CandidateDTO> getAllCandidates(Pageable pageable);

    Page<CandidateDTO> getFilteredCandidates(String filter, Pageable pageable);

    Page<CandidateDTO> getSortedCandidates(Sort sort, Pageable pageable);

    CandidateDTO getCandidateById(Long id);

    MultipartFile getPhotoByCandidateId(Long id);

    MultipartFile getCVByCandidateId(Long id);

    CandidateDTO saveCandidate(CandidateDTO candidateDTO, MultipartFile photo, MultipartFile cv);

    CandidateDTO updateCandidate(Long id, CandidateDTO updatedCandidateDTO, MultipartFile photo, MultipartFile cv);

    void deleteCandidate(Long id);

}