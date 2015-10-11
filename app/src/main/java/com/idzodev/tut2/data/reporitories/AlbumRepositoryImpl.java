package com.idzodev.tut2.data.reporitories;

import android.content.Context;
import android.database.Cursor;

import com.idzodev.tut2.data.database.TABLES;
import com.idzodev.tut2.domain.entities.Album;
import com.idzodev.tut2.domain.repositories.AlbumRepository;
import com.idzodev.tut2.ui.album.album_list.presenter.AlbumPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vova on 06.10.15.
 */
public class AlbumRepositoryImpl extends BaseRepository implements AlbumRepository {

    public AlbumRepositoryImpl(Context context) {
        super(context);
    }

    @Override
    public List<Album> getAlbums() {
        List<Album> list = new ArrayList<>();
        final String SELECT = " select * from " + TABLES.ALBUMS.TABLE_NAME;

        openDatabase();
        Cursor cursor = getDatabase().rawQuery(SELECT, null);
        if (cursor.moveToFirst()){
            do {
                list.add(TABLES.ALBUMS.getAlbum(cursor));
            } while (cursor.moveToNext());
        }
        return list;
    }

    @Override
    public Album getAlbum(long id) {
        Album album = null;
        final String SELECT = " select * from " + TABLES.ALBUMS.TABLE_NAME + " as t " +
                " where t." + TABLES.ALBUMS.ID + " = " + id;

        openDatabase();

        Cursor cursor = getDatabase().rawQuery(SELECT, null);

        if (cursor.moveToFirst()){
            album = TABLES.ALBUMS.getAlbum(cursor);
        }

        closeDatabase();
        return album;
    }

    @Override
    public void insertAlbum(Album album) {
        openDatabase();

        long id = getDatabase().insert(TABLES.ALBUMS.TABLE_NAME, null, TABLES.ALBUMS.getContentValues(album, false));
        album.setId(id);
        closeDatabase();
    }

    @Override
    public void deleteAlbum(Album album) {
        openDatabase();

        getDatabase().delete(TABLES.ALBUMS.TABLE_NAME, TABLES.ALBUMS.ID + " = " + album.getId(), null);

        closeDatabase();
    }

    @Override
    public void updateAlbum(Album album) {
        openDatabase();

        getDatabase().update(
                TABLES.ALBUMS.TABLE_NAME,
                TABLES.ALBUMS.getContentValues(album, true),
                TABLES.ALBUMS.ID + " = " + album.getId(),
                null
        );

        closeDatabase();
    }
}
