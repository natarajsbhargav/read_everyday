package com.scholastic.slz.core.api.v3.form;

import javax.ws.rs.FormParam;

import com.scholastic.slz.core.api.LegacyApiConstants;

/**
 * @author snehalata.raulo
 * 
 */
public class OfflineRegistrationForm {

	/** The user identifier */
	@FormParam(LegacyApiConstants.IDENTIFIER)
	private String userIdentifier;

	/** The product code */
	@FormParam(LegacyApiConstants.PRODUCT)
	private String productCode;

	/** The machine uuid */
	@FormParam(LegacyApiConstants.UUID)
	private String machineUuid;

	/** The machine name */
	@FormParam(LegacyApiConstants.NAME)
	private String machineName;

	/** The registration uuid */
	private String registrationUuid;

	/** The number of seats */
	private Integer numOfSeats;

	/**
	 * @return the userIdentifier
	 */
	public String getUserIdentifier() {
		return userIdentifier;
	}

	/**
	 * @param userIdentifier
	 *            the userIdentifier to set
	 */
	public void setUserIdentifier(final String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode
	 *            the productCode to set
	 */
	public void setProductCode(final String productCode) {
		this.productCode = productCode;
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
}
