package com.example.android.movieapitest;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ka171 on 3/29/2018.
 */

public class MovieObject implements Parcelable {
    //public int getIndex(){ return
    public static final Parcelable.Creator<MovieObject> CREATOR = new Parcelable.Creator<MovieObject>() {
        public MovieObject createFromParcel(Parcel in) {
            return new MovieObject(in);
        }

        @Override
        public MovieObject[] newArray(int i) {
            return new MovieObject[i];
        }
    };

    final String POSTER_PATH = "poster_path";
    final String TITLE = "title";
    final String SYNOPSIS = "overview";
    final String RATING = "vote_average";
    final String DATE = "release_date";
    String posterPath;
    String title;
    String synopsis;
    String rating;
    String date;

    //MovieObject constructor
    public MovieObject() {
        posterPath = "";
        title = "";
        synopsis = "";
        rating = "";
        date = "";
    }

    public MovieObject(String posterPath, String title, String synopsis, String rating, String date) {
        this.posterPath = posterPath;
        this.title = title;
        this.synopsis = synopsis;
        this.rating = rating;
        this.date = date;
    }

    public MovieObject(Parcel in) {
        this.posterPath = in.readString();
        this.title = in.readString();
        this.synopsis = in.readString();
        this.rating = in.readString();
        this.date = in.readString();
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String s) {
        posterPath = s;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String s) {
        title = s;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String s) {
        synopsis = s;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String s) {
        rating = s;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String s) {
        date = s;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(posterPath);
        parcel.writeString(title);
        parcel.writeString(synopsis);
        parcel.writeString(rating);
        parcel.writeString(date);
    }
}
