package edu.gatech.omscs.dscars.model;
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
 * Requisite generated by hbm2java
 */
@Entity
@Table(name = "Requisite", catalog = "DSCARS")
public class Requisite implements java.io.Serializable {

	private Integer requisiteId;
	private Course courseByPreReqCourseId;
	private Course courseByCourseId;

	public Requisite() {
	}

	public Requisite(Course courseByPreReqCourseId, Course courseByCourseId) {
		this.courseByPreReqCourseId = courseByPreReqCourseId;
		this.courseByCourseId = courseByCourseId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RequisiteID", unique = true, nullable = false)
	public Integer getRequisiteId() {
		return this.requisiteId;
	}

	public void setRequisiteId(Integer requisiteId) {
		this.requisiteId = requisiteId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PreReqCourseID", nullable = false)
	public Course getCourseByPreReqCourseId() {
		return this.courseByPreReqCourseId;
	}

	public void setCourseByPreReqCourseId(Course courseByPreReqCourseId) {
		this.courseByPreReqCourseId = courseByPreReqCourseId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CourseID", nullable = false)
	public Course getCourseByCourseId() {
		return this.courseByCourseId;
	}

	public void setCourseByCourseId(Course courseByCourseId) {
		this.courseByCourseId = courseByCourseId;
	}

}
