package edu.gatech.omscs.dscars.solver;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.gatech.omscs.dscars.dao.PchDAO;
import edu.gatech.omscs.dscars.dao.SectionDAO;
import edu.gatech.omscs.dscars.dao.SectionStudentDao;
import edu.gatech.omscs.dscars.model.PchSub;
import edu.gatech.omscs.dscars.model.Section;
import edu.gatech.omscs.dscars.model.PreferredCourseHistory;
import edu.gatech.omscs.dscars.model.SectionStudent;
import gurobi.GRB;
import gurobi.GRBEnv;
import gurobi.GRBException;
import gurobi.GRBLinExpr;
import gurobi.GRBModel;
import gurobi.GRBVar;


public class GurobiSolver implements ISolver{
	
	public Map<Integer, Integer> studentHashMap, sectionHashMap, priorityHashMap;
	public int num_of_Students, num_of_courses, num_of_TAs;
	public int studentid, sectionid, preferredsectionid;
	public int i, j, i1, i2, j1, j2;
	public String Constraint, Objective, Variables = "";
	
	public GRBEnv env;  
	public GRBModel model; 
	public GRBVar[][] schedule; 
	public GRBVar[] classsize; // objective - minimize class size
	public GRBVar x, z;
	public GRBLinExpr expr;
	
	public CoreEngine coreEngine;
	
public void solve(CoreEngine coreEngine){
		
		try{			
			  this.Intialize(coreEngine);  
		      this.SetVariables();		      
		      this.SetObjectiveVariables_StudentHappiness();
		      model.update(); 	
	
		      this.SetObjective_StudentHappiness();
		      
		      //Constraint linear expressions
		      	 
		      this.CreateConstraint_NumCoursesDesired();		      		      
			  this.CreateConstraint_MaxClassSize();
			  this.CreateConstraint_MinimizeClassSize();	
	    	  this.CreateConstraint_IsCourseOffered();	   
	    	  this.CreateConstraint_PrioritySeniority();   //Student Happiness
			   
	    	 
			  model.optimize();			  
			  		
			  this.updateSchedule();		     
		      this.updateClassSize();
		      
		      this.PrintClassSize();
		      this.PrintSchedule();
		      	     
		      model.dispose();
		      env.dispose();

	    } catch (GRBException e) {
	      System.out.println("Error code: " + e.getErrorCode() + ". " +
	                         e.getMessage());
	    }
	}
		

	public void Intialize(CoreEngine coreEngine)
	{		
	  this.coreEngine = coreEngine;
	  studentHashMap = new HashMap<Integer, Integer>();
	  sectionHashMap = new HashMap<Integer, Integer>();
	  priorityHashMap = new HashMap<Integer, Integer>();
		  
	  i = 0;
      for (PreferredCourseHistory student : coreEngine.studentList)
      {
    	  i ++;
    	  studentid = student.getStudent().getStudentId();
    	  studentHashMap.put(studentid, i);
      }
	      
	  j = 0; 
  	  for (Section section: coreEngine.sectionList)
  	  {
  		  j++;
  		  sectionid = section.getSectionId();
	      sectionHashMap.put(sectionid, j);	    		  		
  	  }
  	  
  	  int priority, happiness; happiness = 5;
  	  for ( priority = 1; priority <= 5; priority++ )
  	  {  		  
  		  priorityHashMap.put(priority, happiness);
  		  happiness--;  		  
  	  }
	       
  	  num_of_Students = studentHashMap.size();
  	  num_of_courses = sectionHashMap.size();
		  
	  schedule = new GRBVar[num_of_Students + 1][num_of_courses + 1];
      classsize = new GRBVar[num_of_courses + 1]; // Add Integer variables for class capacity. 
      
      try{
    	  env   = new GRBEnv("Solver.log");
          model = new GRBModel(env);    
      }
      catch (GRBException e) {
	      System.out.println("Error code: " + e.getErrorCode() + ". " +
	                         e.getMessage());
	  }
	}
	
