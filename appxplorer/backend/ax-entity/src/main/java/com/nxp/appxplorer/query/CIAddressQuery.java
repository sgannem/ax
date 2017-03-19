package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.CIAddress;

public class CIAddressQuery extends AbstractDomainQuery<CIAddress> implements DomainQuery<CIAddress> {

    /**
     * This constructors is being called by the DAOs by providing the entity
     * manager.
     * 
     * @param entityManager
     */
    private CIAddressQuery(final EntityManager entityManager) {
	super(CIAddress.class, entityManager, "cia");
    }

    /**
     * This method helps to get runtime instance of the CIAddress Query.
     * 
     * @param entityManager
     * @return
     */
    public static CIAddressQuery newInstance(final EntityManager entityManager) {
	return new CIAddressQuery(entityManager);
    }

    /**
     * This method helps to write some conditions on the CIAddress Query.
     * 
     * @param name
     * @return
     */
    public CIAddressQuery withIds(final String name) {
	addCondition("cia.name=:name", "name", name);
	addCondition("cia.isActive=:isActive", "isActive", "Y");
	return this;
    }

    /**
     * This method helps to write some conditions on the CIAddress Details
     * Query.
     * 
     * @param cardIssuerId
     * @return
     */
    public CIAddressQuery withCardIssuerId(final long cardIssuerId) {
	addCondition("cia.cardIssuer.id=:cardIssuerId", "cardIssuerId", cardIssuerId);
	addCondition("cia.isActive=:isActive", "isActive", "Y");
	return this;
    }

    /**
     * This method helps to write some conditions on the CIAddress Details
     * Query.
     * 
     * @param cardIssuerId
     * @return
     */
    public CIAddressQuery withId(final long id) {
	addCondition("cia.id=:id", "id", id);
	addCondition("cia.isActive=:isActive", "isActive", "Y");
	return this;
    }

}
