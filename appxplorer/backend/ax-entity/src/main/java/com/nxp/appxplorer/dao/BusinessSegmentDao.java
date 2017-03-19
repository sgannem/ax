package com.nxp.appxplorer.dao;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.BusinessSegment;
import com.nxp.appxplorer.query.BusinessSegmentQuery;
import com.nxp.appxplorer.repository.BusinessSegmentRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Business Segment Details.
 * 
 */
@Singleton
public class BusinessSegmentDao extends AbstractDao<BusinessSegment> implements BusinessSegmentRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public BusinessSegmentDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get all the Business Segment from the Data base.
     * 
     * @see com.nxp.appxplorer.repository.BusinessSegmentRepository#
     * getAllBusinessSegments()
     */
    @Override
    public List<BusinessSegment> getAllBusinessSegments() {
	BusinessSegmentQuery bsq = domainQueryFactory.newBusinessSegmentQuery();
	return bsq.getResultList();
    }

    /*
     * This method is used to get all the Business Segment from the Data base by
     * id.
     * 
     * @see com.nxp.appxplorer.repository.BusinessSegmentRepository#findById()
     */
    @Override
    public Optional<BusinessSegment> findById(long id) {
	BusinessSegmentQuery bsq = domainQueryFactory.newBusinessSegmentQuery();
	bsq.withId(id);
	return bsq.getSingleResult();
    }
}
