package com.focus.levelup.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.focus.levelup.model.Quizzes;

public interface QuizzesServices extends CrudRepository<Quizzes, Integer> {

	@Query("SELECT q FROM Quizzes q WHERE q.status = :status")
	List<Quizzes> findAllByStatus(@Param("status") int status);
}
