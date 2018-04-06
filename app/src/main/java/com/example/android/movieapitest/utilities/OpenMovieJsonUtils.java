package com.example.android.movieapitest.utilities;

import android.content.Context;

import com.example.android.movieapitest.MovieObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ka171 on 3/19/2018.
 */

public final class OpenMovieJsonUtils {
    //private static final String OWM_MESSAGE_CODE = "cod";
    public static ArrayList getMovieContentValuesFromJson(Context context, String movieJsonStr) throws JSONException {
        final String MOVIE_RESULTS = "results";
        final String POSTER_PATH = "poster_path";
        final String TITLE = "title";
        final String SYNOPSIS = "overview";
        final String RATING = "vote_average";
        final String DATE = "release_date";

        JSONObject movieJSONObject = new JSONObject(movieJsonStr);
        JSONArray jsonMovieArray = movieJSONObject.getJSONArray(MOVIE_RESULTS);

        ArrayList<MovieObject> mMovieArrayList = new ArrayList();
        //parsing values from jsonMovieArray
        for (int i = 0; i < jsonMovieArray.length(); i++) {
            //movieInfo contains vote count, id, title, poster, etc.
            JSONObject movieInfo = jsonMovieArray.getJSONObject(i);
            MovieObject movieData = new MovieObject();
            movieData.setTitle(movieInfo.getString(TITLE));
            movieData.setSynopsis(movieInfo.getString(SYNOPSIS));
            movieData.setRating(movieInfo.getString(RATING));
            movieData.setDate(movieInfo.getString(DATE));
            movieData.setPosterPath("https://image.tmdb.org/t/p/w342/" + movieInfo.getString(POSTER_PATH));

            mMovieArrayList.add(movieData);
        }
        return mMovieArrayList;
    }
}

/**
 * original title = "title"
 * poster = "poster_path"
 * synopsis/overview = "overview"
 * rating/vote_average ="vote_average"
 * release date = "release_date"
 */