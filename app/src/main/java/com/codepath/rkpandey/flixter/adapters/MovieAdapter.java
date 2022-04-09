package com.codepath.rkpandey.flixter.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.rkpandey.flixter.R;
import com.codepath.rkpandey.flixter.models.Movie;

import java.util.List;



//base recycler view adapaer are abstract class, needs to implement methods
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    //context, where adapter are constructed from
    Context context;
    List<Movie> movies;
    //pass in through constructor
    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    // usually involve inflating a layout from xml and returning the holder
    // expensive cuz inflate a view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter","onCreateViewHolder");
        View movieView=LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(movieView);
    }

    //Involves populating data into the item through holder
    //cheap
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter","onBindViewHolder"+position);
        // get the movie at the passed position
        Movie movie=movies.get(position);
        //bind the movie data into viewHolder
        holder.bind(movie);
    }
    //returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //viewHolder is the representation of row in recyclerview
        //bind data for each vewi in view holder
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvOverview=itemView.findViewById(R.id.tvOverview);
            ivPoster=itemView.findViewById(R.id.ivPoster);
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            //render images
            Glide.with(context).load(movie.getPosterPath()).into(ivPoster);
        }
    }
}
