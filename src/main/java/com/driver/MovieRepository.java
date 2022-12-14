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
        Director dirObj = directorList.getOrDefault(directorName, null);
        Movie movieObj = movieList.getOrDefault(movieName, null);
        if(dirObj != null && movieObj!= null){
            if(movieDirectorPair.containsKey(dirObj)){
                List<Movie> list = movieDirectorPair.get(dirObj);
                list.add(movieObj);
                movieDirectorPair.put(dirObj,list);
            } else {
                List<Movie> list = new ArrayList<>();
                list.add(movieObj);
                movieDirectorPair.put(dirObj, list);
            }
        }

    }

    public Movie getMovieByName(String name){
        return movieList.getOrDefault(name, new Movie());
    }

    public Director getDirectorByName(String name){
        return directorList.getOrDefault(name, new Director());
    }

    public List<String> getMoviesByDirectorName(String name){
        Director dirObj = directorList.getOrDefault(name, null);
        List<String> ans = new ArrayList<>();
        if(dirObj != null && movieDirectorPair.containsKey(dirObj)){
            List<Movie> allMovies = movieDirectorPair.get(dirObj);
            for(Movie curr : allMovies){
                ans.add(curr.getName());
            }
        }
        return ans;
    }

    public List<String> findAllMovies(){
        List<String> ans = new ArrayList<>();
        for(Map.Entry<String, Movie> pair : movieList.entrySet()){
            ans.add(pair.getKey());
        }
        return ans;
    }

    public void deleteDirectorByName(String name){
        Director dirObj = directorList.getOrDefault(name, null);
        if(dirObj != null && movieDirectorPair.containsKey(dirObj)){
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
