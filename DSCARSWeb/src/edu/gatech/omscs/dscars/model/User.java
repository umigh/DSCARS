package edu.gatech.omscs.dscars.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "User", catalog = "DSCARS")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	private String role;
	private String passwordHash;
	private Date lastLoginDate;
	private Contact contact;
	
	public User() {
	}

	public User(int userId, String userName, String role, String passwordHash) {
		this.userId = userId;
		this.userName = userName;
		this.role = role;
		this.passwordHash = passwordHash;
	}

	@Id
	@Column(name = "UserID", unique = true, nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "UserName", nullable = false, length = 65535)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "Role", nullable = false, length = 65535)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "PasswordHash", nullable = false, length = 65535)
	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LastLoginDate", length = 19)
	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid", insertable=false,updatable=false)
	public Contact getContact() {
		return contact;
	}


	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
