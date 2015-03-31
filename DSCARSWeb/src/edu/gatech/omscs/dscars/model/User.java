package edu.gatech.omscs.dscars.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User implements Serializable{
	static final long serialVersionUID = 1L;
	String userId;
	String password;
	Integer gtid;
	String role;
	String firstName;
	String lastName;
	String email;
	Date lastLoginDate;
	
	public User() {
		
	}
	
	public User(String userId, String password, Integer gtid, String role,String firstName, String lastName, String email) {
		super();
		this.userId = userId;
		this.password = password;
		this.gtid = gtid;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	@Id
	@Column(name="userId")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="gtid")
	public Integer getGtid() {
		return gtid;
	}
	public void setGtid(Integer gtid) {
		this.gtid = gtid;
	}
	public String getRole() {
		return role;
	}
	
	@Column(name="role")
	public void setRole(String role) {
		this.role = role;
	}
	public String getFirstName() {
		return firstName;
	}
	
	@Column(name="firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	@Column(name="lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="lastloginDate")
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

}
