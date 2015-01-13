package com.scholastic.slz.core.api.v3.vo;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author madan.haraginadoni
 *
 */
/**
 * Is the VO object for creating the response from the database
 *
 */
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement()
@JsonSerialize
public class SchoolCalenderResponseVO {
	/**
	 * startDate
	 */
	private String startDate;
	/**
	 * endDate
	 */
	private String endDate;
	/**
	 * description
	 */
	private String description;
	/**
	 * List of markingPeriods
	 */
	private ArrayList<MarkingPeriods> markingPeriods;

	/**
	 * @return markingPeriods
	 */
	public ArrayList<MarkingPeriods> getMarkingPeriods() {
		return markingPeriods;
	}

	/**
	 * @param markingPeriods
	 */
	public void setMarkingPeriods(ArrayList<MarkingPeriods> markingPeriods) {
		this.markingPeriods = markingPeriods;
	}

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

}
