package com.scholastic.slz.core.data.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the school_calendars database table.
 * 
 */
@Entity
@Table(name = "school_calendars")
@NamedQueries({
		@NamedQuery(name = "SchoolCalendar.findAll", query = "SELECT s FROM SchoolCalendar s"),
		@NamedQuery(name = "UserAccount.findSchoolCalendarDetails", query = "SELECT distinct sc FROM SchoolCalendar sc join fetch sc.schoolMarkingPeriods where sc.organization.id=:identifier and sc.deleted='N'"),
		@NamedQuery(name = "SchoolCalendar.findByOrgId", query = "SELECT s FROM SchoolCalendar s join fetch s.schoolMarkingPeriods where s.currentCalendar = 'Y' and s.organization = :orgId") })
public class SchoolCalendar extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_ORGID = "SchoolCalendar.findByOrgId";
	public static final String FIND_SCHOOL_CALENDAR_DEATILS = "UserAccount.findSchoolCalendarDetails";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "current_calendar")
	private Boolean currentCalendar;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	private Date endDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	private Organization organization;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	private Date startDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "schoolCalendar")
	private Set<SchoolMarkingPeriod> schoolMarkingPeriods;

	public SchoolCalendar() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getCurrentCalendar() {
		return this.currentCalendar;
	}

	public void setCurrentCalendar(Boolean currentCalendar) {
		this.currentCalendar = currentCalendar;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Set<SchoolMarkingPeriod> getSchoolMarkingPeriods() {
		return this.schoolMarkingPeriods;
	}

	public void setSchoolMarkingPeriods(
			Set<SchoolMarkingPeriod> schoolMarkingPeriods) {
		this.schoolMarkingPeriods = schoolMarkingPeriods;
	}

}