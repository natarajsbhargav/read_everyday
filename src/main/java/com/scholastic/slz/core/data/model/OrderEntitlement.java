package com.scholastic.slz.core.data.model;

import java.io.Serializable;
import java.sql.Timestamp;
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
 * The persistent class for the order_entitlements database table.
 * 
 */
@Entity
@Table(name = "order_entitlements")
@NamedQueries({
		@NamedQuery(name = "OrderEntitlement.findAll", query = "SELECT o FROM OrderEntitlement o"),
		@NamedQuery(name = OrderEntitlement.FIND_BY_OFFLINE_MACHINE_UUID, query = "select oe FROM OrderEntitlement oe "
				+ "join fetch oe.product p "
				+ "join fetch p.offlineRegistrations off "
				+ "where oe.isActive = 1 and "
				+ "off.machineUuid = :machineUuid and off.deleted = 'N'"),
		@NamedQuery(name = OrderEntitlement.FIND_BY_OFFLINE_REGISTRATION_UUID, query = "select oe FROM OrderEntitlement oe "
				+ "join fetch oe.product p "
				+ "join fetch p.offlineRegistrations off "
				+ "where oe.isActive = 1 and "
				+ "off.registrationUuid = :registrationUuid and off.deleted = 'N'") })
public class OrderEntitlement extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_OFFLINE_MACHINE_UUID = "OrderEntitlement.findByOfflineMachineUuid";

	public static final String FIND_BY_OFFLINE_REGISTRATION_UUID = "OrderEntitlement.findByOfflineRegistrationUuid";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "grace_period_end_date")
	private Timestamp gracePeriodEndDate;

	@Column(name = "grace_period_in_days")
	private Integer gracePeriodInDays;

	@Column(name = "grace_period_start_date")
	private Timestamp gracePeriodStartDate;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "num_of_days")
	private Integer numOfDays;

	@Column(name = "num_of_seats")
	private Integer numOfSeats;

	@Column(name = "order_line_id")
	private Integer orderLineId;

	@Column(name = "order_number")
	private String orderNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "subscription_end_date")
	private Date subscriptionEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "subscription_start_date")
	private Date subscriptionStartDate;

	// bi-directional many-to-one association to OrderEntitlement
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "renewal_entitlement_id")
	private OrderEntitlement orderEntitlement;

	// bi-directional many-to-one association to OrderEntitlement
	@OneToMany(mappedBy = "orderEntitlement")
	private Set<OrderEntitlement> orderEntitlements;

	// bi-directional many-to-one association to Organization
	@ManyToOne(fetch = FetchType.LAZY)
	private Organization organization;

	// bi-directional many-to-one association to Product
	@ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private Product product;

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
	 * @return the gracePeriodEndDate
	 */
	public Timestamp getGracePeriodEndDate() {
		return gracePeriodEndDate;
	}

	/**
	 * @param gracePeriodEndDate
	 *            the gracePeriodEndDate to set
	 */
	public void setGracePeriodEndDate(final Timestamp gracePeriodEndDate) {
		this.gracePeriodEndDate = gracePeriodEndDate;
	}

	/**
	 * @return the gracePeriodInDays
	 */
	public Integer getGracePeriodInDays() {
		return gracePeriodInDays;
	}

	/**
	 * @param gracePeriodInDays
	 *            the gracePeriodInDays to set
	 */
	public void setGracePeriodInDays(final Integer gracePeriodInDays) {
		this.gracePeriodInDays = gracePeriodInDays;
	}

	/**
	 * @return the gracePeriodStartDate
	 */
	public Timestamp getGracePeriodStartDate() {
		return gracePeriodStartDate;
	}

	/**
	 * @param gracePeriodStartDate
	 *            the gracePeriodStartDate to set
	 */
	public void setGracePeriodStartDate(final Timestamp gracePeriodStartDate) {
		this.gracePeriodStartDate = gracePeriodStartDate;
	}

	/**
	 * @return the isActive
	 */
	public Boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setActive(final Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the numOfDays
	 */
	public Integer getNumOfDays() {
		return numOfDays;
	}

	/**
	 * @param numOfDays
	 *            the numOfDays to set
	 */
	public void setNumOfDays(final Integer numOfDays) {
		this.numOfDays = numOfDays;
	}

	/**
	 * @return the numOfSeats
	 */
	public Integer getNumOfSeats() {
		return numOfSeats;
	}

	/**
	 * @param numOfSeats
	 *            the numOfSeats to set
	 */
	public void setNumOfSeats(final Integer numOfSeats) {
		this.numOfSeats = numOfSeats;
	}

	/**
	 * @return the orderLineId
	 */
	public Integer getOrderLineId() {
		return orderLineId;
	}

	/**
	 * @param orderLineId
	 *            the orderLineId to set
	 */
	public void setOrderLineId(final Integer orderLineId) {
		this.orderLineId = orderLineId;
	}

	/**
	 * @return the orderNumber
	 */
	public String getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber
	 *            the orderNumber to set
	 */
	public void setOrderNumber(final String orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @return the subscriptionEndDate
	 */
	public Date getSubscriptionEndDate() {
		return subscriptionEndDate;
	}

	/**
	 * @param subscriptionEndDate
	 *            the subscriptionEndDate to set
	 */
	public void setSubscriptionEndDate(final Date subscriptionEndDate) {
		this.subscriptionEndDate = subscriptionEndDate;
	}

	/**
	 * @return the subscriptionStartDate
	 */
	public Date getSubscriptionStartDate() {
		return subscriptionStartDate;
	}

	/**
	 * @param subscriptionStartDate
	 *            the subscriptionStartDate to set
	 */
	public void setSubscriptionStartDate(final Date subscriptionStartDate) {
		this.subscriptionStartDate = subscriptionStartDate;
	}

	/**
	 * @return the orderEntitlement
	 */
	public OrderEntitlement getOrderEntitlement() {
		return orderEntitlement;
	}

	/**
	 * @param orderEntitlement
	 *            the orderEntitlement to set
	 */
	public void setOrderEntitlement(final OrderEntitlement orderEntitlement) {
		this.orderEntitlement = orderEntitlement;
	}

	/**
	 * @return the orderEntitlements
	 */
	public Set<OrderEntitlement> getOrderEntitlements() {
		return orderEntitlements;
	}

	/**
	 * @param orderEntitlements
	 *            the orderEntitlements to set
	 */
	public void setOrderEntitlements(
			final Set<OrderEntitlement> orderEntitlements) {
		this.orderEntitlements = orderEntitlements;
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

}