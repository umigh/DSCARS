package edu.gatech.omscs.dscars.action;

import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.gatech.omscs.dscars.dao.UserDao;
import edu.gatech.omscs.dscars.model.User;


public class SecurityAction extends ActionSupport {

	private static final long serialVersionUID = 9149826260758390091L;
	private String userName;
	private String password;

	UserDao userDao=null;
	public SecurityAction() {
		userDao=new UserDao();
	}
	
	public String execute() {
		return SUCCESS;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String authenticate() {
		try {
			validateForm();
			User user=userDao.getUserByUserName(userName);
			if(userName!=null && password!=null && user!=null && userName.equals(user.getUserName()) && password.equals(user.getPasswordHash())) {
				Map session = ActionContext.getContext().getSession();
				session.put("user",user);
				session.put("lastLoginDate", user.getLastLoginDate());
				ActionContext.getContext().setSession(session);

				//valid user. login permitted. update last login date.
				user.setLastLoginDate(new Date());
				userDao.update(user);
				
				if("Student".equals(user.getRole())) {					
					return "studentPreference";
				} else if("Admin".equals(user.getRole())) {
					return "admin";
				} else if("Professor".equals(user.getRole())) {
					return "professor";
				} else if("TA".equals(user.getRole())) {
					return "TA";
				}
				else {
					addActionError("Invalid User Role!");
					return ERROR;
				}
			}
			else {
				addActionError("Invalid User Id and/or Password entered!");
				return ERROR;
			}
		}
		catch(Exception e) {
			System.err.println(e);
			addActionError(e.getMessage());
			return ERROR;
		}
	}

	@SuppressWarnings({ "rawtypes"})
	public String logoff() {
		try {
			Map session = ActionContext.getContext().getSession();
			((org.apache.struts2.dispatcher.SessionMap) session).invalidate();
			addActionMessage("You have been logged off sucessfully!");
		}
		catch(Exception e) {
			System.err.println(e);
		}
		return SUCCESS; 
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void validateForm() {
		if(userName==null || "".equals(userName)) {
			addActionError("Username is required!");
		}
		if(password==null || "".equals(password)) {
			addActionError("Password is required!");
		}
	}

}
