package com.focus.levelup.services;

import org.springframework.data.repository.CrudRepository;

import com.focus.levelup.model.Users;

public interface UserService extends CrudRepository<Users, Integer> {

}
