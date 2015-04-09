package edu.gatech.omscs.dscars.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Semester", catalog = "DSCARS")
public class Semester implements java.io.Serializable {

	private int semesterId;
	private Integer year;
	private Integer term;
	private String termName;


	public Semester() {
	}

	public Semester(int semesterId) {
		this.semesterId = semesterId;
	}

	public Semester(int semesterId, Integer year, Integer term,String termName) {
		this.semesterId = semesterId;
		this.year = year;
		this.term = term;
		this.termName = termName;
	}

	@Id
	@Column(name = "SemesterID", unique = true, nullable = false)
	public int getSemesterId() {
		return this.semesterId;
	}

	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}

	@Column(name = "Year")
	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Column(name = "Term")
	public Integer getTerm() {
		return this.term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	@Column(name = "TermName", length = 65535)
	public String getTermName() {
		return this.termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}
}
