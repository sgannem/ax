package com.nxp.appxplorer.dao;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.TechnologyUsed;
import com.nxp.appxplorer.query.TechnologyUsedQuery;
import com.nxp.appxplorer.repository.TechnologyUsedRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Technology Used Details.
 * 
 */
@Singleton
public class TechnologyUsedDao extends AbstractDao<TechnologyUsed> implements TechnologyUsedRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public TechnologyUsedDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get all the Technology Used from the Data base.
     * 
     * @see com.nxp.appxplorer.repository.TechnologyUsedRepository#
     * getAllTechnologyUsedes()
     */
    @Override
    public List<TechnologyUsed> getAllTechnologyUsed() {
	TechnologyUsedQuery tuq = domainQueryFactory.newTechnologyUsedQuery();
	return tuq.getResultList();
    }

    /*
     * This method is used to get all the Technology Used from the Data base by
     * id.
     * 
     * @see com.nxp.appxplorer.repository.TechnologyUsedRepository#findById()
     */
    @Override
    public Optional<TechnologyUsed> findById(long id) {
	TechnologyUsedQuery tuq = domainQueryFactory.newTechnologyUsedQuery();
	tuq.withId(id);
	return tuq.getSingleResult();
    }
}
