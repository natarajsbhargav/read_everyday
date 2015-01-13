package com.scholastic.slz.core.producers;

/**
 * @author Madan D H
 *
 */
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.scholastic.slz.core.qualifiers.Primary;

/**
 * This class uses CDI to alias Java EE resources, such as the persistence
 * context, to CDI beans
 */
/**
 * @author snehalata.raulo
 * 
 */
@ApplicationScoped
public class EntityManagerProducer {
	// use @SuppressWarnings to tell IDE to ignore warnings about field not
	// being referenced directly

	/**
	 * EntityManager context
	 */
	@PersistenceContext(unitName = "slz_core", type = PersistenceContextType.EXTENDED)
	private transient EntityManager entityManager;

	/**
	 * @param injectionPoint
	 * @return
	 */
	@Produces
	public Logger produceLog(final InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass()
				.getName());
	}

	/**
	 * @return
	 */
	@Produces
	@RequestScoped
	public FacesContext produceFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * create EntityManager
	 * 
	 * @return
	 */
	@Produces
	@Primary
	protected EntityManager createEntityManager() {
		return this.entityManager;
	}

	/**
	 * close EntityManager
	 * 
	 * @param entityManager
	 */
	protected void closeEntityManager(
			@Disposes @Primary final EntityManager entityManager) {
		/*
		 * if (entityManager.isOpen()) { entityManager.close(); }
		 */
	}
}
