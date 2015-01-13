package com.scholastic.slz.core.api.v3.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.scholastic.slz.core.data.model.Grade;

/**
 * @author snehalata.raulo
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "grade")
@JsonRootName(value = "grade")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class StudentGradeVO {

	/** The localGradeName */
	@XmlAttribute
	@JsonProperty("localGradeName")
	private String localGradeName;

	/** The localGradeCode */
	@XmlAttribute
	@JsonProperty("localGradeCode")
	private String localGradeCode;

	/** The scholasticGradeName */
	@XmlAttribute
	@JsonProperty("scholasticGradeName")
	private String scholasticGradeName;

	/** The localGradeSystemCode */
	@XmlAttribute
	@JsonProperty("localGradeSystemCode")
	private String localGradeSystemCode;

	/** The scholasticGradeCode */
	@XmlAttribute
	@JsonProperty("scholasticGradeCode")
	private String scholasticGradeCode;

	/** The sequenceNum */
	@XmlAttribute
	@JsonProperty("sequenceNum")
	private Integer sequenceNum;

	/** The default Constructor */
	public StudentGradeVO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * The parameterized Constructor
	 * 
	 * @param grade
	 */
	public StudentGradeVO(final Grade grade) {
		this.localGradeCode = grade.getFullName();
		this.localGradeName = grade.getImportCode();
		this.localGradeSystemCode = grade.getGradeSystem().getCode();
		this.scholasticGradeCode = grade.getSriGrade().getShortName();
		this.scholasticGradeName = grade.getSriGrade().getFullName();
		this.sequenceNum = grade.getSequenceNum();
	}

	/**
	 * 
	 * @return The localGradeName
	 */
	@JsonProperty("localGradeName")
	public String getLocalGradeName() {
		return localGradeName;
	}

	/**
	 * 
	 * @param localGradeName
	 *            The localGradeName
	 */
	@JsonProperty("localGradeName")
	public void setLocalGradeName(final String localGradeName) {
		this.localGradeName = localGradeName;
	}

	/**
	 * 
	 * @return The localGradeCode
	 */
	@JsonProperty("localGradeCode")
	public String getLocalGradeCode() {
		return localGradeCode;
	}

	/**
	 * 
	 * @param localGradeCode
	 *            The localGradeCode
	 */
	@JsonProperty("localGradeCode")
	public void setLocalGradeCode(final String localGradeCode) {
		this.localGradeCode = localGradeCode;
	}

	/**
	 * 
	 * @return The scholasticGradeName
	 */
	@JsonProperty("scholasticGradeName")
	public String getScholasticGradeName() {
		return scholasticGradeName;
	}

	/**
	 * 
	 * @param scholasticGradeName
	 *            The scholasticGradeName
	 */
	@JsonProperty("scholasticGradeName")
	public void setScholasticGradeName(final String schGradeName) {
		this.scholasticGradeName = schGradeName;
	}

	/**
	 * 
	 * @return The localGradeSystemCode
	 */
	@JsonProperty("localGradeSystemCode")
	public String getLocalGradeSystemCode() {
		return localGradeSystemCode;
	}

	/**
	 * 
	 * @param localGradeSystemCode
	 *            The localGradeSystemCode
	 */
	@JsonProperty("localGradeSystemCode")
	public void setLocalGradeSystemCode(final String localGradeCode) {
		this.localGradeSystemCode = localGradeCode;
	}

	/**
	 * 
	 * @return The scholasticGradeCode
	 */
	@JsonProperty("scholasticGradeCode")
	public String getScholasticGradeCode() {
		return scholasticGradeCode;
	}

	/**
	 * 
	 * @param scholasticGradeCode
	 *            The scholasticGradeCode
	 */
	@JsonProperty("scholasticGradeCode")
	public void setScholasticGradeCode(final String schGradeCode) {
		this.scholasticGradeCode = schGradeCode;
	}

	/**
	 * 
	 * @return The sequenceNum
	 */
	@JsonProperty("sequenceNum")
	public Integer getSequenceNum() {
		return sequenceNum;
	}

	/**
	 * 
	 * @param sequenceNum
	 *            The sequenceNum
	 */
	@JsonProperty("sequenceNum")
	public void setSequenceNum(final Integer sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
}
