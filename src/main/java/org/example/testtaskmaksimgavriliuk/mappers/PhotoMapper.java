package org.example.testtaskmaksimgavriliuk.mappers;


import org.example.testtaskmaksimgavriliuk.entities.Photo;
import org.example.testtaskmaksimgavriliuk.exceptions.MappingException;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Mapper
public interface PhotoMapper {

    PhotoMapper INSTANCE = Mappers.getMapper(PhotoMapper.class);

    default Photo toPhoto(MultipartFile multipartFile) throws MappingException {
        try {
            return new Photo()
                    .setName(multipartFile.getOriginalFilename())
                    .setType(multipartFile.getContentType())
                    .setPhoto(multipartFile.getBytes());
        } catch (IOException e) {
            throw new MappingException("Ошибка маппинга multipart file в Photo " + e);
        }
    }

}