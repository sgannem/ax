package com.nxp.appxplorer.dao;

import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.LoginDetails;
import com.nxp.appxplorer.entity.LoginTokenDetails;
import com.nxp.appxplorer.query.LoginDetailsQuery;
import com.nxp.appxplorer.query.LoginTokenDetailsQuery;
import com.nxp.appxplorer.repository.LoginTokenDetailsRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Login Token Details.
 * 
 * @author nxa30710
 *
 */
@Singleton
public class LoginTokenDetailsDao extends AbstractDao<LoginTokenDetails> implements LoginTokenDetailsRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public LoginTokenDetailsDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.nxp.appxplorer.repository.LoginTokenDetailsRepository#
     * getLoginTokenDetails(java.lang.String)
     */
    @Override
    public Optional<LoginTokenDetails> getLoginTokenDetails(String bearerToken) {
	LoginTokenDetailsQuery ltdq = domainQueryFactory.newLoginTokenDetailsQuery();
	ltdq.withIds(bearerToken);
	return ltdq.getSingleResult();
    }

    /*
     * This method is used to check whether the given bearer token exists in the
     * data base or not.
     * 
     * @see com.nxp.appxplorer.repository.LoginTokenDetailsRepository#
     * isBearerTokeAlreadyExists(java.lang.String)
     */
    @Override
    public boolean isBearerTokeAlreadyExists(String bearerToken) {
	LoginTokenDetailsQuery ltdq = domainQueryFactory.newLoginTokenDetailsQuery();
	ltdq.withToken(bearerToken);
	Optional<LoginTokenDetails> ltd = ltdq.getSingleResult();
	return ltd.isPresent() ? true : false;
    }

    /*
     * This method is used to get Login Details for a given bearer token.
     * 
     * @see
     * com.nxp.appxplorer.repository.LoginTokenDetailsRepository#getLoginDetails
     * (java.lang.String)
     */
    @Override
    public Optional<LoginDetails> getLoginDetails(String bearerToken) {
	Optional<LoginDetails> ld = null;
	LoginTokenDetailsQuery ltdq = domainQueryFactory.newLoginTokenDetailsQuery();
	ltdq.withToken(bearerToken);
	Optional<LoginTokenDetails> ltd = ltdq.getSingleResult();
	if (ltd.isPresent()) {
	    LoginTokenDetails ltdTemp = ltd.get();
	    LoginDetailsQuery ldq = domainQueryFactory.newLoginDetailsQuery();
	    ld = ldq.findById(ltdTemp.getId());
	}
	return ld;
    }

}
