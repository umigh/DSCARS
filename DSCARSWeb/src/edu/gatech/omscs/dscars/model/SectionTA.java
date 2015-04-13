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

import org.hibernate.annotations.Type;

@Entity
@Table(name = "SectionTA", catalog = "DSCARS")
public class SectionTA implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int sectionTAId;
	private Section section;
	private Instructor instructor;
	boolean generated;
	Date dateCreated;
	
	public SectionTA() {
		dateCreated=new Date();
	}

	public SectionTA(int sectionId,int instructorId,boolean generated) {
		this.section=new Section(sectionId);
		this.instructor=new Instructor(instructorId);
		this.generated=generated;
		dateCreated=new Date();
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "STA_ID", unique = true, nullable = false)
	public int getSectionTAId() {
		return this.sectionTAId;
	}

	public void setSectionTAId(int sectionTAId) {
		this.sectionTAId = sectionTAId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sectionId", insertable=true,updatable=true)
	public Section getSection() {
		return section;
	}


	public void setSection(Section section) {
		this.section = section;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "instructorId", insertable=true,updatable=true)
	public Instructor getInstructor() {
		return instructor;
	}


	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@Type(type="yes_no")
	public boolean isGenerated() {
		return generated;
	}

	public void setGenerated(boolean generated) {
		this.generated = generated;
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
