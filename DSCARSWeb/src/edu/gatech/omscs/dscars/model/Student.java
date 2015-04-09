package edu.gatech.omscs.dscars.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Student", catalog = "DSCARS")
public class Student implements java.io.Serializable {

	private int studentId;
	private int maxCoursesPerTerm;
	private Contact contact;

	public Student() {
	}

	public Student(int studentId, int maxCoursesPerTerm) {
		this.studentId = studentId;
		this.maxCoursesPerTerm = maxCoursesPerTerm;
	}

	@Id
	@Column(name = "StudentID", unique = true, nullable = false)
	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@Column(name = "MaxCoursesPerTerm", columnDefinition = "TINYINT", nullable = false)
	public int getMaxCoursesPerTerm() {
		return maxCoursesPerTerm;
	}

	public void setMaxCoursesPerTerm(int maxCoursesPerTerm) {
		this.maxCoursesPerTerm = maxCoursesPerTerm;
	}

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "studentId", insertable=false, updatable=false)
	public Contact getContact() {
		return contact;
	}


	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
