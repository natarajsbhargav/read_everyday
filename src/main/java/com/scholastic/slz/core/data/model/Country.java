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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the countries database table.
 * 
 */
@Entity
@Table(name = "countries")
@NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String code;

	@Column(name = "currency_id")
	private Integer currencyId;

	@Column(name = "custserv_email")
	private String custservEmail;

	@Column(name = "custserv_phone")
	private String custservPhone;

	@Column(name = "grade_system_id")
	private Integer gradeSystemId;

	@Column(name = "marketing_site_url")
	private String marketingSiteUrl;

	@Column(name = "micro_site_url")
	private String microSiteUrl;

	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "school_year_end_date")
	private Date schoolYearEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "school_year_start_date")
	private Date schoolYearStartDate;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	private Region region;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")
	private Set<OrganizationGroup> organizationGroups;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")
	private Set<Organization> organizations;

	public Country() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public String getCustservEmail() {
		return this.custservEmail;
	}

	public void setCustservEmail(String custservEmail) {
		this.custservEmail = custservEmail;
	}

	public String getCustservPhone() {
		return this.custservPhone;
	}

	public void setCustservPhone(String custservPhone) {
		this.custservPhone = custservPhone;
	}

	public Integer getGradeSystemId() {
		return this.gradeSystemId;
	}

	public void setGradeSystemId(Integer gradeSystemId) {
		this.gradeSystemId = gradeSystemId;
	}

	public String getMarketingSiteUrl() {
		return this.marketingSiteUrl;
	}

	public void setMarketingSiteUrl(String marketingSiteUrl) {
		this.marketingSiteUrl = marketingSiteUrl;
	}

	public String getMicroSiteUrl() {
		return this.microSiteUrl;
	}

	public void setMicroSiteUrl(String microSiteUrl) {
		this.microSiteUrl = microSiteUrl;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getSchoolYearEndDate() {
		return this.schoolYearEndDate;
	}

	public void setSchoolYearEndDate(Date schoolYearEndDate) {
		this.schoolYearEndDate = schoolYearEndDate;
	}

	public Date getSchoolYearStartDate() {
		return this.schoolYearStartDate;
	}

	public void setSchoolYearStartDate(Date schoolYearStartDate) {
		this.schoolYearStartDate = schoolYearStartDate;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Set<OrganizationGroup> getOrganizationGroups() {
		return this.organizationGroups;
	}

	public void setOrganizationGroups(Set<OrganizationGroup> organizationGroups) {
		this.organizationGroups = organizationGroups;
	}

	public Set<Organization> getOrganizations() {
		return this.organizations;
	}

	public void setOrganizations(Set<Organization> organizations) {
		this.organizations = organizations;
	}

}