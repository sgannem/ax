package com.nxp.appxplorer.dao;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.ApplicationProvider;
import com.nxp.appxplorer.query.ApplicationProviderQuery;
import com.nxp.appxplorer.repository.ApplicationProviderRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Application Provider Details.
 * 
 * @author nxa30710
 */

@Singleton
public class ApplicationProviderDao extends AbstractDao<ApplicationProvider> implements ApplicationProviderRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public ApplicationProviderDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get all application providers from the Data Base.
     * 
     * @see com.nxp.appxplorer.repository.ApplicationProviderRepository#
     * getApplicationProviders()
     */
    @Override
    public List<ApplicationProvider> getApplicationProviders() {
	ApplicationProviderQuery apq = domainQueryFactory.newApplicationProviderQuery();
	return apq.getResultList();
    }

    /*
     * To find Application Provider by providing Id(Long).
     * 
     * @see
     * com.nxp.appxplorer.repository.ApplicationProviderRepository#findById(
     * long)
     */
    @Override
    public Optional<ApplicationProvider> findById(long id) {
	ApplicationProviderQuery apq = domainQueryFactory.newApplicationProviderQuery();
	apq.withId(id);
	return apq.getSingleResult();
    }

    /*
     * To find Application Provider by providing Id(String).
     * 
     * @see
     * com.nxp.appxplorer.repository.ApplicationProviderRepository#findById(java
     * .lang.String)
     */
    @Override
    public Optional<ApplicationProvider> findById(String id) {
	ApplicationProviderQuery apq = domainQueryFactory.newApplicationProviderQuery();
	apq.withId(id);
	return apq.getSingleResult();
    }

}