	public void SetVariables()
	{
		try{
		  // Create variables - All variables are binary except X (capacity of the class).
	      // Binary variables
	      Variables = "Variables";
	      for (i = 1; i <= num_of_Students; i++)
	      {
	    	  for (j = 1; j <= num_of_courses; j++)
	    	  {		    		  
	    		  schedule[i][j] = model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "S_" + i + "_" + j);		    		
	    		  System.out.println(Variables);
	    		  Variables = "S_" + i + "_" + j;		
	    	  }
	      }
	      System.out.println(Variables);
	      
	      //Class size
	      Variables = "";
	      // Integer variables -  Xj is the recommended class size.
	      for (j = 1; j <= num_of_courses; j++)
    	  {	
    		  //Xj Can range from 0 to total number of students in the program.
    		  classsize[j] = model.addVar(0.0, num_of_Students, 1.0, GRB.INTEGER, "x" + j);	
    		  System.out.println(Variables);
    		  Variables = "x" + j;		    	  
    	  }
    	  System.out.println(Variables);
		  }
	      catch (GRBException e) {
		      System.out.println("Error code: " + e.getErrorCode() + ". " +
		                         e.getMessage());
		  }
	}
		
	public void SetObjectiveVariables_StudentHappiness()
	{
		try{
			 Objective = "Maximize ";
			 // Add X as Integer variable for class capacity. Can range from 0 to total number of student in the program.
		      x = model.addVar(0.0, this.num_of_Students * this.num_of_courses * 1000000000, 1.0, GRB.INTEGER, "x");
		      Objective = "x";
	    	  System.out.println(Objective);
		}
    	catch (GRBException e) {
	      System.out.println("Error code: " + e.getErrorCode() + ". " +
	                         e.getMessage());
	  }
	}
	
	public void SetObjective_StudentHappiness()
	{
		try{
			// Set objective: MAXIMIZE StudentHappiness
		      GRBLinExpr obj = new GRBLinExpr();
		      	      
		      obj.addTerm(1.0, x);
	    	  model.setObjective(obj, GRB.MAXIMIZE);	
	    	 
		}
    	catch (GRBException e) {
	      System.out.println("Error code: " + e.getErrorCode() + ". " +
	                         e.getMessage());
	  }
	}
	
	public void CreateConstraint_NumCoursesDesired()
	{
		try{
			  //Create constraints
		      /*constraints group 1 - Number of courses desired/sem 
		      PreferredCourseHistorys can take up to allowed number of courses per semester. Considering PreferredCourseHistory's preferred courses for the next term only.
		      S_i_j -  i - PreferredCourseHistorys, j- preferred courses, Ni - no. of courses per semester for PreferredCourseHistory i
		      			  constraint 1: S_1_1 + S_1_2 + .. + S_1_3 <= N1
		      			  constraint 2: S_2_2 + S_2_3 + .. + S_2_6 <= N2
		      			  constraint 3: S_3_1 + S_3_2 + .. + S_3_4 <= N3*/

			  Constraint = "Constraint 1: Number of courses desired ";
		      for (PreferredCourseHistory student : coreEngine.studentList)
			  {
		    	  System.out.println(Constraint);
		    	  Constraint = "";
		    	  expr = new GRBLinExpr();		
		    	
		    	  studentid = student.getStudent().getStudentId();
				  Set<PchSub> set = student.getPchSubs();
				  Iterator<PchSub> iter = set.iterator();
				  while(iter.hasNext()) 
				  {
					PchSub studentPreference = iter.next();
					preferredsectionid = studentPreference.getSection().getSectionId();
					i = studentHashMap.get(studentid);
					j = sectionHashMap.get(preferredsectionid);
					expr.addTerm(1.0, schedule[i][j]);	
					Constraint += " + " + "S" + i + j;		
				  }	
					
				  model.addConstr(expr, GRB.LESS_EQUAL, student.getNumCoursesDesired(),  "c1" + "_" + i );
				  Constraint += " <= " + student.getNumCoursesDesired();
				  Constraint = "c1" + "_" + i + " -> " + Constraint;
			  }
		      System.out.println(Constraint);
	    	  Constraint = "";
		}
    	catch (GRBException e) {
	      System.out.println("Error code: " + e.getErrorCode() + ". " +
	                         e.getMessage());
	  }
	}
	
	public void CreateConstraint_MinimizeClassSize()
	{
		try{
			/*constraint group 2 - Minimize Class Size - recommendation (objective)
		      number of PreferredCourseHistorys in any course should be less than or equal to the max capacity for the course (Xj).
		      		      S_i_j - i - PreferredCourseHistorys, j- courses , Xj - recommended class size j
		      			  constraint 1: S_1_1 + S_2_1 + .. + S_600_1 <= X1
		      			  constraint 2: S_1_2 + S_2_2 + .. + S_600_2 <= X2
		      			  constraint 3: S_1_3 + S_2_3 + .. + S_600_3 <= X3*/
		 	  Constraint = "Constraint 2: Minimize Class Size";
		 	  for (Section section: coreEngine.sectionList)
	    	  {	
		 		  sectionid = section.getSectionId();
		    	  System.out.println(Constraint);
		    	  Constraint = "";
				  expr = new GRBLinExpr();
					
				  for (PreferredCourseHistory student : coreEngine.studentList)
				  {
					    studentid = student.getStudent().getStudentId();
					    Set<PchSub> set = student.getPchSubs();
						Iterator<PchSub> iter = set.iterator();
						while(iter.hasNext()) 
						{
							PchSub studentPreference = iter.next();
							preferredsectionid = studentPreference.getSection().getSectionId();
							if (preferredsectionid == sectionid)
							{
								i = studentHashMap.get(studentid);
								j = sectionHashMap.get(preferredsectionid);
								expr.addTerm(1.0, schedule[i][j]);	
								Constraint += " + " + "S" + i + j;
							}							
						}		
				  }				 
				  if (Constraint != "")
				  {
					  model.addConstr(expr, GRB.EQUAL, classsize[j],  "c2" + "_" + j);	
					  Constraint += " = " + "x" + j;	
					  Constraint = "c2" + "_" + j + " -> " + Constraint;
				  }
				  			  
			  }
		      System.out.println(Constraint);
	    	  Constraint = "";
		}
    	catch (GRBException e) {
	      System.out.println("Error code: " + e.getErrorCode() + ". " +
	                         e.getMessage());
	  }
	}
	
	public void CreateConstraint_MaxClassSize()
	{
		try{
			 /*constraint group 3 - MaxClassSize - Admin allocated
		      number of PreferredCourseHistorys in any course should be less than or equal to the max capacity for the course (Xj).
		      		      S_i_j - i - PreferredCourseHistorys, j- courses , Xj - Max capacity for course j
		      			  constraint 1: S_1_1 + S_2_1 + .. + S_600_1 <= X1
		      			  constraint 2: S_1_2 + S_2_2 + .. + S_600_2 <= X2
		      			  constraint 3: S_1_3 + S_2_3 + .. + S_600_3 <= X3*/
		 	  Constraint = "Constraint 3: Max Class Size - Admin allocated";
		 	  for (Section section: coreEngine.sectionList)
	    	  {	
		 		  sectionid = section.getSectionId();
		    	  System.out.println(Constraint);
		    	  Constraint = "";
				  expr = new GRBLinExpr();
					
				  for (PreferredCourseHistory student : coreEngine.studentList)
				  {
					    studentid = student.getStudent().getStudentId();
					    Set<PchSub> set = student.getPchSubs();
						Iterator<PchSub> iter = set.iterator();
						while(iter.hasNext()) 
						{
							PchSub studentPreference = iter.next();
							preferredsectionid = studentPreference.getSection().getSectionId();
							if (preferredsectionid == sectionid)
							{
								i = studentHashMap.get(studentid);
								j = sectionHashMap.get(preferredsectionid);
								expr.addTerm(1.0, schedule[i][j]);	
								Constraint += " + " + "S" + i + j;
							}							
						}		
				  }				 
				  if (Constraint != "")
				  {
					  model.addConstr(expr, GRB.LESS_EQUAL, section.getMaxClassSize(),  "c3" + "_" + j);	
					  Constraint += " <= " + section.getMaxClassSize();		
					  Constraint = "c3" + "_" + j + " -> " + Constraint;
				  }		  
			  }
		      System.out.println(Constraint);
	    	  Constraint = "";
		}
    	catch (GRBException e) {
	      System.out.println("Error code: " + e.getErrorCode() + ". " +
	                         e.getMessage());
	  }
	}
	
	public void CreateConstraint_IsCourseOffered()
	{
		try{
			/*constraint group 4 - Is course offered
			  number of PreferredCourseHistorys in a course that is not offered during next term should equal 0.
			  	S_i_j - i - PreferredCourseHistorys, j- courses. j course is not offered in next term.
			  			  constraint 1: S_1_1 + S_2_1+ .. + S_600_1 = 0
			  			  constraint 2: S_1_2 + S_2_2 + .. + S_600_2 = 0*/

			  Constraint = "Constraint 4: Is course offered";
			  for (Section section: coreEngine.sectionList)
			  {
				  sectionid = section.getSectionId();
				  System.out.println(Constraint);
		    	  Constraint = "";
				  if (!section.isOffered())
				  {
					  expr = new GRBLinExpr();
					  for (i = 1; i <= num_of_Students; i++)
				      {
						  j = sectionHashMap.get(sectionid);
						  expr.addTerm(1.0, schedule[i][j]);					
						  Constraint += " + " + "S" + i + j ;
					  }
					  model.addConstr(expr, GRB.EQUAL, 0,  "c4" + "_" + j);	
					  Constraint += " = 0";
					  Constraint = "c4" + "_" + j + " -> " + Constraint;
				  }
			  }
			  System.out.println(Constraint);
	    	  Constraint = "";
		}
    	catch (GRBException e) {
	      System.out.println("Error code: " + e.getErrorCode() + ". " +
	                         e.getMessage());
	  }
	}
		
		
	public void CreateConstraint_PrioritySeniority()
	{
		try
		{
			 Constraint = "Constraint 5: Priority ";
			  System.out.println(Constraint);
	    	  Constraint = "";
	    	  expr = new GRBLinExpr();	
		      for (PreferredCourseHistory student : coreEngine.studentList)
			  {	    	  	
		    	  studentid = student.getStudent().getStudentId();
		    	  int seniority = student.getStudent().getNumCoursesCompleted() + 1;
		    	  Set<PchSub> set = student.getPchSubs();
				  Iterator<PchSub> iter = set.iterator();
				  while(iter.hasNext()) 
				  {
					PchSub studentPreference = iter.next();
					preferredsectionid = studentPreference.getSection().getSectionId();
					int priorityid = studentPreference.getPriority();
					int happiness = this.priorityHashMap.get(priorityid);
					
					i = studentHashMap.get(studentid);
					j = sectionHashMap.get(preferredsectionid);
					
					expr.addTerm(happiness * seniority, schedule[i][j]);	
					Constraint += " + " + Integer.toString(happiness * seniority) + "S" + i + j;		
				  }										  
			  }
		      model.addConstr(expr, GRB.EQUAL, x,  "c5" + "_" + i );
			  Constraint += " = x";
			  Constraint = "c1" + "_" + i + " -> " + Constraint;
			  
		      System.out.println(Constraint);
	    	  Constraint = "";
	  }
   	  catch (GRBException e) 
	  {
	      System.out.println("Error code: " + e.getErrorCode() + ". " +
	                         e.getMessage());
	  }
	}
	public void PrintSchedule()
	{
		try{
			System.out.println("Student Schedule: ");
			for (i = 1; i <= num_of_Students; i++)
		      {
		    	  for (j = 1; j <= num_of_courses; j++)
		    	  {	
		    		  System.out.println(schedule[i][j].get(GRB.StringAttr.VarName)
		                        + " " +schedule[i][j].get(GRB.DoubleAttr.X));		    			 
		    	  } 			    	  
	    	 
		      }	      
		}
    	catch (GRBException e) {
	      System.out.println("Error code: " + e.getErrorCode() + ". " +
	                         e.getMessage());
	    }
	}
	
	public void PrintClassSize()
	{
		try{
			
			  System.out.println("Class Size: ");
		      for (j = 1; j <= num_of_courses; j++)
	    	  {
		    	  System.out.println(classsize[j].get(GRB.StringAttr.VarName)
	                         + " " +classsize[j].get(GRB.DoubleAttr.X));	
		    	  
	    	  }		      		
		      
		      System.out.println("Student Happiness: ");
		      System.out.println(x.get(GRB.StringAttr.VarName)
                      + " " +x.get(GRB.DoubleAttr.X));		
		      
		      /*System.out.println("Seniority: ");
		      System.out.println(z.get(GRB.StringAttr.VarName)
                      + " " +z.get(GRB.DoubleAttr.X));	*/	
   	
		      System.out.println("Obj: " + model.get(GRB.DoubleAttr.ObjVal));
		}
    	catch (GRBException e) {
	      System.out.println("Error code: " + e.getErrorCode() + ". " +
	                         e.getMessage());
	    }
	}
	
	public void updateSchedule()
	{
		try{
			SectionStudentDao dao=new SectionStudentDao();
			SectionStudent sectionStudent=null;
			PchDAO pchdao=new PchDAO();
			//System.out.println("Student Schedule: ");

			/*
			for (i = 1; i <= num_of_Students; i++)
		      {
		    	  for (j = 1; j <= num_of_courses; j++)
		    	  {	
		    		  System.out.println(schedule[i][j].get(GRB.StringAttr.VarName) + " " +schedule[i][j].get(GRB.DoubleAttr.X));	
		    	  } 			    	  
	    	 
		      }	
		     */  
		     
			
			//delete all previous section student recommendation before uppdating new.
			dao.deleteAll();
			
			Iterator<Integer> studentIdIter=studentHashMap.keySet().iterator();
			while(studentIdIter.hasNext()) {
				Integer studentId=studentIdIter.next();
				Iterator<Integer> sectionIdIter=sectionHashMap.keySet().iterator();
				while(sectionIdIter.hasNext()) {
					Integer sectionId=sectionIdIter.next();
					boolean recom=false;
					try {
						recom=schedule[studentHashMap.get(studentId)][sectionHashMap.get(sectionId)].get(GRB.DoubleAttr.X)>0;
					}
					catch(Exception e) {
						System.out.println("Error code: " + ((GRBException) e).getErrorCode() + ". " +e.getMessage());
					}
					//System.out.println("Student Id: "+studentId+" Section: "+sectionId+" Recommendation: "+recom);
					if(recom) {
						sectionStudent=new SectionStudent(sectionId, studentId);
						dao.add(sectionStudent);
					}
					
				}
			}
			
			
			for(int i=0;i<coreEngine.studentList.size();i++) {
				Iterator<PchSub> iter=coreEngine.studentList.get(i).getPchSubs().iterator();
				while(iter.hasNext()) {
					PchSub sub=iter.next();
					
					int studentIndex=studentHashMap.get(sub.getPreferredCourseHistory().getStudent().getStudentId());
					int sectionIndex=sectionHashMap.get(sub.getSection().getSectionId());
					boolean recommendation=false;
					try {
						recommendation=schedule[studentIndex][sectionIndex].get(GRB.DoubleAttr.X)>0;
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					if(recommendation) {
						sub.setRecommendedDate(new Date());
					}
					else {
						sub.setRecommendedDate(null);
					}
					pchdao.updateSub(sub);
					
				}
			}

		}
    	catch (Exception e) {
	      System.out.println("Error code: " + ((GRBException) e).getErrorCode() + ". " +e.getMessage());
	    }
	}
	
	public void updateClassSize()
	{
		try{
			  SectionDAO sectiondao=new SectionDAO();
			  /*
			  System.out.println("Class Size: ");
		      for (j = 1; j <= num_of_courses; j++)
	    	  {
		    	  System.out.println(classsize[j].get(GRB.StringAttr.VarName)+ " " +classsize[j].get(GRB.DoubleAttr.X));	
		    	  
	    	  }		   
		      */
		      Iterator<Integer> sectionIter=sectionHashMap.keySet().iterator();
		      while(sectionIter.hasNext()) {
		    	  int sectionId=sectionIter.next();
		    	  int sectionIndex=sectionHashMap.get(sectionId);
		    	  double capacity=classsize[sectionIndex].get(GRB.DoubleAttr.X);		    	  
		    	  Section section= sectiondao.getSection(sectionId);
		    	  //System.out.println("Section Id: "+sectionId+" Course: "+section.getCourse().getCourseId()+" sectionIndex: "+sectionIndex+" Capacity: "+capacity);
		    	  section.setGeneratedClassSize((int) capacity); 
		    	  sectiondao.update(section);
		      }
		}
    	catch (GRBException e) {
	      System.out.println("Error code: " + e.getErrorCode() + ". " +
	                         e.getMessage());
	    }
	}
	
	
}
