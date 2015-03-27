package edu.gatech.omscs.dscars.test;
// default package
// Generated Mar 27, 2015 4:54:21 PM by Hibernate Tools 4.0.0

/**
 * RecommendedTas generated by hbm2java
 */
public class RecommendedTas implements java.io.Serializable {

	private int rtaId;
	private Instructor instructor;
	private RecommendedSection recommendedSection;

	public RecommendedTas() {
	}

	public RecommendedTas(int rtaId, Instructor instructor,
			RecommendedSection recommendedSection) {
		this.rtaId = rtaId;
		this.instructor = instructor;
		this.recommendedSection = recommendedSection;
	}

	public int getRtaId() {
		return this.rtaId;
	}

	public void setRtaId(int rtaId) {
		this.rtaId = rtaId;
	}

	public Instructor getInstructor() {
		return this.instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public RecommendedSection getRecommendedSection() {
		return this.recommendedSection;
	}

	public void setRecommendedSection(RecommendedSection recommendedSection) {
		this.recommendedSection = recommendedSection;
	}

}
