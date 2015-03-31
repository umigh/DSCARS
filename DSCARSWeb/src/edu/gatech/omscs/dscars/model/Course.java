package edu.gatech.omscs.dscars.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Course")
public class Course implements Serializable{
	private static final long serialVersionUID = 1L;
	Long courseId;
	Integer crn;
	String subject;
	String course;
	String section;
	String campus;
	String bas;
	Double credit;
	String title;
	public Course() {
		
	}
	public Course(Long courseId,Integer crn, String subject, String course, String section,String campus, String bas, Double credit, String title) {
		super();
		this.courseId=courseId;
		this.crn = crn;
		this.subject = subject;
		this.course = course;
		this.section = section;
		this.campus = campus;
		this.bas = bas;
		this.credit = credit;
		this.title = title;
	}
	
	@Id
	@GeneratedValue
	@Column(name="courseId")
	public Long getCourseId() {
		return courseId;
	}


	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}


	@Column(name="crn")
	public Integer getCrn() {
		return crn;
	}
	public void setCrn(Integer crn) {
		this.crn = crn;
	}
	
	@Column(name="subject")
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Column(name="course")
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
	@Column(name="section")
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	
	@Column(name="campus")
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	
	@Column(name="bas")
	public String getBas() {
		return bas;
	}
	public void setBas(String bas) {
		this.bas = bas;
	}
	
	@Column(name="credit")
	public Double getCredit() {
		return credit;
	}
	
	public void setCredit(Double credit) {
		this.credit = credit;
	}
	
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
