package org.example.testtaskmaksimgavriliuk.mappers;


import org.example.testtaskmaksimgavriliuk.dtos.CandidateDTO;
import org.example.testtaskmaksimgavriliuk.entities.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CandidateMapper {

    CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);

    Candidate toCandidate(CandidateDTO candidateDTO);

    CandidateDTO toCandidateDTO(Candidate candidate);

}