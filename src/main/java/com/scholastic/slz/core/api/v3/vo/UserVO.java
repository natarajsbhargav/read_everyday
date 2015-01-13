package com.scholastic.slz.core.api.v3.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author snehalata.raulo
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "users")
public class UserVO extends ResultVO {

	/** the identifier */
	@XmlElement
	private String identifier;

	/** the firstName */
	@XmlElement
	private String firstName;

	/** the lastName */
	@XmlElement
	private String lastName;

	/** the enrolled */
	@XmlElement
	private Boolean enrolled;
	
	/** the Grade Code */
	@XmlElement
	private String scholasticGradeCode;
	
	/** the Grade Code */
	@XmlElement
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getScholasticGradeCode() {
		return scholasticGradeCode;
	}

	public void setScholasticGradeCode(String scholasticGradeCode) {
		this.scholasticGradeCode = scholasticGradeCode;
	}

	/** The organization group if any for which this user is an administrator */
	@XmlElement(name = "schoolGroup")
	@JsonProperty("schoolGroup")
	private OrganizationGroupVO orgGroup;

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier
	 *            the identifier to set
	 */
	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the enrolled
	 */
	public Boolean isEnrolled() {
		return enrolled;
	}

	/**
	 * @param enrolled
	 *            the enrolled to set
	 */
	public void setEnrolled(final Boolean enrolled) {
		this.enrolled = enrolled;
	}

	/**
	 * Gets the organization group if any for this user
	 * 
	 * @return
	 */
	public OrganizationGroupVO getOrgGroup() {
		return orgGroup;
	}

	/**
	 * Sets the organization group for this user
	 * 
	 * @param orgGroup
	 *            the organization group to be set
	 */
	public void setOrgGroup(final OrganizationGroupVO orgGroup) {
		this.orgGroup = orgGroup;
	}
}