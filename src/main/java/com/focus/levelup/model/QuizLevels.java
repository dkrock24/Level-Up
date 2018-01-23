package com.focus.levelup.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the quiz_levels database table.
 * 
 */
@Entity
@Table(name="quiz_levels")
@NamedQuery(name="QuizLevels.findAll", query="SELECT q FROM QuizLevels q")
public class QuizLevels implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_level")
	private int idLevel;

	private String level;

	//bi-directional many-to-one association to Quizzes
	@OneToMany(mappedBy="quizLevel")
	private List<Quizzes> quizzes;

	public QuizLevels() {
	}

	public int getIdLevel() {
		return this.idLevel;
	}

	public void setIdLevel(int idLevel) {
		this.idLevel = idLevel;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<Quizzes> getQuizzes() {
		return this.quizzes;
	}

	public void setQuizzes(List<Quizzes> quizzes) {
		this.quizzes = quizzes;
	}

	public Quizzes addQuizze(Quizzes quizze) {
		getQuizzes().add(quizze);
		quizze.setQuizLevel(this);

		return quizze;
	}

	public Quizzes removeQuizze(Quizzes quizze) {
		getQuizzes().remove(quizze);
		quizze.setQuizLevel(null);

		return quizze;
	}

}