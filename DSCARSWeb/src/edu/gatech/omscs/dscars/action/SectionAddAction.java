package edu.gatech.omscs.dscars.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import edu.gatech.omscs.dscars.dao.CourseDAO;
import edu.gatech.omscs.dscars.dao.InstructorDao;
import edu.gatech.omscs.dscars.dao.PchDAO;
import edu.gatech.omscs.dscars.dao.SectionDAO;
import edu.gatech.omscs.dscars.dao.SectionTADAO;
import edu.gatech.omscs.dscars.dao.SemesterDAO;
import edu.gatech.omscs.dscars.model.Course;
import edu.gatech.omscs.dscars.model.Instructor;
import edu.gatech.omscs.dscars.model.Program;
import edu.gatech.omscs.dscars.model.Section;
import edu.gatech.omscs.dscars.model.SectionTA;
import edu.gatech.omscs.dscars.model.Semester;

public class SectionAddAction extends SelectAction {
	private static final long serialVersionUID = 9149826260758390091L;
	List<Instructor> semesterTaList;
	//List<SectionTA> semesterTaList;
	List<Instructor> profList;
	List<SectionTA> sectionTaList;
	Integer instructorId;
	Integer courseId;
	Integer maxClassSize;
	Integer sectionId;
	boolean offered;
	Section section;
	Date date;
	Integer[] selectedTas;
	Integer selectedTA;

	public SectionAddAction() {
		super();
		date = new Date();	
		section = new Section();
		sectionTaList = new ArrayList<SectionTA>();
		//semesterTaList = new ArrayList<SectionTA>();
	}

	public String execute() {
		if ("save".equals(buttonName)){
			validateForm();
			if(sectionId==null || sectionId==0 ) {
				submitAdd();
				addActionMessage("The course "+section.getCourse().getCourseId()+" "+section.getCourse().getCourseName()+"has been added sucessfully!");
			}
			else {
				submitEdit();
				addActionMessage("The course "+section.getCourse().getCourseId()+" "+section.getCourse().getCourseName()+"has been edited sucessfully!");
			}
			return "sectionAdmin";
		}
		else if("addTA".equals(buttonName)){
			validateForm();
			if(sectionId==null || sectionId==0 ) {
				submitAdd();
				sectionId=section.getSectionId();
			}
			else {
				submitEdit();
			}
			addTA();
			edit();
		}
		else if("removeTA".equals(buttonName)){
			if(sectionId==null || sectionId==0 ) {
				submitAdd();
			}
			else {
				submitEdit();
			}
			removeTA();		
			edit();
		}
		else if("edit".equals(buttonName)){
			edit();
		}
		
		setLists();
		CourseDAO cDao = new CourseDAO();
		this.courses = cDao.getCourses();
		SemesterDAO semDao = new SemesterDAO();
		this.semesters = semDao.getSemesters();
		InstructorDao iDao = new InstructorDao();
		this.profList = iDao.professorList();
		semesterTaList = iDao.taList();
		return SUCCESS;
	}
	
	private void addTA() {
		SectionTADAO secTADao = new SectionTADAO();
		sectionTaList = secTADao.getSectionTAsBySection(sectionId);
		boolean exists=false;
		for(int i=0;i<sectionTaList.size();i++) {
			if(sectionTaList.get(i).getInstructor().getInstructorId()==selectedTA) {
				exists=true;
				break;
			}
		}
		if(!exists) {
			SectionTA sta = new SectionTA(sectionId,selectedTA,false);
			SectionTADAO secTaDao = new SectionTADAO();
			secTaDao.add(sta);
		}
	}
	
	private void removeTA() {
		SectionTADAO secTaDao = new SectionTADAO();
		secTaDao.delete(sectionId,selectedTA);
	}

	private void edit() {
		SectionDAO sDao = new SectionDAO();
		SectionTADAO secTADao = new SectionTADAO();
		section=sDao.getSection(sectionId);
		Program program = section.getProgram();
		programId = program.getProgramId();
		Semester semester = section.getSemester();
		semesterId = semester.getSemesterId();
		Course course = section.getCourse();
		courseId = course.getCourseId();
		Instructor instructor = section.getInstructor();
		instructorId = instructor.getInstructorId();
		maxClassSize = section.getMaxClassSize();
		offered = section.isOffered();
		sectionTaList = secTADao.getSectionTAsBySection(sectionId);
		for(int i=0;i<sectionTaList.size();i++) {
			selectedTas=new Integer[sectionTaList.size()];
			selectedTas[i]=sectionTaList.get(i).getInstructor().getInstructorId();
		}
	}
	
