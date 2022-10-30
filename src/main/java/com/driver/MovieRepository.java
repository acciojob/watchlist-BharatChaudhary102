package com.driver;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MovieRepository {

    HashMap<String, Movie> movieList = new HashMap<>();
    HashMap<String, Director> directorList = new HashMap<>();
    HashMap<Director, List<Movie>> movieDirectorPair = new HashMap<>();

    public void addMovie(Movie movie){
        movieList.put(movie.getName(), movie);
    }

    public void addDirector(Director director){
        directorList.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movieName, String directorName){
        Director dirObj = directorList.get(directorName);
        if(movieDirectorPair.containsKey(dirObj)){
            List<Movie> list = movieDirectorPair.get(directorName);
            list.add(movieList.get(movieName));
            movieDirectorPair.put(dirObj,list);
        } else {
            List<Movie> list = new ArrayList<>();
            list.add(movieList.get(movieName));
            movieDirectorPair.put(dirObj, list);
        }
    }

    public Movie getMovieByName(String name){
        return movieList.get(name);
    }

    public Director getDirectorByName(String name){
        return directorList.get(name);
    }

    public List<Movie> getMoviesByDirectorName(String name){
        Director dirObj = directorList.get(name);
        if(movieDirectorPair.containsKey(dirObj)){
            return movieDirectorPair.get(dirObj);
        } else {
            return new ArrayList<Movie>();
        }
    }

    public List<Movie> findAllMovies(){
        List<Movie> ans = new ArrayList<>();
        for(Map.Entry<String, Movie> pair : movieList.entrySet()){
            ans.add(pair.getValue());
        }
        return ans;
    }

    public void deleteDirectorByName(String name){
        Director dirObj = directorList.get(name);
        if(movieDirectorPair.containsKey(dirObj)){
            List<Movie> moviesObj = movieDirectorPair.get(dirObj);
            for(Movie dirMovie : moviesObj){
                movieList.remove(dirMovie.getName());
            }
            movieDirectorPair.remove(dirObj);
        }
        directorList.remove(name);
    }

    public void deleteAllDirectors(){
        for(Map.Entry<String, Director> dirPair : directorList.entrySet()){
            this.deleteDirectorByName(dirPair.getKey());
        }
    }
}
