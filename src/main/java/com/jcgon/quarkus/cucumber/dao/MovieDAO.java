package com.jcgon.quarkus.cucumber.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.jcgon.quarkus.cucumber.entity.Movie;
import com.jcgon.quarkus.cucumber.entity.Movie_;

import br.com.jgon.canary.persistence.GenericDAO;
import br.com.jgon.canary.persistence.filter.CriteriaFilterMetamodel;
import br.com.jgon.canary.persistence.filter.CriteriaWhere.MatchMode;
import br.com.jgon.canary.persistence.filter.CriteriaWhere.RegexWhere;
import br.com.jgon.canary.util.Page;

public class MovieDAO extends GenericDAO<Movie, Integer> {
    
    private EntityManager entityManagerMaster;

    /**
     *
     * @param entityManagerMaster {@link EntityManager}
     */
    public MovieDAO(final EntityManager entityManagerMaster) {
        this.entityManagerMaster = entityManagerMaster;
    }

    /**
     *
     */
    @Override
    protected EntityManager getEntityManager() {
        return entityManagerMaster;
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

        CriteriaFilterMetamodel<Movie> cf = getCriteriaFilterMetamodel()
            .addSelect(fields)
            .addWhereEqual(Movie_.id, id)
            .addWhereILike(Movie_.name, name, MatchMode.START)
            .addWhereRegex(Movie_.releaseDate, releaseDate, RegexWhere.build(
                RegexWhere.LESS_THAN,
                RegexWhere.LESS_THAN_OR_EQUAL_TO,
                RegexWhere.EQUAL,
                RegexWhere.GREATER_THAN_OR_EQUAL_TO,
                RegexWhere.GREATER_THAN,
                RegexWhere.NOT_EQUAL,
                RegexWhere.BETWEEN
            ), RegexWhere.EQUAL);

        return getResultPaginate(cf, page, limit);
    }
}