package com.jcgon.quarkus.cucumber.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "movie")
public class Movie implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "MOVIE_ID_GENERATOR", sequenceName = "movie_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOVIE_ID_GENERATOR")
    @Column
    private Integer id;

    // Default message
    @NotNull
    @Size(max = 20)
    @Column(length = 20)
    private String name;

    // Custom message - check ValidationMessages.properties file
    @NotNull(message = "{movie.releaseDate.notNull}")
    @Column(nullable = false)
    private LocalDate releaseDate;
    
    @Column(nullable = false)
    private LocalDateTime insertDate;

    @NotNull
    @Column(nullable = false)
    private Integer duration;

    @NotNull
    @Size(max = 200, min = 20)
    @Column(length = 200, nullable = false)
    private String description;

    public Movie() {

    }

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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((releaseDate == null) ? 0 : releaseDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movie other = (Movie) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (releaseDate == null) {
            if (other.releaseDate != null)
                return false;
        } else if (!releaseDate.equals(other.releaseDate))
            return false;
        return true;
    }

}