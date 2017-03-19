package com.nxp.appxplorer.dao;

import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.PasswordType;
import com.nxp.appxplorer.query.PasswordTypeQuery;
import com.nxp.appxplorer.repository.PasswordTypeRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Password Type Details.
 * 
 * @author nxa30710
 *
 */
@Singleton
public class PasswordTypeDao extends AbstractDao<PasswordType> implements PasswordTypeRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public PasswordTypeDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get Password type for a given Id.
     * 
     * @see com.nxp.appxplorer.repository.PasswordTypeRepository#getById(short)
     */
    @Override
    public Optional<PasswordType> getById(short id) {
	PasswordTypeQuery p = domainQueryFactory.newPasswordTypeQuery();
	return p.getSingleResult();
    }

}
