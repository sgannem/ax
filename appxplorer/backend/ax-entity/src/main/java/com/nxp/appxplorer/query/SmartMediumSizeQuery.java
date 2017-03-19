package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.SmartMediumSize;

/**
 * This class helps to create a hibernate hql to fetch Smart Medium Size
 * details.
 * 
 */
public class SmartMediumSizeQuery extends AbstractDomainQuery<SmartMediumSize> implements DomainQuery<SmartMediumSize> {
	/**
	 * This constructors is being called by the DAOs by providing the entity
	 * manager.
	 * 
	 * @param entityManager
	 */
	private SmartMediumSizeQuery(final EntityManager entityManager) {
		super(SmartMediumSize.class, entityManager, "sms");
	}

	/**
	 * This method helps to get runtime instance of the SmartMediumSize Query.
	 * 
	 * @param entityManager
	 * @return
	 */
	public static SmartMediumSizeQuery newInstance(final EntityManager entityManager) {
		return new SmartMediumSizeQuery(entityManager);
	}

	/**
	 * This method helps to write some conditions on the SmartMediumSize Details
	 * Query.
	 * 
	 * @param id
	 * @return
	 */
	public SmartMediumSizeQuery withId(final long id) {
		addCondition("sms.id=:id", "id", id);
		addCondition("sms.isActive=:isActive", "isActive", "Y");
		return this;
	}
}
