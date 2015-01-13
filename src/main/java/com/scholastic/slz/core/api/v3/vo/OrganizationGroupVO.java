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
@XmlRootElement(name = "schoolGroup")
public class OrganizationGroupVO extends ResultVO {

	/** The organization group identifier */
	@XmlElement
	private String identifier;

	/** The organization group name */
	@XmlElement
	private String name;

	/** The organization group country code */
	@XmlElement
	private String countryCode;

	/** The count of organizations under this organization group */
	@XmlElement(name = "schoolCount")
	@JsonProperty("schoolCount")
	private Integer orgCount;

	/** The parent organization group */
	@XmlElement(name = "parentSchoolGroup")
	@JsonProperty("parentSchoolGroup")
	private OrganizationGroupVO parentOrgGroup;

	/** List of child organization groups */
	private List<OrganizationGroupVO> childOrgGroups;

	/** List of child organizations */
	@XmlElement(name = "childSchools")
	@JsonProperty("childSchools")
	private List<OrganizationVO> childOrgs;

	/**
	 * Returns the identifier of this organization group
	 * 
	 * @return the organization group identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the identifier for this organization group
	 * 
	 * @param identifier
	 *            the organization group identifier to be set
	 */
	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	/**
	 * Returns the name of this organization group
	 * 
	 * @return the organization group name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name for this organization group
	 * 
	 * @param name
	 *            the organization group name to be set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Returns the country code of this organization group
	 * 
	 * @return the organization group country code
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Sets the country code for this organization group
	 * 
	 * @param countryCode
	 *            the country code to be set
	 */
	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Returns the count of organizations under this organization group
	 * 
	 * @return the count of organizations
	 */
	public Integer getOrgCount() {
		return orgCount;
	}

	/**
	 * Sets the organizations count for this organization group
	 * 
	 * @param orgCount
	 *            the organizations count
	 */
	public void setOrgCount(final Integer orgCount) {
		this.orgCount = orgCount;
	}

	/**
	 * Returns the parent organization group of this organization group
	 * 
	 * @return the parent organization group
	 */
	public OrganizationGroupVO getParentOrgGroup() {
		return parentOrgGroup;
	}

	/**
	 * Sets the parent organization group for this organization group
	 * 
	 * @param parentOrgGroup
	 *            the parent organization group to be set
	 */
	public void setParentOrgGroup(final OrganizationGroupVO parentOrgGroup) {
		this.parentOrgGroup = parentOrgGroup;
	}

	/**
	 * Returns the list of child organization groups of this organization group
	 * 
	 * @return the list of child organization groups
	 */
	@XmlElement(name = "childSchoolGroups")
	@JsonProperty("childSchoolGroups")
	public List<OrganizationGroupVO> getChildOrgGroups() {
		return childOrgGroups;
	}

	/**
	 * Sets the list of child organization groups for this organization group
	 * 
	 * @param childOrgGroups
	 *            the list of child organization groups to be set
	 */
	public void setChildOrgGroups(final List<OrganizationGroupVO> childOrgGroups) {
		this.childOrgGroups = childOrgGroups;
	}

	/**
	 * Returns the list of child organizations of this organization group
	 * 
	 * @return the list of child organizations
	 */
	public List<OrganizationVO> getChildOrgs() {
		return childOrgs;
	}

	/**
	 * Sets the list of child organizations for this organization group
	 * 
	 * @param childOrgs
	 *            the list of child organizations to be set
	 */
	public void setChildOrgs(final List<OrganizationVO> childOrgs) {
		this.childOrgs = childOrgs;
	}
}