package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.RegistrationStatus;

/**
 * This class helps to create a hibernate hql to fetch Registration Status
 * details.
 * 
 */
public class RegistrationStatusQuery extends AbstractDomainQuery<RegistrationStatus>
		implements DomainQuery<RegistrationStatus> {

	/**
	 * This constructors is being called by the DAOs by providing the entity
	 * manager.
	 * 
	 * @param entityManager
	 */
	private RegistrationStatusQuery(final EntityManager entityManager) {
		super(RegistrationStatus.class, entityManager, "rs");
	}

	/**
	 * This method helps to get runtime instance of the RegistrationStatus
	 * Query.
	 * 
	 * @param entityManager
	 * @return
	 */
	public static RegistrationStatusQuery newInstance(final EntityManager entityManager) {
		return new RegistrationStatusQuery(entityManager);
	}

	/**
	 * This method helps to get Registration Status by ID
	 * 
	 * @param id
	 * @return
	 */
	public RegistrationStatusQuery withId(final long id) {
		addCondition("rs.id=:id", "id", id);
		addCondition("rs.isActive=:isActive", "isActive", "Y");
		return this;
	}

}
