package com.focus.levelup.services;

import org.springframework.data.repository.CrudRepository;

import com.focus.levelup.model.ProgrammingLanguage;

public interface ProgrammingLanguageService extends CrudRepository<ProgrammingLanguage, Integer> {

}
