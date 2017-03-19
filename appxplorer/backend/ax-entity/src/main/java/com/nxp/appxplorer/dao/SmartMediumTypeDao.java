package com.nxp.appxplorer.dao;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.SmartMediumType;
import com.nxp.appxplorer.query.SmartMediumTypeQuery;
import com.nxp.appxplorer.repository.SmartMediumTypeRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Smart Medium Type Details.
 * 
 */
@Singleton
public class SmartMediumTypeDao extends AbstractDao<SmartMediumType> implements SmartMediumTypeRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public SmartMediumTypeDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get all the Smart Medium Types from the Data base.
     * 
     * @see com.nxp.appxplorer.repository.SmartMediumTypeRepository#
     * getAllSmartMediumTypes()
     */
    @Override
    public List<SmartMediumType> getAllSmartMediumTypes() {
	SmartMediumTypeQuery smtq = domainQueryFactory.newSmartMediumTypeQuery();
	return smtq.getResultList();
    }

    /*
     * This method is used to get all the Smart Medium Type from the Data base
     * by id.
     * 
     * @see com.nxp.appxplorer.repository.SmartMediumTypeRepository#findById()
     */
    @Override
    public Optional<SmartMediumType> findById(long id) {
	SmartMediumTypeQuery smtq = domainQueryFactory.newSmartMediumTypeQuery();
	smtq.withId(id);
	return smtq.getSingleResult();
    }
}
