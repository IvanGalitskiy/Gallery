package com.idzodev.tut2.data.reporitories;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.idzodev.tut2.data.database.DatabaseFactory;

/**
 * Created by vova on 06.10.15.
 */
public class BaseRepository {
    private SQLiteDatabase database;
    private Context context;

    public BaseRepository(Context context) {
        this.context = context;
    }

    public void openDatabase(){
        database = DatabaseFactory.getInstance(context).open();
    }

    public void closeDatabase(){
        DatabaseFactory.getInstance(context).close();
        database = null;
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }
}
