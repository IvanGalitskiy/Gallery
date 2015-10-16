package com.idzodev.tut2.ui.album.album_detail.presenter;

/**
 * Created by NOTE on 14.10.2015.
 */
public interface AlbumDetailPresenter {
    void showAlbum(long id);
    void getAlbum(long id);
    void editAlbum(long id, String name, String path);
    void insertAlbum(long id, String name, String path);

}
