package com.scholastic.slz.core.data.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the organizations database table.
 * 
 */
@Entity
@Table(name = "organizations")
@NamedQueries({
		@NamedQuery(name = "Organization.findAll", query = "SELECT o FROM Organization o"),
		@NamedQuery(name = "Organization.findByCode", query = "select o from Organization o where o.siteid = :code and o.deleted = 'N'"),
		@NamedQuery(name = "Organization.findByIdentifiers", query = "SELECT o FROM Organization o where o.orgGuid = :orgGuid and o.orgUuid = :orgUuid and o.deleted = 'N'"),
		@NamedQuery(name = "Organization.findOrgDetails", query = "SELECT distinct o FROM Organization as o "
				+ "left join fetch o.timeZone as tz "
				+ "left join fetch o.country as co "
				+ "left join fetch o.region as g "
				+ "left join fetch o.gradeSystem as gs "
				+ "left join fetch o.schoolCalendars as sc "
				+ "left join fetch sc.schoolMarkingPeriods as sm "
				+ "left join fetch o.organizationGroups as og "
				+ "left join fetch o.classes as c "
				+ "where o = :org and o.deleted = 'N' and "
				+ "c.deleted = 'N' and sc.currentCalendar = 1"),
		@NamedQuery(name = "Organization.findByOrgUuidGuid", query = "SELECT o FROM Organization o WHERE o.orgUuid = :orgUuid and o.orgGuid = :orgGuid and o.deleted ='N'"),
		@NamedQuery(name = "Organization.findByOrgNameAndIdentifier", query = "select o from Organization o "
				+ "join fetch o.country "
				+ "join fetch o.region "
				+ "where lower(o.name) like lower(:name) "
				+ "and o.orgGuid = :guid and o.orgUuid = :uuid "
				+ "and o.deleted = 'N'"),
		@NamedQuery(name = "Organization.findByOrgName", query = "select o from Organization o "
				+ "join fetch o.country "
				+ "join fetch o.region "
				+ "where lower(o.name) like lower(:name) "
				+ "and o.deleted = 'N'"),
		@NamedQuery(name = "Organization.findByOrgIdAndGroupName", query = "select o from Organization o "
				+ "left join fetch o.organizationGroups as og "
				+ "where lower(og.name) like lower(:name) "
				+ "and o.orgGuid = :guid and o.orgUuid = :uuid "
				+ "and o.deleted = 'N'") })
