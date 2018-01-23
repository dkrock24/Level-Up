package com.focus.levelup.services;

import org.springframework.data.repository.CrudRepository;

import com.focus.levelup.model.Answer;

public interface AnswerService extends CrudRepository<Answer, Integer> {

}
