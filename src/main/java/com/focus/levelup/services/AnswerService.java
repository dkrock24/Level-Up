package com.focus.levelup.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.focus.levelup.model.Answer;

public interface AnswerService extends CrudRepository<Answer, Integer> {

	@Query("SELECT a FROM Answer a WHERE a.status = :status")
	List<Answer> findAllByStatus(@Param("status") int status);
}
