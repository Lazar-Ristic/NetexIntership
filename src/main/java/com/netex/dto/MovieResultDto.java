package com.netex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.netex.entities.Movie;

import java.util.List;

public class MovieResultDto {
    @JsonProperty(value = "Search")
    List<Movie> search;
    @JsonProperty(value = "totalResults")
    String totalResults;
    @JsonProperty(value = "Response")
    String response;

    public List<Movie> getSearch() {
        return search;
    }

    public void setSearch(List<Movie> search) {
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
