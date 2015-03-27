package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 27, 2015 4:54:21 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * RecommendedSection generated by hbm2java
 */
public class RecommendedSection implements java.io.Serializable {

	private int recSectionId;
	private Instructor instructor;
	private Semester semester;
	private RecommendedSchedule recommendedSchedule;
	private Course course;
	private Integer classSize;
	private Integer sectionDemand;
	private Set<RecommendedStudents> recommendedStudentses = new HashSet<RecommendedStudents>(
			0);
	private Set<RecommendedTas> recommendedTases = new HashSet<RecommendedTas>(
			0);

	public RecommendedSection() {
	}

	public RecommendedSection(int recSectionId, Instructor instructor,
			Semester semester, RecommendedSchedule recommendedSchedule,
			Course course) {
		this.recSectionId = recSectionId;
		this.instructor = instructor;
		this.semester = semester;
		this.recommendedSchedule = recommendedSchedule;
		this.course = course;
	}

	public RecommendedSection(int recSectionId, Instructor instructor,
			Semester semester, RecommendedSchedule recommendedSchedule,
			Course course, Integer classSize, Integer sectionDemand,
			Set<RecommendedStudents> recommendedStudentses,
			Set<RecommendedTas> recommendedTases) {
		this.recSectionId = recSectionId;
		this.instructor = instructor;
		this.semester = semester;
		this.recommendedSchedule = recommendedSchedule;
		this.course = course;
		this.classSize = classSize;
		this.sectionDemand = sectionDemand;
		this.recommendedStudentses = recommendedStudentses;
		this.recommendedTases = recommendedTases;
	}

	public int getRecSectionId() {
		return this.recSectionId;
	}

	public void setRecSectionId(int recSectionId) {
		this.recSectionId = recSectionId;
	}

	public Instructor getInstructor() {
		return this.instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Semester getSemester() {
		return this.semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public RecommendedSchedule getRecommendedSchedule() {
		return this.recommendedSchedule;
	}

	public void setRecommendedSchedule(RecommendedSchedule recommendedSchedule) {
		this.recommendedSchedule = recommendedSchedule;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getClassSize() {
		return this.classSize;
	}

	public void setClassSize(Integer classSize) {
		this.classSize = classSize;
	}

	public Integer getSectionDemand() {
		return this.sectionDemand;
	}

	public void setSectionDemand(Integer sectionDemand) {
		this.sectionDemand = sectionDemand;
	}

	public Set<RecommendedStudents> getRecommendedStudentses() {
		return this.recommendedStudentses;
	}

	public void setRecommendedStudentses(
			Set<RecommendedStudents> recommendedStudentses) {
		this.recommendedStudentses = recommendedStudentses;
	}

	public Set<RecommendedTas> getRecommendedTases() {
		return this.recommendedTases;
	}

	public void setRecommendedTases(Set<RecommendedTas> recommendedTases) {
		this.recommendedTases = recommendedTases;
	}

}
