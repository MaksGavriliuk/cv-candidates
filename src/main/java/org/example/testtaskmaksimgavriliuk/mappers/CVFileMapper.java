package org.example.testtaskmaksimgavriliuk.mappers;


import org.springframework.web.multipart.MultipartFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.example.testtaskmaksimgavriliuk.entities.CVFile;


@Mapper
public interface CVFileMapper {

    CVFileMapper INSTANCE = Mappers.getMapper(CVFileMapper.class);

    @Mapping(target = "originalFilename", source = "name")
    @Mapping(target = "contentType", source = "type")
    @Mapping(target = "bytes", source = "cv")
    MultipartFile toMultipartFile(CVFile cvFile);

    @Mapping(target = "name", source = "originalFilename")
    @Mapping(target = "type", source = "contentType")
    @Mapping(target = "cv", source = "bytes")
    CVFile toCVFile(MultipartFile multipartFile);

}