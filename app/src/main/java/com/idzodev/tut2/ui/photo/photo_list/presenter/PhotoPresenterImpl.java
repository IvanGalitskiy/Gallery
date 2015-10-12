package com.idzodev.tut2.ui.photo.photo_list.presenter;

import com.idzodev.tut2.domain.entities.Photo;
import com.idzodev.tut2.domain.repositories.PhotoRepository;
import com.idzodev.tut2.ui.photo.photo_list.view.PhotoListView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by NOTE on 06.10.2015.
 */
public class PhotoPresenterImpl implements PhotoPresenter {

    private PhotoRepository repository;
    private PhotoListView photoListView;

    public PhotoPresenterImpl(PhotoListView photoListView, PhotoRepository repository) {
        this.photoListView = photoListView;
        this.repository = repository;
    }

    @Override
    public void showPhotos(long album_id) {
        List<Photo> photos = repository.getPhotos(album_id);
        photoListView.showPhoto(photos);

    }

    @Override
    public void onPhotoClick(Photo photo) {
        photoListView.showPhotoSlideFragment(photo);
    }

    @Override
    public void savePhoto(String url, long album_id, int pos) {
        repository.insertPhoto(new Photo(0, url,album_id,pos));
        showPhotos(album_id);
    }

    @Override
    public void onPhotoCreateFromCamera() {
        photoListView.createPhotoFromCamera();
    }
    @Override
    public void onPhotoCreateFromGallery() {
        photoListView.createPhotoFromGallery();
        
    }
}
