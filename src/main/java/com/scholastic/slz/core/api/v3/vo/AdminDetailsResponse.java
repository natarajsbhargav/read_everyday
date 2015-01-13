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
public class AdminDetailsResponse {

	/**
	 * timestamp
	 */
	private String timestamp;
	/**
	 * userIdentifier
	 */
	private String userIdentifier;
	/**
	 * firstName
	 */
	private String firstName;
	/**
	 * lastName
	 */
	private String lastName;
	/**
	 * school
	 */
	private School school;

	/**
	 * @return timestamp
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
	 * @return The userIdentifier
	 */

	public String getUserIdentifier() {
		return userIdentifier;
	}

	/**
	 * 
	 * @param userIdentifier
	 *            The userIdentifier
	 */

	public void setUserIdentifier(final String userIdentifier) {
		this.userIdentifier = userIdentifier;
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

}
