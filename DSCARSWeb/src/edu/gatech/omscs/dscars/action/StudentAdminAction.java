package edu.gatech.omscs.dscars.action;

import java.util.List;

import edu.gatech.omscs.dscars.dao.PchDAO;
import edu.gatech.omscs.dscars.dao.SectionDAO;
import edu.gatech.omscs.dscars.model.PreferredCourseHistory;



public class StudentAdminAction extends SelectAction {
	private static final long serialVersionUID = 9149826260758390091L;
	List<PreferredCourseHistory> pchList;
	int studentId;

	public StudentAdminAction() {
		super();
	}

	public String execute() {
		setLists();
		SectionDAO secDao=new SectionDAO();
		if(semesterId!=null && programId!=null) {
			sections=secDao.getAllSections(semesterId, programId);
			PchDAO dao=new PchDAO();
			pchList=dao.getStudentPch(programId, semesterId);
		}
	    return SUCCESS;
	}
	
	public List<PreferredCourseHistory> getPchList() {
		return pchList;
	}

	public void setPchList(List<PreferredCourseHistory> pchList) {
		this.pchList = pchList;
	}
}
