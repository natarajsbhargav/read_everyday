package com.scholastic.slz.core.api.v3.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author snehalata.raulo
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class RegionResultVO extends ResultVO {

	/** The identifier */
	@XmlAttribute(name = "identifier")
	@JsonProperty("identifier")
	private int identifier;

	/** The name */
	@XmlAttribute
	private String name;

	/** The countries */
	@XmlElement
	private List<CountryVO> countries = new ArrayList<CountryVO>();

	/**
	 * @return the identifier
	 */
	public int getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier
	 *            the id to set
	 */
	public void setIdentifier(final int identifier) {
		this.identifier = identifier;
	}

	/**
	 * 
	 * @return The name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	@JsonProperty("name")
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return The countries
	 */
	public List<CountryVO> getCountries() {
		return countries;
	}

	/**
	 * 
	 * @param countries
	 *            The countries
	 */
	public void setCountries(final List<CountryVO> countries) {
		this.countries = countries;
	}
}
