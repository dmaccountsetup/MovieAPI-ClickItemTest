package com.example.android.movieapitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    //private TextView mMovieTitle;
    //private TextView mDate;
    //private TextView mRating;
    //private TextView mSynopsis;
    //private ImageView mPoster;
    //String posterPath;
   //String title;
   // String synopsis;
    //String rating;
   // String date;
    ImageView mImageView;
    TextView mSynopsis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Movie Detail");
        Intent intent = getIntent();
        MovieObject movieObject = intent.getParcelableExtra("MovieObject");
        String posterPath = movieObject.getPosterPath();
        String title = movieObject.getTitle();
        String synopsis = movieObject.getSynopsis();
        String rating = movieObject.getRating();
        String date = movieObject.getDate();
        //Log.v("Poster path: ", posterPath);
        mImageView = (ImageView)findViewById(R.id.imageView);
        mSynopsis = (TextView)findViewById(R.id.synopsis);
        mSynopsis.setMovementMethod(new ScrollingMovementMethod());
        Picasso.with(getApplicationContext())
                .load(posterPath)
                .fit()
                .into(mImageView);
        TextView mMovieTitle = findViewById(R.id.movie_title);
        mMovieTitle.setText(title);

        TextView mSynopsis = findViewById(R.id.synopsis);
        mSynopsis.setText(synopsis);

        TextView mRating = findViewById(R.id.user_rating);
        mRating.setText("Rating: "+rating+"/10");

        TextView mDate = findViewById(R.id.release_date);
        mDate.setText(date);
    }
}
