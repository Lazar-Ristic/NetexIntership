package com.test.test.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.test.entities.MovieTable;
import com.test.test.dao.MovieTableDao;
import com.test.test.dto.SearchMovieDto;
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

    @Autowired
    private MovieTableDao movieTableDao;

    public List<MovieTable> getAllMovies(){
        List<MovieTable> nasaListaFilmova = movieTableDao.findAll();
        return nasaListaFilmova;
    }
//
//    public MovieTable vratiPoIdu(Integer id){
//        Optional filmO = movieRepository.findById(id);
//        return (MovieTable) filmO.get();
//    }
//
    public List<MovieTable> findByTitle(String movieName, String ordering) {
        List<MovieTable> movieList = movieTableDao.findByTitle(movieName, ordering);
        return movieList;
    }

    public List<MovieTable> findByDate(Date fromDate, Date toDate) {
        List<MovieTable> movieList = movieTableDao.findByDate(fromDate, toDate);
        return movieList;
    }
    public List<MovieTable> findByYear(Integer fromYear, Integer toYear, String ordering) {
        List<MovieTable> movieList = movieTableDao.findByYear(fromYear, toYear, ordering);
        return movieList;
    }
//
//    public void dodajFilm(MovieTable film) {
//        movieRepository.save(film);
//    }

    public void fatchAndSave(String word){
        RestTemplate restTemplate = new RestTemplate();
        List<String> titleList = this.fatchMovieTitles(word);
        for (String title:titleList) {
            String response = restTemplate.getForObject("http://www.omdbapi.com/?t="+title+"&page=1&apikey=f2b58184", String.class);
            String newResponse = response.replace("\"Released\":\"N/A\"", "\"Released\":\"01 Jan 1900\"");
            newResponse = newResponse.replace("\"imdbRating\":\"N/A\"", "\"imdbRating\":\"0.0\"");
            MovieTable movie = new MovieTable();
            try {
                movie = objectMapper.readValue(newResponse, MovieTable.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            movieRepository.save(movie);
        }
    }


    public List<String> fatchMovieTitles(String word){
        RestTemplate restTemplate = new RestTemplate();
        List<MovieTable> allResults = new ArrayList<>();
        for (int i = 1; i<11; i++){
            String response = restTemplate.getForObject("http://www.omdbapi.com/?s="+word+"&page="+i+"&apikey=f2b58184", String.class);
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
