package edu.gatech.omscs.dscars.action;

import edu.gatech.omscs.dscars.dao.StudentPreferenceDao;
import edu.gatech.omscs.dscars.model.StudentPreference;


public class StudentPreferenceAction extends SelectAction {
	private static final long serialVersionUID = 9149826260758390091L;
	StudentPreference studentPreference;

	public StudentPreferenceAction() {
		super();
		studentPreference=new StudentPreference();
	}

	public String execute() {
		if ("Save".equals(buttonName)) {
	         return savePreference();
	    }
	      
		if ("Preview".equals(buttonName)) {
	         previewRecommendation();	         
	    }
		
		if ("Run".equals(buttonName)) {
	         runRecommendation();	         
	    }
	    return ERROR;
	}

	public String savePreference() {
		System.out.println("Student Id: "+studentPreference.getStudent().getStudentId());
		System.out.println("Prorgam Id: "+studentPreference.getProgram().getProgramId());
		System.out.println("Term: "+studentPreference.getTerm());
		System.out.println("Number of courses Id: "+studentPreference.getNumberOfCourses());
		System.out.println("1st Preference Course Id: "+studentPreference.getFirstPreference().getCourseId());
		System.out.println("2nd Preference Course Id: "+studentPreference.getSecondPreference().getCourseId());
		System.out.println("3rd Preference Course Id: "+studentPreference.getThirdPreference().getCourseId());
		System.out.println("4th Preference Course Id: "+studentPreference.getFourthPreference().getCourseId());
		StudentPreferenceDao dao=new StudentPreferenceDao();
		dao.add(studentPreference);
		return SUCCESS;
	}
	
	public StudentPreference getStudentPreference() {
		return studentPreference;
	}

	public void setStudentPreference(StudentPreference studentPreference) {
		this.studentPreference = studentPreference;
	}

	public String previewRecommendation() {
		return SUCCESS;
	}
	
	public String runRecommendation() {
		return SUCCESS;
	}
	

	
	public void validateForm() {
	}

}
