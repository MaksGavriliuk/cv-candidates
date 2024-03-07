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
    public Page<CandidateDTO> getCandidates(Pageable pageable) {
        return candidateRepository.findAll(pageable)
                .map(CandidateMapper.INSTANCE::toCandidateDTO);
    }

    @Override
    public Page<CandidateDTO> getFilteredCandidates(String filter, Pageable pageable) {
        return candidateRepository.findBySurnameContainingIgnoreCase(filter, pageable)
                .map(CandidateMapper.INSTANCE::toCandidateDTO);
    }

    @Override
    public CandidateDTO getCandidateById(Long id) {
        return candidateRepository.findById(id)
                .map(CandidateMapper.INSTANCE::toCandidateDTO)
                .orElseThrow(() -> new NotFoundException("Не удалось найти кандидата с id = " + id));
    }

    @Transactional
    @Override
    public CandidateDTO saveCandidate(CandidateDTO candidateDTO, MultipartFile photo, MultipartFile cv) {
        Photo photo1 = PhotoMapper.INSTANCE.toPhoto(photo);
        CVFile cvFile = CVFileMapper.INSTANCE.toCVFile(cv);
        Photo savedPhoto = photoRepository.save(photo1);
        CVFile savedCVFile = cvFileRepository.save(cvFile);
        Candidate candidate = CandidateMapper.INSTANCE.toCandidate(candidateDTO)
                .setPhoto(savedPhoto)
                .setCvFile(savedCVFile);
        Candidate savedCandidate = candidateRepository.save(candidate);
        return CandidateMapper.INSTANCE.toCandidateDTO(savedCandidate);
    }

    @Transactional
    @Override
    public CandidateDTO updateCandidate(Long id, CandidateDTO candidateDTO, MultipartFile photo, MultipartFile cv) {
        if (!candidateRepository.existsById(id)) {
            throw new NotFoundException("Не удалось найти кандидата с id = " + id);
        }
        Photo updatedPhoto = updatePhotoByCandidateId(id, photo);
        CVFile updatedCVFile = updateCVFileByCandidateId(id, cv);
        Candidate candidate = CandidateMapper.INSTANCE.toCandidate(candidateDTO)
                .setPhoto(updatedPhoto)
                .setCvFile(updatedCVFile);
        candidateRepository.save(candidate);
        return CandidateMapper.INSTANCE.toCandidateDTO(candidate);
    }

    @Transactional
    @Override
    public void deleteCandidate(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не удалось найти кандидата с id = " + id));
        candidateRepository.deleteById(id);
        photoRepository.deleteById(candidate.getPhoto().getId());
        cvFileRepository.deleteById(candidate.getCvFile().getId());
    }

    @Override
    public Photo getPhotoByCandidateId(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не удалось найти кандидата с id = " + id))
                .getPhoto();
    }

    @Override
    public CVFile getCVByCandidateId(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не удалось найти кандидата с id = " + id))
                .getCvFile();
    }

    @Override
    public Photo updatePhotoByCandidateId(Long id, MultipartFile photo) {

        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не удалось найти кандидата с id = " + id));

        Photo existingPhoto = candidate.getPhoto();
        Photo updatedPhoto = PhotoMapper.INSTANCE.toPhoto(photo);
        if (existingPhoto != null) {
            updatedPhoto.setId(existingPhoto.getId());
        }

        Photo savedPhoto = photoRepository.save(updatedPhoto);
        candidate.setPhoto(savedPhoto);
        candidateRepository.save(candidate);

        return savedPhoto;

    }

    @Override
    public CVFile updateCVFileByCandidateId(Long id, MultipartFile cvFile) {

        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не удалось найти кандидата с id = " + id));

        CVFile existingCVFile = candidate.getCvFile();
        CVFile updatedCVFile = CVFileMapper.INSTANCE.toCVFile(cvFile);
        if (existingCVFile != null) {
            updatedCVFile.setId(existingCVFile.getId());
        }

        CVFile savedCVFile = cvFileRepository.save(updatedCVFile);
        candidate.setCvFile(savedCVFile);
        candidateRepository.save(candidate);

        return savedCVFile;

    }

}