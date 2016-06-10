package com.pesantrenprogrammer.fajarainul.mymovies.json;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.pesantrenprogrammer.fajarainul.mymovies.Constant;
import com.pesantrenprogrammer.fajarainul.mymovies.DB.DBHelper;
import com.pesantrenprogrammer.fajarainul.mymovies.entity.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Fajar Ainul on 11/06/2016.
 */
public class JSONParser {

    Context mContext;
    DBHelper dbHelper;


    public void JSONParser(){

    }


    public JSONParser(Context context){
        mContext = context;
        dbHelper = new DBHelper(context);
    }

    public void parseResponse(String response){
        try {
            dbHelper.deleteAllTable(Constant.TABLE_NAME);
            JSONObject jObj = new JSONObject(response);
            JSONArray resultArray = jObj.getJSONArray("results");
            for (int i = 0; i < resultArray.length(); i++) {
                JSONObject movieItem = resultArray.getJSONObject(i);
                // Storing  JSON item in a Variable

                Movie movie = new Movie();

                movie.setMovie_title(movieItem.getString(Constant.TITLE));
                movie.setMovie_overview(movieItem.getString(Constant.OVERVIEW));
                movie.setMovie_rate(movieItem.getDouble(Constant.RATE));
                movie.setPoster_path(movieItem.getString(Constant.POSTER_PATH));
                movie.setYear(movieItem.getString(Constant.YEAR));

                Log.d("RATE", " "+movieItem.getString(Constant.RATE));

                addToDatabase(movie);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void addToDatabase(Movie movie){
        ContentValues contentValues = new ContentValues();

        contentValues.put(Constant.TITLE, movie.getMovie_title());
        contentValues.put(Constant.OVERVIEW, movie.getMovie_overview());
        contentValues.put(Constant.RATE, movie.getMovie_rate());
        contentValues.put(Constant.POSTER_PATH, movie.getPoster_path());
        contentValues.put(Constant.YEAR, movie.getYear());


        Uri contentURI = Constant.CONTENT_URI.buildUpon().appendPath(Constant.TABLE_NAME).build();
        //Log.d("INSERT AAAA", contentURI.toString());
        Uri uri = mContext.getContentResolver().insert(
                contentURI, contentValues);
    }
}
