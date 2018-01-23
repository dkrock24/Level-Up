package com.focus.levelup.services;

import org.springframework.data.repository.CrudRepository;

import com.focus.levelup.model.TestResults;

public interface TestResultsService extends CrudRepository<TestResults, Integer> {

}
