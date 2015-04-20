package edu.gatech.omscs.dscars.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import edu.gatech.omscs.dscars.exception.SettingLockedException;
import edu.gatech.omscs.dscars.model.Contact;
import edu.gatech.omscs.dscars.model.CoreEngineSetting;
import edu.gatech.omscs.dscars.model.Course;
import edu.gatech.omscs.dscars.model.Instructor;
import edu.gatech.omscs.dscars.model.PchSub;
import edu.gatech.omscs.dscars.model.PreferredCourseHistory;
import edu.gatech.omscs.dscars.model.Program;
import edu.gatech.omscs.dscars.model.Section;
import edu.gatech.omscs.dscars.model.SectionStudent;
import edu.gatech.omscs.dscars.model.SectionTA;
import edu.gatech.omscs.dscars.model.Semester;
import edu.gatech.omscs.dscars.model.Student;
import edu.gatech.omscs.dscars.model.User;

@SuppressWarnings("unused")
public class TestData {
	
	public  static void main(String agrs[]) {
		//prorgamData();
		//courseData();
		//semesterData();
		//userData();
		//sectionData();
		//sectionData1();
		//sectionData2();
		//sectionData3();
		setPchData();
		//addSectionTA();
		//addSectionStudent();
		
		//testEligigbleCourse();
		//addCoreEngineSetting();
		//loadTestData();
		testDemand();
	}
	
	public static void testDemand() {
		PchDAO dao=new PchDAO();
		Map<Integer,Integer> demand=dao.getDemand();
		System.out.println(demand);
	}
	
	public static void testEligigbleCourse() {
		PchDAO dao=new PchDAO();
		List<Integer> list=dao.getEligibleCourses(903000001);
		System.out.println(list);
	}
	
