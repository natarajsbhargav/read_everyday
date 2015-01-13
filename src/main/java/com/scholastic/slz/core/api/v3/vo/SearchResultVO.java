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
@XmlRootElement
public class SearchResultVO extends BasicVO {

	/** List of students */
	@XmlElement
	private List<UserVO> students;

	/** List of classes */
	@XmlElement
	private List<ClassVO> classes;

	/** List of organizations */
	@XmlElement(name = "schools")
	@JsonProperty("schools")
	private List<OrganizationVO> orgs;

	/** List of organization groups */
	@XmlElement(name = "schoolGroups")
	@JsonProperty("schoolGroups")
	private List<OrganizationGroupVO> orgGroups;

	/**
	 * Returns the list of students
	 * 
	 * @return the list of students
	 */
	public List<UserVO> getStudents() {
		return students;
	}

	/**
	 * Sets the list of students
	 * 
	 * @param students
	 *            the list of students
	 */
	public void setStudents(final List<UserVO> students) {
		this.students = students;
	}

	/**
	 * Returns the list of classes
	 * 
	 * @return the list of classes
	 */
	public List<ClassVO> getClasses() {
		return classes;
	}

	/**
	 * Sets the list of classes
	 * 
	 * @param classes
	 *            the list of classes
	 */
	public void setClasses(final List<ClassVO> classes) {
		this.classes = classes;
	}

	/**
	 * Returns the list of organizations
	 * 
	 * @return the list of organizations
	 */
	public List<OrganizationVO> getOrgs() {
		return orgs;
	}

	/**
	 * Sets the list of organizations
	 * 
	 * @param orgs
	 *            the list of organizations
	 */
	public void setOrgs(final List<OrganizationVO> orgs) {
		this.orgs = orgs;
	}

	/**
	 * Returns the list of organization groups
	 * 
	 * @return the list of organization groups
	 */
	public List<OrganizationGroupVO> getOrgGroups() {
		return orgGroups;
	}

	/**
	 * Sets the list of organization groups
	 * 
	 * @param orgGroups
	 *            the list of organization groups
	 */
	public void setOrgGroups(final List<OrganizationGroupVO> orgGroups) {
		this.orgGroups = orgGroups;
	}
}