package com.focus.levelup.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.focus.levelup.model.QuestionTypes;

public interface QuestionTypesService extends CrudRepository<QuestionTypes, Integer> {

	@Query("SELECT q FROM QuestionTypes q WHERE q.status = :status")
	List<QuestionTypes> findAllByStatus(@Param("status") int status);
}
