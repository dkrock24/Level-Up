package com.focus.levelup.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.focus.levelup.model.ProgrammingLanguage;

public interface ProgrammingLanguageService extends CrudRepository<ProgrammingLanguage, Integer> {

	@Query("SELECT p FROM ProgrammingLanguage p WHERE p.status = :status")
	List<ProgrammingLanguage> findAllByStatus(@Param("status") int status);
}
