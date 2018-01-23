package com.focus.levelup.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.focus.levelup.model.Users;

public interface UserService extends CrudRepository<Users, Integer> {

	@Query("SELECT u FROM Users u WHERE u.status = :status")
	List<Users> findAllByStatus(@Param("status") int status);
	
}
