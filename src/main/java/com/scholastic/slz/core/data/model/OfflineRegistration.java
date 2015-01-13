package com.scholastic.slz.core.data.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the offline_registrations database table.
 * 
 */
@Entity
@Table(name = "offline_registrations")
@NamedQueries({
		@NamedQuery(name = OfflineRegistration.FIND_BY_PRODUCT_MACHINE, query = "select o from OfflineRegistration o "
				+ "join o.product p "
				+ "where p.code = :productCode "
				+ "and o.machineUuid = :machineUuid and o.deleted = 'N'"),
		@NamedQuery(name = OfflineRegistration.FIND_BY_MACHINE, query = "select o from OfflineRegistration o "
				+ "where o.machineUuid = :machineUuid and o.deleted = 'N'"),
		@NamedQuery(name = OfflineRegistration.FIND_BY_REGISTRATION, query = "select o from OfflineRegistration o "
				+ "where o.registrationUuid = :registrationUuid and o.deleted = 'N'"),
		@NamedQuery(name = OfflineRegistration.VALIDATE_BEFORE_REGISTRATION, query = "select o from OfflineRegistration o "
				+ "join o.userAccount u "
				+ "join o.product p "
				+ "where p.code = :productCode and o.userAccount = :user "
				+ "and o.machineUuid != :machineUuid and o.deleted = 'N'") })
public class OfflineRegistration extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_PRODUCT_MACHINE = "OfflineRegistration.findByProductAndMachine";

	public static final String FIND_BY_MACHINE = "OfflineRegistration.findByMachine";

	public static final String FIND_BY_REGISTRATION = "OfflineRegistration.findByRegistration";

	public static final String VALIDATE_BEFORE_REGISTRATION = "OfflineRegistration.validateBeforeRegistration";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "last_verified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastVerifiedDate;

	@Column(name = "machine_name")
	private String machineName;

	@Column(name = "machine_uuid")
	private String machineUuid;

	@Column(name = "registration_uuid")
	private String registrationUuid;

	// bi-directional many-to-one association to Organization
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "org_id")
	private Organization organization;

	// bi-directional many-to-one association to Product
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	// bi-directional many-to-one association to UserAccount
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserAccount userAccount;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final Integer id) {
		this.id = id;
	}

	/**
	 * @return the lastVerifiedDate
	 */
	public Date getLastVerifiedDate() {
		return lastVerifiedDate;
	}

	/**
	 * @param lastVerifiedDate
	 *            the lastVerifiedDate to set
	 */
	public void setLastVerifiedDate(final Date lastVerifiedDate) {
		this.lastVerifiedDate = lastVerifiedDate;
	}

	/**
	 * @return the machineName
	 */
	public String getMachineName() {
		return machineName;
	}

	/**
	 * @param machineName
	 *            the machineName to set
	 */
	public void setMachineName(final String machineName) {
		this.machineName = machineName;
	}

	/**
	 * @return the machineUuid
	 */
	public String getMachineUuid() {
		return machineUuid;
	}

	/**
	 * @param machineUuid
	 *            the machineUuid to set
	 */
	public void setMachineUuid(final String machineUuid) {
		this.machineUuid = machineUuid;
	}

	/**
	 * @return the registrationUuid
	 */
	public String getRegistrationUuid() {
		return registrationUuid;
	}

	/**
	 * @param registrationUuid
	 *            the registrationUuid to set
	 */
	public void setRegistrationUuid(final String registrationUuid) {
		this.registrationUuid = registrationUuid;
	}

	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param organization
	 *            the organization to set
	 */
	public void setOrganization(final Organization organization) {
		this.organization = organization;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(final Product product) {
		this.product = product;
	}

	/**
	 * @return the userAccount
	 */
	public UserAccount getUserAccount() {
		return userAccount;
	}

	/**
	 * @param userAccount
	 *            the userAccount to set
	 */
	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}