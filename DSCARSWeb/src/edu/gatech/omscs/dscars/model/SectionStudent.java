package edu.gatech.omscs.dscars.model;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "SectionStudent", catalog = "DSCARS")
public class SectionStudent implements java.io.Serializable {
	private int sectionStudentId;
	private Section section;
	private Student student;
	Date dateCreated;
	

	public SectionStudent() {
		dateCreated=new Date();
	}

	public SectionStudent(int sectionId, int studentId) {
		super();
		this.section = new Section(sectionId);
		this.student = new Student(studentId);
		dateCreated=new Date();
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SECTIONSTUDENTID", unique = true, nullable = false)
	public int getSectionStudentId() {
		return sectionStudentId;
	}

	public void setSectionStudentId(int sectionStudentId) {
		this.sectionStudentId = sectionStudentId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SectionID", insertable=true,updatable=true)
	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "StudentId", insertable=true,updatable=true)
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateCreated", nullable = false, length = 19)
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}
