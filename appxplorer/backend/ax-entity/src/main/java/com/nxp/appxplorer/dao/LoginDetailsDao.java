package com.nxp.appxplorer.dao;

import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.LoginDetails;
import com.nxp.appxplorer.query.LoginDetailsQuery;
import com.nxp.appxplorer.repository.LoginDetailsRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Login Details.
 * 
 * @author nxa30710
 *
 */
@Singleton
public class LoginDetailsDao extends AbstractDao<LoginDetails> implements LoginDetailsRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public LoginDetailsDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get login details from the Data Base.
     * 
     * @see
     * com.nxp.appxplorer.repository.LoginDetailsRepository#getLoginDetails(int,
     * int, java.lang.String)
     */
    @Override
    public Optional<LoginDetails> getLoginDetails(short accountType, short loginType, String userName) {
	return domainQueryFactory.newLoginDetailsQuery().withIds(accountType, loginType, userName).getSingleResult();
    }

    /*
     * This method validates the login userName against the Data base.
     * 
     * @see
     * com.nxp.appxplorer.repository.LoginDetailsRepository#isLoginExists(int,
     * int, java.lang.String)
     */
    @Override
    public boolean isLoginExists(short accountType, short loginType, String userName) {
	boolean status = true;
	LoginDetailsQuery q = domainQueryFactory.newLoginDetailsQuery();
	q.withIds(accountType, loginType, userName);
	Optional<LoginDetails> l = q.getSingleResult();
	if (!l.isPresent()) {
	    status = false;
	}
	return status;
    }

    /*
     * To check whether external userId already exists in our DB.
     * 
     * @see com.nxp.appxplorer.repository.LoginDetailsRepository#
     * isExternalUserIdExists(java.lang.String)
     */
    @Override
    public boolean isExternalUserIdExists(String externalUserId) {
	boolean status = false;
	LoginDetailsQuery q = domainQueryFactory.newLoginDetailsQuery();
	q.withExternalUserId(externalUserId);
	Optional<LoginDetails> l = q.getSingleResult();
	if (l.isPresent()) {
	    status = true;
	}
	return status;
    }

    /*
     * This method is used to check whether the given bearer token exists in the
     * data base or not.
     * 
     * @see
     * com.nxp.appxplorer.repository.LoginDetailsRepository#findByExternalUserId
     * (java.lang.String)
     */
    @Override
    public Optional<LoginDetails> findByExternalUserId(String externalUserId) {
	LoginDetailsQuery q = domainQueryFactory.newLoginDetailsQuery();
	q.withExternalUserId(externalUserId);
	return q.getSingleResult();
    }

}
