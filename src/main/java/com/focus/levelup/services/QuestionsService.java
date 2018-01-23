package com.focus.levelup.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.focus.levelup.model.Questions;

public interface QuestionsService extends CrudRepository<Questions, Integer> {
	
	@Query("SELECT q FROM Questions q WHERE q.status = :status")
	List<Questions> findAllByStatus(@Param("status") int status);
}
