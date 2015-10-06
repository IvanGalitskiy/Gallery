package com.idzodev.tut2.data.reporitories;

import android.content.Context;
import android.database.Cursor;

import com.idzodev.tut2.data.database.TABLES;
import com.idzodev.tut2.domain.entities.Photo;
import com.idzodev.tut2.domain.repositories.PhotoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NOTE on 06.10.2015.
 */
public class PhotoRepositoryImpl extends BaseRepository implements PhotoRepository {


    public PhotoRepositoryImpl(Context context) {
        super(context);
    }

    @Override
    public List<Photo> getPhotos() {
       List<Photo> list = new ArrayList<>();
        final String SELECT = " select * from " + TABLES.PHOTOS.TABLE_NAME;
        openDatabase();
        Cursor cursor = getDatabase().rawQuery(SELECT, null);
        if (cursor.moveToFirst()){
            do {
                list.add(TABLES.PHOTOS.getPhoto(cursor));
            } while (cursor.moveToNext());
        }
        return list;
    }

    @Override
    public Photo getPhoto(long id) {
        Photo photo = null;
        final String SELECT = " select * from " + TABLES.PHOTOS.TABLE_NAME + " as t " +
                " where t." + TABLES.PHOTOS.ID + " = " + id;

        openDatabase();
        Cursor cursor = getDatabase().rawQuery(SELECT, null);
        if (cursor.moveToFirst()){
            photo = TABLES.PHOTOS.getPhoto(cursor);
        }

        closeDatabase();
        return photo;
    }

    @Override
    public void insertPhoto(Photo photo) {
        openDatabase();
        long id = getDatabase().insert(TABLES.PHOTOS.TABLE_NAME, null, TABLES.PHOTOS.getContentValues(photo, false));
        photo.setId(id);
        closeDatabase();
    }

    @Override
    public void deletePhoto(Photo photo) {
        openDatabase();

        getDatabase().delete(TABLES.PHOTOS.TABLE_NAME, TABLES.PHOTOS.ID + " = " + photo.getId(), null);

        closeDatabase();
    }

    @Override
    public void updatePhoto(Photo photo) {
        openDatabase();

        getDatabase().update(
                TABLES.PHOTOS.TABLE_NAME,
                TABLES.PHOTOS.getContentValues(photo, true),
                TABLES.PHOTOS.ID + " = " + photo.getId(),
                null
        );

        closeDatabase();
    }
}
