package com.test.test.repository;

import java.util.List;

import com.test.test.entities.MovieTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieTableRepository extends JpaRepository<MovieTable, Integer>{

    public List<MovieTable> findAll();

    public MovieTable findByTitle(String movieName);


//	@Query("From Movie where movieName LIKE %:movieName%")
//	public Movie nadjiPoImenuSamoSlovo(String movieName);

    @Query("From MovieTable where title LIKE %:movieName%")
    public List<MovieTable> nadjiPoImenuSamoSlovo(String movieName);
}

