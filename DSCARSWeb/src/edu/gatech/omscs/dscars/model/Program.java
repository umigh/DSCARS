package edu.gatech.omscs.dscars.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Program", catalog = "DSCARS")
public class Program implements java.io.Serializable {

	private int programId;
	private String programName;

	public Program() {
	}

	public Program(int programId) {
		this.programId = programId;
	}
	
	public Program(int programId, String programName) {
		this.programId = programId;
		this.programName = programName;
	}

	@Id
	@Column(name = "ProgramID", unique = true, nullable = false)
	public int getProgramId() {
		return this.programId;
	}

	public void setProgramId(int programId) {
		this.programId = programId;
	}

	@Column(name = "ProgramName", nullable = false, length = 65535)
	public String getProgramName() {
		return this.programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}
}
