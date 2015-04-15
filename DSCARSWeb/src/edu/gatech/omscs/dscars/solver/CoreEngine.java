package edu.gatech.omscs.dscars.solver;

import java.util.List;

import edu.gatech.omscs.dscars.dao.CoreEngineSettingDao;
import edu.gatech.omscs.dscars.dao.InstructorDao;
import edu.gatech.omscs.dscars.dao.PchDAO;
import edu.gatech.omscs.dscars.dao.SectionDAO;
import edu.gatech.omscs.dscars.model.CoreEngineSetting;
import edu.gatech.omscs.dscars.model.Instructor;
import edu.gatech.omscs.dscars.model.PreferredCourseHistory;
import edu.gatech.omscs.dscars.model.Section;


public class CoreEngine {
	protected CoreEngineSetting setting;
	protected List<PreferredCourseHistory> studentList;
	protected List<Section> sectionList;
	protected List<Instructor> tAList;
	protected Integer studentsPerInstructor;
	protected Integer maxClassSizeDefault;
	protected Integer studentsPerTA;
	protected ISolver solver;
	private CoreEngineSettingDao cedao=null;
  
  
  public static void main(String[] args) {		 
	  CoreEngineSettingDao cedao=new CoreEngineSettingDao();	  
	  while(true) {
		  try {
			  List<CoreEngineSetting> settings=cedao.getAllNewCoreEngineSetting();
			  for(int i=0;i<settings.size();i++) {
				  CoreEngineSetting setting=null;
				  try {
					  setting=settings.get(i);
					  CoreEngine engine = new CoreEngine(setting);
					  engine.solve();
				  }
				  catch(Exception e) {
					  System.err.print("Error running core engine: "+e);
					  System.err.print("Active Semester: "+setting.getActiveSemester().getSemesterId()+" user: "+setting.getUser().getUserName());
				  }
			  }
			  break;
			  //Thread.sleep(5*1000); //5 seconds
		  }
		  catch(Exception e) {
			  System.err.print("Error running core engine: "+e);
			  System.err.print("Could not load core engine settings");
			  System.exit(1);
		  }

	  }
  }
		  
  
  public CoreEngine(CoreEngineSetting setting) {
	  this.setting=setting;
	  cedao=new CoreEngineSettingDao();
	  
	  try {		  
		  cedao.lock(setting);
		  //Use Gurobi to Solve the problem
		  solver = new GurobiSolver();
	
		  SectionDAO s = new SectionDAO();
		  this.sectionList = s.getSectionsOffered(setting.getActiveSemester().getSemesterId());	

		  PchDAO pc = new PchDAO();
		  this.studentList = pc.getStudentPch(setting.getActiveSemester().getSemesterId());	
		  
		  InstructorDao idao=new InstructorDao();
		  this.tAList=idao.listTAs();
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  }
  }
  
  public void solve(){
	  solver.solve(this);
	  cedao.complete(setting);
  }
}