package edu.gatech.omscs.dscars.action;

import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import edu.gatech.omscs.dscars.dao.PchDAO;
import edu.gatech.omscs.dscars.dao.SectionDAO;
import edu.gatech.omscs.dscars.dao.StudentDao;
import edu.gatech.omscs.dscars.model.PchSub;
import edu.gatech.omscs.dscars.model.PreferredCourseHistory;
import edu.gatech.omscs.dscars.model.Student;
import edu.gatech.omscs.dscars.model.User;
import edu.gatech.omscs.dscars.model.Section;



public class PchAction extends SelectAction  {
	private static final long serialVersionUID = 9149826260758390091L;
	Student student;
	PreferredCourseHistory pch;
	int sectionId;
	public PchAction() {
		super();
		student=new Student();
		pch=new PreferredCourseHistory();

	}

	@SuppressWarnings("rawtypes")
	public String execute() {
		setLists();
		setSectionList();
		SectionDAO secDao=new SectionDAO();
		/*
		if(semesterId!=null && programId!=null)
			sections=secDao.getSectionsOffered(semesterId, programId);
		*/
		Map session = ActionContext.getContext().getSession();
		User user=(User) session.get("user");
		StudentDao stDao=new StudentDao(); 
		student=stDao.getStudent(user.getUserId());
		pch=new PreferredCourseHistory();
		if ("AddCourse".equals(buttonName)) {
	         return AddCourse(user);
	    }
		else {
			if(semesterId!=null && programId !=null)
			getPchList(user.getUserId());
		}
	    return ERROR;
	}
	
	private void getPchList(int studentId) {
		PchDAO dao=new PchDAO();
		pch=dao.getStudentPch(programId, semesterId, studentId);
	}

	public String AddCourse(User user) {
		setSectionList();
		PchDAO dao=new PchDAO();
		pch=dao.getStudentPch(programId, semesterId, user.getUserId());
		
		PchSub sub=new PchSub();
		edu.gatech.omscs.dscars.model.Section section=new Section();
		section.setSectionId(sectionId);
		sub.setPreferredCourseHistory(pch);
		sub.setSection(section);
		sub.setCreateDate(new Date());
		sub.setCreateUser(user.getUserName());
		sub.setPriority(pch.getPchSubs().size()+1);
		dao.addSub(sub);
		pch=dao.getStudentPch(programId, semesterId, user.getUserId());

		return SUCCESS;
	}
	
	
	public void validateForm() {
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public PreferredCourseHistory getPch() {
		return pch;
	}

	public void setPch(PreferredCourseHistory pch) {
		this.pch = pch;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
}
