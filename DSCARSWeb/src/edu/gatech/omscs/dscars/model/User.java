package edu.gatech.omscs.dscars.model;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "User", catalog = "DSCARS")
public class User implements java.io.Serializable {
	public User(int userId, String userName, String firstName, String lastName,
			String role, String passwordHash) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.passwordHash = passwordHash;
	}

	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String role;
	private String passwordHash;
	private Date lastLoginDate;
	private Set<CoreEngine> coreEngines = new HashSet<CoreEngine>(0);
	private Set<Section> sections = new HashSet<Section>(0);
	private Set<RecommendedSchedule> recommendedSchedules = new HashSet<RecommendedSchedule>(
			0);

	public User() {
	}

	public User(int userId, String userName, String role, String passwordHash) {
		this.userId = userId;
		this.userName = userName;
		this.role = role;
		this.passwordHash = passwordHash;
	}

	public User(int userId, String userName, String firstName, String lastName,
			String role, String passwordHash, Date lastLoginDate,
			Set<CoreEngine> coreEngines, Set<Section> sections,
			Set<RecommendedSchedule> recommendedSchedules) {
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.passwordHash = passwordHash;
		this.lastLoginDate = lastLoginDate;
		this.coreEngines = coreEngines;
		this.sections = sections;
		this.recommendedSchedules = recommendedSchedules;
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

	@Column(name = "FirstName", length = 65535)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LastName", length = 65535)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<CoreEngine> getCoreEngines() {
		return this.coreEngines;
	}

	public void setCoreEngines(Set<CoreEngine> coreEngines) {
		this.coreEngines = coreEngines;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Section> getSections() {
		return this.sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<RecommendedSchedule> getRecommendedSchedules() {
		return this.recommendedSchedules;
	}

	public void setRecommendedSchedules(
			Set<RecommendedSchedule> recommendedSchedules) {
		this.recommendedSchedules = recommendedSchedules;
	}

}