public class Organization extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_BY_CODE = "Organization.findByCode";

	public static final String FIND_BY_IDENTIFIERS = "Organization.findByIdentifiers";

	public static final String FIND_ORG_DETAILS = "Organization.findOrgDetails";

	public static final String FIND_BY_ORG_UUID_GUID = "Organization.findByOrgUuidGuid";

	public static final String QUERY_FIND_BY_ORG_NAME_AND_ID = "Organization.findByOrgNameAndIdentifier";

	public static final String QUERY_FIND_BY_ORG_NAME = "Organization.findByOrgName";

	public static final String QUERY_FIND_BY_ORG_ID_AND_GROUP_NAME = "Organization.findByOrgIdAndGroupName";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@Column(name = "org_guid")
	private String orgGuid;

	@Column(name = "org_uuid")
	private String orgUuid;

	@Column(name = "reorg_timestamp")
	private Timestamp reorgTimestamp;

	@Column(name = "show_basic_aaiz")
	private byte showBasicAaiz;

	private String siteid;

	@Column(name = "slz_basic_cdn_assets")
	private byte slzBasicCdnAssets;

	@Column(name = "source_user_id")
	private Integer sourceUserId;

	@Column(name = "srii_litpro_flag")
	private byte sriiLitproFlag;

	@Column(name = "test_org")
	private boolean testOrg;

	private Integer ucn;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "grade_system_id")
	private GradeSystem gradeSystem;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Organization organization;

	@ManyToMany(mappedBy = "childOrganizations", fetch = FetchType.LAZY)
	private Set<OrganizationGroup> organizationGroups;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Region region;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "time_zone_id")
	private TimeZone timeZone;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")
	private Set<UserAccount> userAccounts;

	@OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<SchoolCalendar> schoolCalendars;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "organization")
	private Set<ClassInfo> classes;

	@Column(name = "admin_area_name")
	private String adminAreaName;

	@Column(name = "locality_name")
	private String localityName;

	public Organization() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgGuid() {
		return this.orgGuid;
	}

	public void setOrgGuid(String orgGuid) {
		this.orgGuid = orgGuid;
	}

	public String getOrgUuid() {
		return this.orgUuid;
	}

	public void setOrgUuid(String orgUuid) {
		this.orgUuid = orgUuid;
	}

	public Timestamp getReorgTimestamp() {
		return this.reorgTimestamp;
	}

	public void setReorgTimestamp(Timestamp reorgTimestamp) {
		this.reorgTimestamp = reorgTimestamp;
	}

	public byte getShowBasicAaiz() {
		return this.showBasicAaiz;
	}

	public void setShowBasicAaiz(byte showBasicAaiz) {
		this.showBasicAaiz = showBasicAaiz;
	}

	public String getSiteid() {
		return this.siteid;
	}

	public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

	public byte getSlzBasicCdnAssets() {
		return this.slzBasicCdnAssets;
	}

	public void setSlzBasicCdnAssets(byte slzBasicCdnAssets) {
		this.slzBasicCdnAssets = slzBasicCdnAssets;
	}

	public Integer getSourceUserId() {
		return this.sourceUserId;
	}

	public void setSourceUserId(Integer sourceUserId) {
		this.sourceUserId = sourceUserId;
	}

	public byte getSriiLitproFlag() {
		return this.sriiLitproFlag;
	}

	public void setSriiLitproFlag(byte sriiLitproFlag) {
		this.sriiLitproFlag = sriiLitproFlag;
	}

	public Integer getUcn() {
		return this.ucn;
	}

	public void setUcn(Integer ucn) {
		this.ucn = ucn;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @return the gradeSystem
	 */
	public GradeSystem getGradeSystem() {
		return gradeSystem;
	}

	/**
	 * @param gradeSystem
	 *            the gradeSystem to set
	 */
	public void setGradeSystem(GradeSystem gradeSystem) {
		this.gradeSystem = gradeSystem;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public TimeZone getTimeZone() {
		return this.timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public Set<UserAccount> getUserAccounts() {
		return this.userAccounts;
	}

	public void setUserAccounts(Set<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}

	/**
	 * @return the schoolCalendars
	 */
	public Set<SchoolCalendar> getSchoolCalendars() {
		return schoolCalendars;
	}

	/**
	 * @param schoolCalendars
	 *            the schoolCalendars to set
	 */
	public void setSchoolCalendars(Set<SchoolCalendar> schoolCalendars) {
		this.schoolCalendars = schoolCalendars;
	}

	/**
	 * @return the organizationGroups
	 */
	public Set<OrganizationGroup> getOrganizationGroups() {
		return organizationGroups;
	}

	/**
	 * @param organizationGroups
	 *            the organizationGroups to set
	 */
	public void setOrganizationGroups(Set<OrganizationGroup> organizationGroups) {
		this.organizationGroups = organizationGroups;
	}

	/**
	 * @return the classes
	 */
	public Set<ClassInfo> getClasses() {
		return classes;
	}

	/**
	 * @param classes
	 *            the classes to set
	 */
	public void setClasses(Set<ClassInfo> classes) {
		this.classes = classes;
	}

	/**
	 * @return the testOrg
	 */
	public boolean isTestOrg() {
		return testOrg;
	}

	/**
	 * @param testOrg
	 *            the testOrg to set
	 */
	public void setTestOrg(boolean testOrg) {
		this.testOrg = testOrg;
	}

	/**
	 * Returns the admin area name of this organization
	 * 
	 * @return the admin area name
	 */
	public String getAdminAreaName() {
		return adminAreaName;
	}

	/**
	 * Sets the admin area name for this organization
	 * 
	 * @param adminAreaName
	 *            the admin area name to be set
	 */
	public void setAdminAreaName(String adminAreaName) {
		this.adminAreaName = adminAreaName;
	}

	/**
	 * Returns the locality name of this organization
	 * 
	 * @return the locality name
	 */
	public String getLocalityName() {
		return localityName;
	}

	/**
	 * Sets the locality name for this organization
	 * 
	 * @param localityName
	 *            the locality name to be set
	 */
	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}
}