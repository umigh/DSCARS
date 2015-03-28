package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Section generated by hbm2java
 */
@Entity
@Table(name = "Section", catalog = "DSCARS")
public class Section implements java.io.Serializable {

	private Integer sectionId;
	private Instructor instructor;
	private Course course;
	private User user;
	private int semesterId;
	private int maxClassSize;
	private int maxTas;
	private int courseDemand;
	private Date date;

	public Section() {
	}

	public Section(Instructor instructor, Course course, int semesterId,
			int maxClassSize, int maxTas, int courseDemand, Date date) {
		this.instructor = instructor;
		this.course = course;
		this.semesterId = semesterId;
		this.maxClassSize = maxClassSize;
		this.maxTas = maxTas;
		this.courseDemand = courseDemand;
		this.date = date;
	}

	public Section(Instructor instructor, Course course, User user,
			int semesterId, int maxClassSize, int maxTas, int courseDemand,
			Date date) {
		this.instructor = instructor;
		this.course = course;
		this.user = user;
		this.semesterId = semesterId;
		this.maxClassSize = maxClassSize;
		this.maxTas = maxTas;
		this.courseDemand = courseDemand;
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SectionID", unique = true, nullable = false)
	public Integer getSectionId() {
		return this.sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
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
	@JoinColumn(name = "CourseID", nullable = false)
	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserShadowID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "SemesterID", nullable = false)
	public int getSemesterId() {
		return this.semesterId;
	}

	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}

	@Column(name = "MaxClassSize", nullable = false)
	public int getMaxClassSize() {
		return this.maxClassSize;
	}

	public void setMaxClassSize(int maxClassSize) {
		this.maxClassSize = maxClassSize;
	}

	@Column(name = "MaxTAs", nullable = false)
	public int getMaxTas() {
		return this.maxTas;
	}

	public void setMaxTas(int maxTas) {
		this.maxTas = maxTas;
	}

	@Column(name = "CourseDemand", nullable = false)
	public int getCourseDemand() {
		return this.courseDemand;
	}

	public void setCourseDemand(int courseDemand) {
		this.courseDemand = courseDemand;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Date", nullable = false, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
