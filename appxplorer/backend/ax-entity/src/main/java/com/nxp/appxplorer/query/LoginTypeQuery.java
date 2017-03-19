package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.LoginType;

/**
 * This class helps to create a hibernate hql to fetch Login Type Details.
 * 
 * @author nxa30710
 *
 */
public class LoginTypeQuery extends AbstractDomainQuery<LoginType> implements DomainQuery<LoginType> {

    /**
     * This constructors is being called by the DAOs by providing the entity
     * manager.
     * 
     * @param entityManager
     */
    private LoginTypeQuery(final EntityManager entityManager) {
	super(LoginType.class, entityManager, "lt");
    }

    /**
     * This method helps to get runtime instance of the LoginType Details Query.
     * 
     * @param entityManager
     * @return
     */
    public static LoginTypeQuery newInstance(final EntityManager entityManager) {
	return new LoginTypeQuery(entityManager);
    }

    /**
     * This method helps to write some conditions on the LoginType Details
     * Query.
     * 
     * @param id
     * @return
     */
    public LoginTypeQuery withId(final int id) {
	addCondition("lt.id=:id", "id", id);
	return this;
    }

}
