package org.example.testtaskmaksimgavriliuk.mappers;


import org.example.testtaskmaksimgavriliuk.dtos.TestDTO;
import org.example.testtaskmaksimgavriliuk.entities.Test;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface TestMapper {

    TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);

    Test toTest(TestDTO testDTO);

    TestDTO toTestDTO(Test test);

}
