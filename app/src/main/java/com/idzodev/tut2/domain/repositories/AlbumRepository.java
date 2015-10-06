package com.idzodev.tut2.domain.repositories;

import com.idzodev.tut2.domain.entities.Album;

import java.util.List;

/**
 * Created by vova on 06.10.15.
 */
public interface AlbumRepository {
    List<Album> getAlbums();
    Album getAlbum(long id);

    void insertAlbum(Album album);
    void deleteAlbum(Album album);
    void updateAlbum(Album album);
}
