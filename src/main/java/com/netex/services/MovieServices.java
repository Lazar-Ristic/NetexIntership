package com.netex.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netex.entities.Movie;
import com.netex.repository.MovieRepository;
import com.netex.dto.MovieResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MovieServices {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MovieRepository movieRepository;
    @Value("${api.url}")
    private String apiUrl;
    @Value("${api.key}")
    private String apiKey;

    public List<Movie> getAllMovies(){
        List<Movie> nasaListaFilmova = movieRepository.findAll();
        return nasaListaFilmova;
    }

    public List<Movie> findByTitle(String movieName, String ordering) {
        List<Movie> movieList = movieRepository.findByTitle(movieName, ordering);
        return movieList;
    }

    public List<Movie> findByDate(Date fromDate, Date toDate) {
        List<Movie> movieList = movieRepository.findByDate(fromDate, toDate);
        return movieList;
    }
    public List<Movie> findByYear(Integer fromYear, Integer toYear, String ordering) {
        List<Movie> movieList = movieRepository.findByYear(fromYear, toYear, ordering);
        return movieList;
    }


    public void fatchAndSave(String word){
        RestTemplate restTemplate = new RestTemplate();
        List<String> titleList = this.fatchMovieTitles(word);
        for (String title:titleList) {
            String response = restTemplate.getForObject(apiUrl + "?t=" + title + "&page=1&apikey=" + apiKey, String.class);
            String newResponse = response.replace("\"Released\":\"N/A\"", "\"Released\":\"01 Jan 1900\"");
            newResponse = newResponse.replace("\"imdbRating\":\"N/A\"", "\"imdbRating\":\"0.0\"");
            Movie movie = new Movie();
            try {
                movie = objectMapper.readValue(newResponse, Movie.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            movieRepository.save(movie);
        }
    }


    public List<String> fatchMovieTitles(String word){
        RestTemplate restTemplate = new RestTemplate();
        List<Movie> allResults = new ArrayList<>();
        for (int i = 1; i<11; i++){
            String response = restTemplate.getForObject(apiUrl + "?s=" + word + "&page="+i+"&apikey=" + apiKey, String.class);
            MovieResultDto movieResultDto = new MovieResultDto();
            try {
                movieResultDto = objectMapper.readValue(response, MovieResultDto.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            allResults.addAll(movieResultDto.getSearch());
        }

        return allResults.stream().map(Movie::getTitle).collect(Collectors.toList());
    }

}
