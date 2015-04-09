package edu.gatech.omscs.dscars.model;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@SuppressWarnings("serial")
@Entity
@Table(name = "PCH_Sub", catalog = "DSCARS")
public class PchSub implements java.io.Serializable {

	private Integer pchSubId;
	private Section section;
	private PreferredCourseHistory preferredCourseHistory;
	private int priority;
	private boolean recommended;
	private Date createDate;
	private String createUser;
	private Date updateDate;
	private String updateUser;

	public PchSub() {
	}

	public PchSub(Section section, PreferredCourseHistory preferredCourseHistory,int priority) {
		this.section = section;
		this.preferredCourseHistory = preferredCourseHistory;
		this.priority = priority;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PCH_Sub_ID", unique = true, nullable = false)
	public Integer getPchSubId() {
		return this.pchSubId;
	}

	public void setPchSubId(Integer pchSubId) {
		this.pchSubId = pchSubId;
	}


	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SectionID", nullable = false)
	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinColumn(name = "PCH_ID", nullable = false)
	public PreferredCourseHistory getPreferredCourseHistory() {
		return this.preferredCourseHistory;
	}

	public void setPreferredCourseHistory(
			PreferredCourseHistory preferredCourseHistory) {
		this.preferredCourseHistory = preferredCourseHistory;
	}

	@Column(name = "Priority", nullable = false)
	public int getPriority() {
		return this.priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Type(type="yes_no")
	public boolean isRecommended() {
		return recommended;
	}

	public void setRecommended(boolean recommended) {
		this.recommended = recommended;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", length = 19)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "createUser", nullable = false)
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate", length = 19)
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "updateUser")
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}
