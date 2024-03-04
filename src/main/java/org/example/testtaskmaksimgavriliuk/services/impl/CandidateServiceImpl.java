package org.example.testtaskmaksimgavriliuk.services.impl;


import lombok.AllArgsConstructor;
import org.example.testtaskmaksimgavriliuk.dtos.CandidateDTO;
import org.example.testtaskmaksimgavriliuk.entities.CVFile;
import org.example.testtaskmaksimgavriliuk.entities.Candidate;
import org.example.testtaskmaksimgavriliuk.entities.Photo;
import org.example.testtaskmaksimgavriliuk.exceptions.NotFoundException;
import org.example.testtaskmaksimgavriliuk.mappers.CVFileMapper;
import org.example.testtaskmaksimgavriliuk.mappers.CandidateMapper;
import org.example.testtaskmaksimgavriliuk.mappers.PhotoMapper;
import org.example.testtaskmaksimgavriliuk.repositories.CVFileRepository;
import org.example.testtaskmaksimgavriliuk.repositories.CandidateRepository;
import org.example.testtaskmaksimgavriliuk.repositories.PhotoRepository;
import org.example.testtaskmaksimgavriliuk.services.CandidateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final PhotoRepository photoRepository;
    private final CVFileRepository cvFileRepository;


    @Override
    public Page<CandidateDTO> getAllCandidates(Pageable pageable) {
        return candidateRepository.findAll(pageable)
                .map(CandidateMapper.INSTANCE::toCandidateDTO);
    }

    @Override
    public Page<CandidateDTO> getFilteredCandidates(String filter, Pageable pageable) {
        return candidateRepository.findBySurnameContainingIgnoreCase(filter, pageable)
                .map(CandidateMapper.INSTANCE::toCandidateDTO);
    }

    @Override
    public Page<CandidateDTO> getSortedCandidates(Sort sort, Pageable pageable) {
        return candidateRepository.findSortedCandidates(sort, pageable)
                .map(CandidateMapper.INSTANCE::toCandidateDTO);
    }

    @Override
    public CandidateDTO getCandidateById(Long id) {
        return candidateRepository.findById(id)
                .map(CandidateMapper.INSTANCE::toCandidateDTO)
                .orElseThrow(() -> new NotFoundException("Не удалось найти кандидата с id = " + id));
    }

    @Override
    public MultipartFile getPhotoByCandidateId(Long id) {
        Photo photo = candidateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не удалось найти кандидата с id = " + id))
                .getPhoto();
        return PhotoMapper.INSTANCE.toMultipartFile(photo);
    }

    @Override
    public MultipartFile getCVByCandidateId(Long id) {
        CVFile cvFile = candidateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не удалось найти кандидата с id = " + id))
                .getCvFile();
        return CVFileMapper.INSTANCE.toMultipartFile(cvFile);
    }

    @Transactional
    @Override
    public CandidateDTO saveCandidate(CandidateDTO candidateDTO, MultipartFile photo, MultipartFile cv) {
        Photo photo1 = PhotoMapper.INSTANCE.toPhoto(photo);
        CVFile cvFile = CVFileMapper.INSTANCE.toCVFile(cv);
        Candidate candidate = CandidateMapper.INSTANCE.toCandidate(candidateDTO)
                .setPhoto(photo1)
                .setCvFile(cvFile);
        photoRepository.save(photo1);
        cvFileRepository.save(cvFile);
        candidateRepository.save(candidate);
        return CandidateMapper.INSTANCE.toCandidateDTO(candidate);
    }

    @Transactional
    @Override
    public CandidateDTO updateCandidate(Long id, CandidateDTO candidateDTO, MultipartFile photo, MultipartFile cv) {
        if (!candidateRepository.existsById(id)) {
            throw new NotFoundException("Не удалось найти кандидата с id = " + id);
        }
        Photo photo1 = PhotoMapper.INSTANCE.toPhoto(photo);
        CVFile cvFile = CVFileMapper.INSTANCE.toCVFile(cv);
        Candidate candidate = CandidateMapper.INSTANCE.toCandidate(candidateDTO)
                .setPhoto(photo1)
                .setCvFile(cvFile);
        photoRepository.save(photo1);
        cvFileRepository.save(cvFile);
        candidateRepository.save(candidate);
        return CandidateMapper.INSTANCE.toCandidateDTO(candidate);
    }

    @Transactional
    @Override
    public void deleteCandidate(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не удалось найти кандидата с id = " + id));
        photoRepository.deleteById(candidate.getPhoto().getId());
        cvFileRepository.deleteById(candidate.getCvFile().getId());
        candidateRepository.deleteById(id);
    }

}