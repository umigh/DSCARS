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
@Table(name = "Course", catalog = "DSCARS")
public class Course implements java.io.Serializable {

	private int courseId;
	private int courseNumber;
	private Instructor defaultInstructor;
	private String courseName;

	public Course() {
	}

	public Course(int courseId, String courseName) {
		this.courseId = courseId;
		this.courseName = courseName;
	}
	
	public Course(int courseId) {
		this.courseId = courseId;
	}

	public Course(int courseId, Instructor defaultInstructor, String courseName) {
		this.courseId = courseId;
		this.defaultInstructor = defaultInstructor;
		this.courseName = courseName;
	}

	@Id
	@Column(name = "CourseID", unique = true, nullable = false)
	public int getCourseId() {
		return this.courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DefaultInstructorID")
	public Instructor getDefaultInstructor() {
		return this.defaultInstructor;
	}

	public void setDeafultInstructor(Instructor defaultInstructor) {
		this.defaultInstructor = defaultInstructor;
	}

	@Column(name = "CourseName", nullable = false, length = 100)
	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseNumber() {
		return courseNumber;
	}

	@Column(name = "CourseNumber", nullable = false, length = 20)
	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}

	public void setDefaultInstructor(Instructor defaultInstructor) {
		this.defaultInstructor = defaultInstructor;
	}
	
	
}
