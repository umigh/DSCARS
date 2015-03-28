package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * StudentCourseHistory generated by hbm2java
 */
@Entity
@Table(name = "StudentCourseHistory", catalog = "DSCARS")
public class StudentCourseHistory implements java.io.Serializable {

	private Integer schId;
	private Instructor instructor;
	private Semester semester;
	private Student student;
	private Course course;
	private byte grade;
	private byte creditHours;

	public StudentCourseHistory() {
	}

	public StudentCourseHistory(Instructor instructor, Semester semester,
			Student student, Course course, byte grade, byte creditHours) {
		this.instructor = instructor;
		this.semester = semester;
		this.student = student;
		this.course = course;
		this.grade = grade;
		this.creditHours = creditHours;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SCH_ID", unique = true, nullable = false)
	public Integer getSchId() {
		return this.schId;
	}

	public void setSchId(Integer schId) {
		this.schId = schId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "InstructorID", nullable = false)
	public Instructor getInstructor() {
		return this.instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SemesterID", nullable = false)
	public Semester getSemester() {
		return this.semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "StudentID", nullable = false)
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CourseID", nullable = false)
	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Column(name = "Grade", nullable = false)
	public byte getGrade() {
		return this.grade;
	}

	public void setGrade(byte grade) {
		this.grade = grade;
	}

	@Column(name = "CreditHours", nullable = false)
	public byte getCreditHours() {
		return this.creditHours;
	}

	public void setCreditHours(byte creditHours) {
		this.creditHours = creditHours;
	}

}
