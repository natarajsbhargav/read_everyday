package com.scholastic.slz.core.api.v3.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author snehalata.raulo
 * 
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class RegionListResultVO extends ResultVO {
	
	/** The regions*/
	@XmlElement
	private List<BasicVO> regions;

	/**
	 * @return the regions
	 */
	public List<BasicVO> getRegions() {
		return regions;
	}

	/**
	 * @param regions the regions to set
	 */
	public void setRegions(final List<BasicVO> regions) {
		this.regions = regions;
	}
	
	

}