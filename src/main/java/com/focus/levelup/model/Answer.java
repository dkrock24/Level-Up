package com.focus.levelup.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the answers database table.
 * 
 */
@Entity
@Table(name="answers")
@NamedQuery(name="Answer.findAll", query="SELECT a FROM Answer a")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_answer")
	private int idAnswer;

	@Lob
	private String answer;

	private int score;

	//bi-directional many-to-one association to Questions
	@ManyToOne
	@JoinColumn(name="id_question")
	private Questions question;

	//bi-directional many-to-one association to TestResults
	@OneToMany(mappedBy="answer")
	private List<TestResults> testResults;

	public Answer() {
	}

	public int getIdAnswer() {
		return this.idAnswer;
	}

	public void setIdAnswer(int idAnswer) {
		this.idAnswer = idAnswer;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Questions getQuestion() {
		return this.question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}

	public List<TestResults> getTestResults() {
		return this.testResults;
	}

	public void setTestResults(List<TestResults> testResults) {
		this.testResults = testResults;
	}

	public TestResults addTestResult(TestResults testResult) {
		getTestResults().add(testResult);
		testResult.setAnswer(this);

		return testResult;
	}

	public TestResults removeTestResult(TestResults testResult) {
		getTestResults().remove(testResult);
		testResult.setAnswer(null);

		return testResult;
	}

}