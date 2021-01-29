package com.test.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.test.entities.MovieTable;

import java.util.List;

public class SearchMovieDto {
    @JsonProperty(value = "Search")
    List<MovieTable> search;
    @JsonProperty(value = "totalResults")
    String totalResults;
    @JsonProperty(value = "Response")
    String response;

    public List<MovieTable> getSearch() {
        return search;
    }

    public void setSearch(List<MovieTable> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
