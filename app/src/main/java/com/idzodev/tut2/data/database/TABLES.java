package com.idzodev.tut2.data.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.idzodev.tut2.domain.entities.Album;
import com.idzodev.tut2.domain.entities.Photo;

/**
 * Created by vova on 06.10.15.
 */
public class TABLES {

    public static class ALBUMS {
        public final static String TABLE_NAME = "Albums";

        public final static String ID = "ALBUMS_ID";
        public final static String NAME = "ALBUMS_NAME";
        public final static String URL = "ALBUMS_URL";

        public static String createTable() {
            return " create table " + TABLE_NAME + " ( " +
                    ID + " integer primary key autoincrement, " +
                    NAME + " text, " +
                    URL + " text " +
                    " ) ";
        }

        public static ContentValues getContentValues(Album album, boolean addId) {
            ContentValues values = new ContentValues();
            if (addId) {
                values.put(ID, album.getId());
            }
            values.put(NAME, album.getName());
            values.put(URL, album.getPhoto());
            return values;
        }

        public static Album getAlbum(Cursor cursor) {
            int indexUrl = cursor.getColumnIndexOrThrow(URL);
            int indexId = cursor.getColumnIndexOrThrow(ID);
            int indexName = cursor.getColumnIndexOrThrow(NAME);

            return new Album(
                    cursor.getLong(indexId),
                    cursor.getString(indexName),
                    cursor.getString(indexUrl)
            );
        }
    }

    public static class PHOTOS {
        public final static String TABLE_NAME = "Photos";

        public final static String ID = "PHOTOS_ID";
        public final static String URL = "PHOTOS_URL";
        public final static String ALBUM_ID = "ALBUM_ID";
        public final static String POSITION = "POSITION";


        public static String createTable() {
            return " create table " + TABLE_NAME + " ( " +
                    ID + " integer primary key autoincrement, " +
                    URL + " text, " +
                    ALBUM_ID + " text, " +
                    POSITION + " text " +
                    " ) ";
        }

        public static ContentValues getContentValues(Photo photo, boolean addId) {
            ContentValues values = new ContentValues();
            if (addId) {
                values.put(ID, photo.getId());
            }
            values.put(URL, photo.getUrl());
            values.put(ALBUM_ID, photo.getAlbumId());
            values.put(POSITION, photo.getPosition());
            return values;
        }

        public static Photo getPhoto(Cursor cursor) {
            int indexUrl = cursor.getColumnIndexOrThrow(URL);
            int indexId = cursor.getColumnIndexOrThrow(ID);
            int indexAlbum_Id = cursor.getColumnIndexOrThrow(ALBUM_ID);
            int indexPosition = cursor.getColumnIndexOrThrow(POSITION);

            return new Photo(
                    cursor.getLong(indexId),
                    cursor.getString(indexUrl),
                    cursor.getLong(indexAlbum_Id),
                    cursor.getInt(indexPosition)
            );
        }
    }
}
