package com.focus.levelup.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.focus.levelup.model.Tests;

public interface TestsService extends CrudRepository<Tests, Integer> {

	@Query("SELECT t FROM Tests t WHERE t.status = :status")
	List<Tests> findAllByStatus(@Param("status") int status);
}
