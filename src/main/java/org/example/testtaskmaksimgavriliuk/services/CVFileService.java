package org.example.testtaskmaksimgavriliuk.services;


import org.example.testtaskmaksimgavriliuk.entities.CVFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;


public interface CVFileService {

    Page<CVFile> getCVFiles(Pageable pageable);

    CVFile getCVFileById(long id);

    CVFile getCVFileByName(String name);

    CVFile saveCVFile(MultipartFile file);

    CVFile updateCVFile(long id, MultipartFile file);

    void deleteCVFileById(long id);

}