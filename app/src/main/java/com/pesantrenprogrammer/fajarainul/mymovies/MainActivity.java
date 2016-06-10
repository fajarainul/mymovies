package com.pesantrenprogrammer.fajarainul.mymovies;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.pesantrenprogrammer.fajarainul.mymovies.adapter.MovieAdapter;
import com.pesantrenprogrammer.fajarainul.mymovies.entity.Movie;
import com.pesantrenprogrammer.fajarainul.mymovies.json.DetailMovie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    String movie_title;
    String movie_overview;
    String poster_path;
    String year;
    int movie_rate;

    TextView textEmpty;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    MovieAdapter mAdapter;

    ArrayList<Movie> list_movie = new ArrayList<Movie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportLoaderManager().initLoader(Constant.LOADER_MOVIE, null,this);

        textEmpty = (TextView)findViewById(R.id.text_empty);
        recyclerView = (RecyclerView)findViewById(R.id.main_rv);

    }

    public void setRecyclerView(Cursor data){
        list_movie.clear();
        if (data == null) {
            Log.e("data ", "Error");
        } else if (data.getCount() < 1) {
            textEmpty.setText("Movie tidak tersedia");
        } else {
            textEmpty.setText("");
            while (data.moveToNext()) {
                movie_title = data.getString(data.getColumnIndex(Constant.TITLE));
                movie_overview = data.getString(data.getColumnIndex(Constant.OVERVIEW));
                poster_path = data.getString(data.getColumnIndex(Constant.POSTER_PATH));
                year = data.getString(data.getColumnIndex(Constant.YEAR));

                movie_rate = data.getInt(data.getColumnIndex(Constant.RATE));

                //Log.d("TEST CURSOR", judul_kajian);

                Movie movie = new Movie();
                movie.setMovie_title(movie_title);
                movie.setMovie_overview(movie_overview);
                movie.setPoster_path(poster_path);
                movie.setYear(year);
                movie.setMovie_rate(movie_rate);

                list_movie.add(movie);
            }

            mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            mAdapter = new MovieAdapter(getApplicationContext(), list_movie,
                    new MovieAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Movie movie) {
                            Intent intent = new Intent(MainActivity.this, DetailMovie.class);
                            intent.putExtra(Constant.TITLE, movie.getMovie_title());
                            intent.putExtra(Constant.OVERVIEW, movie.getMovie_overview());
                            intent.putExtra(Constant.YEAR, movie.getYear());
                            intent.putExtra(Constant.POSTER_PATH, movie.getPoster_path());
                            intent.putExtra(Constant.RATE, movie.getMovie_rate());
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), movie.getMovie_title(), Toast.LENGTH_SHORT).show();
                        }
                    }
            );
            recyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Uri uri = Constant.CONTENT_URI.buildUpon().appendPath(Constant.TABLE_NAME).build();

        CursorLoader cursorLoader = new CursorLoader(getApplicationContext(), uri, null, null, null, null);

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        setRecyclerView(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }


    @Override
    public void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(Constant.LOADER_MOVIE, null,this);
    }
}
