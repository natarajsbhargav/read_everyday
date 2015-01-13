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
public class MarkingPeriods {
	/**
	 * startDate
	 */
	private String startDate;

	/**
	 * @return startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 */
	public void setStartDate(final String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 */
	public void setEndDate(final String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * endDate
	 */
	private String endDate;
	/**
	 * description
	 */
	private String description;

}
