package org.example.testtaskmaksimgavriliuk.mappers;

import org.example.testtaskmaksimgavriliuk.dtos.TestDTO;
import org.example.testtaskmaksimgavriliuk.entities.Direction;
import org.example.testtaskmaksimgavriliuk.entities.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface TestMapper {

    TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);

    @Mapping(target = "directionsId", source = "directions", qualifiedByName = "mapDirectionsToDirectionsId")
    TestDTO toTestDTO(Test test);

    Test toTest(TestDTO testDTO);

    @Named("mapDirectionsToDirectionsId")
    default List<Long> mapDirectionsToDirectionsId(List<Direction> directions) {
        return directions.stream()
                .map(Direction::getId)
                .toList();
    }

}