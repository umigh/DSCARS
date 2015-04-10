package edu.gatech.omscs.dscars.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import edu.gatech.omscs.dscars.model.Contact;
import edu.gatech.omscs.dscars.model.Course;
import edu.gatech.omscs.dscars.model.Instructor;
import edu.gatech.omscs.dscars.model.PchSub;
import edu.gatech.omscs.dscars.model.PreferredCourseHistory;
import edu.gatech.omscs.dscars.model.Program;
import edu.gatech.omscs.dscars.model.Section;
import edu.gatech.omscs.dscars.model.Semester;
import edu.gatech.omscs.dscars.model.Student;
import edu.gatech.omscs.dscars.model.User;

@SuppressWarnings("unused")
public class TestData {
	
	public  static void main(String agrs[]) {
		
		//prorgamData();

		//userData();
		
		/*

		courseData();
		
		semesterData();
		
		instructorData();
		
		sectionData();
		sectionData1();
		sectionData2();
		sectionData3();
		
		setPchData();
				
		PchDAO dao=new PchDAO();
		PreferredCourseHistory pch= dao.getStudentPch(1, 1,903000001);
		Set<PchSub> set=pch.getPchSubs();
		Iterator<PchSub> iter=set.iterator();
		while(iter.hasNext()) {
			PchSub sub=iter.next();
			System.out.println(sub.getPriority()+" "+sub.getSection().getCourse().getCourseId()+" "+sub.getSection().getCourse().getCourseName());
		}
		*/

		
	}

	private static void setPchData() {
		PchDAO pdao=new PchDAO();
		StudentDao sdao=new StudentDao();
		CourseDAO course=new CourseDAO();
		SemesterDAO sem=new SemesterDAO();
		ProgramDAO pro=new ProgramDAO();
		SectionDAO scdao=new SectionDAO();
		
		PreferredCourseHistory pch=new PreferredCourseHistory();
		pch.setDateCreated(new Date());
		pch.setNumCoursesDesired(2);
		Contact contact=new Contact();
		contact.setGtid(903000001);
		pch.setContact(contact);
		pch.setSemester(sem.getSemester(1));
		pch.setDateCreated(new Date());
		pch.setProgram(pro.getProgram(1));

		
		Set<PchSub> pchset=new HashSet<PchSub>();
		PchSub sub=new PchSub();
		sub.setSection(scdao.getSection(1));
		sub.setPriority(1);
		sub.setCreateDate(new Date());
		sub.setCreateUser("uma");
		pchset.add(sub);
		
		sub=new PchSub();
		sub.setSection(scdao.getSection(2));
		sub.setPriority(2);
		sub.setCreateDate(new Date());
		sub.setCreateUser("uma");
		pchset.add(sub);
		
		pch.setPchSubs(pchset);
		pdao.add(pch);
	}

	private static void sectionData() {
		Section s=new Section();
		InstructorDao idao=new InstructorDao();
		Instructor i=idao.getInstructor(903000004);
		CourseDAO cdao=new CourseDAO();		
		Course c=cdao.getCourse(6250);
		UserDao udao=new UserDao();
		User user=udao.getUserByUserName("rwilson");
		s.setCourse(c);
		s.setCourseDemand(230);
		s.setDate(new Date());
		s.setInstructor(i);
		s.setMaxClassSize(200);
		s.setMaxTas(4);
		s.setSemester(new Semester(1));
		s.setUser(user);
		s.setProgram(new Program(1));
		SectionDAO scdao=new SectionDAO();
		scdao.add(s);
	}
	
	private static void sectionData1() {
		Section s=new Section();
		InstructorDao idao=new InstructorDao();
		Instructor i=idao.getInstructor(903000004);
		CourseDAO cdao=new CourseDAO();		
		Course c=cdao.getCourse(6300);
		UserDao udao=new UserDao();
		User user=udao.getUserByUserName("rwilson");
		s.setCourse(c);
		s.setCourseDemand(180);
		s.setDate(new Date());
		s.setInstructor(i);
		s.setMaxClassSize(200);
		s.setMaxTas(3);
		s.setSemester(new Semester(1));
		s.setUser(user);
		s.setProgram(new Program(1));
		SectionDAO scdao=new SectionDAO();
		scdao.add(s);
	}

