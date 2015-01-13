package com.scholastic.slz.core.api.v3.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "school")
public class OrganizationVO extends ResultVO {

	/** The organization identifier */
	@XmlElement
	private String identifier;

	/** The organization name */
	@XmlElement
	private String name;

	/** The flag indicating if the organization is a test account or not */
	@XmlElement
	private Boolean testAccount;

	/** The region identifier */
	@XmlElement
	private String regionIdentifier;

	/** The region name */
	@XmlElement
	private String regionName;

	/** The country code */
	@XmlElement
	private String countryCode;

	/** The country name */
	@XmlElement
	private String countryName;

	/** The admin area name */
	@XmlElement
	private String adminAreaName;

	/** The locality name */
	@XmlElement
	private String localityName;

	/**
	 * Returns the identifier of this organization
	 * 
	 * @return the organization identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the identifier for this organization
	 * 
	 * @param identifier
	 *            the organization identifier to be set
	 */
	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	/**
	 * Returns the name of this organization
	 * 
	 * @return the organization name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name for this organization
	 * 
	 * @param name
	 *            the organization name to be set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Returns if this organization is test account or not
	 * 
	 * @return true if this organization is test account; false otherwise
	 */
	public Boolean isTestAccount() {
		return testAccount;
	}

	/**
	 * Sets the test account flag for this organization
	 * 
	 * @param testAccount
	 *            true if this organization is test account; false otherwise
	 */
	public void setTestAccount(final Boolean testAccount) {
		this.testAccount = testAccount;
	}

	/**
	 * Returns the region identifier of this organization
	 * 
	 * @return the region identifier
	 */
	public String getRegionIdentifier() {
		return regionIdentifier;
	}

	/**
	 * Sets the region identifier for this organization
	 * 
	 * @param regionIdentifier
	 *            the region identifier to be set
	 */
	public void setRegionIdentifier(final String regionIdentifier) {
		this.regionIdentifier = regionIdentifier;
	}

	/**
	 * Returns the region name of this organization
	 * 
	 * @return the region name
	 */
	public String getRegionName() {
		return regionName;
	}

	/**
	 * Sets the region name for this organization
	 * 
	 * @param regionName
	 *            the region name to be set
	 */
	public void setRegionName(final String regionName) {
		this.regionName = regionName;
	}

	/**
	 * Returns the country code of this organization
	 * 
	 * @return the country code
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Sets the country code for this organization
	 * 
	 * @param countryCode
	 *            the country code to be set
	 */
	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Returns the country name of this organization
	 * 
	 * @return the country name
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * Sets the country name for this organization
	 * 
	 * @param countryName
	 *            the country name to be set
	 */
	public void setCountryName(final String countryName) {
		this.countryName = countryName;
	}

	/**
	 * Returns the admin area name of this organization
	 * 
	 * @return the admin area name
	 */
	public String getAdminAreaName() {
		return adminAreaName;
	}

	/**
	 * Sets the admin area name for this organization
	 * 
	 * @param adminAreaName
	 *            the admin area name to be set
	 */
	public void setAdminAreaName(final String adminAreaName) {
		this.adminAreaName = adminAreaName;
	}

	/**
	 * Returns the locality name of this organization
	 * 
	 * @return the locality name
	 */
	public String getLocalityName() {
		return localityName;
	}

	/**
	 * Sets the locality name for this organization
	 * 
	 * @param localityName
	 *            the locality name
	 */
	public void setLocalityName(final String localityName) {
		this.localityName = localityName;
	}
}