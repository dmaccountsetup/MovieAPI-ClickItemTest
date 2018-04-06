package com.example.android.movieapitest.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by ka171 on 3/19/2018.
 * These utilities will be used to communicate with the movie database
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    final static String MOVIEDB_BASE_URL = "http://api.themoviedb.org/3/movie/";
    final static String TEST_URL ="http://api.themoviedb.org/3/movie/popular?api_key=fd53a74c7042fe3ca5c5be0d7ad7f994";
    //Example URL: http://api.themoviedb.org/3/movie/popular?api_key=[API_KEY]
    //API Key: fd53a74c7042fe3ca5c5be0d7ad7f994
    private static final String API_KEY = "fd53a74c7042fe3ca5c5be0d7ad7f994";

    /**/
    public static URL buildUrl(String sortType){
        Uri movieQueryUri = Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                .appendPath(sortType)
                .appendQueryParameter("api_key", API_KEY)
                .build();
        URL movieQueryUrl = null;
        try {
            movieQueryUrl = new URL(movieQueryUri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        Log.v(TAG, "URL: " + movieQueryUrl);
        return movieQueryUrl;
    }

    public static String getResponseFromHttpUrl (URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A"); //beginning of the input of the line

            boolean hasInput = scanner.hasNext();
            String response = null;
            if(hasInput){
                response = scanner.next();
            }
            scanner.close();
            return response;
        }finally {
            urlConnection.disconnect();
        }
    }

}
