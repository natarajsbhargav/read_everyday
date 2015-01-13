package com.scholastic.slz.core.api.v3.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author madan.haraginadoni
 *
 */
/**
 * Is the VO object for creating the response from the database
 * 
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement()
@JsonSerialize
public class GradeVO {
	/**
	 * identifier
	 */
	private String scholasticGradeCode;
	/**
	 * scholasticGradeName
	 */
	private String scholasticGradeName;
	/**
	 * localGradeCode
	 */
	private String localGradeCode;
	/**
	 * localGradeName
	 */
	private String localGradeName;
	/**
	 * sequenceNum
	 */
	String sequenceNum;

	/**
	 * 
	 * @return The scholasticGradeCode
	 */
	public String getScholasticGradeCode() {
		return scholasticGradeCode;
	}

	/**
	 * @param scholasticGradeCode
	 */
	public void setScholasticGradeCode(String scholasticGradeCode) {
		this.scholasticGradeCode = scholasticGradeCode;
	}

	/**
	 * 
	 * @return The scholasticGradeName
	 */
	public String getScholasticGradeName() {
		return scholasticGradeName;
	}

	/**
	 * @param scholasticGradeName
	 */
	public void setScholasticGradeName(String scholasticGradeName) {
		this.scholasticGradeName = scholasticGradeName;
	}

	/**
	 * 
	 * @return The localGradeCode
	 */
	public String getLocalGradeCode() {
		return localGradeCode;
	}

	/**
	 * @param localGradeCode
	 */
	public void setLocalGradeCode(String localGradeCode) {
		this.localGradeCode = localGradeCode;
	}

	/**
	 * 
	 * @return The localGradeName
	 */
	public String getLocalGradeName() {
		return localGradeName;
	}

	/**
	 * @param localGradeName
	 */
	public void setLocalGradeName(String localGradeName) {
		this.localGradeName = localGradeName;
	}

	/**
	 * 
	 * @return The sequenceNum
	 */
	public String getSequenceNum() {
		return sequenceNum;
	}

	/**
	 * @param sequenceNum
	 */
	public void setSequenceNum(String sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

}
