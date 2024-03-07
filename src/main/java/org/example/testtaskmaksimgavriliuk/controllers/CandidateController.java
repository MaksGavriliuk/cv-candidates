package org.example.testtaskmaksimgavriliuk.controllers;


import lombok.AllArgsConstructor;
import org.example.testtaskmaksimgavriliuk.dtos.CandidateDTO;
import org.example.testtaskmaksimgavriliuk.entities.CVFile;
import org.example.testtaskmaksimgavriliuk.entities.Photo;
import org.example.testtaskmaksimgavriliuk.services.CandidateService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("/candidates")
@AllArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CandidateDTO> getCandidates(Pageable pageable) {
        return candidateService.getCandidates(pageable);
    }

    @GetMapping("/filtered")
    @ResponseStatus(HttpStatus.OK)
    public Page<CandidateDTO> getCandidates(@RequestParam(name = "surname") String filter, Pageable pageable) {
        return candidateService.getFilteredCandidates(filter, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CandidateDTO getCandidate(@PathVariable Long id) {
        return candidateService.getCandidateById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CandidateDTO saveCandidate(CandidateDTO candidateDTO, MultipartFile photo, MultipartFile cv) {
        return candidateService.saveCandidate(candidateDTO, photo, cv);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CandidateDTO updateCandidate(
            @PathVariable Long id,
            CandidateDTO candidateDTO, MultipartFile photo, MultipartFile cv
    ) {
        return candidateService.updateCandidate(id, candidateDTO, photo, cv);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<Resource> getPhotoByCandidateId(@PathVariable Long id) {
        Photo photo = candidateService.getPhotoByCandidateId(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + URLEncoder.encode(photo.getName(), StandardCharsets.UTF_8) + "\"")
                .contentLength(photo.getPhoto().length)
                .contentType(MediaType.parseMediaType(photo.getType()))
                .body(new ByteArrayResource(photo.getPhoto()));
    }


    @GetMapping("/{id}/cv")
    public ResponseEntity<Resource> getCVFileByCandidateId(@PathVariable Long id) {
        CVFile cvFile = candidateService.getCVByCandidateId(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + URLEncoder.encode(cvFile.getName(), StandardCharsets.UTF_8) + "\"")
                .contentLength(cvFile.getCv().length)
                .contentType(MediaType.parseMediaType(cvFile.getType()))
                .body(new ByteArrayResource(cvFile.getCv()));
    }

    @PutMapping("/{id}/photo")
    @ResponseStatus(HttpStatus.OK)
    public void updatePhoto(
            @PathVariable Long id,
            @RequestBody MultipartFile photo
    ) {
        candidateService.updatePhotoByCandidateId(id, photo);
    }

    @PutMapping("/{id}/cv")
    @ResponseStatus(HttpStatus.OK)
    public void updateCVFile(
            @PathVariable Long id,
            @RequestBody MultipartFile cv
    ) {
        candidateService.updateCVFileByCandidateId(id, cv);
    }

}