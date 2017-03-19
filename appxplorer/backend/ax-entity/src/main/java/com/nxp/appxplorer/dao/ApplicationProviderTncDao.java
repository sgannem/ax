package com.nxp.appxplorer.dao;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.ApplicationProviderTnc;
import com.nxp.appxplorer.query.ApplicationProviderTncQuery;
import com.nxp.appxplorer.repository.ApplicationProviderTncRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Application Provider Tnc Details.
 * 
 */
@Singleton
public class ApplicationProviderTncDao extends AbstractDao<ApplicationProviderTnc>
	implements ApplicationProviderTncRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public ApplicationProviderTncDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get all ApplicationProvider Tnc details from the
     * Data Base.
     * 
     * @see com.nxp.appxplorer.repository.ApplicationProviderTncRepository#
     * getApplicationProvidersTnc()
     */
    @Override
    public List<ApplicationProviderTnc> getApplicationProvidersTnc() {
	ApplicationProviderTncQuery aptncq = domainQueryFactory.newApplicationProviderTncQuery();
	return aptncq.getResultList();
    }

    /*
     * To find ApplicationProvider Tnc Details by providing Application Provider
     * Id.
     * 
     * @see
     * com.nxp.appxplorer.repository.ApplicationProviderTncRepository#findById(
     * long)
     */
    @Override
    public Optional<ApplicationProviderTnc> findById(long applicationProviderId) {
	ApplicationProviderTncQuery aptncq = domainQueryFactory.newApplicationProviderTncQuery();
	aptncq.withId(applicationProviderId);
	return aptncq.getSingleResult();
    }

}
