package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.Application;

/**
 * This class helps to create a hibernate hql to fetch Card details.
 * 
 */

public class ApplicationQuery extends AbstractDomainQuery<Application> implements DomainQuery<Application> {

    /**
     * This constructors is being called by the DAOs by providing the entity
     * manager.
     * 
     * @param entityManager
     */
    private ApplicationQuery(final EntityManager entityManager) {
	super(Application.class, entityManager, "a");
    }

    /**
     * This method helps to get runtime instance of the Card Query.
     * 
     * @param entityManager
     * @return
     */
    public static ApplicationQuery newInstance(final EntityManager entityManager) {
	return new ApplicationQuery(entityManager);
    }

    /**
     * This method helps to write some conditions on the Application Query.
     * 
     * @param applicationProviderId
     * @return
     */
    public ApplicationQuery withIds(final long applicationProviderId) {
	addCondition("a.applicationProvider.Id=:applicationProviderId", "applicationProviderId", applicationProviderId);
	addCondition("a.isActive=:isActive", "isActive", "Y");
	return this;
    }

    /**
     * This method helps to write some conditions on the Application Query.
     * 
     * @param applicationProviderId
     * @return
     */
    public ApplicationQuery withDraftIds(final long applicationProviderId) {
	addCondition("a.applicationProvider.Id=:applicationProviderId", "applicationProviderId", applicationProviderId);
	addCondition("a.applicationProvider.Id=:applicationProviderId", "applicationProviderId", applicationProviderId);
	addCondition("a.isDraft=:isDraft", "isDraft", "Y");
	addCondition("a.isActive=:isActive", "isActive", "Y");
	return this;
    }

    /**
     * This method helps to write some conditions on the Card Details Query.
     * 
     * @param id
     * @return
     */
    public ApplicationQuery withId(final long id) {
	addCondition("a.id=:id", "id", id);
	addCondition("a.isActive=:isActive", "isActive", "Y");
	return this;
    }
}
