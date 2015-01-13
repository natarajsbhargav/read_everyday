package com.scholastic.slz.core.api.v3.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author snehalata.raulo
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "locale")
@JsonRootName(value = "locale")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class LocaleVO {

	/** The region Identifier */
	@XmlAttribute
	private Integer regionIdentifier;

	/** The region name */
	@XmlAttribute
	private String regionName;

	/** The Country code */
	@XmlAttribute
	private String countryCode;

	/** The Country name */
	@XmlAttribute
	private String countryName;

	/**
	 * 
	 * @return The regionIdentifier
	 */
	public Integer getRegionIdentifier() {
		return regionIdentifier;
	}

	/**
	 * 
	 * @param regionIdentifier
	 *            The regionIdentifier
	 */
	public void setRegionIdentifier(final Integer regionIdentifier) {
		this.regionIdentifier = regionIdentifier;
	}

	/**
	 * 
	 * @return The regionName
	 */
	public String getRegionName() {
		return regionName;
	}

	/**
	 * 
	 * @param regionName
	 *            The regionName
	 */
	public void setRegionName(final String regionName) {
		this.regionName = regionName;
	}

	/**
	 * 
	 * @return The countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * 
	 * @param countryCode
	 *            The countryCode
	 */
	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * 
	 * @return The countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * 
	 * @param countryName
	 *            The countryName
	 */
	public void setCountryName(final String countryName) {
		this.countryName = countryName;
	}
}
