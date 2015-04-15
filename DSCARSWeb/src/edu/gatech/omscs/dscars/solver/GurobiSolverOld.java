package edu.gatech.omscs.dscars.solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.gatech.omscs.dscars.model.PchSub;
import edu.gatech.omscs.dscars.model.Section;
import edu.gatech.omscs.dscars.model.PreferredCourseHistory;
import gurobi.GRB;
import gurobi.GRBEnv;
import gurobi.GRBException;
import gurobi.GRBLinExpr;
import gurobi.GRBModel;
import gurobi.GRBVar;


public class GurobiSolverOld implements ISolver{
	
	public Map<Integer, Integer> studentHashMap, sectionHashMap;
	public int num_of_Students, num_of_courses, num_of_TAs;
	public int studentid, sectionid, preferredsectionid;
	public int i, j, j1, j2;
	public String Constraint, Objective, Variables = "";
	
	public GRBEnv env;  
	public GRBModel model; 
	public GRBVar[][] schedule; 
	public GRBVar[] capacity; // objective - minimize class size
	
	public CoreEngine coreEngine;
	
	public void intialize()
	{
		try{
			studentHashMap = new HashMap<Integer, Integer>();
			  sectionHashMap = new HashMap<Integer, Integer>();
				  
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
			       
		  	  num_of_Students = studentHashMap.size();
		  	  num_of_courses = sectionHashMap.size();
				  
			  env   = new GRBEnv("Solver.log");
		      model = new GRBModel(env);
		      schedule = new GRBVar[num_of_Students + 1][num_of_courses + 1];
		      capacity = new GRBVar[num_of_courses + 1]; // Add Integer variables for class capacity. 
		}
		catch (GRBException e) {
		      System.out.println("Error code: " + e.getErrorCode() + ". " +
		                         e.getMessage());
		    }
	}
	
	public void solve(CoreEngine coreEngine){
		
		try{
			
			  studentHashMap = new HashMap<Integer, Integer>();
			  sectionHashMap = new HashMap<Integer, Integer>();
			  
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
		       
	    	  num_of_Students = studentHashMap.size();
	    	  num_of_courses = sectionHashMap.size();
			  
			  env   = new GRBEnv("Solver.log");
		      model = new GRBModel(env);
		      schedule = new GRBVar[num_of_Students + 1][num_of_courses + 1];
		      capacity = new GRBVar[num_of_courses + 1]; // Add Integer variables for class capacity. 		      	      		     
		      
		      // Create variables - All variables are binary except X (capacity of the class).
		      // Binary variables
		      Variables = "Variables";
		      for (i = 1; i <= num_of_Students; i++)
		      {
		    	  for (j = 1; j <= num_of_courses; j++)
		    	  {		    		  
		    		  schedule[i][j] = model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "Y_" + i + "_" + j);		    		
		    		  System.out.println(Variables);
		    		  Variables = "Y_" + i + "_" + j;		
		    	  }
		      }
		      System.out.println(Variables);
		      
		      Objective = "Minimize ";
		      // Integer variables -  Xj is the recommended class size.
		      for (j = 1; j <= num_of_courses; j++)
	    	  {	
	    		  //Xj Can range from 0 to total number of students in the program.
	    		  capacity[j] = model.addVar(0.0, num_of_Students, 1.0, GRB.INTEGER, "x" + j);	
	    		  System.out.println(Objective);
	    		  Objective = "x" + j;		    	  
	    	  }
	    	  System.out.println(Objective);
	    	  
		      // Integrate new variables.	
		      model.update();
	
		      // Set objective: minimize class capacity variable	
		      GRBLinExpr obj = new GRBLinExpr();
		      	      
		      for (j = 1; j <= num_of_courses; j++)
	    	  {	
	    		  obj.addTerm(1.0, capacity[j]); 	
	    		  model.setObjective(obj, GRB.MINIMIZE);	
	    	  }
	    	  
	    	  //Constraint linear expressions
		      GRBLinExpr expr = new GRBLinExpr();
		      		      
			  //Create constraints
		      /*constraints group 1 - Allowed course/sem 
		      PreferredCourseHistorys can take up to allowed number of courses per semester. Considering PreferredCourseHistory's preferred courses for the next term only.
		      y_i_j -  i - PreferredCourseHistorys, j- preferred courses, Ni - no. of courses per semester for PreferredCourseHistory i
		      			  constraint 1: y_1_1 + y_1_2 + .. + y_1_3 <= N1
		      			  constraint 2: y_2_2 + y_2_3 + .. + y_2_6 <= N2
		      			  constraint 3: y_3_1 + y_3_2 + .. + y_3_4 <= N3*/

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
					Constraint += " + " + "Y" + i + j;		
				  }	
					
