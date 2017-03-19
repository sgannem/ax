package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.BusinessSegment;

/**
 * This class helps to create a hibernate hql to fetch Login details.
 * 
 */
public class BusinessSegmentQuery extends AbstractDomainQuery<BusinessSegment> implements DomainQuery<BusinessSegment> {
	/**
	 * This constructors is being called by the DAOs by providing the entity
	 * manager.
	 * 
	 * @param entityManager
	 */
	private BusinessSegmentQuery(final EntityManager entityManager) {
		super(BusinessSegment.class, entityManager, "bs");
	}

	/**
	 * This method helps to get runtime instance of the BusinessSegment Query.
	 * 
	 * @param entityManager
	 * @return
	 */
	public static BusinessSegmentQuery newInstance(final EntityManager entityManager) {
		return new BusinessSegmentQuery(entityManager);
	}

	/**
	 * This method helps to write some conditions on the LoginType Details
	 * Query.
	 * 
	 * @param id
	 * @return
	 */
	public BusinessSegmentQuery withId(final long id) {
		addCondition("bs.id=:id", "id", id);
		addCondition("bs.isActive=:isActive", "isActive", "Y");
		return this;
	}
}
