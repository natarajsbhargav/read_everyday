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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the organization_groups database table.
 * 
 */
@Entity
@Table(name = "organization_groups")
@NamedQueries({
		@NamedQuery(name = "OrganizationGroup.findAll", query = "SELECT o FROM OrganizationGroup o"),
		@NamedQuery(name = "OrganizationGroup.findByUuid", query = "select distinct o from OrganizationGroup o "
				+ "left join fetch o.parentOrgGroup "
				+ "left join fetch o.childOrganizations "
				+ "left join fetch o.childOrgGroups "
				+ "where o.uuid = :uuid and o.deleted = 'N'"),
		@NamedQuery(name = "OrganizationGroup.findByUuidFull", query = "select distinct o from OrganizationGroup o "
				+ "left join fetch o.country "
				+ "left join fetch o.parentOrgGroup "
				+ "left join fetch o.childOrganizations "
				+ "left join fetch o.childOrgGroups "
				+ "where o.uuid = :uuid and o.deleted = 'N'"),
		@NamedQuery(name = "OrganizationGroup.findByName", query = "select o from OrganizationGroup o "
				+ "where lower(o.name) like lower(:name) "
				+ "and o.deleted = 'N'") })
public class OrganizationGroup extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_BY_UUID = "OrganizationGroup.findByUuid";

	public static final String QUERY_FIND_BY_UUID_FULL = "OrganizationGroup.findByUuidFull";

	public static final String QUERY_FIND_BY_NAME = "OrganizationGroup.findByName";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "admin_area_name")
	private String adminAreaName;

	private String description;

	@Column(name = "locality_name")
	private String localityName;

	private String name;

	@Column(name = "source_user_id")
	private Integer sourceUserId;

	private String uuid;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Country country;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private OrganizationGroup parentOrgGroup;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentOrgGroup")
	private Set<OrganizationGroup> childOrgGroups;

	@ManyToOne(fetch = FetchType.LAZY)
	private Region region;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "organization_group_mapping", joinColumns = { @JoinColumn(name = "organization_group_id") }, inverseJoinColumns = { @JoinColumn(name = "organization_id") })
	private Set<Organization> childOrganizations;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organizationGroup")
	private Set<UserAccount> userAccounts;

	public OrganizationGroup() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdminAreaName() {
		return this.adminAreaName;
	}

	public void setAdminAreaName(String adminAreaName) {
		this.adminAreaName = adminAreaName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocalityName() {
		return this.localityName;
	}

	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSourceUserId() {
		return this.sourceUserId;
	}

	public void setSourceUserId(Integer sourceUserId) {
		this.sourceUserId = sourceUserId;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public OrganizationGroup getParentOrgGroup() {
		return parentOrgGroup;
	}

	public void setParentOrgGroup(OrganizationGroup parentOrgGroup) {
		this.parentOrgGroup = parentOrgGroup;
	}

	public Set<OrganizationGroup> getChildOrgGroups() {
		return childOrgGroups;
	}

	public void setChildOrgGroups(Set<OrganizationGroup> childOrgGroups) {
		this.childOrgGroups = childOrgGroups;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Set<Organization> getChildOrganizations() {
		return childOrganizations;
	}

	public void setChildOrganizations(Set<Organization> childOrganizations) {
		this.childOrganizations = childOrganizations;
	}

	public Set<UserAccount> getUserAccounts() {
		return userAccounts;
	}

	public void setUserAccounts(Set<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}
}