package com.idzodev.tut2.ui.album.album_list.view;

import com.idzodev.tut2.domain.entities.Album;

import java.util.List;

/**
 * Created by vova on 06.10.15.
 */
public interface AlbumListView {
    void showAlbums(List<Album> albums);
    void showCreateAlbumFragment(Album album);
    void showPhotoListFragment(Album album);
}
