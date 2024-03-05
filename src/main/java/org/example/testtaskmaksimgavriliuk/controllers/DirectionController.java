package org.example.testtaskmaksimgavriliuk.controllers;


import lombok.AllArgsConstructor;
import org.example.testtaskmaksimgavriliuk.dtos.DirectionDTO;
import org.example.testtaskmaksimgavriliuk.services.DirectionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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


@RestController
@RequestMapping("/api/v1/directions")
@AllArgsConstructor
public class DirectionController {

    private final DirectionService directionService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DirectionDTO> getDirections(Pageable pageable) {
        return directionService.getDirections(pageable);
    }

    @GetMapping("/filtered")
    @ResponseStatus(HttpStatus.OK)
    public Page<DirectionDTO> getDirections(@RequestParam(name = "name") String filter, Pageable pageable) {
        return directionService.getFilteredDirections(filter, pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DirectionDTO getDirection(@PathVariable Long id) {
        return directionService.getDirectionById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DirectionDTO saveDirection(@RequestBody DirectionDTO directionDTO) {
        return directionService.saveDirection(directionDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DirectionDTO updateDirection(
            @PathVariable Long id,
            @RequestBody DirectionDTO directionDTO
    ) {
        return directionService.updateDirection(id, directionDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDirection(@PathVariable Long id) {
        directionService.deleteDirection(id);
    }

}