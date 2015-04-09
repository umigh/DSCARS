package edu.gatech.omscs.dscars.action;

import edu.gatech.omscs.dscars.dao.SectionDAO;



public class AdminAction extends SelectAction {
	private static final long serialVersionUID = 9149826260758390091L;
	Integer courseId;

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public AdminAction() {
		super();
	}

	public String execute() {
		SectionDAO secDao=new SectionDAO();
		if(semesterId!=null && programId!=null)
			sections=secDao.getAllSections(semesterId, programId);
	    return ERROR;
	}
	
	public String courseAdmin() {
		return SUCCESS;
	}
}
