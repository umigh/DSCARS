package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 27, 2015 4:54:21 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Course generated by hbm2java
 */
public class Course implements java.io.Serializable {

	private int courseId;
	private Instructor instructor;
	private String courseName;
	private Set<PchSub> pchSubs = new HashSet<PchSub>(0);
	private Set<CourseCompetency> courseCompetencies = new HashSet<CourseCompetency>(
			0);
	private Set<Section> sections = new HashSet<Section>(0);
	private Set<RecommendedSection> recommendedSections = new HashSet<RecommendedSection>(
			0);
	private Set<StudentCourseHistory> studentCourseHistories = new HashSet<StudentCourseHistory>(
			0);
	private Set<Requisite> requisitesForPreReqCourseId = new HashSet<Requisite>(
			0);
	private Set<Requisite> requisitesForCourseId = new HashSet<Requisite>(0);

	public Course() {
	}

	public Course(int courseId, String courseName) {
		this.courseId = courseId;
		this.courseName = courseName;
	}

	public Course(int courseId, Instructor instructor, String courseName,
			Set<PchSub> pchSubs, Set<CourseCompetency> courseCompetencies,
			Set<Section> sections, Set<RecommendedSection> recommendedSections,
			Set<StudentCourseHistory> studentCourseHistories,
			Set<Requisite> requisitesForPreReqCourseId,
			Set<Requisite> requisitesForCourseId) {
		this.courseId = courseId;
		this.instructor = instructor;
		this.courseName = courseName;
		this.pchSubs = pchSubs;
		this.courseCompetencies = courseCompetencies;
		this.sections = sections;
		this.recommendedSections = recommendedSections;
		this.studentCourseHistories = studentCourseHistories;
		this.requisitesForPreReqCourseId = requisitesForPreReqCourseId;
		this.requisitesForCourseId = requisitesForCourseId;
	}

	public int getCourseId() {
		return this.courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Instructor getInstructor() {
		return this.instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Set<PchSub> getPchSubs() {
		return this.pchSubs;
	}

	public void setPchSubs(Set<PchSub> pchSubs) {
		this.pchSubs = pchSubs;
	}

	public Set<CourseCompetency> getCourseCompetencies() {
		return this.courseCompetencies;
	}

	public void setCourseCompetencies(Set<CourseCompetency> courseCompetencies) {
		this.courseCompetencies = courseCompetencies;
	}

	public Set<Section> getSections() {
		return this.sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
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

	public Set<Requisite> getRequisitesForPreReqCourseId() {
		return this.requisitesForPreReqCourseId;
	}

	public void setRequisitesForPreReqCourseId(
			Set<Requisite> requisitesForPreReqCourseId) {
		this.requisitesForPreReqCourseId = requisitesForPreReqCourseId;
	}

	public Set<Requisite> getRequisitesForCourseId() {
		return this.requisitesForCourseId;
	}

	public void setRequisitesForCourseId(Set<Requisite> requisitesForCourseId) {
		this.requisitesForCourseId = requisitesForCourseId;
	}

}
