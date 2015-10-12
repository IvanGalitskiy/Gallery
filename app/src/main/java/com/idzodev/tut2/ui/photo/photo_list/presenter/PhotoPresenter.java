package com.idzodev.tut2.ui.photo.photo_list.presenter;

import com.idzodev.tut2.domain.entities.Album;
import com.idzodev.tut2.domain.entities.Photo;

/**
 * Created by NOTE on 06.10.2015.
 */
public interface PhotoPresenter {
    void showPhotos(long album_id);
    void onPhotoCreateFromGallery();
    void onPhotoCreateFromCamera();
    void onPhotoClick(Photo photo);
    void savePhoto(String url, long album_id, int pos);

}