				  model.addConstr(expr, GRB.EQUAL, student.getNumCoursesDesired(),  "c1" + "_" + i );
				  Constraint += " = " + student.getNumCoursesDesired();
			  }
		      System.out.println(Constraint);
	    	  Constraint = "";
	    	  
		      /*constraint group 2 - Minimize Course capacity
		      number of PreferredCourseHistorys in any course should be less than or equal to the max capacity for the course (Xj).
		      		      y_i_j - i - PreferredCourseHistorys, j- courses , Xj - Max capacity for course j
		      			  constraint 1: y_1_1 + y_2_1 + .. + y_600_1 <= X1
		      			  constraint 2: y_1_2 + y_2_2 + .. + y_600_2 <= X2
		      			  constraint 3: y_1_3 + y_2_3 + .. + y_600_3 <= X3*/
		 	  Constraint = "Constraint 2: Course capacity";
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
								Constraint += " + " + "Y" + i + j;
							}							
						}		
				  }				 
				  if (Constraint != "")
				  {
					  model.addConstr(expr, GRB.LESS_EQUAL, capacity[j],  "c2" + "_" + j);	
					  Constraint += " <= " + "x" + j;	
				  }
				  			  
			  }
		      System.out.println(Constraint);
	    	  Constraint = "";
	    	  
	    	  /*constraint group 3 - Max Course capacity - Admin allocated
		      number of PreferredCourseHistorys in any course should be less than or equal to the max capacity for the course (Xj).
		      		      y_i_j - i - PreferredCourseHistorys, j- courses , Xj - Max capacity for course j
		      			  constraint 1: y_1_1 + y_2_1 + .. + y_600_1 <= X1
		      			  constraint 2: y_1_2 + y_2_2 + .. + y_600_2 <= X2
		      			  constraint 3: y_1_3 + y_2_3 + .. + y_600_3 <= X3*/
		 	  Constraint = "Constraint 3: Max Course capacity - Admin allocated";
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
								Constraint += " + " + "Y" + i + j;
							}							
						}		
				  }				 
				  if (Constraint != "")
				  {
					  model.addConstr(expr, GRB.LESS_EQUAL, section.getMaxClassSize(),  "c3" + "_" + j);	
					  Constraint += " <= " + section.getMaxClassSize();		
				  }		  
			  }
		      System.out.println(Constraint);
	    	  Constraint = "";
	    	  
			  /*constraint group 4 - Is course offered
			  number of PreferredCourseHistorys in a course that is not offered during next term should equal 0.
			  	y_i_j - i - PreferredCourseHistorys, j- courses. j course is not offered in next term.
			  			  constraint 1: y_1_1 + y_2_1+ .. + y_600_1 = 0
			  			  constraint 2: y_1_2 + y_2_2 + .. + y_600_2 = 0*/

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
						  Constraint += " + " + "Y" + i + j ;
					  }
					  model.addConstr(expr, GRB.EQUAL, 0,  "c4" + "_" + j);	
					  Constraint += " = 0";
				  }
			  }
			  System.out.println(Constraint);
	    	  Constraint = "";
	    	  
	    	  /*constraint group 5 -Priority
			  y_i_j_p - i - students, j- preferred courses, p - priority
 			  constraint 1: y_1_13_1 >= y_1_4_2 
			  constraint 2: y_1_4_2 >= y_1_3_3
			  constraint 3: y_1_13_1 >= y_1_3_3
	    	  */
	    	  
	    	  Constraint = "Constraint 5: Priority of preferred courses";
	    	  List<String> ConstraintName = new ArrayList<String>();
	    	
			  for (PreferredCourseHistory student : coreEngine.studentList)
			  {
				    studentid = student.getStudent().getStudentId();
				    System.out.println(Constraint);
			    	Constraint = "";
			    	  
				    Set<PchSub> set = student.getPchSubs();
					Iterator<PchSub> iter1 = set.iterator();
					while(iter1.hasNext()) 
					{
						PchSub thisstudentPreference = iter1.next();
						int thispreferredsectionid = thisstudentPreference.getSection().getSectionId();
						int thispriority = thisstudentPreference.getPriority();
						
						Iterator<PchSub> iter2 = set.iterator();
						while(iter2.hasNext()) 
						{
							PchSub eachstudentPreference = iter2.next();
							int eachpreferredsectionid = eachstudentPreference.getSection().getSectionId();
							int eachpriority = eachstudentPreference.getPriority();
							
							if (thispreferredsectionid != eachpreferredsectionid)
							{
								i = studentHashMap.get(studentid);
								if (thispriority < eachpriority)
								{
									j1 = sectionHashMap.get(thispreferredsectionid);
									j2 = sectionHashMap.get(eachpreferredsectionid);
									
								}
								else
								{
									j1 = sectionHashMap.get(eachpreferredsectionid);
									j2 = sectionHashMap.get(thispreferredsectionid);	
								}
								
								if (!ConstraintName.contains("c5" + "_" + i + "_" +  j1 + "_" +  j2))
								{
									ConstraintName.add("c5" + "_" + i + "_" +  j1 + "_" +  j2);
									expr = new GRBLinExpr();
									expr.addTerm(1.0, schedule[i][j1]);	
									Constraint += "Y" + i + j1;
									
									model.addConstr(expr, GRB.GREATER_EQUAL, schedule[i][j2],  "c5" + "_" + i + "_" +  j1 + "_" +  j2);	
									Constraint += " >= " + "Y" + i + j2;	
									
									System.out.println(Constraint);
							    	Constraint = "";					
								}
											
							}		
						}
											
					}		
			  }
			  System.out.println(Constraint);
	    	  Constraint = "";
	    	  
	    	  
	    	  
		      // Optimize model
			  model.optimize();
		      		
			  for (i = 1; i <= num_of_Students; i++)
		      {
		    	  for (j = 1; j <= num_of_courses; j++)
		    	  {	
		    		  System.out.println(schedule[i][j].get(GRB.StringAttr.VarName)
		                        + " " +schedule[i][j].get(GRB.DoubleAttr.X));		    			 
		    	  } 			    	  
	    	 
		      }	      
		     
		      System.out.println("Obj: ");
		      for (j = 1; j <= num_of_courses; j++)
	    	  {
		    	  System.out.println(capacity[j].get(GRB.StringAttr.VarName)
	                         + " " +capacity[j].get(GRB.DoubleAttr.X));	
		    	  
	    	  }
		      			      
		      	
		      //System.out.println("Obj: " + model.get(GRB.DoubleAttr.ObjVal));
		      
		      model.write("Solver.lp");
		      model.write("Solver.sol");
	
		      // Dispose of model and environment
	
		      model.dispose();
		      env.dispose();

	    } catch (GRBException e) {
	      System.out.println("Error code: " + e.getErrorCode() + ". " +
	                         e.getMessage());
	    }
	}
	
}
