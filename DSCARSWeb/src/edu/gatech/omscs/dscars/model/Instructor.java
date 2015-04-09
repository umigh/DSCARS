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
@Table(name = "Instructor", catalog = "DSCARS")
public class Instructor implements java.io.Serializable {

	private int instructorId;
	private boolean isProfessor;
	private boolean active;
	private int currentCourseCount;
	private Contact contact=null;

	public Instructor() {
	}

	public Instructor(int instructorId,  boolean isProfessor,boolean active, int currentCourseCount) {
		this.instructorId = instructorId;
		this.isProfessor = isProfessor;
		this.active = active;
		this.currentCourseCount = currentCourseCount;
	}

	@Id
	@Column(name = "instructorId", unique = true, nullable = false)
	public int getInstructorId() {
		return this.instructorId;
	}

	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}

	@Column(name = "isProfessor")
	public boolean isIsProfessor() {
		return this.isProfessor;
	}

	public void setIsProfessor(boolean isProfessor) {
		this.isProfessor = isProfessor;
	}

	@Column(name = "active")
	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "currentCourseCount")
	public int getCurrentCourseCount() {
		return this.currentCourseCount;
	}

	public void setCurrentCourseCount(int currentCourseCount) {
		this.currentCourseCount = currentCourseCount;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "instructorId", insertable=false,updatable=false)
	public Contact getContact() {
		return contact;
	}


	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
