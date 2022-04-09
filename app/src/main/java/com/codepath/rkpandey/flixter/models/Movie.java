package com.codepath.rkpandey.flixter.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//create movie models
public class Movie {
    String posterPath;
    String title;
    String overview;
    String backdropPath;

    //take in a json, read in the fields we care about
    public Movie(JSONObject jsonOBject) throws JSONException {
        backdropPath=jsonOBject.getString("backdrop_path");
        posterPath=jsonOBject.getString("poster_path");
        title=jsonOBject.getString("title");
        overview=jsonOBject.getString("overview");
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        //iterate through JSON array, construct movie for each element in JSON array
        List<Movie> movies=new ArrayList<>();
        for(int i=0;i<movieJsonArray.length();i++){
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }
    //get data out of the object
    //fetch all available size, add relative path
    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
