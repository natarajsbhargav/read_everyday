package com.scholastic.slz.core.api.v3.vo;

/**
 * @author madan.haraginadoni
 *
 */
/**
 * Is the VO object for creating the response from the database
 * 
 */
public class ClassResponse {
	/**
	 * identifier
	 */
	private String identifier;
	/**
	 * name
	 */
	private String name;

	/**
	 * 
	 * @return The identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * 
	 * @param identifier
	 *            The identifier
	 */
	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	/**
	 * 
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name
	 */
	public void setName(final String name) {
		this.name = name;
	}

}
