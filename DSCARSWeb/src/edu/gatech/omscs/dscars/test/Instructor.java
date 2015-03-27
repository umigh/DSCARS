package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 27, 2015 4:54:21 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

/**
 * Instructor generated by hbm2java
 */
public class Instructor implements java.io.Serializable {

	private int instructorId;
	private String name;
	private boolean isProfessor;
	private boolean active;
	private byte currentCourseCount;
	private Set<CourseCompetency> courseCompetencies = new HashSet<CourseCompetency>(
			0);
	private Set<RecommendedSection> recommendedSections = new HashSet<RecommendedSection>(
			0);
	private Set<Course> courses = new HashSet<Course>(0);
	private Set<RecommendedTas> recommendedTases = new HashSet<RecommendedTas>(
			0);
	private Set<StudentCourseHistory> studentCourseHistories = new HashSet<StudentCourseHistory>(
			0);
	private Set<Section> sections = new HashSet<Section>(0);

	public Instructor() {
	}

	public Instructor(int instructorId, String name, boolean isProfessor,
			boolean active, byte currentCourseCount) {
		this.instructorId = instructorId;
		this.name = name;
		this.isProfessor = isProfessor;
		this.active = active;
		this.currentCourseCount = currentCourseCount;
	}

	public Instructor(int instructorId, String name, boolean isProfessor,
			boolean active, byte currentCourseCount,
			Set<CourseCompetency> courseCompetencies,
			Set<RecommendedSection> recommendedSections, Set<Course> courses,
			Set<RecommendedTas> recommendedTases,
			Set<StudentCourseHistory> studentCourseHistories,
			Set<Section> sections) {
		this.instructorId = instructorId;
		this.name = name;
		this.isProfessor = isProfessor;
		this.active = active;
		this.currentCourseCount = currentCourseCount;
		this.courseCompetencies = courseCompetencies;
		this.recommendedSections = recommendedSections;
		this.courses = courses;
		this.recommendedTases = recommendedTases;
		this.studentCourseHistories = studentCourseHistories;
		this.sections = sections;
	}

	public int getInstructorId() {
		return this.instructorId;
	}

	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isIsProfessor() {
		return this.isProfessor;
	}

	public void setIsProfessor(boolean isProfessor) {
		this.isProfessor = isProfessor;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public byte getCurrentCourseCount() {
		return this.currentCourseCount;
	}

	public void setCurrentCourseCount(byte currentCourseCount) {
		this.currentCourseCount = currentCourseCount;
	}

	public Set<CourseCompetency> getCourseCompetencies() {
		return this.courseCompetencies;
	}

	public void setCourseCompetencies(Set<CourseCompetency> courseCompetencies) {
		this.courseCompetencies = courseCompetencies;
	}

	public Set<RecommendedSection> getRecommendedSections() {
		return this.recommendedSections;
	}

	public void setRecommendedSections(
			Set<RecommendedSection> recommendedSections) {
		this.recommendedSections = recommendedSections;
	}

	public Set<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Set<RecommendedTas> getRecommendedTases() {
		return this.recommendedTases;
	}

	public void setRecommendedTases(Set<RecommendedTas> recommendedTases) {
		this.recommendedTases = recommendedTases;
	}

	public Set<StudentCourseHistory> getStudentCourseHistories() {
		return this.studentCourseHistories;
	}

	public void setStudentCourseHistories(
			Set<StudentCourseHistory> studentCourseHistories) {
		this.studentCourseHistories = studentCourseHistories;
	}

	public Set<Section> getSections() {
		return this.sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}

}
