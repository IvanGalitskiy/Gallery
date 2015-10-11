package com.idzodev.tut2.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vova on 06.10.15.
 */
public class DatabaseFactory extends SQLiteOpenHelper {
    private static DatabaseFactory factory;
    private final static String DB_NAME = "db_tutorial";
    private final static int DB_VERSION = 1;

    private SQLiteDatabase db;

    private DatabaseFactory(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static DatabaseFactory getInstance(Context context){
        if (factory == null){
            factory = new DatabaseFactory(context);
        }
        return factory;
    }

    public SQLiteDatabase open(){
        db = getWritableDatabase();
        return db;
    }

    public void close(){
        db.close();
        db = null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLES.ALBUMS.createTable());
        db.execSQL(TABLES.PHOTOS.createTable());
        for (int i = 0; i < 5; i++)
            i =i;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLES.ALBUMS.drop());
        db.execSQL(TABLES.PHOTOS.drop());

    }
    public void createAlbumsTable()
    {
        db.execSQL(TABLES.ALBUMS.createTable());
    }
    public void createPhotosTable()
    {
        db.execSQL(TABLES.PHOTOS.createTable());
    }
}
