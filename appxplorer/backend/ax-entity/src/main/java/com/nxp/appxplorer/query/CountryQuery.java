package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.Country;

/**
 * This class helps to create a hibernate hql to fetch Address Type Details.
 */
public class CountryQuery extends AbstractDomainQuery<Country> implements DomainQuery<Country> {

    /**
     * This constructors is being called by the DAOs by providing the entity
     * manager.
     * 
     * @param entityManager
     */
    private CountryQuery(final EntityManager entityManager) {
	super(Country.class, entityManager, "c");
    }

    /**
     * This method helps to get runtime instance of the Country Details Query.
     * 
     * @param entityManager
     * @return
     */
    public static CountryQuery newInstance(final EntityManager entityManager) {
	return new CountryQuery(entityManager);
    }

    /**
     * This method helps to write some conditions on the Country Details Query.
     * 
     * @param id
     * @return
     */
    public CountryQuery withId(final int id) {
	addCondition("c.id=:id", "id", id);
	return this;
    }

}
