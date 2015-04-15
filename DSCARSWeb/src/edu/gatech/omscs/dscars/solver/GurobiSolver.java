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
	
	public Map<Integer, Integer> studentHashMap, sectionHashMap;
	public int num_of_Students, num_of_courses, num_of_TAs;
	public int studentid = 0, sectionid = 0, preferredsectionid = 0;
	public int i = 0, j = 0, i1 = 0, i2 = 0, j1 = 0, j2 = 0;
	public String Constraint, Objective, Variables = "";
	
	public GRBEnv env;  
	public GRBModel model; 
	public GRBVar[][] schedule; 
	public GRBVar[] classsize; // objective - minimize class size
	public GRBLinExpr expr;
	
	public CoreEngine coreEngine;
	
	public void solve(CoreEngine coreEngine){
		
		try{			
			  this.Intialize(coreEngine);  
		      this.SetVariables();		      
		      this.SetObjectiveVariables();	      
		     
		      model.update(); 	
	
		      this.SetObjective();
	    	  
	    	  //Constraint linear expressions
		      	 
		      this.CreateConstraint_NumCoursesDesired();		      		      
			  this.CreateConstraint_MinimizeClassSize();	    	  
		      this.CreateConstraint_MaxClassSize();	    	  
	    	  this.CreateConstraint_IsCourseOffered();	   
	    	  //this.CreateConstraint_PreferredCoursePriority();
			  //this.CreateConstraint_StudentSeniority();  	
			       
			  model.optimize();
		      		
			  updateSchedule();		     
		      updateClassSize();
		      
		     /* model.write("Solver.lp");
		      model.write("Solver.sol");*/
	
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
	    		  schedule[i][j] = model.addVar(0.0, 1.0, 0.0, GRB.BINARY, "Y_" + i + "_" + j);		    		
	    		  //System.out.println(Variables);
	    		  Variables = "Y_" + i + "_" + j;		
	    	  }
	      }
	      //System.out.println(Variables);
		  }
	      catch (GRBException e) {
		      System.out.println("Error code: " + e.getErrorCode() + ". " +
		                         e.getMessage());
		  }
	}
	
	public void SetObjectiveVariables()
	{
		try{
			 Objective = "Minimize ";
		      // Integer variables -  Xj is the recommended class size.
		      for (j = 1; j <= num_of_courses; j++)
	    	  {	
	    		  //Xj Can range from 0 to total number of students in the program.
	    		  classsize[j] = model.addVar(0.0, num_of_Students, 1.0, GRB.INTEGER, "x" + j);	
	    		  //System.out.println(Objective);
	    		  Objective = "x" + j;		    	  
	    	  }
	    	  //System.out.println(Objective);
		}
    	catch (GRBException e) {
	      System.out.println("Error code: " + e.getErrorCode() + ". " +
	                         e.getMessage());
	  }
	}
	
	public void SetObjective()
	{
		try{
			// Set objective: minimize class capacity variable	
		      GRBLinExpr obj = new GRBLinExpr();
		      	      
		      for (j = 1; j <= num_of_courses; j++)
	    	  {	
	    		  obj.addTerm(1.0, classsize[j]); 	
	    		  model.setObjective(obj, GRB.MINIMIZE);	
	    	  }
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
		      y_i_j -  i - PreferredCourseHistorys, j- preferred courses, Ni - no. of courses per semester for PreferredCourseHistory i
		      			  constraint 1: y_1_1 + y_1_2 + .. + y_1_3 <= N1
		      			  constraint 2: y_2_2 + y_2_3 + .. + y_2_6 <= N2
		      			  constraint 3: y_3_1 + y_3_2 + .. + y_3_4 <= N3*/

			  Constraint = "Constraint 1: Number of courses desired ";
		      for (PreferredCourseHistory student : coreEngine.studentList)
			  {
		    	  //System.out.println(Constraint);
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
				  Constraint = "c1" + "_" + i + " -> " + Constraint;
			  }
		      //System.out.println(Constraint);
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
		      		      y_i_j - i - PreferredCourseHistorys, j- courses , Xj - recommended class size j
		      			  constraint 1: y_1_1 + y_2_1 + .. + y_600_1 <= X1
		      			  constraint 2: y_1_2 + y_2_2 + .. + y_600_2 <= X2
		      			  constraint 3: y_1_3 + y_2_3 + .. + y_600_3 <= X3*/
		 	  Constraint = "Constraint 2: Minimize Class Size";
		 	  for (Section section: coreEngine.sectionList)
	    	  {	
		 		  sectionid = section.getSectionId();
		    	  //System.out.println(Constraint);
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
					  model.addConstr(expr, GRB.LESS_EQUAL, classsize[j],  "c2" + "_" + j);	
					  Constraint += " <= " + "x" + j;	
					  Constraint = "c2" + "_" + j + " -> " + Constraint;
				  }
				  			  
			  }
		      //System.out.println(Constraint);
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
		      		      y_i_j - i - PreferredCourseHistorys, j- courses , Xj - Max capacity for course j
		      			  constraint 1: y_1_1 + y_2_1 + .. + y_600_1 <= X1
		      			  constraint 2: y_1_2 + y_2_2 + .. + y_600_2 <= X2
		      			  constraint 3: y_1_3 + y_2_3 + .. + y_600_3 <= X3*/
		 	  Constraint = "Constraint 3: Max Class Size - Admin allocated";
		 	  for (Section section: coreEngine.sectionList)
	    	  {	
		 		  sectionid = section.getSectionId();
		    	  //System.out.println(Constraint);
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
					  Constraint = "c3" + "_" + j + " -> " + Constraint;
				  }		  
			  }
		      //System.out.println(Constraint);
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
			  	y_i_j - i - PreferredCourseHistorys, j- courses. j course is not offered in next term.
			  			  constraint 1: y_1_1 + y_2_1+ .. + y_600_1 = 0
			  			  constraint 2: y_1_2 + y_2_2 + .. + y_600_2 = 0*/

			  Constraint = "Constraint 4: Is course offered";
			  for (Section section: coreEngine.sectionList)
			  {
				  sectionid = section.getSectionId();
				  //System.out.println(Constraint);
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
					  Constraint = "c4" + "_" + j + " -> " + Constraint;
				  }
			  }
			  //System.out.println(Constraint);
	    	  Constraint = "";
		}
    	catch (GRBException e) {
	      System.out.println("Error code: " + e.getErrorCode() + ". " +
	                         e.getMessage());
	  }
	}
	
	public void CreateConstraint_PreferredCoursePriority()
	{
		try{
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
				    //System.out.println(Constraint);
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
								
								int j, k;
								i = studentHashMap.get(studentid);
								if (thispriority > eachpriority)
								{
									j = sectionHashMap.get(thispreferredsectionid);
									k = sectionHashMap.get(eachpreferredsectionid);
									
								}
								else
								{
									j = sectionHashMap.get(eachpreferredsectionid);
									k = sectionHashMap.get(thispreferredsectionid);	
								}
								
								if (!ConstraintName.contains("c5" + "_" + i + "_" +  j + "_" +  k))
								{
									ConstraintName.add("c5" + "_" + i + "_" +  j + "_" +  k);
									GRBLinExpr expr1 = new GRBLinExpr();
									expr1.addTerm(1.0, schedule[i][j]);	
									Constraint += "Y" + i + j;
									
									GRBLinExpr expr2 = new GRBLinExpr();
									expr2.addTerm(1.0, schedule[i][k]);	
									Constraint += "<= Y" + i + k;
									
									model.addConstr(expr1, GRB.LESS_EQUAL, expr2 ,  "c5" + "_" + i + "_" +  j + "_" +  k);	
									//Constraint += " >= 0 ";	
									
									/*model.addConstr(expr, GRB.GREATER_EQUAL, schedule[i][k],  "c5" + "_" + i + "_" +  j + "_" +  k);	
									Constraint += " >= " + "Y" + i + k;	
									*/Constraint = "c5" + "_" + i + "_" +  j + "_" +  k + " -> " + Constraint;
									
									//System.out.println(Constraint);
							    	Constraint = "";					
								}
											
							}		
						}
											
					}		
			  }
			  //System.out.println(Constraint);
	    	  Constraint = "";
		}
    	catch (GRBException e) {
	      System.out.println("Error code: " + e.getErrorCode() + ". " +
	                         e.getMessage());
	  }
	}
	
	public void CreateConstraint_StudentSeniority()
	{
		try
		{
			/*constraint group 5 -Student Seniority
			  y_i_j_p - i - students, j- preferred courses, s - Seniority
			  constraint 1: y_1_1_3 >= y_2_1_2 
			  constraint 2: y_2_1_2 >= y_4_1_0
			  constraint 3: y_5_13_6 >= y_1_13_3
	    	  */
	    	  
	    	  Constraint = "Constraint 6: Student Seniority";
	    	  List<String> ConstraintName = new ArrayList<String>();
	    	
			  for (PreferredCourseHistory thisStudent : coreEngine.studentList)
			  {
				    int thisStudentid = thisStudent.getStudent().getStudentId();
				    int thisSeniority = thisStudent.getStudent().getNumCoursesCompleted();
				    //System.out.println(Constraint);
			    	Constraint = "";
			    	  
				    Set<PchSub> thisSet = thisStudent.getPchSubs();
					Iterator<PchSub> thisIter = thisSet.iterator();
					while(thisIter.hasNext()) 
					{
						PchSub thisStudentPreference = thisIter.next();
						int thisPreferredSectionid = thisStudentPreference.getSection().getSectionId();
						
						for (PreferredCourseHistory eachStudent : coreEngine.studentList)
						{
							int eachStudentid = eachStudent.getStudent().getStudentId();
						    int eachSeniority = eachStudent.getStudent().getNumCoursesCompleted();
							if (thisStudentid != eachStudentid)
							{							    
							    //System.out.println(Constraint);
						    	Constraint = "";
						    	  
							    Set<PchSub> eachSet = eachStudent.getPchSubs();
								Iterator<PchSub> eachIter = eachSet.iterator();
								while(eachIter.hasNext()) 
								{
									PchSub eachStudentPreference = eachIter.next();
									int eachPreferredSectionid = eachStudentPreference.getSection().getSectionId();
									
									if (thisPreferredSectionid == eachPreferredSectionid)
									{
										j = sectionHashMap.get(thisPreferredSectionid);
										if (thisSeniority > eachSeniority)
										{
											i1 = studentHashMap.get(thisStudentid);
											i2 = studentHashMap.get(eachStudentid);
											
										}
										else
										{
											i1 = studentHashMap.get(eachStudentid);
											i2 = studentHashMap.get(thisStudentid);											
										}
										
										if (!ConstraintName.contains("c6" + "_" + i1 + "_" +  i2 + "_" +  j))
										{
											ConstraintName.add("c6" + "_" + i1 + "_" +  i2 + "_" +  j);
											
											expr = new GRBLinExpr();	
											 
											expr.addTerm(1.0, schedule[i1][j]);	
											Constraint += "Y" + i1 + j;
											
											model.addConstr(expr, GRB.GREATER_EQUAL, schedule[i2][j],  "c5" + "_" + i + "_" +  j1 + "_" +  j2);	
											Constraint += " >= " + "Y" + i2 + j;	
											Constraint = "c6" + "_" + i1 + "_" +  i2 + "_" +  j + " -> " + Constraint;
											
											//System.out.println(Constraint);
									    	Constraint = "";					
										}
								     }
								 }
							}
						 }
					  }
			       }
				   //System.out.println(Constraint);
			       Constraint = "";
			}
	    	catch (GRBException e) 
		    {
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
