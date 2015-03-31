package edu.gatech.omscs.dscars.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Student")
public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	Integer studentId; //gtid
	Program program;
	Double earnedCredit;
	
	public Student() {
		
	}
	
	@Id
	@Column(name="studentId")
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	@ManyToOne(cascade=CascadeType.ALL) 
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	
	@Column(name="earnedCredit")
	public Double getEarnedCredit() {
		return earnedCredit;
	}
	public void setEarnedCredit(Double earnedCredit) {
		this.earnedCredit = earnedCredit;
	}
}
