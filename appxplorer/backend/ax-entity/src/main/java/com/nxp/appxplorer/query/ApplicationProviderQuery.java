package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.ApplicationProvider;

/**
 * This class helps to create a hibernate hql to fetch Application Provider
 * details.
 * 
 */
public class ApplicationProviderQuery extends AbstractDomainQuery<ApplicationProvider>
	implements DomainQuery<ApplicationProvider> {
    /**
     * This constructors is being called by the DAOs by providing the entity
     * manager.
     * 
     * @param entityManager
     */
    private ApplicationProviderQuery(final EntityManager entityManager) {
	super(ApplicationProvider.class, entityManager, "ap");
    }

    /**
     * This method helps to get runtime instance of the CardIssuer Query.
     * 
     * @param entityManager
     * @return
     */
    public static ApplicationProviderQuery newInstance(final EntityManager entityManager) {
	return new ApplicationProviderQuery(entityManager);
    }

    /**
     * This method helps to write some conditions on the CardIssuer Query.
     * 
     * @param firstName
     * @param lastName
     * @param email
     * @return
     */
    public ApplicationProviderQuery withIds(final String firstName, final String lastName, final String email) {
	addCondition("ap.firstName=:firstName", "firstName", firstName);
	addCondition("ap.lastName=:lastName", "lastName", lastName);
	addCondition("ap.email=:email", "email", email);
	addCondition("ap.isActive=:isActive", "isActive", "Y");
	return this;
    }

    /**
     * This method helps to write some conditions on the CardIssuer Query.
     * 
     * @param companyName
     * @return
     */
    public ApplicationProviderQuery withIds(final String companyName) {
	addCondition("ap.companyName=:companyName", "companyName", companyName);
	addCondition("ap.isActive=:isActive", "isActive", "Y");
	return this;
    }

    /**
     * This method helps to write some conditions on the CardIssuer Details
     * Query.
     * 
     * @param id
     * @return
     */
    public ApplicationProviderQuery withId(final long id) {
	addCondition("ap.id=:id", "id", id);
	addCondition("ap.isActive=:isActive", "isActive", "Y");
	return this;
    }

    /**
     * This method helps to write some conditions on the CardIssuer Details
     * Query.
     * 
     * @param id
     * @return
     */
    public ApplicationProviderQuery withId(final String id) {
	addCondition("ap.id=:id", "id", Long.parseLong(id));
	addCondition("ap.isActive=:isActive", "isActive", "Y");
	return this;
    }
}
