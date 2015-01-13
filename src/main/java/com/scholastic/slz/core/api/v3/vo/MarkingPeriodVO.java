package com.scholastic.slz.core.api.v3.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
@XmlRootElement(name = "markingPeriods")
@JsonRootName(value = "markingPeriods")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class MarkingPeriodVO {

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

	/**
	 * 
	 * @return The description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 *            The description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return The startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * 
	 * @param startDate
	 *            The startDate
	 */
	public void setStartDate(Date startDate) {
		if (startDate != null) {
			this.startDate = dateFormat.format(startDate);
		}
	}

	/**
	 * 
	 * @return The endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * 
	 * @param endDate
	 *            The endDate
	 */
	public void setEndDate(Date endDate) {
		if (endDate != null) {
			this.endDate = dateFormat.format(endDate);
		}
	}

}
