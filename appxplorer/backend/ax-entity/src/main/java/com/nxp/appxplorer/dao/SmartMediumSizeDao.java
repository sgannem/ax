package com.nxp.appxplorer.dao;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.SmartMediumSize;
import com.nxp.appxplorer.query.SmartMediumSizeQuery;
import com.nxp.appxplorer.repository.SmartMediumSizeRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Smart Medium Size Details.
 * 
 */
@Singleton
public class SmartMediumSizeDao extends AbstractDao<SmartMediumSize> implements SmartMediumSizeRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public SmartMediumSizeDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get all the SmartMediumSizes from the Data base.
     * 
     * @see com.nxp.appxplorer.repository.SmartMediumSizeRepository#
     * getAllSmartMediumSizes()
     */
    @Override
    public List<SmartMediumSize> getAllSmartMediumSizes() {
	SmartMediumSizeQuery smsq = domainQueryFactory.newSmartMediumSizeQuery();
	return smsq.getResultList();
    }

    /*
     * This method is used to get all the Smart Medium Size from the Data base
     * by id.
     * 
     * @see
     * com.nxp.appxplorer.repository.RegistrationStatusRepository#findById()
     */
    @Override
    public Optional<SmartMediumSize> findById(long id) {
	SmartMediumSizeQuery smsq = domainQueryFactory.newSmartMediumSizeQuery();
	smsq.withId(id);
	return smsq.getSingleResult();
    }

}
