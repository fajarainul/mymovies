package com.pesantrenprogrammer.fajarainul.mymovies.entity;

/**
 * Created by Fajar Ainul on 10/06/2016.
 */
public class Movie {
    String movie_title;
    String movie_overview;
    String poster_path;
    String year;
    double movie_rate;

    public void Movie(){

    }

    public void Movie(String title, String overview, String poster,String year, double rate){
        this.movie_title = title;
        this.movie_overview = overview;
        this.poster_path = poster;
        this.year = year;
        this.movie_rate = rate;
    }

    /*SETTER MOVIE CLASS*/
    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public void setMovie_overview(String movie_overview) {
        this.movie_overview = movie_overview;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setMovie_rate(double movie_rate) {
        this.movie_rate = movie_rate;
    }

    /*GETTER MOVIE CLASS*/

    public String getMovie_title() {
        return movie_title;
    }

    public String getMovie_overview() {
        return movie_overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getYear() {
        return year;
    }

    public double getMovie_rate() {
        return movie_rate;
    }
}
