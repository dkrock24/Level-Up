package com.focus.levelup.services;

import org.springframework.data.repository.CrudRepository;

import com.focus.levelup.model.Quizzes;

public interface QuizzesServices extends CrudRepository<Quizzes, Integer> {

}
