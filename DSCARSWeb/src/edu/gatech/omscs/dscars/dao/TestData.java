package edu.gatech.omscs.dscars.dao;

import edu.gatech.omscs.dscars.model.Course;
import edu.gatech.omscs.dscars.model.Program;
import edu.gatech.omscs.dscars.model.Student;
import edu.gatech.omscs.dscars.model.User;

public class TestData {
	
	public  static void main(String agrs[]) {
		ProgramDAO pdao=new ProgramDAO();
		Program program=new Program();
		program.setProgramId(new Long(1));
		program.setProgramName("MS in Computer Science");
		program.setLevel("Graduate Semester");
		program.setAdmitTerm("Spring 2014");
		program.setAdmitType("Masters");
		program.setCatalogTerm("Spring 2014");
		program.setCollege("College of Computing");
		program.setCampus("Online");
		program.setMajor("Computer Science");
		program.setDepartment("Dept/Computer Science");
		pdao.add(program);

		UserDao udao=new UserDao();
		User user=new User("umashankar3", "dscars", 903049720, "Student","Umashankar", "Gaddameedi", "umashankar3@gatech.edu");
		udao.add(user);
		StudentDao sdao=new StudentDao();
		Student s=new Student();
		s.setStudentId(user.getGtid());
		s.setProgram(program);
		s.setEarnedCredit(9.0);
		sdao.add(s);
		
		user=new User("rwilson", "dscars", 903000002, "Admin","Rebeca", "Wilson", "rwilson@cc.gatech.edu");		
		udao.add(user);
		user=new User("dfaour", "dscars", 903000002, "TA","David", "Faour", "dfaour3@gatech.edu");		
		udao.add(user);
		user=new User("eric.feron", "dscars", 903000003, "Professor","Eric", "Feron", "eric.feron@aerospace.gatech.edu");		
		udao.add(user);
		user=udao.getUser("umashankar3");
		System.out.println("User Id: "+user.getUserId()+" FirstName: "+user.getFirstName());
		user=udao.getUser("rwilson");
		System.out.println("User Id: "+user.getUserId()+" FirstName: "+user.getFirstName());
		user=udao.getUser("dfaour");
		System.out.println("User Id: "+user.getUserId()+" FirstName: "+user.getFirstName());
		user=udao.getUser("eric.feron");
		System.out.println("User Id: "+user.getUserId()+" FirstName: "+user.getFirstName());

		CourseDAO cdao=new CourseDAO();		
		Course course=new Course(new Long(1),55955,"CS","6250","O01","O","ALP",3.0,"Computer Networks");
		cdao.add(course);
		course=new Course(new Long(2),55954,"CS","6300","O01","O","ALP",3.0,"Software Dev Process");
		cdao.add(course);
		course=new Course(new Long(3),56425,"CS","6475","O01","O","ALP",3.0,"Comp. Photography");
		cdao.add(course);
		course=new Course(new Long(4),55956,"CS","8803","O01","O","ALP",3.0,"Special Topics");
		cdao.add(course);
		course=new Course(new Long(5),56424,"CS","8803","O02","O","ALP",3.0,"Special Topics Intro to OS");
		cdao.add(course);
	}
}