	public static void addCoreEngineSetting() {
		CoreEngineSetting setting=new CoreEngineSetting(903000002, 200, 200, 1);
		CoreEngineSettingDao dao=new CoreEngineSettingDao();
		
		//add new setting by user
		try {
			dao.addOrUpdate(setting);
		} catch (SettingLockedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		//This should update existing setting record. Should not create a new one.
		try {
			setting.setMaxClassSizeDefault(300);
			dao.addOrUpdate(setting);
		} catch (SettingLockedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//lock by core engine
		setting=dao.getCoreEngineSettingBySemester(1);
		dao.lock(setting);

		//This should throw locked exception.
		try {
			setting.setMaxClassSizeDefault(400);
			dao.addOrUpdate(setting);
		} catch (SettingLockedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//unlock.
		setting=dao.getCoreEngineSettingBySemester(1);
		dao.complete(setting);
		
		//This should update MaxClassSizeDefault to 300, status to New.
		try {
			setting.setMaxClassSizeDefault(500);
			setting.setStudentsPerInstructor(500);
			dao.addOrUpdate(setting);
		} catch (SettingLockedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void addSectionTA() {
		SectionTADAO stadao=new SectionTADAO();		
		SectionTA sta=new SectionTA(1,903000004,true);
		sta=stadao.add(sta);
		sta=new SectionTA(2,903000004,false);
		stadao.add(sta);
		List<Integer> tas=new ArrayList<Integer>();
		tas.add(903000003);
		tas.add(903000010);
		stadao.merge(1, tas, true);
	}
	
	public static void addSectionStudent() {
		SectionStudentDao sstdao=new SectionStudentDao();		
		SectionStudent sst=new SectionStudent(1,903000001);
		sst=sstdao.add(sst);
		sst=new SectionStudent(2,903000008);
		sstdao.add(sst);
		sst=new SectionStudent(2,903000006);
		sstdao.add(sst);
		sst=new SectionStudent(2,903000007);
		sstdao.add(sst);

		sst=new SectionStudent(3,903000007);
		sstdao.add(sst);
		
		sstdao.delete(3);
		
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
		pch.setStudent(new Student(903000001));
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
	
	public static void loadTestData() {
		ContactDao cdao=new ContactDao();
		StudentDao sdao=new StudentDao();
		Student s=null;
		User user=null;
		UserDao udao=new UserDao();
		SectionDAO secdao=new SectionDAO();
		PchDAO pchdao=new PchDAO();
		
		//Semester1: 2015 Summer
		//Only one subject is allowed.
		//int semesterid=1;
		//Semester sem=new Semester(semesterid);
		PreferredCourseHistory pch=null;
		int sectionIndex=0;		
		for(int i=0;i<500;i++) {
			int id=903000100+i;
			Contact contact=new Contact(id,"Student", ""+i,"student"+i+"@gatech.edu");
			cdao.add(contact);
			s=new Student(id,2,randInt(1,5));
			sdao.add(s);
			user=new User(id, "student"+i, "Student", "dscars");
			udao.add(user);
			
			
			//PCH for semester1: 2015 Summer
			pch=new PreferredCourseHistory();
			pch.setDateCreated(new Date());
			pch.setNumCoursesDesired(randInt(2,2));
			pch.setStudent(s);
			pch.setSemester(new Semester(1));
			pch.setDateCreated(new Date());

			
			Set<PchSub> pchset=new HashSet<PchSub>();
			List<Integer> sectionIds=new ArrayList<Integer>();
			for(int j=0;j<2;j++) {
				PchSub sub=new PchSub(); 
				int sectionId=randInt(1,4);
				while(true) {
					if(sectionIds.contains(sectionId)) 
						sectionId=randInt(1,4);
					else 
						break;
				}
				sectionIds.add(sectionId);
				sub.setSection(new Section(sectionId));
				sub.setPriority(j+1);
				sub.setCreateDate(new Date());
				sub.setCreateUser("uma");
				pchset.add(sub);
			}
			
			pch.setPchSubs(pchset);
			pchdao.add(pch);
			
		}
		/*
		sectionIndex=0;		
		for(int i=0;i<200;i++) {			
			//PCH for semester1: 2015 Fall
			pch=new PreferredCourseHistory();
			pch.setDateCreated(new Date());
			pch.setNumCoursesDesired(2);
			pch.setStudent(s);
			pch.setSemester(new Semester(2));
			pch.setDateCreated(new Date());

			
			Set<PchSub> pchset=new HashSet<PchSub>();
			List<Integer> sectionIds=new ArrayList<Integer>();
			for(int j=0;j<2;j++) {
				PchSub sub=new PchSub(); 
				int sectionId=randInt(1,4);
				while(true) {
					if(sectionIds.contains(sectionId)) 
						sectionId=randInt(1,4);
					else 
						break;
				}
				sectionIds.add(sectionId);
				sub.setSection(new Section(sectionId));
				sub.setPriority(j+1);
				sub.setCreateDate(new Date());
				sub.setCreateUser("uma");
				pchset.add(sub);
			}
			
			pch.setPchSubs(pchset);
			pchdao.add(pch);			
		}
		*/
	}
	
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
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
		StudentDao sdao=new StudentDao();
		UserDao udao=new UserDao();
		InstructorDao idao=new InstructorDao();
		User user=null;
		Contact contact=null;
		Student s=null;
		
		contact=new Contact(903000001,"Umashankar", "Gaddameedi","umashankar3@gatech.edu");
		cdao.add(contact);
		s=new Student(903000001,2);
		sdao.add(s);
		user=new User(903000001, "umashankar3", "Student", "dscars");
		udao.add(user);
		
		contact=new Contact(903000002,"Rebeca", "Wilson", null);	
		cdao.add(contact);
		user=new User(903000002, "rwilson", "Admin", "dscars");
		udao.add(user);
		
		contact=new Contact(903000003,"David", "Faour", null);	
		cdao.add(contact);
		user=new User(903000003, "dfaour", "TA", "dscars");
		udao.add(user);		
		Instructor i=new Instructor(903000003, false, true, 2);
		idao.add(i);
		
		contact=new Contact(903000004,"Eric", "Feron", null);	
		cdao.add(contact);
		user=new User(903000004, "eric.feron", "Professor", "dscars");
		udao.add(user);
		i=new Instructor(903000004, true, true, 2);
		idao.add(i);
		
		contact=new Contact(903000005,"Amudha", "Sethuraman", "amudha77@gmail.com");	
		cdao.add(contact);
		user=new User(903000005, "amudha", "Student", "dscars");
		udao.add(user);
		s=new Student(903000005,2);
		sdao.add(s);
		
		
		contact=new Contact(903000006,"Caleb", "Rapier", "caleb.rapier@gmail.com");	
		cdao.add(contact);
		user=new User(903000006, "caleb", "Student", "dscars");
		udao.add(user);
		s=new Student(903000006,2);
		sdao.add(s);
		
		contact=new Contact(903000007,"Ray", "Reese", "rnreese@comcast.net");	
		cdao.add(contact);
		user=new User(903000007, "ray", "Student", "dscars");
		udao.add(user);
		s=new Student(903000007,2);
		sdao.add(s);
		
		contact=new Contact(903000008,"Thomas", "Wyrick", "thomas.wyrick@gatech.edu");	
		cdao.add(contact);
		user=new User(903000008, "tom", "Student", "dscars");
		udao.add(user);
		s=new Student(903000008,2);
		sdao.add(s);
		
		contact=new Contact(903000010,"Kerman", "Ian", null);	
		cdao.add(contact);
		user=new User(903000010, "ian.kerman", "TA", "dscars");
		udao.add(user);
		i=new Instructor(903000010, false, true, 2);
		idao.add(i);
	}
	
	
	private static void prorgamData() {
		ProgramDAO pdao=new ProgramDAO();
		Program program=new Program();
		program.setProgramId(1);
		program.setProgramName("MS in Computer Science");
		pdao.add(program);
	}
}
