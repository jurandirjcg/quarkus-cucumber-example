package com.jcgon.quarkus.cucumber.ws.service.rest.v1.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jcgon.quarkus.cucumber.entity.Movie;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import br.com.jgon.canary.ws.rest.util.RequestConverter;
import io.quarkus.runtime.annotations.RegisterForReflection;

@Schema(name = "Movie - Save", description = "receive the movie information to save, apply some validations")
@RegisterForReflection
public class V1MovieRequest extends RequestConverter<Movie> {

    @NotNull
    @Size(max = 20)
    private String name;

    @NotNull
    private Integer duration;

    @NotNull
    @Size(max = 200, min = 20)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}