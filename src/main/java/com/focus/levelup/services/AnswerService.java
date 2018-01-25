package com.focus.levelup.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.focus.levelup.model.Answer;

public interface AnswerService extends CrudRepository<Answer, Integer> {

	@Query("SELECT a FROM Answer a ORDER BY a.idAnswer ASC")
	List<Answer> findAllOrderedByIdAsc();

	@Query("SELECT a FROM Answer a ORDER BY a.idAnswer DESC")
	List<Answer> findAllOrderedByIdDesc();

	@Query("SELECT a FROM Answer a WHERE a.status = :status")
	List<Answer> findAllByStatus(@Param("status") int status);

	//Get all active answers of a question
	@Query("SELECT a FROM Answer a WHERE a.status = 1 AND a.question.idQuestions = :questionId")
	List<Answer> findAllActiveByQuestionId(@Param("questionId") int questionId);
}
