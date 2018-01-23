package com.focus.levelup.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.focus.levelup.model.QuizLevels;

public interface QuizLevelsService extends CrudRepository<QuizLevels, Integer> {

	@Query("SELECT q FROM QuizLevels q WHERE q.status = :status")
	List<QuizLevels> findAllByStatus(@Param("status") int status);
}
