package com.focus.levelup.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the test_results database table.
 * 
 */
@Entity
@Table(name="test_results")
@NamedQuery(name="TestResults.findAll", query="SELECT t FROM TestResults t")
public class TestResults implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_result")
	private int idResult;

	//bi-directional many-to-one association to Answer
	@ManyToOne
	@JoinColumn(name="id_answer")
	private Answer answer;

	//bi-directional many-to-one association to Questions
	@ManyToOne
	@JoinColumn(name="id_question")
	private Questions question;

	//bi-directional many-to-one association to Tests
	@ManyToOne
	@JoinColumn(name="id_test")
	private Tests test;

	public TestResults() {
	}

	public int getIdResult() {
		return this.idResult;
	}

	public void setIdResult(int idResult) {
		this.idResult = idResult;
	}

	public Answer getAnswer() {
		return this.answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Questions getQuestion() {
		return this.question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}

	public Tests getTest() {
		return this.test;
	}

	public void setTest(Tests test) {
		this.test = test;
	}

}