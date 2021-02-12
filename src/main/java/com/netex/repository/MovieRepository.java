package com.netex.repository;

import com.netex.entities.QMovie;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.netex.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class MovieRepository {

    @Autowired
    EntityManager entityManager;
    @Autowired
    JPAQueryFactory queryFactory;

    public List<Movie> findAll(){
        QMovie movieTable = new QMovie("movie");
        List<Movie> listMovies= queryFactory.selectFrom(movieTable).fetch();
        return listMovies;
    }

    public List<Movie> findByTitle(String title, String ordering){
        QMovie movieTable = new QMovie("movie");
        List<Movie> listMovies = new ArrayList<>();
        if(ordering != null && ordering.equals("desc")){
            listMovies = queryFactory.selectFrom(movieTable).where(movieTable.title.like("%"+title+"%")).orderBy(movieTable.title.desc()).fetch();
        }else {
            listMovies = queryFactory.selectFrom(movieTable).where(movieTable.title.like("%"+title+"%")).orderBy(movieTable.title.asc()).fetch();
        }
        return listMovies;
    }

    public List<Movie> findByDate(Date fromDate, Date toDate){
        QMovie movieTable = new QMovie("movie");
        List<Movie> listMovies1 = new ArrayList<>();
        listMovies1 = queryFactory.selectFrom(movieTable).where(movieTable.released.between(fromDate, toDate)).fetch();
        return listMovies1;
    }

    public List<Movie> findByYear(Integer fromYear, Integer toYear, String ordering){
        QMovie movieTable = new QMovie("movie");
        List<Movie> listMovies1 = new ArrayList<>();
        if(ordering != null && ordering.equals("desc")){
            listMovies1 = queryFactory.selectFrom(movieTable).where(movieTable.released.year().between(fromYear, toYear)).orderBy(movieTable.released.desc()).fetch();
        } else{
            listMovies1 = queryFactory.selectFrom(movieTable).where(movieTable.released.year().between(fromYear, toYear)).orderBy(movieTable.released.asc()).fetch();
        }

        return listMovies1;
    }

    @Transactional
    public void save(Movie movie){
        entityManager.persist(movie);
    }
}
