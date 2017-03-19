package com.nxp.appxplorer.dao;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.Application;
import com.nxp.appxplorer.query.ApplicationQuery;
import com.nxp.appxplorer.repository.ApplicationRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Application Issuer Details.
 * 
 */
@Singleton
public class ApplicationDao extends AbstractDao<Application> implements ApplicationRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public ApplicationDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get all the Applications for a given application
     * Provider Id from the Data Base.
     * 
     * @see com.nxp.appxplorer.repository.ApplicationRepository#
     * getApplicationsByApplicationId(long)
     */
    @Override
    public List<Application> getApplicationsByApplicationId(long applicationProviderId) {
	ApplicationQuery aq = domainQueryFactory.newApplicationQuery();
	aq.withIds(applicationProviderId);
	return aq.getResultList();
    }

    /*
     * This method is used to get all the drafted Applications for a given
     * application Provider Id from the Data Base.
     * 
     * @see com.nxp.appxplorer.repository.ApplicationRepository#
     * getDraftedApplicationsByApplicationId(long)
     */
    @Override
    public List<Application> getDraftedApplicationsByApplicationId(long applicationProviderId) {
	ApplicationQuery aq = domainQueryFactory.newApplicationQuery();
	aq.withDraftIds(applicationProviderId);
	return aq.getResultList();
    }

    /*
     * To find Application Details by providing Id.
     * 
     * @see com.nxp.appxplorer.repository.ApplicationRepository#findById(long)
     */
    @Override
    public Optional<Application> findById(long id) {
	ApplicationQuery aq = domainQueryFactory.newApplicationQuery();
	aq.withId(id);
	return aq.getSingleResult();
    }

}
