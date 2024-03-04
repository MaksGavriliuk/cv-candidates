package org.example.testtaskmaksimgavriliuk.services;

import org.example.testtaskmaksimgavriliuk.dtos.DirectionDTO;
import org.example.testtaskmaksimgavriliuk.entities.Direction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DirectionService {

    Page<DirectionDTO> getDirections(Pageable pageable);

    DirectionDTO getDirectionById(long id);

    DirectionDTO saveDirection(DirectionDTO directionDTO);

    DirectionDTO updateDirection(long id, DirectionDTO directionDTO);

    void deleteDirection(long id);

    Page<Direction> getFilteredDirections(String filter, Pageable pageable);

}