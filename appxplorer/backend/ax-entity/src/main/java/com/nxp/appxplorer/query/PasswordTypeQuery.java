package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.PasswordType;

/**
 * This class helps to create a hibernate hql to fetch Password Type Details.
 * 
 * @author nxa30710
 *
 */
public class PasswordTypeQuery extends AbstractDomainQuery<PasswordType> implements DomainQuery<PasswordType> {

    /**
     * This constructors is being called by the DAOs by providing the entity
     * manager.
     * 
     * @param entityManager
     */
    private PasswordTypeQuery(final EntityManager entityManager) {
	super(PasswordType.class, entityManager, "p");
    }

    /**
     * This method helps to get runtime instance of the PasswordType Details
     * Query.
     * 
     * @param entityManager
     * @return
     */
    public static PasswordTypeQuery newInstance(final EntityManager entityManager) {
	return new PasswordTypeQuery(entityManager);
    }

    /**
     * This method helps to write some conditions on the PasswordType Details
     * Query.
     * 
     * @param id
     * @return
     */
    public PasswordTypeQuery withId(final short id) {
	addCondition("p.id=:id", "id", id);
	return this;
    }

}
