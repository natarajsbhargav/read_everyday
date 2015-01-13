package com.scholastic.slz.core.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.scholastic.slz.core.api.v3.form.OfflineRegistrationForm;
import com.scholastic.slz.core.data.dao.OfflineRegistrationDao;
import com.scholastic.slz.core.data.model.OfflineRegistration;
import com.scholastic.slz.core.data.model.OrderEntitlement;
import com.scholastic.slz.core.data.model.Product;
import com.scholastic.slz.core.data.model.UserAccount;

/**
 * @author snehalata.raulo
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class OfflineServiceTest {

	/** the offline registration DAO */
	@Mock
	private transient OfflineRegistrationDao offlineRegDao;

	/** the offline service */
	@InjectMocks
	private final transient OfflineService offlineService = new OfflineService();

	/** the machine uuid */
	public static final String MACHINE_UUID = "0b9e058c-39c8-49d5-8c1c-dfd585fdaca90";

	/** the machine name */
	public static final String MACHINE_NAME = "machine1";

	/** the product Code */
	public static final String PRODUCT_CODE = "TLINE";

	/**
	 * Tests that
	 * {@link com.scholastic.slz.core.services.OfflineService#geNumOfSeats(com.scholastic.slz.core.data.model.UserAccount)}
	 * returns null when user is not entitled to any products
	 */
	@Test
	public final void testGeNumOfSeatsForEmpty() {
		final Set<Product> product = new HashSet<Product>();
		final UserAccount user = new UserAccount();
		user.setProducts(product);
		final Integer result = offlineService.geNumOfSeats(user);
		assertEquals(result, null);
	}

	/**
	 * Tests that
	 * {@link com.scholastic.slz.core.services.OfflineService#geNumOfSeats(com.scholastic.slz.core.data.model.UserAccount)}
	 * returns appropriate number of seats for a user
	 */
	@Test
	public final void testGeNumOfSeats() {
		final UserAccount user = new UserAccount();
		user.setProducts(createProducts());
		final Integer result = offlineService.geNumOfSeats(user);
		assertEquals(result, Integer.valueOf(4));
	}

	/**
	 * Test method for
	 * {@link com.scholastic.slz.core.services.OfflineService#getByProductCode(java.lang.String, java.lang.String)}
	 */
	@Test
	public final void testGetByProductCode() {
		final List<OfflineRegistration> offlineRegistrations = getOfflineRegistrations();
		Mockito.when(offlineRegDao.findByProductCode("PIW1A", MACHINE_UUID))
				.thenReturn(offlineRegistrations);
		final List<OfflineRegistration> result = offlineService
				.getByProductCode("PIW1A", MACHINE_UUID);
		assertNotNull(result);
	}

	/**
	 * Test method for
	 * {@link com.scholastic.slz.core.services.OfflineService#getByMachineUuid(java.lang.String)}
	 */
	@Test
	public final void testGetByMachineUuid() {
		final List<OfflineRegistration> offlineRegistrations = getOfflineRegistrations();
		Mockito.when(offlineRegDao.findByMachineUuid(MACHINE_UUID)).thenReturn(
				offlineRegistrations);
		final List<OfflineRegistration> result = offlineService
				.getByMachineUuid(MACHINE_UUID);
		assertNotNull(result);
	}

	/**
	 * Tests
	 * {@link com.scholastic.slz.core.services.OfflineService#removeRegistration(java.util.List)}
	 * for empty list of registrations
	 */
	@Test
	public final void testRemoveRegistrationForEmptylist() {
		final List<OfflineRegistration> offlineRegs = new ArrayList<OfflineRegistration>();
		final Boolean result = offlineService.removeRegistration(offlineRegs);
		assertFalse(result);
	}

	/**
	 * Tests
	 * {@link com.scholastic.slz.core.services.OfflineService#removeRegistration(java.util.List)}
	 * for null
	 */
	@Test
	public final void testRemoveRegistrationForNull() {
		final Boolean result = offlineService.removeRegistration(null);
		assertFalse(result);
	}

	/**
	 * Test method for
	 * {@link com.scholastic.slz.core.services.OfflineService#removeRegistration(java.util.List)}
	 */
	@Test
	public final void testRemoveRegistration() {
		final List<OfflineRegistration> offlineRegistrations = getOfflineRegistrations();
		Mockito.when(offlineRegDao.removeRegistration(offlineRegistrations))
				.thenReturn(offlineRegistrations);
		final Boolean result = offlineService
				.removeRegistration(offlineRegistrations);
		assertFalse(result);
	}

	/**
	 * Tests
	 * {@link com.scholastic.slz.core.services.OfflineService#verifyRegistration(java.util.List)}
	 * for empty list of registrations
	 */
	@Test
	public final void testVerifyRegistrationForEmptylist() {
		final List<OfflineRegistration> offlineRegistrations = new ArrayList<OfflineRegistration>();
		final List<OfflineRegistration> result = offlineService
				.verifyRegistration(offlineRegistrations);
		assertNotNull(result);
	}

	/**
	 * Test method for
	 * {@link com.scholastic.slz.core.services.OfflineService#verifyRegistration(com.scholastic.slz.core.data.model.OfflineRegistration)}
	 */
	@Test
	public void testVerifyRegistrationForListOfRegistration() {
		final OfflineRegistration offlineRegistration = createOfflineRegistration(
				1, MACHINE_NAME, "N");
		final List<OfflineRegistration> offlineRegistrations = new ArrayList<OfflineRegistration>();
		offlineRegistrations.add(offlineRegistration);
		Mockito.when(offlineRegDao.verifyRegistration(offlineRegistration))
				.thenReturn(offlineRegistration);
		final List<OfflineRegistration> result = offlineService
				.verifyRegistration(offlineRegistrations);
		assertNotNull(result);
	}

	/**
	 * Test method for
	 * {@link com.scholastic.slz.core.services.OfflineService#getByRegistrationUuid(java.lang.String)}
	 */
	@Test
	public void testGetByRegistrationUuid() {
		final OfflineRegistration offlineregistration = createOfflineRegistration(1,
				MACHINE_NAME, "N");
		Mockito.when(offlineRegDao.findByRegistrationUuid(MACHINE_UUID))
				.thenReturn(offlineregistration);
		final OfflineRegistration result = offlineService
				.getByRegistrationUuid(MACHINE_UUID);
		assertNotNull(result);
	}

	/**
	 * Test method for
	 * {@link com.scholastic.slz.core.services.OfflineService#verifyRegistration(com.scholastic.slz.core.data.model.OfflineRegistration)}
	 */
	@Test
	public void testVerifyRegistration() {
		final OfflineRegistration offlineRegistration = createOfflineRegistration(
				1, MACHINE_NAME, "Y");
		Mockito.when(offlineRegDao.verifyRegistration(offlineRegistration))
				.thenReturn(offlineRegistration);
		final OfflineRegistration result = offlineService
				.verifyRegistration(offlineRegistration);
		assertNotNull(result);
	}

	/**
	 * Test method for
	 * {@link com.scholastic.slz.core.services.OfflineService#validateAndCreateRegistration(com.scholastic.slz.core.data.model.UserAccount)}
	 */
	@Test
	public final void testValidateAndCreateRegistration() {
		final OfflineRegistrationForm form = new OfflineRegistrationForm();
		final UserAccount user = new UserAccount();
		user.setProducts(createProducts());
		final OfflineRegistration offlineRegistration = createOfflineRegistration(
				1, MACHINE_NAME, "N");
		final List<OfflineRegistration> offlineRegistrations = new ArrayList<OfflineRegistration>();
		offlineRegistrations.add(offlineRegistration);
		Mockito.when(
				offlineRegDao.findByProductAndUser(MACHINE_UUID, PRODUCT_CODE,
						user)).thenReturn(offlineRegistrations);
		Mockito.when(
				offlineRegDao.createOfflineRegistration(offlineRegistration))
				.thenReturn(offlineRegistration);
		final Boolean result = offlineService.validateAndCreateRegistration(
				form, user);
		assertTrue(result);

	}

	/**
	 * Test method for
	 * {@link com.scholastic.slz.core.services.OfflineService#validateAndCreateRegistration(com.scholastic.slz.core.data.model.UserAccount)}
	 */
	@Test
	public final void testValidateAndCreateRegistrationForNullnoOfSeats() {
		final OfflineRegistrationForm form = new OfflineRegistrationForm();
		form.setProductCode(PRODUCT_CODE);
		form.setMachineUuid(MACHINE_UUID);
		final Set<OrderEntitlement> orderEntitlements = new HashSet<OrderEntitlement>();
		orderEntitlements.add(createOrderEntitlement(1, "1", null));
		final UserAccount user = new UserAccount();
		user.setProducts(createProducts());
		final OfflineRegistration offlineRegistration = createOfflineRegistration(
				1, MACHINE_NAME, "N");
		final List<OfflineRegistration> offlineRegistrations = new ArrayList<OfflineRegistration>();
		offlineRegistrations.add(offlineRegistration);
		Mockito.when(
				offlineRegDao.findByProductAndUser(MACHINE_UUID, PRODUCT_CODE,
						user)).thenReturn(offlineRegistrations);
		Mockito.when(
				offlineRegDao.createOfflineRegistration(offlineRegistration))
				.thenReturn(offlineRegistration);
		final Boolean result = offlineService.validateAndCreateRegistration(
				form, user);
		assertFalse(result);

	}

	/**
	 * Test method for
	 * {@link com.scholastic.slz.core.services.OfflineService#validateAndCreateRegistration(com.scholastic.slz.core.data.model.UserAccount)}
	 */
	@Test
	public final void testValidateAndCreateRegistrationMoreNoOfSeats() {
		final OfflineRegistrationForm form = new OfflineRegistrationForm();
		form.setProductCode(PRODUCT_CODE);
		form.setMachineUuid(MACHINE_UUID);
		form.setNumOfSeats(Integer.valueOf(4));
		final Set<OrderEntitlement> orderEntitlements = new HashSet<OrderEntitlement>();
		orderEntitlements.add(createOrderEntitlement(1, "1", 4));
		final UserAccount user = new UserAccount();
		user.setProducts(createProducts());
		final OfflineRegistration offlineRegistration = createOfflineRegistration(
				1, MACHINE_NAME, "N");
		final List<OfflineRegistration> offlineRegistrations = new ArrayList<OfflineRegistration>();
		offlineRegistrations.add(offlineRegistration);
		Mockito.when(
				offlineRegDao.findByProductAndUser(MACHINE_UUID, PRODUCT_CODE,
						user)).thenReturn(offlineRegistrations);
		Mockito.when(
				offlineRegDao.createOfflineRegistration(offlineRegistration))
				.thenReturn(offlineRegistration);
		final Boolean result = offlineService.validateAndCreateRegistration(
				form, user);
		assertTrue(result);

	}

	/**
	 * Returns a list of registrations
	 * 
	 * @return the list of registrations
	 */
	private List<OfflineRegistration> getOfflineRegistrations() {
		final List<OfflineRegistration> offlineregistrations = new ArrayList<OfflineRegistration>();
		offlineregistrations.add(createOfflineRegistration(1, "machine1", "N"));
		offlineregistrations.add(createOfflineRegistration(2, "machine2", "N"));
		offlineregistrations.add(createOfflineRegistration(3, "machine3", "N"));
		return offlineregistrations;
	}

	/**
	 * Creates a offline registration with a given identifier and name
	 * 
	 * @param identifier
	 *            the offline registration identifier
	 * @param name
	 *            the machine name
	 * @return the offline registration
	 */
	private OfflineRegistration createOfflineRegistration(
			final Integer identifier, final String name, final String deleted) {
		final OfflineRegistration offlineRegistration = new OfflineRegistration();
		offlineRegistration.setId(identifier);
		offlineRegistration.setMachineName(name);
		offlineRegistration.setDeleted(deleted);
		return offlineRegistration;
	}

	/**
	 * Creates a product with a given identifier and code
	 * 
	 * @param identifier
	 *            the product identifier
	 * @param code
	 *            the product code
	 * @return the product
	 */

	private Product createProduct(final Integer identifier, final String code) {
		final Product product = new Product();
		product.setOrderEntitlements(createOrderEntitlements());
		product.setId(1);
		product.setCode("TLINE");
		product.setId(identifier);
		product.setCode(code);
		return product;
	}

	/**
	 * Creates a list of products
	 * 
	 * @return the product list
	 */
	private Set<Product> createProducts() {
		final Set<Product> product = new HashSet<Product>();
		product.add(createProduct(1, PRODUCT_CODE));
		return product;
	}

	/**
	 * Creates a order entitlement with a given identifier, code and numOfSeats
	 * 
	 * @param identifier
	 *            the order entitlement identifier
	 * @param code
	 *            the order entitlement code
	 * @param numOfSeats
	 *            number of seats
	 * @return the order entitlement
	 */

	private OrderEntitlement createOrderEntitlement(final Integer identifier,
			final String code, final Integer numOfSeats) {
		final OrderEntitlement orderEntitlement = new OrderEntitlement();
		orderEntitlement.setId(identifier);
		orderEntitlement.setOrderNumber(code);
		orderEntitlement.setNumOfSeats(numOfSeats);
		return orderEntitlement;
	}

	/**
	 * Creates a list of order entitlements
	 * 
	 * @return the list of order entitlements
	 */
	private Set<OrderEntitlement> createOrderEntitlements() {
		final Set<OrderEntitlement> orderEntitlements = new HashSet<OrderEntitlement>();
		orderEntitlements.add(createOrderEntitlement(1, "1", 4));
		return orderEntitlements;
	}
}