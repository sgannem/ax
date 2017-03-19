package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.APAddress;

public class APAddressQuery extends AbstractDomainQuery<APAddress> implements DomainQuery<APAddress> {

    /**
     * This constructors is being called by the DAOs by providing the entity
     * manager.
     * 
     * @param entityManager
     */
    private APAddressQuery(final EntityManager entityManager) {
	super(APAddress.class, entityManager, "apa");
    }

    /**
     * This method helps to get runtime instance of the APAddress Query.
     * 
     * @param entityManager
     * @return
     */
    public static APAddressQuery newInstance(final EntityManager entityManager) {
	return new APAddressQuery(entityManager);
    }

    /**
     * This method helps to write some conditions on the APAddress Query.
     * 
     * @param name
     * @return
     */
    public APAddressQuery withIds(final String name) {
	addCondition("apa.name=:name", "name", name);
	addCondition("apa.isActive=:isActive", "isActive", "Y");
	return this;
    }

    /**
     * This method helps to write some conditions on the APAddress Details
     * Query.
     * 
     * @param id
     * @return
     */
    public APAddressQuery withId(final long id) {
	addCondition("apa.id=:id", "id", id);
	addCondition("apa.isActive=:isActive", "isActive", "Y");
	return this;
    }

    /**
     * This method helps to write some conditions on the APAddress Details
     * Query.
     * 
     * @param id
     * @return
     */
    public APAddressQuery withApplicationProviderId(final long applicationProviderId) {
	addCondition("apa.applicationProvider.id=:applicationProviderId", "applicationProviderId",
		applicationProviderId);
	addCondition("apa.isActive=:isActive", "isActive", "Y");
	return this;
    }

}
