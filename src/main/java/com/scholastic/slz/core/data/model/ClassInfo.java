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
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the classes database table.
 */
@Entity
@Table(name = "classes")
@NamedQueries({
		@NamedQuery(name = "ClassInfo.findByUuid", query = "select c from ClassInfo c join fetch c.organization where c.uuid = :uuid and c.deleted = 'N'"),
		@NamedQuery(name = "ClassInfo.findClassesForTeacher", query = "select c from ClassInfo c join fetch c.users u where u.id = :identifier and u.deleted ='N' and c.deleted= 'N' and LOWER(CONCAT(u.firstName , u.lastName, u.username)) like  :queryString"),
		@NamedQuery(name = "ClassInfo.findAllClasses", query = "select c from ClassInfo c join fetch c.organization o where o.orgGuid = :orgGuid and o.orgUuid = :orgUuid and c.deleted = 'N' and o.deleted ='N' and LOWER(o.name) like  :queryString") })
public class ClassInfo extends BaseEntity implements Serializable {

	public ClassInfo(String uuid, String name) {
		this.uuid = uuid;
		this.name = name;
	}

	public ClassInfo() {
	}

	private static final long serialVersionUID = 1L;

	public static final String QUERY_FIND_BY_UUID = "ClassInfo.findByUuid";
	public static final String QUERY_FIND_CLASSES_STUDENT = "ClassInfo.findAllClasses";
	public static final String QUERY_FIND_CLASSES_TEACHER = "ClassInfo.findClassesForTeacher";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@Column(name = "class_uuid")
	private String uuid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "organization_id", referencedColumnName = "id") })
	private Organization organization;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "class_user_mapping", joinColumns = { @JoinColumn(name = "class_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	private Set<UserAccount> users;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Set<UserAccount> getUsers() {
		return users;
	}

	public void setUsers(Set<UserAccount> users) {
		this.users = users;
	}
}
