package com.pesantrenprogrammer.fajarainul.mymovies.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.pesantrenprogrammer.fajarainul.mymovies.Constant;
import com.pesantrenprogrammer.fajarainul.mymovies.R;
import com.squareup.picasso.Picasso;

public class DetailMovie extends AppCompatActivity {

    String movie_title;
    String movie_overview;
    String poster_path;
    String year;
    double movie_rate;

    ImageView imgPoster;
    TextView textTitle, textOverview, textRate, textYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        Bundle extras = getIntent().getExtras();
        movie_title = extras.getString(Constant.TITLE);
        movie_overview = extras.getString(Constant.OVERVIEW);
        poster_path = extras.getString(Constant.POSTER_PATH);
        year = extras.getString(Constant.YEAR);
        movie_rate = extras.getDouble(Constant.RATE);

        textTitle = (TextView)findViewById(R.id.title_detail);
        textOverview = (TextView)findViewById(R.id.overview_detail);
        textRate = (TextView)findViewById(R.id.rating_detail);
        textYear = (TextView)findViewById(R.id.year_detail);
        imgPoster = (ImageView) findViewById(R.id.detail_image);


        textTitle.setText(movie_title);
        textOverview.setText(movie_overview);
        textYear.setText(year);
        textRate.setText(""+movie_rate);

        String url = "http://image.tmdb.org/t/p/w500/"+poster_path;

        Picasso.with(getApplicationContext()).load(url).into(imgPoster);
    }
}
