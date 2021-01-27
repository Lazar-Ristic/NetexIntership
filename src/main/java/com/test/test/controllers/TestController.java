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
        movieService.fatchAndSave();
        return returnString;
    }

    @GetMapping("/movies")
    public List<MovieTable> getAllMovies() {
        List<MovieTable> nasaLista = movieService.getAllMovies();
        return nasaLista;
    }

    @GetMapping(value = "/jedanFilm")
    public List<MovieTable> nadjiPoImenu(@RequestParam String movieName) {
        List<MovieTable> film = movieService.nadjiPoImenu(movieName);
        return film;
    }

    @PostMapping(value = "/movies")
    public String dodajNoviFilm(@RequestBody MovieTable film) {
        movieService.dodajFilm(film);
        return "Uspesno dodat film";
    }

    //napravi za delete, za edit i za findById

    @GetMapping(value = "/sviFilmovi")
    public ResponseEntity<Object> nadjiSveFilmove(){
        List<MovieTable> nasaLista = movieService.getAllMovies();
        return new ResponseEntity(nasaLista, HttpStatus.OK);
    }

//    @GetMapping(value = "/restTest")
//    public ResponseEntity<Object> restTest(){
//        VezbaRest vezbaRest = new VezbaRest();
//        String rezultat = vezbaRest.ispisi();
//        return new ResponseEntity(rezultat, HttpStatus.OK);
//    }

    @GetMapping(value = "/poIdu/{id}")
    public ResponseEntity<Object> poIdu(@PathVariable Integer id){
        MovieTable film = movieService.vratiPoIdu(id);
        return new ResponseEntity(film, HttpStatus.OK);
    }

}

