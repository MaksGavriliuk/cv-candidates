package org.example.testtaskmaksimgavriliuk.services.impl;

import lombok.AllArgsConstructor;
import org.example.testtaskmaksimgavriliuk.entities.CVFile;
import org.example.testtaskmaksimgavriliuk.exceptions.NotFoundException;
import org.example.testtaskmaksimgavriliuk.repositories.CVFileRepository;
import org.example.testtaskmaksimgavriliuk.services.CVFileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@AllArgsConstructor
public class CVFileServiceImpl implements CVFileService {

    private final CVFileRepository cvFileRepository;

    @Override
    public Page<CVFile> getCVFiles(Pageable pageable) {
        return cvFileRepository.findAll(pageable);
    }

    @Override
    public CVFile getCVFileById(long id) {
        return cvFileRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не удалось найти CV-файл с id = " + id));
    }

    @Override
    public CVFile getCVFileByName(String name) {
        return cvFileRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Не удалось найти CV-файл с именем = " + name));
    }

    @Override
    public CVFile saveCVFile(MultipartFile file) {
        try {
            CVFile cvFile = new CVFile()
                    .setName(file.getOriginalFilename())
                    .setType(file.getContentType())
                    .setCv(file.getBytes());
            return cvFileRepository.save(cvFile);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении CV-файла", e);
        }
    }

    @Override
    public CVFile updateCVFile(long id, MultipartFile file) {
        if (!cvFileRepository.existsById(id)) {
            throw new NotFoundException("Не удалось найти CV-файл с id = " + id);
        }
        try {
            CVFile cvFile = new CVFile()
                    .setId(id)
                    .setName(file.getOriginalFilename())
                    .setType(file.getContentType())
                    .setCv(file.getBytes());
            return cvFileRepository.save(cvFile);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при обновлении CV-файла", e);
        }
    }

    @Override
    public void deleteCVFileById(long id) {
        cvFileRepository.deleteById(id);
    }

}