package edu.gatech.omscs.dscars.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.gatech.omscs.dscars.dao.CourseDAO;
import edu.gatech.omscs.dscars.dao.InstructorDao;
import edu.gatech.omscs.dscars.dao.SectionTADAO;
import edu.gatech.omscs.dscars.model.Course;
import edu.gatech.omscs.dscars.model.Instructor;
import edu.gatech.omscs.dscars.model.Program;
import edu.gatech.omscs.dscars.model.SectionTA;
import edu.gatech.omscs.dscars.model.Semester;
import edu.gatech.omscs.dscars.model.Section;
import edu.gatech.omscs.dscars.dao.SectionDAO;

public class SectionAddAction extends SelectAction {
	private static final long serialVersionUID = 9149826260758390091L;
	List<Instructor> taList;
	List<Instructor> profList;
	List<Integer> taInsertList;
	int instructorId;
	int courseId;
	//int maxClassSize;
	String maxClassSize;
	int sectionId;
	boolean offered;
	Section section;
	Date date;

	public SectionAddAction() {
		super();
		programId = 0;
		semesterId = 0;
		//maxClassSize = 0;
		instructorId = 0;
		courseId = 0;
		sectionId = 0;
		offered = true;
		date = new Date();	
		section = new Section();
		taInsertList = new ArrayList<Integer>();
	}

	@SuppressWarnings("rawtypes")
	public String execute() {
		SectionDAO sDao = new SectionDAO();
		if ("edit".equals(buttonName)) {
			section=sDao.getSection(sectionId);
		}
		setLists();
		
		InstructorDao iDao=new InstructorDao();
		CourseDAO cDao=new CourseDAO();

		this.taList=iDao.taList();
		this.profList=iDao.professorList();
		this.courses = cDao.getCourses();
		

		Course course = new Course();
		Instructor instructor = new Instructor();
		Program program = new Program();
		Semester semester = new Semester();
		
		instructor.setInstructorId(instructorId);
		course.setCourseId(courseId);
		program.setProgramId(programId);
		semester.setSemesterId(semesterId);
		
		if(validateForm()){
			instructor.setInstructorId(instructorId);
			course.setCourseId(courseId);
			program.setProgramId(programId);
			semester.setSemesterId(semesterId);
			
			if ("".equals(maxClassSize)){
				section.setMaxClassSize(0);
			}
			else {
				section.setMaxClassSize(Integer.parseInt(maxClassSize));
			}
			
			section.setCourse(course);
			section.setInstructor(instructor);
			//section.setMaxClassSize(maxClassSize);
			section.setMaxTas(100);
			section.setOffered(offered);
			section.setProgram(program);
			section.setSemester(semester);
			section.setDate(date);
			
			System.out.println("MaxStudent " + section.getMaxClassSize());
			System.out.println("CourseId " + section.getCourse().getCourseId());
			System.out.println("Professor " + section.getInstructor().getInstructorId());
			System.out.println("Program " + section.getProgram().getProgramId());
			System.out.println("Semester " + section.getSemester().getSemesterId());
			System.out.println("Offered " + section.isOffered());
			
			
			SectionTADAO secTaDao = new SectionTADAO();
			Section newSection = sDao.add(section);
			
			Iterator itr = taInsertList.iterator();
			while(itr.hasNext()){
				int instructorId = (int) itr.next();
				SectionTA sta = new SectionTA(newSection.getSectionId(),instructorId,false);
				secTaDao.add(sta);
			}
			//Course course=Course.getSection(section.)
			addActionMessage("The course "+newSection.getCourse().getCourseId()+" "+newSection.getCourse().getCourseName()+"has been added sucessfully!");
			return "sectionAdmin";
		}
		return SUCCESS;
	}
	
	public List<Instructor> getTaList() {
		return taList;
	}
	
	public List<Instructor> getProfList() {
		return profList;
	}	
	
	public void setTaList(List<Instructor> taList) {
		this.taList =  taList;
	}
	
	public void setProfList(List<Instructor> profList) {
		this.profList = profList;
	}	
	
	public String getMaxClassSize() {
		return maxClassSize;
	}
	
	public void setMaxClassSize(String maxClassSize) {
		this.maxClassSize = maxClassSize;
	}
	
	public int getInstructorId() {
		return instructorId;
	}
	
	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}
	
	public int getCourseId() {
		return courseId;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public Section getSection() {
		return section;
	}
	
	public void setSection(Section section) {
		this.section = section;
	}
	
	public List<Integer> getTaInsertList() {
		return taInsertList;
	}
	
	public void setTaInsertList(List<Integer> taInsertList) {
		this.taInsertList = taInsertList;
	}
	
	public boolean getOffered(){
		return offered;
	}
	
	public void setOffered(boolean offered){
		this.offered = offered;
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
			/*
			else if (this.getTaInsertList().size() < 1) {
				isValid = false;
				addActionError("You must select at least one TA.");
			}*/
			else if (!"".equals(this.maxClassSize))
			{
				try {
					Integer.parseInt(this.maxClassSize);
				} catch(NumberFormatException e){
					addActionError("Max class size must be a numberr.");
					isValid = false;
				}
			}
		}
		return isValid;
	}
	
}
