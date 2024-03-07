package org.example.testtaskmaksimgavriliuk.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/directions")
@AllArgsConstructor
@Tag(name = "Direction", description = "Controller for manage directions")
public class DirectionController {

    private final DirectionService directionService;


    @Operation(
            summary = "Getting directions",
            description = "Allows to get directions page by specifying the page number and size. The default page size is 20"
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DirectionDTO> getDirections(
            @Parameter(description = "Page of directions. It consists of the page number and size", example = "page=1&size=10") Pageable pageable
    ) {
        return directionService.getDirections(pageable);
    }

    @Operation(
            summary = "Getting filtered directions",
            description = "Allows to get directions page filtered by name by specifying the page number and size. The default page size is 20"
    )
    @GetMapping("/filtered")
    @ResponseStatus(HttpStatus.OK)
    public Page<DirectionDTO> getDirections(
            @RequestParam(name = "name") @Parameter(description = "Direction name", example = "backend") String filter,
            @Parameter(description = "Page of directions. It consists of the page number and size", example = "page=1&size=10") Pageable pageable) {
        return directionService.getFilteredDirections(filter, pageable);
    }

    @Operation(summary = "Getting direction by id", description = "Allows to get direction by id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DirectionDTO getDirection(@PathVariable @Parameter(description = "Direction id", example = "1") Long id) {
        return directionService.getDirectionById(id);
    }

    @Operation(summary = "Create new direction", description = "Allows to create new direction and return it")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DirectionDTO saveDirection(
            @RequestBody @Parameter(description = "DirectionDTO includes name and description",
                    example = """
                            {  "name": "backend",
                              "description": "backend of web-application"
                            }""")
            DirectionDTO directionDTO
    ) {
        return directionService.saveDirection(directionDTO);
    }

    @Operation(summary = "Update an existing direction", description = "Allows to update an existing direction and return it")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DirectionDTO updateDirection(
            @PathVariable @Parameter(description = "Direction id", example = "1") Long id,
            @RequestBody @Parameter(description = "DirectionDTO includes name and description",
                    example = """
                            {  "name": "backend",
                              "description": "backend of web-application"
                            }""")
            DirectionDTO directionDTO
    ) {
        return directionService.updateDirection(id, directionDTO);
    }

    @Operation(summary = "Delete direction", description = "Allows to delete a direction")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDirection(@PathVariable @Parameter(description = "Direction id", example = "1") Long id) {
        directionService.deleteDirection(id);
    }

}