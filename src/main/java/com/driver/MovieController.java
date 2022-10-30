package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody()Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody()Director director){
        movieService.addDirector(director);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movie")String movieName,
                                               @RequestParam("director")String directorName){
        movieService.addMovieDirectorPair(movieName, directorName);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name")String name){
        return new ResponseEntity<>(movieService.getMovieByName(name), HttpStatus.OK);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name")String name){
        return new ResponseEntity<>(movieService.getDirectorByName(name), HttpStatus.OK);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<Movie>> getMoviesByDirectorName(@PathVariable("director")String directorName){
        return new ResponseEntity<>(movieService.getMoviesByDirectorName(directorName), HttpStatus.OK);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<Movie>> findAllMovies(){
        return new ResponseEntity<>(movieService.findAllMovies(), HttpStatus.OK);
    }

//    @DeleteMapping("/delete-director-by-name")
    @RequestMapping(value = "/delete-director-by-name", params="name", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteDirectorByName(@RequestParam("name")String name){
        movieService.deleteDirectorByName(name);
        return new ResponseEntity(HttpStatus.OK);
    }

//    @DeleteMapping("/delete-director-by-name")
    @RequestMapping(value = "/delete-director-by-name", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity(HttpStatus.OK);
    }
}
