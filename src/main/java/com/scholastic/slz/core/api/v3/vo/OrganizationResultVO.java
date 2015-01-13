package com.scholastic.slz.core.api.v3.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author snehalata.raulo
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class OrganizationResultVO extends ResultVO {

	/** The identifier */
	@XmlAttribute
	private String identifier;

	/** The name */
	@XmlAttribute
	private String name;

	/** The testAccount */
	@XmlAttribute
	private boolean testAccount;

	/** The list of schoolGroups */
	@XmlElement
	private List<BasicVO> schoolGroups = new ArrayList<BasicVO>();

	/** The list of classes */
	@XmlElement
	private List<BasicVO> classes = new ArrayList<BasicVO>();

	/** The time zone */
	@XmlElement
	private TimezoneVO timezone;

	/** The schoolCalendar */
	@XmlElement
	private SchoolCalendarVO schoolCalendar;

	/** The locale */
	@XmlElement
	private LocaleVO locale;

	/** The gradeSystem */
	@XmlElement
	private GradeSystemVO gradeSystem;

	/**
	 * 
	 * @return The identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * 
	 * @param identifier
	 *            The identifier
	 */
	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	/**
	 * 
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return The schoolGroups
	 */
	public List<BasicVO> getSchoolGroups() {
		return schoolGroups;
	}

	/**
	 * 
	 * @param schoolGroups
	 *            The schoolGroups
	 */
	public void setSchoolGroups(final List<BasicVO> schoolGroups) {
		this.schoolGroups = schoolGroups;
	}

	/**
	 * @return the classes
	 */
	public List<BasicVO> getClasses() {
		return classes;
	}

	/**
	 * @param classes
	 *            the classes to set
	 */
	public void setClasses(final List<BasicVO> classes) {
		this.classes = classes;
	}

	/**
	 * 
	 * @return The timezone
	 */
	public TimezoneVO getTimezone() {
		return timezone;
	}

	/**
	 * 
	 * @param timezone
	 *            The timezone
	 */
	public void setTimezone(final TimezoneVO timezone) {
		this.timezone = timezone;
	}

	/**
	 * 
	 * @return The schoolCalendar
	 */
	public SchoolCalendarVO getSchoolCalendar() {
		return schoolCalendar;
	}

	/**
	 * 
	 * @param schoolCalendar
	 *            The schoolCalendar
	 */
	public void setSchoolCalendar(final SchoolCalendarVO schoolCalendar) {
		this.schoolCalendar = schoolCalendar;
	}

	/**
	 * 
	 * @return The locale
	 */
	public LocaleVO getLocale() {
		return locale;
	}

	/**
	 * 
	 * @param locale
	 *            The locale
	 */
	public void setLocale(final LocaleVO locale) {
		this.locale = locale;
	}

	/**
	 * 
	 * @return The gradeSystem
	 */
	public GradeSystemVO getGradeSystem() {
		return gradeSystem;
	}

	/**
	 * 
	 * @param gradeSystem
	 *            The gradeSystem
	 */
	public void setGradeSystem(final GradeSystemVO gradeSystem) {
		this.gradeSystem = gradeSystem;
	}

	/**
	 * @return the testAccount
	 */
	public boolean isTestAccount() {
		return testAccount;
	}

	/**
	 * @param testAccount
	 *            the testAccount to set
	 */
	public void setTestAccount(final boolean testAccount) {
		this.testAccount = testAccount;
	}

}