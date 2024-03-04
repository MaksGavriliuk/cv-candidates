package org.example.testtaskmaksimgavriliuk.services;

import org.example.testtaskmaksimgavriliuk.entities.Photo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

    Page<Photo> getPhotos(Pageable pageable);

    Photo getPhotoById(long id);

    Photo getPhotoByName(String name);

    Photo savePhoto(MultipartFile file);

    Photo updatePhoto(long id, MultipartFile file);

    void deletePhotoById(long id);


}
