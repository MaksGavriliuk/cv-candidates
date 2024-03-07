package org.example.testtaskmaksimgavriliuk.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Candidate", description = "Controller for manage candidates")
public class CandidateController {

    private final CandidateService candidateService;

    @Operation(
            summary = "Get candidates",
            description = "Allows to get candidates page by specifying the page number and size. The default page size is 20"
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CandidateDTO> getCandidates(Pageable pageable) {
        return candidateService.getCandidates(pageable);
    }

    @Operation(summary = "Get filtered candidates", description = "Allows to get candidates page filtered by surname by specifying the page number and size. The default page size is 20")
    @GetMapping("/filtered")
    @ResponseStatus(HttpStatus.OK)
    public Page<CandidateDTO> getCandidates(@RequestParam(name = "surname") String filter, Pageable pageable) {
        return candidateService.getFilteredCandidates(filter, pageable);
    }

    @Operation(summary = "Get candidate by id", description = "Allows to get candidate by id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CandidateDTO getCandidate(@PathVariable Long id) {
        return candidateService.getCandidateById(id);
    }

    @Operation(summary = "Create new candidate", description = "Allows to create new candidate and return it")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CandidateDTO saveCandidate(CandidateDTO candidateDTO, MultipartFile photo, MultipartFile cv) {
        return candidateService.saveCandidate(candidateDTO, photo, cv);
    }

    @Operation(summary = "Update an existing candidate", description = "Allows to update an existing candidate and return it")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CandidateDTO updateCandidate(
            @PathVariable Long id,
            CandidateDTO candidateDTO, MultipartFile photo, MultipartFile cv
    ) {
        return candidateService.updateCandidate(id, candidateDTO, photo, cv);
    }

    @Operation(summary = "Delete candidate", description = "Allows to delete a candidate")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
    }

    @Operation(summary = "Get photo of candidate", description = "Allows to get photo of candidate by candidate id")
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


    @Operation(summary = "Get cv file of candidate", description = "Allows to get cv file of candidate by candidate id")
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

    @Operation(summary = "Update photo of candidate", description = "Allows to update an existing photo of candidate by candidate id")
    @PutMapping("/{id}/photo")
    @ResponseStatus(HttpStatus.OK)
    public void updatePhoto(
            @PathVariable Long id,
            @RequestBody MultipartFile photo
    ) {
        candidateService.updatePhotoByCandidateId(id, photo);
    }

    @Operation(summary = "Update cv file of candidate", description = "Allows to update an existing cv file of candidate by candidate id")
    @PutMapping("/{id}/cv")
    @ResponseStatus(HttpStatus.OK)
    public void updateCVFile(
            @PathVariable Long id,
            @RequestBody MultipartFile cv
    ) {
        candidateService.updateCVFileByCandidateId(id, cv);
    }

}