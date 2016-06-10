package com.pesantrenprogrammer.fajarainul.mymovies.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pesantrenprogrammer.fajarainul.mymovies.R;
import com.pesantrenprogrammer.fajarainul.mymovies.entity.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Fajar Ainul on 10/06/2016.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    ArrayList<Movie> list_movie;
    Context mContext;
    OnItemClickListener listener;

    public MovieAdapter(Context context, ArrayList<Movie> list_movie, OnItemClickListener listener){
        this.list_movie = list_movie;
        this.mContext = context;
        this.listener = listener;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_main, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(list_movie.get(position), listener);
        Movie movie = list_movie.get(position);
        if(movie.getMovie_title().length()>12){
            holder.textTitle.setText(cutString(movie.getMovie_title(), 12) + "...");
        }else{
            holder.textTitle.setText(movie.getMovie_title());
        }

        if(movie.getMovie_overview().length()>40){
            holder.textOverview.setText(cutString(movie.getMovie_overview(), 40) + "...");
        }else{
            holder.textOverview.setText(movie.getMovie_overview());
        }

        holder.textRate.setText(Double.toString(movie.getMovie_rate()));
        holder.textYear.setText(movie.getYear());

        //et image with picasso
        String url = "http://image.tmdb.org/t/p/w500/"+movie.getPoster_path();

        Picasso.with(mContext).load(url).into(holder.imageMovie);

    }

    @Override
    public int getItemCount() {
        return list_movie.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView textTitle;
        TextView textOverview;
        TextView textRate;
        TextView textYear;
        ImageView imageMovie;
        Button btnReadMore;

        public MovieViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.main_cv);
            textTitle = (TextView)itemView.findViewById(R.id.movie_title);
            textRate = (TextView)itemView.findViewById(R.id.movie_rating);
            textOverview = (TextView)itemView.findViewById(R.id.movie_overview);
            textYear = (TextView)itemView.findViewById(R.id.movie_year);
            imageMovie = (ImageView)itemView.findViewById(R.id.movie_image);
            btnReadMore = (Button)itemView.findViewById(R.id.btn_read_more);
        }

        public void bind(final Movie item, final OnItemClickListener listener) {
            btnReadMore.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    public String cutString(String string, int length){
        return string.substring(0,length);
    }



}
