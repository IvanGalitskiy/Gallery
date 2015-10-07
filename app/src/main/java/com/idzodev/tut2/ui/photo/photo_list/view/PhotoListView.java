package com.idzodev.tut2.ui.photo.photo_list.view;

import com.idzodev.tut2.domain.entities.Photo;

import java.util.List;

/**
 * Created by NOTE on 06.10.2015.
 */
public interface PhotoListView {
    void showPhoto(List<Photo> photos);
    void createPhotoFromGallery();
    void createPhotoFromCamera();
    void showPhotoSlideFragment(Photo photo);
}
