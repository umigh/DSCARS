package edu.gatech.omscs.dscars.model;
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
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@SuppressWarnings("serial")
@Entity
@Table(name = "Section", catalog = "DSCARS")
public class Section implements java.io.Serializable {

	private Integer sectionId;
	private Instructor instructor;
	private Course course;
	private User user;
	private Semester semester;
	private int maxClassSize;
	private int maxTas;
	private int courseDemand;
	private Date date;
	private Program program;
	private boolean offered=true;
	private Integer generatedClassSize;
	private Integer generatedTASize;

	public Section() {
	}
	
	public Section(int sectionId) {
		this.sectionId=sectionId;
	}

	public Section(Instructor instructor, Course course, Semester semester,
			int maxClassSize, int maxTas, int courseDemand, Date date) {
		this.instructor = instructor;
		this.course = course;
		this.semester = semester;
		this.maxClassSize = maxClassSize;
		this.maxTas = maxTas;
		this.courseDemand = courseDemand;
		this.date = date;
	}

	public Section(Instructor instructor, Course course, User user,
			Semester semester, int maxClassSize, int maxTas, int courseDemand,
			Date date) {
		this.instructor = instructor;
		this.course = course;
		this.user = user;
		this.semester = semester;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "InstructorID", nullable = false)
	public Instructor getInstructor() {
		return this.instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CourseID", nullable = false)
	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "UserShadowID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SemesterID")
	public Semester getSemester() {
		return this.semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "programId")
	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	@Type(type="yes_no")
	public boolean isOffered() {
		return offered;
	}

	public void setOffered(boolean offered) {
		this.offered = offered;
	}

	@Column(name = "GeneratedClassSize", nullable = true)
	public Integer getGeneratedClassSize() {
		return generatedClassSize;
	}

	public void setGeneratedClassSize(Integer generatedClassSize) {
		this.generatedClassSize = generatedClassSize;
	}

	@Column(name = "GeneratedTASize", nullable = true)
	public Integer getGeneratedTASize() {
		return generatedTASize;
	}

	public void setGeneratedTASize(Integer generatedTASize) {
		this.generatedTASize = generatedTASize;
	}
	
	@Transient
	public int getStudentCapacity() {
		if(generatedClassSize>0) {
			return generatedClassSize;
		}
		else {
			return maxClassSize;
		}
	}
	
	@Transient
	public int getTACapacity() {
		if(generatedTASize>0) {
			return generatedClassSize;
		}
		else {
			return maxTas;
		}
	}

}
