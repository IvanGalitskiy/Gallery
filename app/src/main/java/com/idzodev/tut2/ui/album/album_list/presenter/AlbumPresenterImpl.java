package com.idzodev.tut2.ui.album.album_list.presenter;

import com.idzodev.tut2.domain.entities.Album;
import com.idzodev.tut2.domain.repositories.AlbumRepository;
import com.idzodev.tut2.ui.album.album_list.view.AlbumListView;

import java.util.List;

/**
 * Created by vova on 06.10.15.
 */
public class AlbumPresenterImpl implements AlbumPresenter {
    private AlbumListView albumListView;
    private AlbumRepository repository;

    public AlbumPresenterImpl(AlbumListView albumListView, AlbumRepository repository) {
        this.albumListView = albumListView;
        this.repository = repository;
    }

    @Override
    public void showAlbums() {
        List<Album> albums = repository.getAlbums();
        albumListView.showAlbums(albums);
    }

    @Override
    public void onAlbumClick(Album album) {
        albumListView.showPhotoListFragment(album);
    }

    @Override
    public void onAlbumEdit(Album album) {
        albumListView.showCreateAlbumFragment(album);
    }

    @Override
    public void onAlbumCreate() {
        albumListView.showCreateAlbumFragment(null);
    }
}
