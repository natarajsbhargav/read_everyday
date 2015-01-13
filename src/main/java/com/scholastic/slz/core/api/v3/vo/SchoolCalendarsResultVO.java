package com.scholastic.slz.core.api.v3.vo;

import java.util.List;

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
@XmlRootElement(name = "schoolCalendars")
@JsonSerialize
public class SchoolCalendarsResultVO {
	/**
	 * List schoolCalendars
	 */
	List<SchoolCalenderResponseVO> schoolCalendars;

	/**
	 * @return List of SchoolCalenderResponse
	 */
	public List<SchoolCalenderResponseVO> getSchoolCalendars() {
		return schoolCalendars;
	}

	/**
	 * @param schoolCalendars
	 */
	public void setSchoolCalendars(
			final List<SchoolCalenderResponseVO> schoolCalendars) {
		this.schoolCalendars = schoolCalendars;
	}

}
