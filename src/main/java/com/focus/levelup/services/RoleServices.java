package com.focus.levelup.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.focus.levelup.model.Roles;

public interface RoleServices extends CrudRepository<Roles, Integer>{

	@Query("SELECT r FROM Roles r ORDER BY r.idRole ASC")
	List<Roles> findAllOrderedByIdAsc();

	@Query("SELECT r FROM Roles r ORDER BY r.idRole DESC")
	List<Roles> findAllOrderedByIdDesc();

	@Query("SELECT r FROM Roles r WHERE r.status = :status")
	List<Roles> findAllByStatus(@Param("status") int status);
}
