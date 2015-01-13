package com.scholastic.slz.core.api.v3.vo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import com.scholastic.slz.core.api.LegacyApiConstants;

/**
 * @author snehalata.raulo
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class VerifyRegistrationResultVO extends ResultVO {

	/** The dateFormat */
	final transient private SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd", Locale.getDefault());

	/** the product code */
	@XmlAttribute
	private String productCode;

	/** the offline Number of Days */
	@XmlAttribute(name = LegacyApiConstants.OFFLINE_NUM_DAYS)
	@JsonProperty(LegacyApiConstants.OFFLINE_NUM_DAYS)
	private Integer offlineNumDays;

	/** the order Expiry Date */
	@XmlAttribute(name = LegacyApiConstants.ORDER_EXPIRY_DATE)
	@JsonProperty(LegacyApiConstants.ORDER_EXPIRY_DATE)
	private String orderExpiryDate;

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
	 * @return the offlineNumDays
	 */
	public Integer getOfflineNumDays() {
		return offlineNumDays;
	}

	/**
	 * @param offlineNumDays
	 *            the offlineNumDays to set
	 */
	public void setOfflineNumDays(final Integer offlineNumDays) {
		this.offlineNumDays = offlineNumDays;
	}

	/**
	 * @return the orderExpiryDate
	 */
	public String getOrderExpiryDate() {
		return orderExpiryDate;
	}

	/**
	 * @param orderExpiryDate
	 *            the orderExpiryDate to set
	 */
	public void setOrderExpiryDate(final Timestamp orderExpiryDate) {
		if (orderExpiryDate != null) {
			this.orderExpiryDate = dateFormat.format(orderExpiryDate);
		}
	}

}