	private static void sectionData2() {
		Section s=new Section();
		InstructorDao idao=new InstructorDao();
		Instructor i=idao.getInstructor(903000004);
		CourseDAO cdao=new CourseDAO();		
		Course c=cdao.getCourse(6475);
		UserDao udao=new UserDao();
		User user=udao.getUserByUserName("rwilson");
		s.setCourse(c);
		s.setCourseDemand(150);
		s.setDate(new Date());
		s.setInstructor(i);
		s.setMaxClassSize(200);
		s.setMaxTas(3);
		s.setSemester(new Semester(1));
		s.setUser(user);
		s.setProgram(new Program(1));
		SectionDAO scdao=new SectionDAO();
		scdao.add(s);
	}
	
	
	private static void sectionData3() {
		Section s=new Section();
		InstructorDao idao=new InstructorDao();
		Instructor i=idao.getInstructor(903000004);
		CourseDAO cdao=new CourseDAO();		
		Course c=cdao.getCourse(8803);
		UserDao udao=new UserDao();
		User user=udao.getUserByUserName("rwilson");
		s.setCourse(c);
		s.setCourseDemand(190);
		s.setDate(new Date());
		s.setInstructor(i);
		s.setMaxClassSize(200);
		s.setMaxTas(3);
		s.setSemester(new Semester(1));
		s.setUser(user);
		s.setProgram(new Program(1));
		SectionDAO scdao=new SectionDAO();
		scdao.add(s);
	}
	
	
	private static void instructorData() {
		Instructor i=new Instructor();
		i.setActive(true);
		i.setCurrentCourseCount(4);
		i.setInstructorId(903000004);
		i.setIsProfessor(true);
		InstructorDao idao=new InstructorDao();
		idao.add(i);
	}

	private static void semesterData() {
		SemesterDAO dao=new SemesterDAO();
		Semester s=new Semester();
		s.setSemesterId(1);
		s.setYear(2015);
		s.setTerm(2);
		s.setTermName("Summer");
		dao.add(s);
		
		s=new Semester();
		s.setSemesterId(2);
		s.setYear(2015);
		s.setTerm(3);
		s.setTermName("Fall");
		dao.add(s);
	}

	private static void courseData() {
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

	
	private static void userData() {
		ContactDao cdao=new ContactDao();
		Contact contact=new Contact(903000001,"Umashankar", "Gaddameedi","umashankar3@gatech.edu");
		cdao.add(contact);

		contact=new Contact(903000002,"Rebeca", "Wilson", null);	
		cdao.add(contact);
		
		contact=new Contact(903000003,"David", "Faour", null);	
		cdao.add(contact);
		
		contact=new Contact(903000004,"Eric", "Feron", null);	
		cdao.add(contact);
		
		/*
		StudentDao sdao=new StudentDao();
		Student s=new Student(903000001,2);
		sdao.add(s);
		

		
		UserDao udao=new UserDao();
		User user=new User(903000001, "umashankar3", "Student", "dscars");
		udao.add(user);
		
		user=new User(903000002, "rwilson", "Admin", "dscars");
		udao.add(user);
		
		user=new User(903000003, "eric.feron", "Professor", "dscars");
		udao.add(user);
		
		user=new User(903000004, "dfaour", "TA", "dscars");
		udao.add(user);
		*/
		
	}
	
	
	private static void prorgamData() {
		ProgramDAO pdao=new ProgramDAO();
		Program program=new Program();
		program.setProgramId(1);
		program.setProgramName("MS in Computer Science");
		pdao.add(program);
	}
}
