package com.focus.levelup.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@NamedQuery(name="Users.findAll", query="SELECT u FROM Users u")
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_user")
	private int idUser;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String password;

	private int status;

	//bi-directional many-to-one association to Quizzes
	@OneToMany(mappedBy="user")
	private List<Quizzes> quizzes;

	//bi-directional many-to-one association to Tests
	@OneToMany(mappedBy="user")
	private List<Tests> tests;

	//bi-directional many-to-one association to Roles
	@ManyToOne
	@JoinColumn(name="id_role")
	private Roles role;

	public Users() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Quizzes> getQuizzes() {
		return this.quizzes;
	}

	public void setQuizzes(List<Quizzes> quizzes) {
		this.quizzes = quizzes;
	}

	public Quizzes addQuizze(Quizzes quizze) {
		getQuizzes().add(quizze);
		quizze.setUser(this);

		return quizze;
	}

	public Quizzes removeQuizze(Quizzes quizze) {
		getQuizzes().remove(quizze);
		quizze.setUser(null);

		return quizze;
	}

	public List<Tests> getTests() {
		return this.tests;
	}

	public void setTests(List<Tests> tests) {
		this.tests = tests;
	}

	public Tests addTest(Tests test) {
		getTests().add(test);
		test.setUser(this);

		return test;
	}

	public Tests removeTest(Tests test) {
		getTests().remove(test);
		test.setUser(null);

		return test;
	}

	public Roles getRole() {
		return this.role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

}