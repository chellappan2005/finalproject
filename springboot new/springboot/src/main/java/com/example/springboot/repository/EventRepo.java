package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.springboot.model.Event;

public interface EventRepo extends JpaRepository<Event, Integer> {
    @Query("SELECT e FROM Event e WHERE e.fname = :fname")
    Event findEventByFirstName(@Param("fname") String fname);
    
}
