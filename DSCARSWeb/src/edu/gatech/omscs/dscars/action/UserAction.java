package edu.gatech.omscs.dscars.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import edu.gatech.omscs.dscars.dao.UserDao;
import edu.gatech.omscs.dscars.model.User;


public class UserAction extends ActionSupport {

	private static final long serialVersionUID = 9149826260758390091L;
	private User user;
	private List<User> userList;
	private Integer userId;

	private UserDao userDao;

	public UserAction() {
		userDao = new UserDao();
	}

	public String execute() {
		this.userList = userDao.list();
		return SUCCESS;
	}

	public String list() {
		this.userList = userDao.list();
		return SUCCESS;
	}
	
	public String add() {
		System.out.println(getUser());
		try {
			userDao.add(getUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.userList = userDao.list();
		return SUCCESS;
	}

	public String delete() {
		userDao.delete(getUserId());
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserList(List<User> userList) {
		if(userList==null) {
			this.userList = userDao.list();
		}
		this.userList = userList;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
