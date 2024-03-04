package org.example.testtaskmaksimgavriliuk.services.impl;

import lombok.AllArgsConstructor;
import org.example.testtaskmaksimgavriliuk.dtos.DirectionDTO;
import org.example.testtaskmaksimgavriliuk.entities.Direction;
import org.example.testtaskmaksimgavriliuk.mappers.DirectionMapper;
import org.example.testtaskmaksimgavriliuk.repositories.DirectionRepository;
import org.example.testtaskmaksimgavriliuk.services.DirectionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.example.testtaskmaksimgavriliuk.exceptions.NotFoundException;


@Service
@AllArgsConstructor
public class DirectionServiceImpl implements DirectionService {

    private final DirectionRepository directionRepository;


    @Override
    public Page<DirectionDTO> getDirections(Pageable pageable) {
        return directionRepository.findAll(pageable)
                .map(DirectionMapper.INSTANCE::DirectionToDirectionDTO);
    }

    @Override
    public DirectionDTO getDirectionById(long id) {
        return directionRepository.findById(id)
                .map(DirectionMapper.INSTANCE::DirectionToDirectionDTO)
                .orElseThrow(() -> new NotFoundException("Не удалось найти направление с id = " + id));
    }

    @Override
    public DirectionDTO saveDirection(DirectionDTO directionDTO) {
        Direction direction = DirectionMapper.INSTANCE.DirectionDTOToDirection(directionDTO);
        directionRepository.save(direction);
        return DirectionMapper.INSTANCE.DirectionToDirectionDTO(direction);
    }

    @Override
    public DirectionDTO updateDirection(long id, DirectionDTO directionDTO) {
        Direction direction = DirectionMapper.INSTANCE.DirectionDTOToDirection(directionDTO).setId(id);
        directionRepository.save(direction);
        return DirectionMapper.INSTANCE.DirectionToDirectionDTO(direction);
    }

    @Override
    public void deleteDirection(long id) {
        directionRepository.deleteById(id);
    }

    @Override
    public Page<Direction> getFilteredDirections(String filter, Pageable pageable) {
        return directionRepository.findByNameContainingIgnoreCase(filter, pageable);
    }
}