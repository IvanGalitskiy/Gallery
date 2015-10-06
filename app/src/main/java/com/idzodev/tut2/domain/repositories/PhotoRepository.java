package com.idzodev.tut2.domain.repositories;

import com.idzodev.tut2.domain.entities.Photo;

import java.util.List;

/**
 * Created by vova on 06.10.15.
 */
public interface PhotoRepository {
    List<Photo> getPhotos();
    Photo getPhoto(long id);

    void insertPhoto(Photo photo);
    void deletePhoto(Photo photo);
    void updatePhoto(Photo photo);
}
