package org.example.testtaskmaksimgavriliuk.mappers;


import org.example.testtaskmaksimgavriliuk.dtos.DirectionDTO;
import org.example.testtaskmaksimgavriliuk.entities.Direction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface DirectionMapper {

    DirectionMapper INSTANCE = Mappers.getMapper(DirectionMapper.class);

    Direction DirectionDTOToDirection(DirectionDTO directionDTO);

    DirectionDTO DirectionToDirectionDTO(Direction direction);

}
