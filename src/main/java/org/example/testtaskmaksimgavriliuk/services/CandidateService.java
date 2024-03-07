package org.example.testtaskmaksimgavriliuk.services;

import org.example.testtaskmaksimgavriliuk.dtos.CandidateDTO;
import org.example.testtaskmaksimgavriliuk.entities.CVFile;
import org.example.testtaskmaksimgavriliuk.entities.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;


public interface CandidateService {

    Page<CandidateDTO> getCandidates(Pageable pageable);

    Page<CandidateDTO> getFilteredCandidates(String filter, Pageable pageable);

    CandidateDTO getCandidateById(Long id);

    CandidateDTO saveCandidate(CandidateDTO candidateDTO, MultipartFile photo, MultipartFile CV);

    CandidateDTO updateCandidate(Long id, CandidateDTO updatedCandidateDTO, MultipartFile photo, MultipartFile cv);

    void deleteCandidate(Long id);

    Photo getPhotoByCandidateId(Long id);

    CVFile getCVByCandidateId(Long id);

    Photo updatePhotoByCandidateId(Long id, MultipartFile photo);

    CVFile updateCVFileByCandidateId(Long id, MultipartFile cvFile);

}