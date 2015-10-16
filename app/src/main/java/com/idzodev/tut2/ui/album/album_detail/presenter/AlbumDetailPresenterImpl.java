package com.idzodev.tut2.ui.album.album_detail.presenter;

import com.idzodev.tut2.domain.entities.Album;
import com.idzodev.tut2.domain.repositories.AlbumRepository;
import com.idzodev.tut2.ui.album.album_detail.AlbumDetailView;
import com.idzodev.tut2.ui.album.album_list.view.AlbumListView;

/**
 * Created by NOTE on 14.10.2015.
 */
public class AlbumDetailPresenterImpl implements AlbumDetailPresenter {
    private AlbumRepository repository;
    private AlbumDetailView listener;
    private Album album;

    public AlbumDetailPresenterImpl(AlbumDetailView listener, AlbumRepository repository) {
        this.listener = listener;
        this.repository = repository;
    }


    @Override
    public void showAlbum(long id) {
        album = repository.getAlbum(id);
        listener.showEditDetail(album);
    }
    @Override
    public void saveAlbum(long id, String name, String path) {
        album = new Album(id, name,path);
        if (id == 0){
            repository.insertAlbum(album);
        } else {
            repository.updateAlbum(album);
        }
    }
}
