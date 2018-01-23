package com.focus.levelup.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the question_types database table.
 * 
 */
@Entity
@Table(name="question_types")
@NamedQuery(name="QuestionTypes.findAll", query="SELECT q FROM QuestionTypes q")
public class QuestionTypes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_type")
	private int idType;

	@Column(name="friendly_name")
	private String friendlyName;

	private int status;

	private String type;

	//bi-directional many-to-one association to Questions
	@OneToMany(mappedBy="questionType")
	private List<Questions> questions;

	public QuestionTypes() {
	}

	public int getIdType() {
		return this.idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public String getFriendlyName() {
		return this.friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Questions> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}

	public Questions addQuestion(Questions question) {
		getQuestions().add(question);
		question.setQuestionType(this);

		return question;
	}

	public Questions removeQuestion(Questions question) {
		getQuestions().remove(question);
		question.setQuestionType(null);

		return question;
	}

}