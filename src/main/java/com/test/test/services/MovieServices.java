package com.test.test.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.test.dto.SearchMovieDto;
import com.test.test.entities.MovieTable;
import com.test.test.repository.MovieTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MovieServices {

    @Autowired
    private MovieTableRepository movieRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public List<MovieTable> getAllMovies(){
        List<MovieTable> nasaListaFilmova = movieRepository.findAll();
        return nasaListaFilmova;
    }

    public MovieTable vratiPoIdu(Integer id){
        Optional filmO = movieRepository.findById(id);
        return (MovieTable) filmO.get();
    }

    public List<MovieTable> nadjiPoImenu(String movieName) {
        List<MovieTable> film = movieRepository.nadjiPoImenuSamoSlovo(movieName);
        return film;
    }

    public void dodajFilm(MovieTable film) {
        movieRepository.save(film);
    }

    public void fatchAndSave(){
        RestTemplate restTemplate = new RestTemplate();
        List<String> titleList = this.fatchMovieTitles();
        for (String title:titleList) {
            String response = restTemplate.getForObject("http://www.omdbapi.com/?t="+title+"&page=1&apikey=f2b58184", String.class);
            MovieTable movie = new MovieTable();
            try {
                movie = objectMapper.readValue(response, MovieTable.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            movieRepository.save(movie);
        }
    }

    public List<String> fatchMovieTitles(){
        RestTemplate restTemplate = new RestTemplate();
        List<MovieTable> allResults = new ArrayList<>();
        for (int i = 1; i<11; i++){
            String response = restTemplate.getForObject("http://www.omdbapi.com/?s=Sta&page="+i+"&apikey=f2b58184", String.class);
            SearchMovieDto searchMovieDto = new SearchMovieDto();
            try {
                searchMovieDto = objectMapper.readValue(response, SearchMovieDto.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            allResults.addAll(searchMovieDto.getSearch());
        }

        return allResults.stream().map(MovieTable::getTitle).collect(Collectors.toList());
    }

}
