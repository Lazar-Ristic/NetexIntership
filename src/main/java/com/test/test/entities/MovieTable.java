package com.test.test.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.solr.client.solrj.beans.Field;

import javax.persistence.*;
import java.util.Date;

//@SolrDocument(collection = "movietable")
@Entity

@Table(name = "movietable")
public class MovieTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Indexed(name = "id", type = "integer")
    @Field
    private Integer id;
    @JsonProperty(value = "Title")
//    @Indexed(name = "title", type = "string")
    @Field
    private String title;
    @JsonFormat(pattern = "dd MMM yyyy")
    @JsonProperty(value = "Released")
//    @Indexed(name = "released", type = "date")
    @Field
    private Date released;
    @JsonProperty(value = "Plot")
//    @Indexed(name = "plot", type = "string")
    @Field
    private String plot;
    @JsonProperty(value = "Rated")
//    @Indexed(name = "rated", type = "string")
    @Field
    private String rated;
    @JsonProperty(value = "Genre")
//    @Indexed(name = "genre", type = "string")
    @Field
    private String genre;
    @JsonProperty(value = "Actors")
//    @Indexed(name = "actors", type = "string")
    @Field
    private String actors;
    @JsonProperty(value = "Writer")
//    @Indexed(name = "writer", type = "string")
    @Field
    private String writer;
    @JsonProperty(value = "Director")
//    @Indexed(name = "director", type = "string")
    @Field
    private String director;
    @JsonProperty(value = "imdbRating")
//    @Indexed(name = "imdbrating", type = "float")
    @Field
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
