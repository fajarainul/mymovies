package com.pesantrenprogrammer.fajarainul.mymovies.DB;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.pesantrenprogrammer.fajarainul.mymovies.Constant;

/**
 * Created by Fajar Ainul on 10/06/2016.
 */
public class DBProvider extends ContentProvider {
    private DBHelper dbHelper;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(Constant.PROVIDER, Constant.TABLE_NAME, Constant.MOVIE);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sURIMatcher.match(uri)) {
            case Constant.MOVIE:
            {
                retCursor = dbHelper.getReadableDatabase().query(
                        Constant.TABLE_NAME,
                        null,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Uri returnUri;

        switch (sURIMatcher.match(uri)){
            case Constant.MOVIE:
                long rowID = db.insert(	Constant.TABLE_NAME, null, values);
                if (rowID > 0)
                {
                    returnUri = ContentUris.withAppendedId(Constant.CONTENT_URI.buildUpon().appendPath(Constant.TABLE_NAME).build(), rowID);
                }else {
                    throw new SQLException("Failed to add a record into " + uri);
                }
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(returnUri, null);
        db.close();
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsDeleted;

        switch (sURIMatcher.match(uri)) {
            case Constant.MOVIE:
                rowsDeleted = db.delete(Constant.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsUpdated;

        switch (sURIMatcher.match(uri)) {
            case Constant.MOVIE:
                rowsUpdated = db.update(Constant.TABLE_NAME, values, "_id = ?", selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
}
