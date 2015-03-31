package edu.gatech.omscs.dscars.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import edu.gatech.omscs.dscars.dao.CourseDAO;
import edu.gatech.omscs.dscars.dao.ProgramDAO;
import edu.gatech.omscs.dscars.dao.TermDAO;
import edu.gatech.omscs.dscars.model.Course;
import edu.gatech.omscs.dscars.model.Program;


public class SelectAction extends ActionSupport {
	private static final long serialVersionUID = 9149826260758390091L;
	String buttonName;
	List<Course> courses;
	List<Program> programs;
	List<String> terms;
	
	public SelectAction() {
		CourseDAO cDao=new CourseDAO();
		courses=cDao.getCourses();
		ProgramDAO pDao=new ProgramDAO();
		programs=pDao.getPrograms();
		TermDAO tDao=new TermDAO();
		terms=tDao.getTerms();
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public List<String> getTerms() {
		return terms;
	}

	public void setTerms(List<String> terms) {
		this.terms = terms;
	}

	public List<Program> getPrograms() {
		return programs;
	}

	public void setPrograms(List<Program> programs) {
		this.programs = programs;
	}
	
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
