package org.example.testtaskmaksimgavriliuk.mappers;


import org.example.testtaskmaksimgavriliuk.entities.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;


@Mapper
public interface PhotoMapper {

    PhotoMapper INSTANCE = Mappers.getMapper(PhotoMapper.class);

    @Mapping(target = "name", source = "originalFilename")
    @Mapping(target = "type", source = "contentType")
    @Mapping(target = "photo", source = "bytes")
    Photo toPhoto(MultipartFile multipartFile);

    @Mapping(target = "originalFilename", source = "name")
    @Mapping(target = "contentType", source = "type")
    @Mapping(target = "bytes", source = "photo")
    MultipartFile toMultipartFile(Photo photo);

}