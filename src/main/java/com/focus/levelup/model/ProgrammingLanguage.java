package com.focus.levelup.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the programming_languages database table.
 * 
 */
@Entity
@Table(name="programming_languages")
@NamedQuery(name="ProgrammingLanguage.findAll", query="SELECT p FROM ProgrammingLanguage p")
public class ProgrammingLanguage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_language")
	private int idLanguage;

	private String language;

	//bi-directional many-to-one association to Quizzes
	@OneToMany(mappedBy="programmingLanguage")
	private List<Quizzes> quizzes;

	public ProgrammingLanguage() {
	}

	public int getIdLanguage() {
		return this.idLanguage;
	}

	public void setIdLanguage(int idLanguage) {
		this.idLanguage = idLanguage;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<Quizzes> getQuizzes() {
		return this.quizzes;
	}

	public void setQuizzes(List<Quizzes> quizzes) {
		this.quizzes = quizzes;
	}

	public Quizzes addQuizze(Quizzes quizze) {
		getQuizzes().add(quizze);
		quizze.setProgrammingLanguage(this);

		return quizze;
	}

	public Quizzes removeQuizze(Quizzes quizze) {
		getQuizzes().remove(quizze);
		quizze.setProgrammingLanguage(null);

		return quizze;
	}

}