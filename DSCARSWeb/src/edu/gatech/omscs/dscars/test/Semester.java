package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 27, 2015 4:54:21 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Semester generated by hbm2java
 */
public class Semester implements java.io.Serializable {

	private int semesterId;
	private Integer year;
	private Integer term;
	private String termName;
	private Set<RecommendedSection> recommendedSections = new HashSet<RecommendedSection>(
			0);
	private Set<StudentCourseHistory> studentCourseHistories = new HashSet<StudentCourseHistory>(
			0);

	public Semester() {
	}

	public Semester(int semesterId) {
		this.semesterId = semesterId;
	}

	public Semester(int semesterId, Integer year, Integer term,
			String termName, Set<RecommendedSection> recommendedSections,
			Set<StudentCourseHistory> studentCourseHistories) {
		this.semesterId = semesterId;
		this.year = year;
		this.term = term;
		this.termName = termName;
		this.recommendedSections = recommendedSections;
		this.studentCourseHistories = studentCourseHistories;
	}

	public int getSemesterId() {
		return this.semesterId;
	}

	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getTerm() {
		return this.term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public String getTermName() {
		return this.termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public Set<RecommendedSection> getRecommendedSections() {
		return this.recommendedSections;
	}

	public void setRecommendedSections(
			Set<RecommendedSection> recommendedSections) {
		this.recommendedSections = recommendedSections;
	}

	public Set<StudentCourseHistory> getStudentCourseHistories() {
		return this.studentCourseHistories;
	}

	public void setStudentCourseHistories(
			Set<StudentCourseHistory> studentCourseHistories) {
		this.studentCourseHistories = studentCourseHistories;
	}

}
