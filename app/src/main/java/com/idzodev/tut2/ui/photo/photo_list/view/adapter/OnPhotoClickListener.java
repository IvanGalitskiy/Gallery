package com.idzodev.tut2.ui.photo.photo_list.view.adapter;

import com.idzodev.tut2.domain.entities.Photo;

/**
 * Created by NOTE on 07.10.2015.
 */
public interface OnPhotoClickListener {
    void onPhotoClick(Photo photo);
    void deletePhoto(Photo photo);
}
