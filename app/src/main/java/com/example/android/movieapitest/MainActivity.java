package com.example.android.movieapitest;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.movieapitest.utilities.NetworkUtils;
import com.example.android.movieapitest.utilities.OpenMovieJsonUtils;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String option = "popular";
    MyRecyclerViewAdapter adapter;
    ArrayList<MovieObject> mMovieArray;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setTitle("Pop Movies");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_image);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this,numberOfColumns));
        adapter = new MyRecyclerViewAdapter(this);
        loadMovieData();

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MovieObject movieObject) {
                //Toast.makeText(getApplicationContext(), movieObject.getTitle(),Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_SHORT).show();
                //Log.v("adapter getItem title", adapter.getItem(position).getTitle());
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("MovieObject", movieObject);
                startActivity(intent);
            }
        });


/**
    }


    private void buildRecyclerView() {
        int numberOfColumns = 2;
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_image);
        mLayoutManager = new GridLayoutManager(this, numberOfColumns);
        //recyclerView.setLayoutManager(new GridLayoutManager(this,numberOfColumns));
        adapter = new MyRecyclerViewAdapter(this);
        recyclerView.setLayoutManager(mLayoutManager);

        //loadMovieData();
        //Log.v("ArrayList size", mMovieArray.get(1).getTitle());
        recyclerView.setAdapter(adapter);
/**
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.v("adapter getItem title", adapter.getItem(position).getTitle());
                //Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                //intent.putExtra("MovieObject", adapter.getItem(position));
                //startActivity(intent);
            }
        });

         adapter = new MyRecyclerViewAdapter(this, new MyRecyclerViewAdapter.OnItemClickListener() {
        @Override public void onClick(MovieObject movieObject) {
        //Toast.makeText( getApplicationContext(),movieObject.getTitle(), Toast.LENGTH_SHORT).show();
        Log.v("main click listener", movieObject.getTitle());
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("MovieObject", movieObject);
        startActivity(intent);

        }
        });
         //recyclerView.setAdapter(adapter);/*/

    }

    //@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private void loadMovieData() {
        FetchMovieData asyncTask = (FetchMovieData) new FetchMovieData().execute();
    }

    //@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public class FetchMovieData extends AsyncTask<Void, Void, ArrayList<MovieObject>> {

        @Override
        protected ArrayList<MovieObject> doInBackground(Void... voids) {
            URL movieDataRequestUrl = NetworkUtils.buildUrl(option);
            ArrayList<MovieObject> simpleJsonMovieData = null;
            try {
                String jsonMovieDataResponse = NetworkUtils.getResponseFromHttpUrl(movieDataRequestUrl);
                simpleJsonMovieData = OpenMovieJsonUtils.getMovieContentValuesFromJson(MainActivity.this, jsonMovieDataResponse);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("err", e.getMessage());
            }

            return simpleJsonMovieData;
        }

        @Override
        protected void onPostExecute(ArrayList<MovieObject> movieData) {
            if (movieData != null) {
                // TODO write method to show data view. use adapter to show data
                adapter.setMovieData(movieData);
                //setMovieData(movieData);
                //Log.v("time stamp 3", String.valueOf(new Timestamp(System.currentTimeMillis())));
                //mMovieArray = movieData;
            } else {
                // TODO write method to show error message.
                Log.e("err", "The movieData (ArrayList) is empty.");
            }
        }
    }

    public void setMovieData(ArrayList<MovieObject> m) {
        if (mMovieArray.size()!=0){
            mMovieArray.clear();
        }
        for (int i = 0; i < m.size(); i++) {
            mMovieArray.add(m.get(i));
            //Log.v("movs",((MovieObject) mMovieArray.get(i)).getTitle());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    //@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_popular:
                option = "popular";
                setTitle("Pop Movies");
                loadMovieData();
                return true;
            case R.id.option_top_rated:
                option = "top_rated";
                setTitle("Top-rated Movies");
                loadMovieData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void creatTestDataArray() {
        mMovieArray = new ArrayList<>();
        mMovieArray.add(new MovieObject("Poster path 1", "Title 1", "Synopsis 1", "rating 1", "date"));
        mMovieArray.add(new MovieObject("Poster path 2", "Title 2", "Synopsis 2", "rating 2", "date 2 1"));
        mMovieArray.add(new MovieObject("Poster path 3", "Title 3", "Synopsis 3", "rating 3", "date 3"));
    }


}
