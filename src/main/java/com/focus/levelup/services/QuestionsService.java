package com.focus.levelup.services;

import org.springframework.data.repository.CrudRepository;

import com.focus.levelup.model.Questions;

public interface QuestionsService extends CrudRepository<Questions, Integer> {

}
