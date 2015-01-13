/**
 * 
 */
package com.scholastic.slz.core.api.v3.vo;

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
public class OfflineRegistrationResultVO extends ResultVO {

	/** the offline Number of Days */
	@XmlAttribute(name = LegacyApiConstants.OFFLINE_NUM_DAYS)
	@JsonProperty(LegacyApiConstants.OFFLINE_NUM_DAYS)
	private Integer offlineNumDays;

	/** the registration uuid */
	@XmlAttribute(name = LegacyApiConstants.REGISTRATION_UUID)
	@JsonProperty(LegacyApiConstants.REGISTRATION_UUID)
	private String registrationUuid;

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

}
