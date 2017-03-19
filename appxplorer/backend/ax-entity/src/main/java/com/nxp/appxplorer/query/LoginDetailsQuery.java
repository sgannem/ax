package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.LoginDetails;

/**
 * This class helps to create a hibernate hql to fetch Login details.
 * 
 * @author nxa30710
 *
 */
public class LoginDetailsQuery extends AbstractDomainQuery<LoginDetails> implements DomainQuery<LoginDetails> {

    /**
     * This constructors is being called by the DAOs by providing the entity
     * manager.
     * 
     * @param entityManager
     */
    private LoginDetailsQuery(final EntityManager entityManager) {
	super(LoginDetails.class, entityManager, "ld");
    }

    /**
     * This method helps to get runtime instance of the LoginDetails Query.
     * 
     * @param entityManager
     * @return
     */
    public static LoginDetailsQuery newInstance(final EntityManager entityManager) {
	return new LoginDetailsQuery(entityManager);
    }

    /**
     * This method helps to write some conditions on the LoginDetails Query.
     * 
     * @param accountTypeId
     * @param loginTypeId
     * @param userName
     * @return
     */
    public LoginDetailsQuery withIds(final short accountTypeId, final short loginTypeId, final String userName) {
	addCondition("ld.accountTypeId=:accountTypeId", "accountTypeId", accountTypeId);
	addCondition("ld.loginTypeId=:loginTypeId", "loginTypeId", loginTypeId);
	addCondition("ld.userName=:userName", "userName", userName);
	addCondition("ld.isActive=:isActive", "isActive", "Y");
	return this;
    }

    /**
     * This method helps to write some conditions on the LoginType Details
     * Query.
     * 
     * @param id
     * @return
     */
    public LoginDetailsQuery withId(final long id) {
	addCondition("ld.id=:id", "id", id);
	addCondition("ld.isActive=:isActive", "isActive", "Y");
	return this;
    }

    /**
     * This method helps to write some conditions on the LoginType Details
     * Query.
     * 
     * @param id
     * @return
     */
    public LoginDetailsQuery withExternalUserId(final String externalUserId) {
	addCondition("ld.externalUserId=:externalUserId", "externalUserId", externalUserId);
	addCondition("ld.isActive=:isActive", "isActive", "Y");
	return this;
    }

}
