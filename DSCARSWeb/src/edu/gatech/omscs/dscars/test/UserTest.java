package edu.gatech.omscs.dscars.test;

import edu.gatech.omscs.dscars.dao.UserDao;
import edu.gatech.omscs.dscars.model.User;

public class UserTest {
	
	public  static void main(String agrs[]) {
		User user=new User();
		user.setUserName("Uma");
		user.setPassword("test");
		user.setRole("Admin");
		UserDao dao=new UserDao();
		user=dao.add(user);
		System.out.println("UserId: "+user.getUserId()+" Username: "+user.getUserName());		
	}
}
