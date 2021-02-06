package com.test.test.solrJ;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.test.test.dao.MovieTableDao;
import com.test.test.entities.MovieTable;
import com.test.test.entities.QMovieTable;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

@Component
public class SolrJConnection {
    private static final String URL_STRING = "http://localhost:8983/solr/movietable";
    private static final SolrClient solrClient = getSolrClient();

    @Autowired
    MovieTableDao movieTableDao;

    private static SolrClient getSolrClient() {
        return new HttpSolrClient.Builder(URL_STRING).withConnectionTimeout(5000).withSocketTimeout(3000).build();
    }

    public JPAQueryFactory jpaQueryFactory(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("movietable");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return new JPAQueryFactory(entityManager);
    }

    public SolrJConnection(){
        try {
            List<MovieTable> movieTableList = jpaQueryFactory().selectFrom(QMovieTable.movieTable).fetch();
            MovieTable movieTable = new MovieTable();
//            movieTable.setId(123);
//            movieTable.setTitle("Rane");
//            List<MovieTable> movieTableList = movieTableDao.findAll();
            solrClient.addBeans(movieTableList);
//            solrClient.addBean(movieTable);
            solrClient.commit();
          System.out.println("Laza");
        } catch (SolrServerException | IOException e) {
            System.err.printf("\nFailed to indexing movies: %s", e.getMessage());
        }
    }
}
