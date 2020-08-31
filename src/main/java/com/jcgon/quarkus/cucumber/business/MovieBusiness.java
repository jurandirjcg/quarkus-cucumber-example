package com.jcgon.quarkus.cucumber.business;

import java.time.LocalDateTime;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.jcgon.quarkus.cucumber.dao.MovieDAO;
import com.jcgon.quarkus.cucumber.entity.Movie;
import com.jcgon.quarkus.cucumber.entity.Movie_;
import com.jcgon.quarkus.cucumber.util.Utils;

import br.com.jgon.canary.util.Page;

@ApplicationScoped
public class MovieBusiness {

    @Inject
    MovieDAO movieDAO;

    /**
     * 
     * @param movieRequest
     * @return
     */
    public Movie save(final Movie movieRequest){
        movieRequest.setInsertDate(LocalDateTime.now());

        return movieDAO.save(movieRequest);
    }
    
    /**
     * 
     * @param id
     * @param name
     * @param releaseDate - this attribute allows to use Regex to apply advanced datetime filters, see below:
     * 1. search for movies that release date is less than 2020-01-01: "< 2020-01-01"
     * 2. search for movies that release date is bwteween 2020-01-01 and 2020-08-20:  "2020-01-01 btwn 2020-08-20"
     * 3. search for movies that release date is greater than or equal to 2020-07-31: ">= 2020-07-31"
     * 4. search for movies that release date is not equal to 2020-02-25: "!= 2020-02-25"
     * 5. search for movies that release date is equal to 2020-05-25: "= 2020-05-25"
     * 
     * if no one regex is informed the application will use equal (=) as default
     * @param fields
     * @param sort
     * @param page
     * @param limit
     * @return
     */
    public Page<Movie> paginate(
        final Integer id,
        final String name,
        final String releaseDate,
        final List<String> fields,
        final List<String> sort,
        final Integer page,
        final Integer limit){

       return movieDAO.paginate(id, name, releaseDate, Utils.checkFields(fields, Movie_.ID, Movie_.NAME), sort, page, Utils.checkLimit(limit)); 
    }
}