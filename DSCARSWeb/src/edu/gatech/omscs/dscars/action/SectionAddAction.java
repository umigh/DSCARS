package edu.gatech.omscs.dscars.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.gatech.omscs.dscars.dao.CourseDAO;
import edu.gatech.omscs.dscars.dao.InstructorDao;
import edu.gatech.omscs.dscars.dao.ProgramDAO;
import edu.gatech.omscs.dscars.dao.SectionTADAO;
import edu.gatech.omscs.dscars.dao.SemesterDAO;
import edu.gatech.omscs.dscars.model.Course;
import edu.gatech.omscs.dscars.model.Instructor;
import edu.gatech.omscs.dscars.model.Program;
import edu.gatech.omscs.dscars.model.SectionTA;
import edu.gatech.omscs.dscars.model.Semester;
import edu.gatech.omscs.dscars.model.Section;
import edu.gatech.omscs.dscars.dao.SectionDAO;

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

	public SectionAddAction() {
		super();
		date = new Date();	
		section = new Section();
		sectionTaList = new ArrayList<SectionTA>();
		//semesterTaList = new ArrayList<SectionTA>();
	}

	@SuppressWarnings("rawtypes")
	public String execute() {
		setLists();
		CourseDAO cDao = new CourseDAO();
		this.courses = cDao.getCourses();
		SemesterDAO semDao = new SemesterDAO();
		this.semesters = semDao.getSemesters();
		InstructorDao iDao = new InstructorDao();
		this.profList = iDao.professorList();
		semesterTaList = iDao.taList();
		/*
		//Replace once we can pass in semesterId
		if (semesterId != null){
			SectionTADAO secTaDao = new SectionTADAO();
			semesterTaList = secTaDao.getSectionTasBySemester(semesterId);
			System.out.println(semesterTaList.size());
		}
		*/
		if ("edit".equals(buttonName)){
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
		}
		
		if (validateForm()){
			//Adding new section
			if (sectionId == null){
				insertSection();
			}
			//Modify an existing section
			else {
				modifySection();
			}
			return "sectionAdmin";
		}
		return SUCCESS;
	}
	
	public void insertSection() {
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
		SectionTADAO secTaDao = new SectionTADAO();
		SectionDAO secDao = new SectionDAO();
		Section newSection = secDao.add(section);
		
		Iterator<SectionTA> itr = sectionTaList.iterator();
		while(itr.hasNext()){
			SectionTA secTa = itr.next();
			SectionTA sta = new SectionTA(newSection.getSectionId(),
					                      secTa.getInstructor().getInstructorId(),false);
			secTaDao.add(itr.next());
		}
		
		addActionMessage("The course "+newSection.getCourse().getCourseId()+" "+newSection.getCourse().getCourseName()+"has been added sucessfully!");
	}
	
	
	public void modifySection(){
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
		
		SectionTADAO secTaDao = new SectionTADAO();
		List<SectionTA> currentDbList = secTaDao.getSectionTAsBySection(sectionId);
		List<SectionTA> deleteTAList = currentDbList;
		List<SectionTA> insertTAList = sectionTaList;
		
		//List of items exisiting in form list not existing in db list
		insertTAList.retainAll(currentDbList);
		System.out.println(insertTAList.size());
		
		//List of items existing in db that do not exist in form list
		deleteTAList.retainAll(sectionTaList);
		System.out.println(deleteTAList.size());
		
		Iterator<SectionTA> secTaItr = insertTAList.iterator();
		while (secTaItr.hasNext()){
			SectionTA newSecTa = secTaItr.next();
			System.out.println(newSecTa.getSectionTAId());
			System.out.println(newSecTa.getInstructor().getInstructorId());
			System.out.println(newSecTa.isGenerated());
			System.out.println(newSecTa.getSection());
			//secTaDao.add(newSecTa);
		}
		
		secTaItr = deleteTAList.iterator();
		while (secTaItr.hasNext()){
			secTaDao.delete(secTaItr.next().getSectionTAId());
		}
		addActionMessage("The course "+section.getCourse().getCourseId()+" "+section.getCourse().getCourseName()+"has been edited sucessfully!");
	}
	
	
	public boolean validateForm(){
		boolean isValid = true;
		//The save button has not been pressed
		if(!"SaveSection".equals(buttonName)){
			isValid = false;
		}
		//The save button has been pressed
		else {
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
	
}
