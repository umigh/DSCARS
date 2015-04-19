package edu.gatech.omscs.dscars.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import edu.gatech.omscs.dscars.dao.CourseDAO;
import edu.gatech.omscs.dscars.dao.PchDAO;
import edu.gatech.omscs.dscars.dao.ProgramDAO;
import edu.gatech.omscs.dscars.dao.SectionDAO;
import edu.gatech.omscs.dscars.dao.SemesterDAO;
import edu.gatech.omscs.dscars.model.Course;
import edu.gatech.omscs.dscars.model.Program;
import edu.gatech.omscs.dscars.model.Section;
import edu.gatech.omscs.dscars.model.Semester;


public class SelectAction extends ActionSupport {
	private static final long serialVersionUID = 9149826260758390091L;
	String buttonName;
	Integer programId;
	Integer semesterId;
	Map<Integer,Integer> demand;
	
	List<Course> courses;
	List<Program> programs;
	List<Semester> semesters;
	List<Section> sections;
	
	public String execute() {
		return SUCCESS;
	}
	
	protected void setLists() {		
		ProgramDAO pDao=new ProgramDAO();
		programs=pDao.getPrograms();
		SemesterDAO sDao=new SemesterDAO();
		semesters=sDao.getSemesters();		
	}
	
	protected void setCourseList() {
		CourseDAO cDao=new CourseDAO();
		courses=cDao.getCourses();
	}
	
	protected void setSectionList(boolean admin) {
		SectionDAO secDao=new SectionDAO();
		if(semesterId!=null) {
			//sections=secDao.getSectionsOffered(semesterId, programId);
			if(admin) {
				sections=secDao.getSections(semesterId);
			}
			else {
				sections=secDao.getSectionsOffered(semesterId);
			}			
			PchDAO pchdao=new PchDAO();
			demand=pchdao.getDemand();
		}
		
	}
	
	public SelectAction() {
		courses=new ArrayList<Course>();
		programs=new ArrayList<Program>();
		semesters=new ArrayList<Semester>();
		sections=new ArrayList<Section>();
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public List<Semester> getSemesters() {
		return semesters;
	}

	public void setSemesters(List<Semester> semesters) {
		this.semesters = semesters;
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

	public Integer getProgramId() {
		return programId;
	}

	public void setProgramId(Integer programId) {
		this.programId = programId;
	}

	public Integer getSemesterId() {
		return semesterId;
	}

	public void setSemesterId(Integer semesterId) {
		this.semesterId = semesterId;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public Map<Integer, Integer> getDemand() {
		return demand;
	}

	public void setDemand(Map<Integer, Integer> demand) {
		this.demand = demand;
	}
}
