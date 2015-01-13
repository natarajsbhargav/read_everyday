package com.scholastic.slz.core.api;

/**
 * 
 * @author vijay.abbigeri
 * 
 */
public final class LegacyApiUtils {

	private LegacyApiUtils() {
	}

	/**
	 * Checks if the array has null values
	 * 
	 * @param array
	 *            the array whose values needs to be checked
	 * @return true if the specified array has null values; false otherwise
	 */
	public static boolean hasNull(final String array[]) {
		boolean hasNull = false;
		if (array != null) {
			for (final String value : array) {
				if (value == null || value.isEmpty()) {
					hasNull = true;
					break;
				}
			}
		}
		return hasNull;
	}

}
