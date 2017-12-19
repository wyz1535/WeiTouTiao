package com.leyifu.weitoutiao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.leyifu.weitoutiao.InitApp;
import com.leyifu.weitoutiao.db.table.MediaChannelTable;
import com.leyifu.weitoutiao.db.table.NewsChannelTable;
import com.leyifu.weitoutiao.db.table.SearchHistoryTable;

/**
 * Created by hahaha on 2017/12/18 0018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "toutiao";
    public static final int DB_VERSION = 1;
    private static DatabaseHelper mInstance;
    private static SQLiteDatabase db;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static synchronized DatabaseHelper getInstance() {

        if (mInstance == null) {

            mInstance = new DatabaseHelper(InitApp.getContext, DB_NAME, null, DB_VERSION);
        }

        return mInstance;
    }

    public static synchronized SQLiteDatabase SQLIteDatabase() {

        if (db == null) {
            db = getInstance().getWritableDatabase();
        }
        return db;
    }

    public static synchronized void clearDb() {

        if (db != null) {
            db.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(NewsChannelTable.CREATE_TABLE);
        db.execSQL(MediaChannelTable.CREATE_TABLE);
        db.execSQL(SearchHistoryTable.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
