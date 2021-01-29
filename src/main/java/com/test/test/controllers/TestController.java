package com.test.test.controllers;

import java.util.List;


import com.test.test.entities.MovieTable;
import com.test.test.services.MovieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/laza")
public class TestController {

    @Autowired
    private MovieServices movieService;


    @GetMapping("/zdravo")
    public String hello() {
        String returnString = "helloWorld";

        return returnString;
    }

    @PostMapping("/hundredMovies")
    public ResponseEntity<Object> insertHundredMovies(@RequestParam String word){
        movieService.fatchAndSave(word);
        return new ResponseEntity("Succesfully inserted movies", HttpStatus.OK);
    }



    @GetMapping("/movies")
    public List<MovieTable> getAllMovies() {
        List<MovieTable> ourList = movieService.getAllMovies();
        return ourList;
    }
//
    @GetMapping(value = "/movieTitle")
    public List<MovieTable> findByTitle(@RequestParam String movieName, @RequestParam(required = false) String ordering) {
        List<MovieTable> movieList = movieService.findByTitle(movieName,ordering);
        return movieList;
    }
//
//    @PostMapping(value = "/movies")
//    public String dodajNoviFilm(@RequestBody MovieTable film) {
//        movieService.dodajFilm(film);
//        return "Uspesno dodat film";
//    }
//
//    //napravi za delete, za edit i za findById
//
//    @GetMapping(value = "/sviFilmovi")
//    public ResponseEntity<Object> nadjiSveFilmove(){
//        List<MovieTable> nasaLista = movieService.getAllMovies();
//        return new ResponseEntity(nasaLista, HttpStatus.OK);
//    }
//
////    @GetMapping(value = "/restTest")
////    public ResponseEntity<Object> restTest(){
////        VezbaRest vezbaRest = new VezbaRest();
////        String rezultat = vezbaRest.ispisi();
////        return new ResponseEntity(rezultat, HttpStatus.OK);
////    }
//
//    @GetMapping(value = "/poIdu/{id}")
//    public ResponseEntity<Object> poIdu(@PathVariable Integer id){
//        MovieTable film = movieService.vratiPoIdu(id);
//        return new ResponseEntity(film, HttpStatus.OK);
//    }

}

