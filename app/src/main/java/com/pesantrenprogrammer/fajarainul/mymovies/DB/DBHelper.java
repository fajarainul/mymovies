package com.pesantrenprogrammer.fajarainul.mymovies.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pesantrenprogrammer.fajarainul.mymovies.Constant;

/**
 * Created by Fajar Ainul on 10/06/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "movie.db";
    static final int DATABASE_VERSION = 4;
    private String CREATE_TABLE_KAJIAN =
            " CREATE TABLE " + Constant.TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Constant.TITLE+" TEXT NOT NULL, " +
                    Constant.OVERVIEW+" TEXT NOT NULL, " +
                    Constant.POSTER_PATH+" TEXT NOT NULL, " +
                    Constant.YEAR+" TEXT NOT NULL, " +
                    Constant.RATE+" REAL NOT NULL); ";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_KAJIAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constant.TABLE_NAME);

        onCreate(db);
    }

    public void deleteAllTable(String table_name){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name,null, null);

    }
}
