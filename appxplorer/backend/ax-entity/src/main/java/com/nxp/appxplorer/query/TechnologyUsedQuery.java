package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.TechnologyUsed;

/**
 * This class helps to create a hibernate hql to fetch Technology Used details.
 * 
 */
public class TechnologyUsedQuery extends AbstractDomainQuery<TechnologyUsed> implements DomainQuery<TechnologyUsed> {
	/**
	 * This constructors is being called by the DAOs by providing the entity
	 * manager.
	 * 
	 * @param entityManager
	 */
	private TechnologyUsedQuery(final EntityManager entityManager) {
		super(TechnologyUsed.class, entityManager, "tu");
	}

	/**
	 * This method helps to get runtime instance of the TechnologyUsed Query.
	 * 
	 * @param entityManager
	 * @return
	 */
	public static TechnologyUsedQuery newInstance(final EntityManager entityManager) {
		return new TechnologyUsedQuery(entityManager);
	}

	/**
	 * This method helps to write some conditions on the TechnologyUsed Details
	 * Query.
	 * 
	 * @param id
	 * @return
	 */
	public TechnologyUsedQuery withId(final long id) {
		addCondition("tu.id=:id", "id", id);
		addCondition("tu.isActive=:isActive", "isActive", "Y");
		return this;
	}
}
