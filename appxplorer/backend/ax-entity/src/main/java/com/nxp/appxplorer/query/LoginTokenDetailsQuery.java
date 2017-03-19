package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.LoginTokenDetails;

/**
 * This class helps to create a hibernate hql to fetch Login Token Details.
 * 
 * @author nxa30710
 *
 */
public class LoginTokenDetailsQuery extends AbstractDomainQuery<LoginTokenDetails>
	implements DomainQuery<LoginTokenDetails> {

    /**
     * This constructors is being called by the DAOs by providing the entity
     * manager.
     * 
     * @param entityManager
     */
    private LoginTokenDetailsQuery(final EntityManager entityManager) {
	super(LoginTokenDetails.class, entityManager, "ltd");
    }

    /**
     * This method helps to get runtime instance of the LoginToken Details
     * Query.
     * 
     * @param entityManager
     * @return
     */
    public static LoginTokenDetailsQuery newInstance(final EntityManager entityManager) {
	return new LoginTokenDetailsQuery(entityManager);
    }

    /**
     * This method helps to write some conditions on the LoginToken Details
     * Query.
     * 
     * @param bearerToken
     * @return
     */
    public LoginTokenDetailsQuery withIds(String bearerToken) {
	addCondition("ltd.bearerToken=:bearerToken", "bearerToken", bearerToken);
	addCondition("ltd.isActive=:isActive", "isActive", "Y");
	return this;
    }

    /**
     * This method is used to write condition to fetch LoginToken Details by
     * providing the bearerToken.
     * 
     * @param bearerToken
     * @return
     */
    public LoginTokenDetailsQuery withToken(String bearerToken) {
	addCondition("ltd.bearerToken=:bearerToken", "bearerToken", bearerToken);
	addCondition("ltd.isActive=:isActive", "isActive", "Y");
	return this;
    }

}
