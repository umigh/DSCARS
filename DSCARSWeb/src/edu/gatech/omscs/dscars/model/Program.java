package edu.gatech.omscs.dscars.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Program")
public class Program implements Serializable{
	private static final long serialVersionUID = 1L;
	Long programId;
	String programName;
	String level;
	String admitTerm;
	String admitType;
	String catalogTerm;
	String college;
	String campus;
	String major;
	String department;

	public Program() {
		
	}
	@Id
	@GeneratedValue
	@Column(name="programId")
	public Long getProgramId() {
		return programId;
	}
	public void setProgramId(Long programId) {
		this.programId = programId;
	}
	@Column(name="programName")
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	@Column(name="level")
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getAdmitTerm() {
		return admitTerm;
	}
	@Column(name="admitTerm")
	public void setAdmitTerm(String admitTerm) {
		this.admitTerm = admitTerm;
	}	
	@Column(name="admitType")
	public String getAdmitType() {
		return admitType;
	}
	public void setAdmitType(String admitType) {
		this.admitType = admitType;
	}
	@Column(name="catalogTerm")
	public String getCatalogTerm() {
		return catalogTerm;
	}
	public void setCatalogTerm(String catalogTerm) {
		this.catalogTerm = catalogTerm;
	}
	@Column(name="college")
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	@Column(name="campus")
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	@Column(name="major")
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	@Column(name="department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
}
