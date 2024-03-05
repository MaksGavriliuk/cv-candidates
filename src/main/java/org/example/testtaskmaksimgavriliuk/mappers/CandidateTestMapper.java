package org.example.testtaskmaksimgavriliuk.mappers;


import org.example.testtaskmaksimgavriliuk.dtos.CandidateTestDTO;
import org.example.testtaskmaksimgavriliuk.entities.CandidateTest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CandidateTestMapper {

    CandidateTestMapper INSTANCE = Mappers.getMapper(CandidateTestMapper.class);

    @Mapping(target = "candidate.id", source = "candidateId")
    @Mapping(target = "test.id", source = "testId")
    CandidateTest toCandidateTest(CandidateTestDTO candidateTestDTO);

    @Mapping(target = "candidateId", source = "candidate.id")
    @Mapping(target = "testId", source = "test.id")
    CandidateTestDTO toCandidateTestDTO(CandidateTest candidateTest);

}
