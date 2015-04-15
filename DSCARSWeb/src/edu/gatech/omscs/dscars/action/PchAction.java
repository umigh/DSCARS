package edu.gatech.omscs.dscars.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.opensymphony.xwork2.ActionContext;

import edu.gatech.omscs.dscars.dao.CoreEngineSettingDao;
import edu.gatech.omscs.dscars.dao.PchDAO;
import edu.gatech.omscs.dscars.exception.SettingLockedException;
import edu.gatech.omscs.dscars.model.CoreEngineSetting;
import edu.gatech.omscs.dscars.model.PchSub;
import edu.gatech.omscs.dscars.model.PreferredCourseHistory;
import edu.gatech.omscs.dscars.model.Section;
import edu.gatech.omscs.dscars.model.Semester;
import edu.gatech.omscs.dscars.model.User;
import edu.gatech.omscs.dscars.solver.CoreEngine;



public class PchAction extends SelectAction  {
	private static final long serialVersionUID = 9149826260758390091L;
	PreferredCourseHistory pch;
	int sectionId;
	int pchSubIdcount;
	String pchSubIds;
	int numCoursesDesired;
	List<Integer> eligibleCourseList;
	public PchAction() {
		super();
		pch=new PreferredCourseHistory();

	}

	@SuppressWarnings("rawtypes")
	public String execute() {
		Map session = ActionContext.getContext().getSession();
		User user=(User) session.get("user");
		PchDAO dao=new PchDAO();
		numCoursesDesired=pch.getNumCoursesDesired();
		pch=new PreferredCourseHistory();
		if ("AddCourse".equals(buttonName)) {
	         addCourse(user);
	    }
		else if("SavePch".equals(buttonName)) {
			savePch(user, dao);
		}
		else if("RunRecommendation".equals(buttonName)) {
			runRecommendation(user, dao);
		}
		

		if(semesterId!=null)
			getPchList(user.getUserId());
	
		setLists();
		filterSections(user, dao);
	    return SUCCESS;
	}

	private void filterSections(User user, PchDAO dao) {
		setSectionList();
		eligibleCourseList=dao.getEligibleCourses(user.getUserId());
		for(int i=0;i<sections.size();i++) {
			if(!eligibleCourseList.contains(((Section) sections.get(i)).getCourse().getCourseId())) {
				sections.remove(i);
			}
		}
		if(pch!=null && pch.getPchSubs()!=null) {
			Iterator<PchSub> iter=pch.getPchSubs().iterator();
			while(iter.hasNext()) {
				PchSub sub=iter.next();
				for(int i=0;i<sections.size();i++) {
					int subSectionId=sub.getSection().getSectionId();
					int selectSectionId=sections.get(i).getSectionId();
					if(subSectionId==selectSectionId) {
						sections.remove(i);
					}
				}
			}
		}
		

	}

	
	private void runRecommendation(User user, PchDAO dao) {
		savePch(user, dao);
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
	private void savePch(User user, PchDAO dao) {
		List<Integer> newSectionIds=new ArrayList<Integer>();
		StringTokenizer tokens=new StringTokenizer(pchSubIds,",");
		while(tokens.hasMoreTokens()) {
			String token=tokens.nextToken();
			if(token!=null && !"".equals(token)) {
				int pchSubId=Integer.parseInt(token);
				newSectionIds.add(pchSubId);
			}
		}

		
		pch=dao.getStudentPchBySemester(semesterId, user.getUserId());
		Iterator<PchSub> iter=pch.getPchSubs().iterator();
		while(iter.hasNext()) {
			PchSub sub=iter.next();
			if(!newSectionIds.contains(sub.getPchSubId())) {
				dao.deleteSub(sub.getPchSubId());
			}
			else {
				sub.setPriority(newSectionIds.indexOf(sub.getPchSubId())+1);
				dao.updateSub(sub);
			}
		}
		pch.setNumCoursesDesired(numCoursesDesired);
		dao.update(pch);
	}
	
	private void getPchList(int studentId) {
		PchDAO dao=new PchDAO();
		pch=dao.getStudentPchBySemester(semesterId, studentId);
	}

	public void addCourse(User user) {
		PchDAO dao=new PchDAO();
		PreferredCourseHistory pch=dao.getStudentPchBySemester(semesterId, user.getUserId());
		if(pch==null) {
			pch=new PreferredCourseHistory(user.getUserId(), 1, new Date());
			pch.setSemester(new Semester(semesterId));
			//pch.setProgram(new Program(programId));
			dao.add(pch);
			pch=dao.getStudentPchBySemester(semesterId, user.getUserId());
		}
		PchSub sub=new PchSub();
		edu.gatech.omscs.dscars.model.Section section=new Section();
		section.setSectionId(sectionId);
		sub.setPreferredCourseHistory(pch);
		sub.setSection(section);
		sub.setCreateDate(new Date());
		sub.setCreateUser(user.getUserName());
		sub.setPriority(pch.getPchSubs().size()+1);
		dao.addSub(sub);
		pch=dao.getStudentPchBySemester(semesterId, user.getUserId());
		pch.setNumCoursesDesired(numCoursesDesired);
		dao.update(pch);
	}
	
	
	public void validateForm() {
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

	public int getPchSubIdcount() {
		return pchSubIdcount;
	}

	public void setPchSubIdcount(int pchSubIdcount) {
		this.pchSubIdcount = pchSubIdcount;
	}


	public String getPchSubIds() {
		return pchSubIds;
	}

	public void setPchSubIds(String pchSubIds) {
		this.pchSubIds = pchSubIds;
	}
}