	public void submitAdd() {
		Course course = new Course();
		Instructor instructor = new Instructor();
		Program program = new Program();
		Semester semester = new Semester();
		instructor.setInstructorId(instructorId);
		course.setCourseId(courseId);
		program.setProgramId(programId);
		semester.setSemesterId(semesterId);
		section.setMaxClassSize(maxClassSize);
		section.setCourse(course);
		section.setInstructor(instructor);
		section.setMaxTas(100);
		section.setOffered(offered);
		section.setProgram(program);
		section.setSemester(semester);
		section.setDate(date);
		
		SectionDAO secDao = new SectionDAO();
		section = secDao.add(section);	
		section=secDao.getSection(section.getSectionId());
	}
	
	
	public void submitEdit(){
		Course course = new Course();
		Instructor instructor = new Instructor();
		Program program = new Program();
		Semester semester = new Semester();
		instructor.setInstructorId(instructorId);
		course.setCourseId(courseId);
		program.setProgramId(programId);
		semester.setSemesterId(semesterId);
		section.setMaxClassSize(maxClassSize);
		section.setCourse(course);
		section.setInstructor(instructor);
		section.setMaxTas(100);
		section.setOffered(offered);
		section.setProgram(program);
		section.setSemester(semester);
		section.setDate(date);
		
		section.setSectionId(sectionId);
		SectionDAO dao=new SectionDAO();
		dao.update(section);
		section=dao.getSection(section.getSectionId());
		
		if(!section.isOffered()) {
			PchDAO pchd=new PchDAO();
			pchd.deletePchSubBySectionId(sectionId);
		}
	}
	
	
	public boolean validateForm(){
		boolean isValid = true;
		if ("edit".equals(buttonName)){ 
			if(sectionId==null || sectionId==0) {
				addActionError("Section Id cannot be null or zero.");
			}
		}
		if (this.getProgramId() < 1) {
			isValid = false;
			addActionError("You must select a program.");
		}
		else if (this.getCourseId() < 1) {
			isValid = false;
			addActionError("You must select a course.");
		}
		else if (this.getSemesterId() < 1) {
			isValid = false;
			addActionError("You must select a semester.");
		}
		return isValid;
	}
	
	
	public List<Instructor> getProfList() {
		return profList;
	}
	
	public void setProfList(List<Instructor> profList) {
		this.profList = profList;
	}
	
	/*
	public void setSemesterTaList(List<SectionTA> semesterTaList) {
		this.semesterTaList =  semesterTaList;
	}
	
	public List<SectionTA> getSemesterTaList() {
		return semesterTaList;
	}
	*/
		
	
	public void setSemesterTaList(List<Instructor> semesterTaList) {
		this.semesterTaList =  semesterTaList;
	}
	
	public List<Instructor> getSemesterTaList() {
		return semesterTaList;
	}
	
		
	public Integer getMaxClassSize() {
		return maxClassSize;
	}
	
	public void setMaxClassSize(Integer maxClassSize) {
		if (maxClassSize == null){
			this.maxClassSize = 0;
		}
		else {
			this.maxClassSize = maxClassSize;
		}
	}
	
	public Integer getInstructorId() {
		return instructorId;
	}
	
	public void setInstructorId(Integer instructorId) {
		this.instructorId = instructorId;
	}
	
	public Integer getCourseId() {
		return courseId;
	}
	
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
	public Integer getSectionId() {
		return sectionId;
	}
	
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	
	public List<SectionTA> getSectionTaList() {
		return sectionTaList;
	}
	
	public void setSectionTaList(List<SectionTA> sectionTaList) {
		this.sectionTaList = sectionTaList;
	}
	
	
	public boolean getOffered(){
		return offered;
	}
	
	public void setOffered(boolean offered){
		this.offered = offered;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer[] getSelectedTas() {
		return selectedTas;
	}

	public void setSelectedTas(Integer[] selectedTas) {
		this.selectedTas = selectedTas;
	}

	public Integer getSelectedTA() {
		return selectedTA;
	}

	public void setSelectedTA(Integer selectedTA) {
		this.selectedTA = selectedTA;
	}

}
