package com.netex.controllers;

import java.util.Date;
import java.util.List;


import com.netex.entities.Movie;
import com.netex.services.MovieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/rest")
public class MovieController {

    @Autowired
    private MovieServices movieService;


    @PostMapping("/hundredMovies")
    public ResponseEntity<Object> insertHundredMovies(@RequestParam String word){
        movieService.fatchAndSave(word);
        return new ResponseEntity("Succesfully inserted movies", HttpStatus.OK);
    }



    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        List<Movie> ourList = movieService.getAllMovies();
        return ourList;
    }
//
    @GetMapping(value = "/movieTitle")
    public List<Movie> findByTitle(@RequestParam String movieName, @RequestParam(required = false) String ordering) {
        List<Movie> movieList = movieService.findByTitle(movieName,ordering);
        return movieList;
    }

    @GetMapping(value = "/movieTitleDate")
    public List<Movie> findByDate(@RequestParam Long fromDate, @RequestParam Long toDate) {
        Date fromDateD = new Date(fromDate);
        Date toDateD = new Date(toDate);
        List<Movie> movieList = movieService.findByDate(fromDateD, toDateD);
        return movieList;
    }
    @GetMapping(value = "/movieTitleYear")
    public List<Movie> findByYear(@RequestParam Integer fromYear, @RequestParam Integer toYear, @RequestParam(required = false) String ordering) {
        List<Movie> movieList = movieService.findByYear(fromYear, toYear, ordering);
        return movieList;
    }

}

