package com.rungroup.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rungroup.web.models.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	
}
