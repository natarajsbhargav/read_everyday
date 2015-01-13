package com.scholastic.slz.core.api.exception;

/**
 * @author snehalata.raulo
 * 
 */
public enum ESlzApiErrorReason {

	CLIENT_ERROR_GENERIC("400"), AUTH_USER_NOT_FOUND("40401"), SC_USER_NOT_FOUND(
			"40402"), SCHOOL_GROUP_NOT_FOUND("40403"), SCHOOL_NOT_FOUND("40404"), SCHOOL_CLASS_NOT_FOUND(
			"40405"), STUDENT_GROUP_NOT_FOUND("40406"), REGIONS_NOT_FOUND(
			"40407"), COUNTRIES_REGION_NOT_FOUND("40408"), REGISTRATION_NOT_MODIFIED(
			"40409"),

	SERVER_ERROR_GENERIC("500");

	/**
	 * Error reason code
	 */
	private String code;

	/**
	 * @param pCode
	 */
	ESlzApiErrorReason(final String pCode) {
		this.code = pCode;
	}

	/**
	 * @return code
	 */
	public String getCode() {
		return code;
	}

}
