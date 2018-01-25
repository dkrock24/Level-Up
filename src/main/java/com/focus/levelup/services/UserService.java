package com.focus.levelup.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.focus.levelup.model.Users;

public interface UserService extends CrudRepository<Users, Integer> {

	// Get all users ascending ordered
	@Query("SELECT u FROM Users u ORDER BY t.idUser ASC")
	List<Users> findAllOrderedByIdAsc();

	// Get all users descending ordered
	@Query("SELECT u FROM Users u ORDER BY t.idUser DESC")
	List<Users> findAllOrderedByIdDesc();

	// Get all users by status passed
	@Query("SELECT u FROM Users u WHERE u.status = :status")
	List<Users> findAllByStatus(@Param("status") int status);

	// Get all users by name or last name
	@Query("SELECT u FROM Users u WHERE u.firstName LIKE CONCAT('%',:name,'%') OR u.lastName LIKE CONCAT('%',:name,'%')")
	List<Users> findAllByName(@Param("name") String name);

	// Get all users by email
	@Query("SELECT u FROM Users u WHERE u.email LIKE CONCAT('%',:email,'%')")
	List<Users> findAllByEmail(@Param("email") String email);

	//Get all users by RoleId
	@Query("SELECT u FROM Users u WHERE u.role.idRole = :roleId")
	List<Users> findAllByRoleId(@Param("roleId") int roleId);
}
