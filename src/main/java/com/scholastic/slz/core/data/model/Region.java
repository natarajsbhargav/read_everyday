package com.scholastic.slz.core.data.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the regions database table.
 * 
 */
@Entity
@Table(name = "regions")
@NamedQueries({
		@NamedQuery(name = "Region.findAll", query = "SELECT r FROM Region r where r.deleted = 'N'"),
		@NamedQuery(name = "Region.findCountryById", query = "SELECT distinct r FROM Region r "
				+ "left join fetch r.countries  "
				+ "where r.deleted = 'N' and r.id = :id") })
public class Region extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_ALL = "Region.findAll";

	public static final String QUERY_FIND_BY_ID = "Region.findCountryById";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "custserv_email")
	private String custservEmail;

	@Column(name = "custserv_phone")
	private String custservPhone;

	@Column(name = "grade_system_id")
	private Integer gradeSystemId;

	@Column(name = "marketing_site_url")
	private String marketingSiteUrl;

	private String name;

	@Column(name = "request_trial_url")
	private String requestTrialUrl;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "region")
	private Set<Country> countries;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "region")
	private Set<OrganizationGroup> organizationGroups;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "region")
	private Set<Organization> organizations;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRequestTrialUrl() {
		return this.requestTrialUrl;
	}

	public void setRequestTrialUrl(String requestTrialUrl) {
		this.requestTrialUrl = requestTrialUrl;
	}

	public Set<Country> getCountries() {
		return this.countries;
	}

	public void setCountries(Set<Country> countries) {
		this.countries = countries;
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