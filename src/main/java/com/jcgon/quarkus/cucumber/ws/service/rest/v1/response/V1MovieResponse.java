package com.jcgon.quarkus.cucumber.ws.service.rest.v1.response;

import java.time.LocalDateTime;

import com.jcgon.quarkus.cucumber.entity.Movie;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import br.com.jgon.canary.ws.rest.util.ResponseConverter;
import io.quarkus.runtime.annotations.RegisterForReflection;

/**
 * Allow to return all or some attributes
 * In this example the field "duration" was excluded for the return Object
 */
@Schema(name = "Movie", description = "movie data")
@RegisterForReflection
public class V1MovieResponse extends ResponseConverter<Movie> {

    private Integer id;
    private String name;
    private LocalDateTime releaseDate;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}