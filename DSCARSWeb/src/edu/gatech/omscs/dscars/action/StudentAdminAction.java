package edu.gatech.omscs.dscars.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import edu.gatech.omscs.dscars.dao.CoreEngineSettingDao;
import edu.gatech.omscs.dscars.dao.PchDAO;
import edu.gatech.omscs.dscars.dao.SectionDAO;
import edu.gatech.omscs.dscars.exception.SettingLockedException;
import edu.gatech.omscs.dscars.model.CoreEngineSetting;
import edu.gatech.omscs.dscars.model.PreferredCourseHistory;
import edu.gatech.omscs.dscars.model.User;



public class StudentAdminAction extends SelectAction {
	private static final long serialVersionUID = 9149826260758390091L;
	List<PreferredCourseHistory> pchList;
	int studentId;

	public StudentAdminAction() {
		super();
	}

	@SuppressWarnings("rawtypes")
	public String execute() {
		Map session = ActionContext.getContext().getSession();
		User user=(User) session.get("user");
		setLists();
		if ("RunRecommendation".equals(buttonName)) {
			runRecommendation(user);
	    }
		if(semesterId!=null) {
			PchDAO dao=new PchDAO();
			pchList=dao.getStudentPch(semesterId);
		}
	    return SUCCESS;
	}
	
	private void runRecommendation(User user) {
		CoreEngineSetting setting=new CoreEngineSetting(user.getUserId(), 200, 200, semesterId);
		CoreEngineSettingDao coedao=new CoreEngineSettingDao();
		
		try {
			coedao.addOrUpdate(setting);
			addActionMessage("A core engine job is schedule to run recommendations. Please check results in a minute.");
		} catch (SettingLockedException e) {
			System.out.println(e);
			addActionError("A scheduled core engine job is already running recommendations. Please check results in a minute.");
		}
	}
	
	public List<PreferredCourseHistory> getPchList() {
		return pchList;
	}

	public void setPchList(List<PreferredCourseHistory> pchList) {
		this.pchList = pchList;
	}
}
