package edu.gatech.omscs.dscars.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="StudentPreference")
public class StudentPreference implements Serializable{
	private static final long serialVersionUID = 1L;
	Long preferenceId;; //gtid
	Student student; //gtid
	Program program;
	String term;
	Integer numberOfCourses;
	Course firstPreference;
	Course secondPreference;
	Course thirdPreference;
	Course fourthPreference;
	
	
	@Id
	@GeneratedValue
	@Column(name="preferenceId")
	public Long getPreferenceId() {
		return preferenceId;
	}
	public void setPreferenceId(Long preferenceId) {
		this.preferenceId = preferenceId;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	@OneToOne(cascade=CascadeType.ALL) 
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	
	@Column(name="term")
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	
	@Column(name="numberOfCourses")
	public Integer getNumberOfCourses() {
		return numberOfCourses;
	}
	public void setNumberOfCourses(Integer numberOfCourses) {
		this.numberOfCourses = numberOfCourses;
	}


	@OneToOne()
	public Course getFirstPreference() {
		return firstPreference;
	}
	public void setFirstPreference(Course firstPreference) {
		this.firstPreference = firstPreference;
	}


	@OneToOne()
	public Course getSecondPreference() {
		return secondPreference;
	}
	public void setSecondPreference(Course secondPreference) {
		this.secondPreference = secondPreference;
	}


	@OneToOne()
	public Course getThirdPreference() {
		return thirdPreference;
	}
	public void setThirdPreference(Course thirdPreference) {
		this.thirdPreference = thirdPreference;
	}
	

	@OneToOne()
	public Course getFourthPreference() {
		return fourthPreference;
	}
	public void setFourthPreference(Course fourthPreference) {
		this.fourthPreference = fourthPreference;
	}

}
