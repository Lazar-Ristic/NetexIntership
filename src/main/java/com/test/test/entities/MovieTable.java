package com.test.test.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name= "movietable")
public class MovieTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty(value = "Title")
    private String title;
    @JsonFormat(pattern = "dd MMM yyyy")
    @JsonProperty(value = "Released")
    private Date released;
    @JsonProperty(value = "Plot")
    private String plot;
    @JsonProperty(value = "Rated")
    private String rated;
    @JsonProperty(value = "Genre")
    private String genre;
    @JsonProperty(value = "Actors")
    private String actors;
    @JsonProperty(value = "Writer")
    private String writer;
    @JsonProperty(value = "Director")
    private String director;
    @JsonProperty(value = "imdbRating")
    private Float imdbrating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleased() {
        return released;
    }

    public void setReleased(Date released) {
        this.released = released;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Float getImdbrating() {
        return imdbrating;
    }

    public void setImdbrating(Float imdbrating) {
        this.imdbrating = imdbrating;
    }
}
