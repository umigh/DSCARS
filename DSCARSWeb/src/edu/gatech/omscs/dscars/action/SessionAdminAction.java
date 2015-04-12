package edu.gatech.omscs.dscars.action;

import java.util.List;

import edu.gatech.omscs.dscars.dao.UserDao;
import edu.gatech.omscs.dscars.dao.SectionDAO;
import edu.gatech.omscs.dscars.model.Instructor;
import edu.gatech.omscs.dscars.model.User;

public class SessionAdminAction extends SelectAction {
	private static final long serialVersionUID = 9149826260758390091L;
	List<User> taList;
	List<User> profList;

	public SessionAdminAction() {
		super();
	}

	public String execute() {
		setLists();
		UserDao uDao=new UserDao();
		this.taList=uDao.getTAs();
		this.profList=uDao.getProfessors();
		System.out.println(profList.size());
	    return SUCCESS;
	}
	
	public List<User> getTaList() {
		return taList;
	}
	
	public List<User> getProfList() {
		return this.profList;
	}	
	
	public void setTaList(List<User> taList) {
		this.taList =  taList;
	}
	
	public void setProfList(List<User> professorList) {
		this.profList = professorList;
	}	
	

}
