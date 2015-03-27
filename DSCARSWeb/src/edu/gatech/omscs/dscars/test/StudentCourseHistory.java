package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 27, 2015 4:54:21 PM by Hibernate Tools 4.0.0

/**
 * StudentCourseHistory generated by hbm2java
 */
public class StudentCourseHistory implements java.io.Serializable {

	private int schId;
	private Instructor instructor;
	private Semester semester;
	private Student student;
	private Course course;
	private byte grade;
	private byte creditHours;

	public StudentCourseHistory() {
	}

	public StudentCourseHistory(int schId, Instructor instructor,
			Semester semester, Student student, Course course, byte grade,
			byte creditHours) {
		this.schId = schId;
		this.instructor = instructor;
		this.semester = semester;
		this.student = student;
		this.course = course;
		this.grade = grade;
		this.creditHours = creditHours;
	}

	public int getSchId() {
		return this.schId;
	}

	public void setSchId(int schId) {
		this.schId = schId;
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

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public byte getGrade() {
		return this.grade;
	}

	public void setGrade(byte grade) {
		this.grade = grade;
	}

	public byte getCreditHours() {
		return this.creditHours;
	}

	public void setCreditHours(byte creditHours) {
		this.creditHours = creditHours;
	}

}
