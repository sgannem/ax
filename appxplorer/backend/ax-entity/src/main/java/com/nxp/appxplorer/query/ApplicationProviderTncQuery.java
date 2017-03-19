package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.ApplicationProviderTnc;

/**
 * This class helps to create a hibernate hql to fetch Application ProviderTnc
 * tnc details.
 * 
 */
public class ApplicationProviderTncQuery extends AbstractDomainQuery<ApplicationProviderTnc>
	implements DomainQuery<ApplicationProviderTnc> {
    /**
     * This constructors is being called by the DAOs by providing the entity
     * manager.
     * 
     * @param entityManager
     */
    private ApplicationProviderTncQuery(final EntityManager entityManager) {
	super(ApplicationProviderTnc.class, entityManager, "aptnc");
    }

    /**
     * This method helps to get runtime instance of the CardIssuerTnc Query.
     * 
     * @param entityManager
     * @return
     */
    public static ApplicationProviderTncQuery newInstance(final EntityManager entityManager) {
	return new ApplicationProviderTncQuery(entityManager);
    }

    /**
     * This method helps to write some conditions on the Application Provider
     * Tnc Query.
     * 
     * @param cardIssuerId
     * @return
     */
    public ApplicationProviderTncQuery withId(final long applicationProviderId) {
	addCondition("aptnc.applicationProvider.id=:applicationProviderId", "applicationProviderId",
		applicationProviderId);
	addCondition("aptnc.isActive=:isActive", "isActive", "Y");
	return this;
    }

}
