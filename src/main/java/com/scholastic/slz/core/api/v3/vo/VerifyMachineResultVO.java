/**
 * 
 */
package com.scholastic.slz.core.api.v3.vo;

import java.util.ArrayList;
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
public class VerifyMachineResultVO extends ResultVO {

	/** The list of products */
	@XmlElement
	private List<VerifyRegistrationVO> products = new ArrayList<VerifyRegistrationVO>();

	/**
	 * @return the products
	 */
	public List<VerifyRegistrationVO> getProducts() {
		return products;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(final List<VerifyRegistrationVO> products) {
		this.products = products;
	}

}
