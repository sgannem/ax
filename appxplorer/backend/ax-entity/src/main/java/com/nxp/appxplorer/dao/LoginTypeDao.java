package com.nxp.appxplorer.dao;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.LoginType;
import com.nxp.appxplorer.query.LoginTypeQuery;
import com.nxp.appxplorer.repository.LoginTypeRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Login Type details.
 * 
 * @author nxa30710
 *
 */
@Singleton
public class LoginTypeDao extends AbstractDao<LoginType> implements LoginTypeRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public LoginTypeDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get all the Login Types from the Data base.
     * 
     * @see com.nxp.appxplorer.repository.LoginTypeRepository#getAllLoginTypes()
     */
    @Override
    public List<LoginType> getAllLoginTypes() {
	LoginTypeQuery ltq = domainQueryFactory.newLoginTypeQuery();
	return ltq.getResultList();
    }

}
