package com.netex.solrJ;

import com.netex.entities.QMovie;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.netex.repository.MovieRepository;
import com.netex.entities.Movie;
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
    private static final String URL_STRING = "http://localhost:8983/solr/movie";
    private static final SolrClient solrClient = getSolrClient();

    @Autowired
    MovieRepository movieRepository;

    private static SolrClient getSolrClient() {
        return new HttpSolrClient.Builder(URL_STRING).withConnectionTimeout(5000).withSocketTimeout(3000).build();
    }

    public JPAQueryFactory jpaQueryFactory(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("movie");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return new JPAQueryFactory(entityManager);
    }

    public SolrJConnection(){
        try {
            List<Movie> movieList = jpaQueryFactory().selectFrom(QMovie.movie).fetch();
            Movie movie = new Movie();
            solrClient.addBeans(movieList);
            solrClient.commit();
          System.out.println("project");
        } catch (SolrServerException | IOException e) {
            System.err.printf("\nFailed to indexing movies: %s", e.getMessage());
        }
    }
}
