package com.focus.levelup.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@NamedQuery(name="Roles.findAll", query="SELECT r FROM Roles r")
public class Roles implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_role")
	private int idRole;

	private String role;

	//bi-directional many-to-one association to Users
	@OneToMany(mappedBy="role")
	private List<Users> users;

	public Roles() {
	}

	public int getIdRole() {
		return this.idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Users> getUsers() {
		return this.users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public Users addUser(Users user) {
		getUsers().add(user);
		user.setRole(this);

		return user;
	}

	public Users removeUser(Users user) {
		getUsers().remove(user);
		user.setRole(null);

		return user;
	}

}