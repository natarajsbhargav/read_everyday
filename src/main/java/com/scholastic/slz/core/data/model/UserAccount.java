package com.scholastic.slz.core.data.model;

import java.io.Serializable;
import java.util.Set;

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
import javax.persistence.Table;

/**
 * The persistent class for the user_accounts database table.
 * 
 */
@Entity
@Table(name = "user_accounts")
@NamedQueries({
		@NamedQuery(name = UserAccount.FIND_BY_UUID_AND_TYPE, query = "SELECT u FROM UserAccount u "
				+ "join fetch u.organization "
				+ "left join fetch u.classes "
				+ "left join fetch u.grade as g "
				+ "left join fetch g.sriGrade "
				+ "left join fetch g.gradeSystem "
				+ "where u.uuid = :uuid and u.type = :userType and u.deleted = 'N'"),
		@NamedQuery(name = UserAccount.FIND_BY_ORG_AND_GRADE, query = "select u from UserAccount u "
				+ "join fetch u.grade as g "
				+ "join fetch g.sriGrade as sg "
				+ "join fetch g.gradeSystem as gs "
				+ "left join fetch u.products as p "
				+ "left join fetch p.application as a "
				+ "where (u.organization = :org "
				+ "and sg.shortName = :gradeCode and u.deleted = 'N') or "
				+ "(u.organization = :org and sg.shortName = :gradeCode "
				+ "and u.deleted = 'N' and a.code = :appCode)"),
		@NamedQuery(name = UserAccount.FIND_ADMIN_DETAILS_BY_CODE, query = "select  ua   FROM  UserAccount ua join fetch ua.organization    where ua.uuid = :identifier      AND  ua.type = 'administrator'  AND ua.enabled = 1 AND ua.organization.deleted = 'n'"),
		@NamedQuery(name = UserAccount.FIND_TEACHER_DETAILS_BY_CODE, query = "SELECT ua FROM UserAccount ua   join fetch ua.organization as o  left join fetch o.classes where  o.deleted = 'N' and ua.uuid = :identifier AND ua.type = 'teacher' AND ua.enabled = 1"),
		@NamedQuery(name = UserAccount.FIND_BY_CODE, query = "select  ua   FROM  UserAccount ua join fetch ua.organization where ua.uuid = :identifier"),
		@NamedQuery(name = UserAccount.QUERY_FIND_BY_CLASS_ID_AND_TYPE, query = "select distinct u from UserAccount u "
				+ "left join fetch u.classes c "
				+ "left join fetch u.products p "
				+ "left join fetch p.application a "
				+ "where (c.id = :classId and u.deleted = 'N' "
				+ "and u.type = :userType) or (c.id = :classId and u.deleted = 'N' "
				+ "and u.type = :userType and a.code = :appCode)"),
		@NamedQuery(name = UserAccount.QUERY_FIND_ORG_GROUP_ADMIN_BY_ID, query = "select u from UserAccount u "
				+ "join fetch u.organizationGroup og "
				+ "where u.uuid = :uuid and u.type = 'gadmin' "
				+ "and u.enabled = 1 and u.deleted = 'N'"),
		@NamedQuery(name = UserAccount.FIND_STUDENT_BY_CLASSES, query = "select distinct u from UserAccount u "
				+ "left join fetch u.grade as g "
				+ "left join fetch g.sriGrade "
				+ "left outer join fetch u.products p "
				+ "left outer join fetch p.application a "
				+ "left join fetch u.organization o   "
				+ "where u.type = :userType  and a.code = :appCode and o.id = :organizationID and LOWER(CONCAT(u.firstName , u.lastName, u.username)) like  :queryString"),
		@NamedQuery(name = UserAccount.FIND_USER_ENTITLEMENT, query = "select distinct u from UserAccount u "
				+ "join fetch u.products p "
				+ "join fetch p.orderEntitlements o "
				+ "join fetch u.organization org "
				+ "where u.uuid = :uuid and "
				+ "o.organization = u.organization and "
				+ "p.code = :productCode and "
				+ "p.isOffline = 1 and o.isActive = 1 "),
		@NamedQuery(name = "UserAccount.findStudentByOrgIdAndUsername", query = "select distinct u from UserAccount u "
				+ "join fetch u.organization o "
				+ "left join fetch u.classes "
				+ "left join fetch u.grade g "
				+ "left join fetch g.sriGrade "
				+ "left join fetch g.gradeSystem "
				+ "left join fetch u.products as p "
				+ "left join fetch p.application as a "
				+ "where u.type = 'student' "
				+ "and u.username = :username "
				+ "and o.id = :orgId "
				+ "and u.enabled = 1 and u.deleted = 'N'") })
public class UserAccount extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String FIND_BY_UUID_AND_TYPE = "UserAccount.findByUuidAndType";
	public static final String FIND_BY_ORG_AND_GRADE = "UserAccount.findByOrgAndGrade";
	public static final String FIND_ADMIN_DETAILS_BY_CODE = "UserAccount.findAdminDetailsByCode";
	public static final String FIND_TEACHER_DETAILS_BY_CODE = "UserAccount.findTeacherDetailsByCode";
	public static final String FIND_BY_CODE = "UserAccount.findByCode";
	public static final String QUERY_FIND_BY_CLASS_ID_AND_TYPE = "UserAccount.findByClassIdAndType";
	public static final String QUERY_FIND_ORG_GROUP_ADMIN_BY_ID = "UserAccount.findOrgGroupAdminById";
	public static final String FIND_STUDENT_BY_CLASSES = "UserAccount.findStudentByClasses";
	public static final String FIND_USER_ENTITLEMENT = "UserAccount.findUserEntitlement";
	public static final String FIND_STUDENT_BY_ORG_ID_AND_USERNAME = "UserAccount.findStudentByOrgIdAndUsername";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "district_user_id")
	private String districtUserId;

	private String email;

	private byte enabled;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "lexile_level")
	private Integer lexileLevel;

	@Column(name = "lexile_source")
	private String lexileSource;

	private String password;

	private String type;

	private String username;

	private String uuid;

	// bi-directional many-to-one association to Grade
	@ManyToOne(fetch = FetchType.LAZY)
	private Grade grade;

	// bi-directional many-to-one association to Organization
	@ManyToOne(fetch = FetchType.LAZY)
	private Organization organization;

	@ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
	private Set<ClassInfo> classes;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_entitlements", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "product_id") })
	private Set<Product> products;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_group_id")
	private OrganizationGroup organizationGroup;

	public UserAccount() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDistrictUserId() {
		return this.districtUserId;
	}

	public void setDistrictUserId(String districtUserId) {
		this.districtUserId = districtUserId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getLexileLevel() {
		return this.lexileLevel;
	}

	public void setLexileLevel(Integer lexileLevel) {
		this.lexileLevel = lexileLevel;
	}

	public String getLexileSource() {
		return this.lexileSource;
	}

	public void setLexileSource(String lexileSource) {
		this.lexileSource = lexileSource;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Grade getGrade() {
		return this.grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Set<ClassInfo> getClasses() {
		return classes;
	}

	public void setClasses(Set<ClassInfo> classes) {
		this.classes = classes;
	}

	/**
	 * @return the products
	 */
	public Set<Product> getProducts() {
		return products;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public OrganizationGroup getOrganizationGroup() {
		return organizationGroup;
	}

	public void setOrganizationGroup(OrganizationGroup organizationGroup) {
		this.organizationGroup = organizationGroup;
	}
}