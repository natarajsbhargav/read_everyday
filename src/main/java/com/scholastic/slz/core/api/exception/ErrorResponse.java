package com.scholastic.slz.core.api.exception;

/**
 * @author Madan D H
 *
 */
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ErrorResponse which is sent back as a resposne from SLZAPI
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "error")
public class ErrorResponse {
	/**
	 * error400
	 */
	public static final String ERROR400 = "Sorry, there was a problem gathering some information from SLZ.  If this problem persists, please contact a Scholastic representative for assistance. [4xx]";
	/**
	 * error40401
	 */
	public static final String ERROR40401 = "Sorry, a user could not be found within SLZ.  If this problem persists, please contact a Scholastic representative for assistance. [40401]";
	/**
	 * error405
	 */
	public static final String ERROR405 = "Sorry, there was a problem gathering some information from SLZ.  If this problem persists, please contact a Scholastic representative for assistance. [405]";
	/**
	 * error500
	 */
	public static final String ERROR500 = "Sorry, there was a problem gathering some information from SLZ.  If this problem persists, please contact a Scholastic representative for assistance. [5xx]";
	/**
	 * status
	 */
	private String status;
	/**
	 * errorMessage
	 */
	/**
	 * diagnosticInfo
	 */
	private String errorMessage, diagnosticInfo;

	/**
	 * timestamp
	 */
	private String timestamp;

	/**
	 * code
	 */
	private String code;

	/**
	 * @return code
	 */
	@XmlElement
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @return timestamp
	 */
	@XmlElement
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setTimestamp(final String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return status
	 */
	@XmlElement
	public String getStatus() {
		return status;
	}

	/**
	 * @return errorMessage
	 */
	@XmlElement
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @return diagnosticInfo
	 */
	@XmlElement
	public String getDiagnosticInfo() {
		return diagnosticInfo;
	}

	/**
	 * @param status
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * @param errorMessage
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @param diagnosticInfo
	 */
	public void setDiagnosticInfo(final String diagnosticInfo) {
		this.diagnosticInfo = diagnosticInfo;
	}

}
