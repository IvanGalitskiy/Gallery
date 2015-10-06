package com.idzodev.tut2.ui.album.album_list.presenter;

import com.idzodev.tut2.domain.entities.Album;

/**
 * Created by vova on 06.10.15.
 */
public interface AlbumPresenter {
    void showAlbums();

    void onAlbumClick(Album album);
    void onAlbumEdit(Album album);
    void onAlbumCreate();
}
