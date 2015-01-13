package com.scholastic.slz.core.api.v3.vo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author snehalata.raulo
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "schoolCalendar")
@JsonRootName(value = "schoolCalendar")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SchoolCalendarVO {

	/** The dateFormat */
	final private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd", Locale.getDefault());

	/** The description */
	@XmlAttribute
	private String description;

	/** The startDate */
	@XmlAttribute
	private String startDate;

	/** The endDate */
	@XmlAttribute
	private String endDate;

	/** The markingPeriods */
	@XmlElement
	private List<MarkingPeriodVO> markingPeriods = new ArrayList<MarkingPeriodVO>();

	/**
	 * 
	 * @return The description
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 *            The description
	 */
	@JsonProperty("description")
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return The startDate
	 */
	@JsonProperty("startDate")
	public String getStartDate() {
		return startDate;
	}

	/**
	 * 
	 * @param startDate
	 *            The startDate
	 */
	@JsonProperty("startDate")
	public void setStartDate(final Date startDate) {
		if (startDate != null) {
			this.startDate = dateFormat.format(startDate);
		}
	}

	/**
	 * 
	 * @return The endDate
	 */
	@JsonProperty("endDate")
	public String getEndDate() {
		return endDate;
	}

	/**
	 * 
	 * @param endDate
	 *            The endDate
	 */
	@JsonProperty("endDate")
	public void setEndDate(final Date endDate) {
		if (endDate != null) {
			this.endDate = dateFormat.format(endDate);
		}
	}

	/**
	 * 
	 * @return The markingPeriods
	 */
	@JsonProperty("markingPeriods")
	public List<MarkingPeriodVO> getMarkingPeriods() {
		return markingPeriods;
	}

	/**
	 * 
	 * @param markingPeriods
	 *            The markingPeriods
	 */
	@JsonProperty("markingPeriods")
	public void setMarkingPeriods(final List<MarkingPeriodVO> markingPeriods) {
		this.markingPeriods = markingPeriods;
	}

}
