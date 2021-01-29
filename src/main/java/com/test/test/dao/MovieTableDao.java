package com.test.test.dao;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.test.entities.MovieTable;
import com.test.test.entities.QMovieTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieTableDao {

    @Autowired
    EntityManager entityManager;

    public List<MovieTable> findAll(){
        JPAQueryFactory queryFactory= new JPAQueryFactory(entityManager);
        QMovieTable movieTable = new QMovieTable("movieTable");
        List<MovieTable> listMovies= queryFactory.selectFrom(movieTable).fetch();
        return listMovies;
    }

    public List<MovieTable> findByTitle(String title, String ordering){
        JPAQueryFactory queryFactory= new JPAQueryFactory(entityManager);
        QMovieTable movieTable = new QMovieTable("movieTable");
        List<MovieTable> listMovies = new ArrayList<>();
        if(ordering != null && ordering.equals("desc")){
            listMovies = queryFactory.selectFrom(movieTable).where(movieTable.title.like("%"+title+"%")).orderBy(movieTable.title.desc()).fetch();
        }else {
            listMovies = queryFactory.selectFrom(movieTable).where(movieTable.title.like("%"+title+"%")).orderBy(movieTable.title.asc()).fetch();
        }
        return listMovies;
    }

}
