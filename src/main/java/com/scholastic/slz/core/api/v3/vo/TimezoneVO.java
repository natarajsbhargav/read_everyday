package com.scholastic.slz.core.api.v3.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author snehalata.raulo
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "timezone")
@JsonRootName(value = "timezone")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TimezoneVO {

	/** The canonicalName */
	@XmlAttribute(name = "id")
	@JsonProperty("id")
	private String canonicalName;

	/** The description */
	@XmlAttribute(name = "name")
	@JsonProperty("name")
	private String description;

	/**
	 * @return the canonicalName
	 */
	public String getCanonicalName() {
		return canonicalName;
	}

	/**
	 * @param canonicalName
	 *            the canonicalName to set
	 */
	public void setCanonicalName(final String canonicalName) {
		this.canonicalName = canonicalName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

}
