package edu.gatech.omscs.dscars.dao;

import edu.gatech.omscs.dscars.model.Course;
import edu.gatech.omscs.dscars.model.Program;
import edu.gatech.omscs.dscars.model.Student;
import edu.gatech.omscs.dscars.model.User;

public class TestData {
	
	public  static void main(String agrs[]) {
		/*
		ProgramDAO pdao=new ProgramDAO();
		Program program=new Program();
		program.setProgramId(1);
		program.setProgramName("MS in Computer Science");
		pdao.add(program);
*/
		UserDao udao=new UserDao();
		User user=new User(903000001,"umashankar3","Umashankar", "Gaddameedi", "Student", "dscars");
		//udao.add(user);
		
		StudentDao sdao=new StudentDao();
		Student s=new Student();
		s.setStudentId(user.getUserId());
		s.setMaxCoursesPerTerm(2);
		sdao.add(s);
		
		user=new User( 903000002,"rwilson", "Rebeca", "Wilson", "Admin","dscars");		
		udao.add(user);
		user=new User( 903000003, "dfaour","David", "Faour","TA", "dscars");		
		udao.add(user);
		user=new User(903000004, "eric.feron","Eric", "Feron","Professor","dscars");		
		udao.add(user);
		
		user=udao.getUserByUserName("umashankar3");
		System.out.println("User Id: "+user.getUserId()+" FirstName: "+user.getFirstName());
		user=udao.getUserByUserName("rwilson");
		System.out.println("User Id: "+user.getUserId()+" FirstName: "+user.getFirstName());
		user=udao.getUserByUserName("dfaour");
		System.out.println("User Id: "+user.getUserId()+" FirstName: "+user.getFirstName());
		user=udao.getUserByUserName("eric.feron");
		System.out.println("User Id: "+user.getUserId()+" FirstName: "+user.getFirstName());

		CourseDAO cdao=new CourseDAO();		
		Course course=new Course(6250,"Computer Networks");
		cdao.add(course);
		course=new Course(6300,"Software Dev Process");
		cdao.add(course);
		course=new Course(6475,"Comp. Photography");
		cdao.add(course);
		course=new Course(8803,"Special Topics");
		cdao.add(course);
	}
}
