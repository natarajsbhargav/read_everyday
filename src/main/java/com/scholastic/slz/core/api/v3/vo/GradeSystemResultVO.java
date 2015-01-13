package com.scholastic.slz.core.api.v3.vo;

import java.util.List;

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
public class GradeSystemResultVO {
	/**
	 * timestamp
	 */
	private String timestamp;
	/**
	 * name
	 */
	private String name;
	/**
	 * code
	 */
	private String code;
	/**
	 * grades
	 */
	private List<GradeVO> grades;

	/**
	 * 
	 * @return The timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * 
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return The code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 
	 * @return The grades
	 */
	public List<GradeVO> getGrades() {
		return grades;
	}

	/**
	 * @param grades
	 */
	public void setGrades(List<GradeVO> grades) {
		this.grades = grades;
	}

}
