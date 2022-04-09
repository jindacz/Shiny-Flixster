package com.codepath.rkpandey.flixter.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.rkpandey.flixter.DetailActivity;
import com.codepath.rkpandey.flixter.R;
import com.codepath.rkpandey.flixter.models.Movie;

import org.parceler.Parcels;

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
        //viewHolder is the reference of each row in recyclerview
        //bind data for each view in view holder
        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvOverview=itemView.findViewById(R.id.tvOverview);
            ivPoster=itemView.findViewById(R.id.ivPoster);
            container=itemView.findViewById(R.id.container);
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());

            String imageUrl;
            //if phone is in landscape
            if(context.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
                imageUrl=movie.getBackdropPath();
            }else{
                imageUrl=movie.getPosterPath();
            }
            //then imageUrl=back drop image
            //else imageUrl=poster image

            //render images
            Glide.with(context).load(imageUrl).into(ivPoster);

            //1.register click listener on the whole row

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //2.navigate to a new activity on tap
                    //Toast.makeText(context,movie.getTitle(),Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(context, DetailActivity.class);

                    //commend+p see what can you pass as parameter
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });
        }
    }
}
