package com.scholastic.slz.core.api.v3.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "classes")
public class ClassVO extends ResultVO {

	/** The class identifier */
	@XmlElement
	private String identifier;

	/** The class name */
	@XmlElement
	private String name;

	/** The organization this class belongs to */
	@XmlElement(name = "school")
	@JsonProperty("school")
	private OrganizationVO organization;

	/** The list of students belonging to this class */
	@XmlElement
	private List<UserVO> students;

	/** The list of teachers belonging to this class */
	@XmlElement
	private List<UserVO> teachers;

	/**
	 * Returns the identifier of this class
	 * 
	 * @return the class identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the identifier for this class
	 * 
	 * @param identifier
	 *            the class identifier to be set
	 */
	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	/**
	 * Returns the name of this class
	 * 
	 * @return the class name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name for this class
	 * 
	 * @param name
	 *            the class name to be set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Returns the organization this class belongs to
	 * 
	 * @return the organization
	 */
	public OrganizationVO getOrganization() {
		return organization;
	}

	/**
	 * Sets the organization for this class
	 * 
	 * @param organization
	 *            the organization to be set
	 */
	public void setOrganization(final OrganizationVO organization) {
		this.organization = organization;
	}

	/**
	 * Returns the list of students belonging to this class
	 * 
	 * @return the list of students
	 */
	public List<UserVO> getStudents() {
		return students;
	}

	/**
	 * Sets the list of students for this class
	 * 
	 * @param students
	 *            the list of students to be set
	 */
	public void setStudents(final List<UserVO> students) {
		this.students = students;
	}

	/**
	 * Returns the list of teachers belonging to this class
	 * 
	 * @return the list of teachers
	 */
	public List<UserVO> getTeachers() {
		return teachers;
	}

	/**
	 * Sets the list of teachers for this class
	 * 
	 * @param teachers
	 *            the list of teachers to be set
	 */
	public void setTeachers(final List<UserVO> teachers) {
		this.teachers = teachers;
	}
}