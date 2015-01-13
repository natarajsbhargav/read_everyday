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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the products database table.
 * 
 */
@Entity
@Table(name = "products")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String code;

	private String description;

	@Column(name = "is_offline")
	private boolean isOffline;

	@Column(name = "is_roster")
	private boolean isRoster;

	private String isbn;

	@ManyToOne(fetch = FetchType.LAZY)
	private Application application;

	@OneToMany(mappedBy = "products", fetch = FetchType.LAZY)
	private Set<UserAccount> userAccounts;

	// bi-directional many-to-one association to OfflineRegistration
	@OneToMany(mappedBy = "product", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private Set<OfflineRegistration> offlineRegistrations;

	// bi-directional many-to-one association to OrderEntitlement
	@OneToMany(mappedBy = "product", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private Set<OrderEntitlement> orderEntitlements;

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
	public void setOrderEntitlements(Set<OrderEntitlement> orderEntitlements) {
		this.orderEntitlements = orderEntitlements;
	}

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the isOffline
	 */
	public boolean isOffline() {
		return isOffline;
	}

	/**
	 * @param isOffline
	 *            the isOffline to set
	 */
	public void setOffline(boolean isOffline) {
		this.isOffline = isOffline;
	}

	/**
	 * @return the isRoster
	 */
	public boolean isRoster() {
		return isRoster;
	}

	/**
	 * @param isRoster
	 *            the isRoster to set
	 */
	public void setRoster(boolean isRoster) {
		this.isRoster = isRoster;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Application getApplication() {
		return this.application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Set<UserAccount> getUserAccounts() {
		return this.userAccounts;
	}

	public void setUserAccounts(Set<UserAccount> userAccounts) {
		this.userAccounts = userAccounts;
	}

	/**
	 * @return the offlineRegistrations
	 */
	public Set<OfflineRegistration> getOfflineRegistrations() {
		return offlineRegistrations;
	}

	/**
	 * @param offlineRegistrations
	 *            the offlineRegistrations to set
	 */
	public void setOfflineRegistrations(
			Set<OfflineRegistration> offlineRegistrations) {
		this.offlineRegistrations = offlineRegistrations;
	}

}