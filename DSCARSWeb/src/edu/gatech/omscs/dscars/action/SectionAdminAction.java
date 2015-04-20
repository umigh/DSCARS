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
			return  "recommendation";
	    }
		if(semesterId!=null)
				setSectionList(user.getRole().equals("Admin"));
		setLists();
	    return SUCCESS;
	}
	
	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
}
