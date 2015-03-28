package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 28, 2015 1:24:52 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * RecommendedTas generated by hbm2java
 */
@Entity
@Table(name = "RecommendedTAs", catalog = "DSCARS")
public class RecommendedTas implements java.io.Serializable {

	private Integer rtaId;
	private Instructor instructor;
	private RecommendedSection recommendedSection;

	public RecommendedTas() {
	}

	public RecommendedTas(Instructor instructor,
			RecommendedSection recommendedSection) {
		this.instructor = instructor;
		this.recommendedSection = recommendedSection;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RTA_ID", unique = true, nullable = false)
	public Integer getRtaId() {
		return this.rtaId;
	}

	public void setRtaId(Integer rtaId) {
		this.rtaId = rtaId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "InstructorID", nullable = false)
	public Instructor getInstructor() {
		return this.instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RecSectionID", nullable = false)
	public RecommendedSection getRecommendedSection() {
		return this.recommendedSection;
	}

	public void setRecommendedSection(RecommendedSection recommendedSection) {
		this.recommendedSection = recommendedSection;
	}

}
