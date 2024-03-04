package org.example.testtaskmaksimgavriliuk.services.impl;

import lombok.AllArgsConstructor;
import org.example.testtaskmaksimgavriliuk.entities.Photo;
import org.example.testtaskmaksimgavriliuk.exceptions.NotFoundException;
import org.example.testtaskmaksimgavriliuk.mappers.PhotoMapper;
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
        Photo photo = PhotoMapper.INSTANCE.toPhoto(file);
        return photoRepository.save(photo);
    }

    @Override
    public Photo updatePhoto(long id, MultipartFile file) {
        if (!photoRepository.existsById(id)) {
            throw new NotFoundException("Не удалось найти фото с id = " + id);
        }
        Photo photo = PhotoMapper.INSTANCE.toPhoto(file).setId(id);
        return photoRepository.save(photo);
    }

    @Override
    public void deletePhotoById(long id) {
        photoRepository.deleteById(id);
    }

}