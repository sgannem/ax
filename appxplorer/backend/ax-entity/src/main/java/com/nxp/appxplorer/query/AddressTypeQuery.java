package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.AddressType;

/**
 * This class helps to create a hibernate hql to fetch Address Type Details.
 */
public class AddressTypeQuery extends AbstractDomainQuery<AddressType> implements DomainQuery<AddressType> {

    /**
     * This constructors is being called by the DAOs by providing the entity
     * manager.
     * 
     * @param entityManager
     */
    private AddressTypeQuery(final EntityManager entityManager) {
	super(AddressType.class, entityManager, "at");
    }

    /**
     * This method helps to get runtime instance of the AddressType Details
     * Query.
     * 
     * @param entityManager
     * @return
     */
    public static AddressTypeQuery newInstance(final EntityManager entityManager) {
	return new AddressTypeQuery(entityManager);
    }

    /**
     * This method helps to write some conditions on the AddressType Details
     * Query.
     * 
     * @param id
     * @return
     */
    public AddressTypeQuery withId(final int id) {
	addCondition("at.id=:id", "id", id);
	return this;
    }

}
