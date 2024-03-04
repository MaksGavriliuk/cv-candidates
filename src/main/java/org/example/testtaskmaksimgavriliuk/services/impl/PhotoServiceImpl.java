package org.example.testtaskmaksimgavriliuk.services.impl;

import lombok.AllArgsConstructor;
import org.example.testtaskmaksimgavriliuk.entities.Photo;
import org.example.testtaskmaksimgavriliuk.exceptions.NotFoundException;
import org.example.testtaskmaksimgavriliuk.repositories.PhotoRepository;
import org.example.testtaskmaksimgavriliuk.services.PhotoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@AllArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;


    @Override
    public Page<Photo> getPhotos(Pageable pageable) {
        return photoRepository.findAll(pageable);
    }

    @Override
    public Photo getPhotoById(long id) {
        return photoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Не удалось найти фото с id = " + id));
    }

    @Override
    public Photo getPhotoByName(String name) {
        return photoRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Не удалось найти фото с именем = " + name));
    }

    @Override
    public Photo savePhoto(MultipartFile file) {
        try {
            Photo photo = new Photo()
                    .setName(file.getOriginalFilename())
                    .setType(file.getContentType())
                    .setPhoto(file.getBytes());
            return photoRepository.save(photo);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Photo updatePhoto(long id, MultipartFile file) {
        if (!photoRepository.existsById(id)) {
            throw new NotFoundException("Не удалось найти фото с id = " + id);
        }
        try {
            Photo photo = new Photo()
                    .setId(id)
                    .setName(file.getOriginalFilename())
                    .setType(file.getContentType())
                    .setPhoto(file.getBytes());
            return photoRepository.save(photo);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deletePhotoById(long id) {
        photoRepository.deleteById(id);
    }

}