package com.example.android.movieapitest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by ka171 on 3/27/2018.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<MovieObject> mMovieData;
    private OnItemClickListener mListener;

    /**
     * MyRecyclerViewAdapter(Context context) {
     * //mMovieData =null;
     * this.mInflater = LayoutInflater.from(context);
     * mContext = context;
     * }/
     */
    //Constructor will receive the context and the object that will implement the interface
    MyRecyclerViewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        mMovieData = new ArrayList<MovieObject>();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_image, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mListener);
        //MyViewHolder viewHolder = new MyViewHolder(view, mListener);
        return viewHolder;
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieObject m = mMovieData.get(position);
        String poster = m.getPosterPath();
        Picasso.with(mContext)
                .load(poster)
                .fit()
                .into(holder.myImageView);
    }

    // convenience method for getting data at click position
    public MovieObject getItem(int id) {
        return mMovieData.get(id);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        Log.v("mMovieData size gic = ", String.valueOf(mMovieData.size()));
        return mMovieData.size();
    }

    public ArrayList<MovieObject> getmMovieData(){
        return mMovieData;
    }

    public void setMovieData(ArrayList movieData) {
        if (mMovieData.size() != 0) {
            mMovieData.clear();
        }
        for (int i = 0; i < movieData.size(); i++) {
            mMovieData.add((MovieObject) movieData.get(i));
            Log.v("mMovieData Title",((MovieObject) mMovieData.get(i)).getTitle());
        }
        //Log.v("mMovieData size gmd= ", String.valueOf(mMovieData.size()));
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(MovieObject movieObject);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView myImageView;

        ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            myImageView = (ImageView) itemView.findViewById(R.id.info_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(mMovieData.get(position));
                        }
                    }
                }
            });
        }
        /**
         public void bind(final MovieObject movieObject, final OnItemClickListener listener){
         itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
        if (listener != null) {
        listener.onClick(movieObject);
        }
        }
        });
         }/*/
        //@Override
        //public void onClick(View view) {
        //onItemClick(view,getAdapterPosition());}
    }

}

/**
 * Created by ka171 on 3/22/2018.
 * <p>
 * <p>
 * class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
 * <p>
 * private String[] mData = new String[0];
 * private LayoutInflater mInflater;
 * //private ItemClickListener mClickListener;
 * <p>
 * // data is passed into the constructor
 * MyRecyclerViewAdapter(Context context) {
 * this.mInflater = LayoutInflater.from(context);
 * }
 * <p>
 * // inflates the cell layout from xml when needed
 *
 * @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
 * View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
 * ViewHolder viewHolder = new ViewHolder(view);
 * return viewHolder;
 * }
 * <p>
 * // binds the data to the textview in each cell
 * @Override public void onBindViewHolder(ViewHolder holder, int position) {
 * String animal = mData[position];
 * holder.myTextView.setText(animal);
 * }
 * <p>
 * // total number of cells
 * @Override public int getItemCount() {
 * return mData.length;
 * }
 * <p>
 * <p>
 * // stores and recycles views as they are scrolled off screen
 * public class ViewHolder extends RecyclerView.ViewHolder {
 * TextView myTextView;
 * <p>
 * ViewHolder(View itemView) {
 * super(itemView);
 * myTextView = (TextView) itemView.findViewById(R.id.info_text);
 * //itemView.setOnClickListener(this);
 * }
 * /**
 * <p>
 * }
 * <p>
 * // convenience method for getting data at click position
 * String getItem(int id) {
 * return mData[id];
 * }
 * <p>
 * public void setMovieData(String[] movieData){
 * mData = movieData;
 * notifyDataSetChanged();
 * }
 * }
 */