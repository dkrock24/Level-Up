package com.focus.levelup.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the questions database table.
 * 
 */
@Entity
@NamedQuery(name="Questions.findAll", query="SELECT q FROM Questions q")
public class Questions implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_questions")
	private int idQuestions;

	private String question;

	//bi-directional many-to-one association to Answer
	@OneToMany(mappedBy="question")
	private List<Answer> answers;

	//bi-directional many-to-one association to QuestionTypes
	@ManyToOne
	@JoinColumn(name="id_type")
	private QuestionTypes questionType;

	//bi-directional many-to-one association to Quizzes
	@ManyToOne
	@JoinColumn(name="id_quiz")
	private Quizzes quizze;

	//bi-directional many-to-one association to TestResults
	@OneToMany(mappedBy="question")
	private List<TestResults> testResults;

	public Questions() {
	}

	public int getIdQuestions() {
		return this.idQuestions;
	}

	public void setIdQuestions(int idQuestions) {
		this.idQuestions = idQuestions;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Answer addAnswer(Answer answer) {
		getAnswers().add(answer);
		answer.setQuestion(this);

		return answer;
	}

	public Answer removeAnswer(Answer answer) {
		getAnswers().remove(answer);
		answer.setQuestion(null);

		return answer;
	}

	public QuestionTypes getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(QuestionTypes questionType) {
		this.questionType = questionType;
	}

	public Quizzes getQuizze() {
		return this.quizze;
	}

	public void setQuizze(Quizzes quizze) {
		this.quizze = quizze;
	}

	public List<TestResults> getTestResults() {
		return this.testResults;
	}

	public void setTestResults(List<TestResults> testResults) {
		this.testResults = testResults;
	}

	public TestResults addTestResult(TestResults testResult) {
		getTestResults().add(testResult);
		testResult.setQuestion(this);

		return testResult;
	}

	public TestResults removeTestResult(TestResults testResult) {
		getTestResults().remove(testResult);
		testResult.setQuestion(null);

		return testResult;
	}

}