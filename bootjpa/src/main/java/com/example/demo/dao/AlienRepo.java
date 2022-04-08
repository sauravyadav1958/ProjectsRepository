package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.example.demo.model.Alien;

// CrudRepository : Interface for generic CRUD operations on a repository for a specific type.
// Here repository/class is Alien and Integer is the type of primary key
public interface AlienRepo extends JpaRepository<Alien, Integer> {
	
	 List<Alien> findByAname(String aname);
	 List<Alien> findByAidGreaterThan(int aid);
	 
	 @Query("from Alien where aname=?1 order by aid")
	 List<Alien> findByAnameSortedByAid(String aname);
	
}
