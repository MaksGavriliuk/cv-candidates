package org.example.testtaskmaksimgavriliuk.mappers;

import org.example.testtaskmaksimgavriliuk.exceptions.MappingException;
import org.springframework.web.multipart.MultipartFile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.example.testtaskmaksimgavriliuk.entities.CVFile;

import java.io.IOException;


@Mapper
public interface CVFileMapper {

    CVFileMapper INSTANCE = Mappers.getMapper(CVFileMapper.class);

    default CVFile toCVFile(MultipartFile multipartFile) throws MappingException {
        try {
            return new CVFile()
                    .setName(multipartFile.getOriginalFilename())
                    .setType(multipartFile.getContentType())
                    .setCv(multipartFile.getBytes());
        } catch (IOException e) {
            throw new MappingException("Ошибка маппинга multipart file в CVFile " + e);
        }
    }

}