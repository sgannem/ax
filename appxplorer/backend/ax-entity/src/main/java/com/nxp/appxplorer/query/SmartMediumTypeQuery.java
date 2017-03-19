package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.SmartMediumType;

/**
 * This class helps to create a hibernate hql to fetch Smart Medium Type
 * details.
 * 
 */
public class SmartMediumTypeQuery extends AbstractDomainQuery<SmartMediumType> implements DomainQuery<SmartMediumType> {
	/**
	 * This constructors is being called by the DAOs by providing the entity
	 * manager.
	 * 
	 * @param entityManager
	 */
	private SmartMediumTypeQuery(final EntityManager entityManager) {
		super(SmartMediumType.class, entityManager, "smt");
	}

	/**
	 * This method helps to get runtime instance of the SmallMediumType Query.
	 * 
	 * @param entityManager
	 * @return
	 */
	public static SmartMediumTypeQuery newInstance(final EntityManager entityManager) {
		return new SmartMediumTypeQuery(entityManager);
	}

	/**
	 * This method helps to write some conditions on the LoginType Details
	 * Query.
	 * 
	 * @param id
	 * @return
	 */
	public SmartMediumTypeQuery withId(final long id) {
		addCondition("smt.id=:id", "id", id);
		addCondition("smt.isActive=:isActive", "isActive", "Y");
		return this;
	}
}
