package com.scholastic.slz.core.api.v3.vo;

import java.util.ArrayList;
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
public class TeacherDetailsResponseVO {

	/**
	 * timestamp
	 */
	private String timestamp;
	/**
	 * identifier of the Teacher
	 */
	private String identifier;
	/**
	 * Teacher First name
	 */
	private String firstName;
	/**
	 * Teacher last name
	 */
	private String lastName;

	/**
	 * Organization working with
	 */
	private School school;
	/**
	 * Classes teacher is managing
	 */
	private List<ClassResponse> classes = new ArrayList<ClassResponse>();

	/**
	 * @param timestamp
	 * @param identifier
	 * @param firstName
	 * @param lastName
	 * @param school
	 * @param classes
	 */
	public TeacherDetailsResponseVO(final String timestamp,
			final String identifier, final String firstName,
			final String lastName, final School school,
			final List<ClassResponse> classes) {
		super();
		this.timestamp = timestamp;
		this.identifier = identifier;
		this.firstName = firstName;
		this.lastName = lastName;
		this.school = school;
		this.classes = classes;
	}

	public TeacherDetailsResponseVO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return The timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * 
	 * @param timestamp
	 *            The timestamp
	 */
	public void setTimestamp(final String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * 
	 * @return The identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * 
	 * @param identifier
	 *            The identifier
	 */
	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	/**
	 * 
	 * @return The firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 *            The firstName
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @return The lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 *            The lastName
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 
	 * @return The school
	 */
	public School getSchool() {
		return school;
	}

	/**
	 * 
	 * @param school
	 *            The school
	 */
	public void setSchool(final School school) {
		this.school = school;
	}

	/**
	 * 
	 * @return The classes
	 */
	public List<ClassResponse> getClasses() {
		return classes;
	}

	/**
	 * 
	 * @param classes
	 *            The classes
	 */
	public void setClasses(final List<ClassResponse> classes) {
		this.classes = classes;
	}

}
