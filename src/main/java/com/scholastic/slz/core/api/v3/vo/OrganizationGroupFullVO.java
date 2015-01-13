package com.scholastic.slz.core.api.v3.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 
 * @author vijay.abbigeri
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class OrganizationGroupFullVO extends OrganizationGroupVO {

	/**
	 * Returns the list of child organization groups of this organization group
	 * 
	 * @return the list of child organization groups
	 */
	@XmlElement(name = "childrenSchoolGroups")
	@JsonProperty("childrenSchoolGroups")
	public List<OrganizationGroupVO> getChildOrgGroups() {
		return super.getChildOrgGroups();
	}
	
}