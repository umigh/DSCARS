package edu.gatech.omscs.dscars.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import edu.gatech.omscs.dscars.dao.CoreEngineSettingDao;
import edu.gatech.omscs.dscars.dao.SectionDAO;
import edu.gatech.omscs.dscars.exception.SettingLockedException;
import edu.gatech.omscs.dscars.model.CoreEngineSetting;
import edu.gatech.omscs.dscars.model.Section;
import edu.gatech.omscs.dscars.model.User;
import edu.gatech.omscs.dscars.solver.CoreEngine;

public class SectionAdminAction extends SelectAction  {
	private static final long serialVersionUID = 9149826260758390091L;
	int sectionId;
	public SectionAdminAction() {
		super();
	}

	@SuppressWarnings("rawtypes")
	public String execute() {
		Map session = ActionContext.getContext().getSession();
		User user=(User) session.get("user");
		if ("delete".equals(buttonName)) {
	         SectionDAO sdao=new SectionDAO();
	         Section section=sdao.getSection(sectionId);
	         sdao.delete(sectionId);
	         addActionMessage("The course "+section.getCourse().getCourseId()+" "+section.getCourse().getCourseName()+"has been deleted sucessfully!");
	    }
		else if ("RunRecommendation".equals(buttonName)) {
			runRecommendation(user);
	    }
		if(semesterId!=null)
				setSectionList(user.getRole().equals("Admin"));
		setLists();
	    return SUCCESS;
	}
	
	private void runRecommendation(User user) {
		CoreEngineSetting setting=new CoreEngineSetting(user.getUserId(), 200, 200, semesterId);
		CoreEngineSettingDao coedao=new CoreEngineSettingDao();
		
		try {
			setting=coedao.addOrUpdate(setting);
			CoreEngine engine=new CoreEngine(setting);
			engine.solve();
			//addActionMessage("A core engine job is schedule to run recommendations. Please check results in a minute.");
			addActionMessage("Core engine has been run successfully and recommendations are shown below.");
		} catch (SettingLockedException e) {
			System.out.println(e);
			addActionError("A scheduled core engine job is already running recommendations. Please check results in a minute.");
		}
	}


	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
}
