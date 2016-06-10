package com.pesantrenprogrammer.fajarainul.mymovies;

import android.net.Uri;

/**
 * Created by Fajar Ainul on 10/06/2016.
 */
public class Constant {

    //definisi semua konstanta yang nantiny digunakan pada latihan ini
    public static String TABLE_NAME = "movie";
    public static String TITLE = "title";
    public static String OVERVIEW = "overview";
    public static String RATE = "vote_average";
    public static String POSTER_PATH = "poster_path";
    public static String YEAR = "release_date";


    public static String PROVIDER = "com.pesantrenprogrammer.fajarainul.latihan";
    public static String URL = "content://"+PROVIDER;
    public static final Uri CONTENT_URI = Uri.parse(URL);

    public static final int MOVIE = 100;

    public static int LOADER_MOVIE = 101;

}
