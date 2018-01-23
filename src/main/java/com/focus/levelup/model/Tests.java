package com.focus.levelup.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tests database table.
 * 
 */
@Entity
@NamedQuery(name="Tests.findAll", query="SELECT t FROM Tests t")
public class Tests implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_test")
	private int idTest;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="assigned_dt")
	private Date assignedDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_dt")
	private Date createdDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="finished_dt")
	private Date finishedDt;

	private int status;

	//bi-directional many-to-one association to TestResults
	@OneToMany(mappedBy="test")
	private List<TestResults> testResults;

	//bi-directional many-to-one association to Quizzes
	@ManyToOne
	@JoinColumn(name="id_quiz")
	private Quizzes quizze;

	//bi-directional many-to-one association to Users
	@ManyToOne
	@JoinColumn(name="id_user")
	private Users user;

	public Tests() {
	}

	public int getIdTest() {
		return this.idTest;
	}

	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}

	public Date getAssignedDt() {
		return this.assignedDt;
	}

	public void setAssignedDt(Date assignedDt) {
		this.assignedDt = assignedDt;
	}

	public Date getCreatedDt() {
		return this.createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	public Date getFinishedDt() {
		return this.finishedDt;
	}

	public void setFinishedDt(Date finishedDt) {
		this.finishedDt = finishedDt;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<TestResults> getTestResults() {
		return this.testResults;
	}

	public void setTestResults(List<TestResults> testResults) {
		this.testResults = testResults;
	}

	public TestResults addTestResult(TestResults testResult) {
		getTestResults().add(testResult);
		testResult.setTest(this);

		return testResult;
	}

	public TestResults removeTestResult(TestResults testResult) {
		getTestResults().remove(testResult);
		testResult.setTest(null);

		return testResult;
	}

	public Quizzes getQuizze() {
		return this.quizze;
	}

	public void setQuizze(Quizzes quizze) {
		this.quizze = quizze;
	}

	public Users getUser() {
		return this.user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}