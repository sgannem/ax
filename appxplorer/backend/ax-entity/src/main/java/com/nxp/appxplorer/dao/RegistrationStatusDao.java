package com.nxp.appxplorer.dao;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.RegistrationStatus;
import com.nxp.appxplorer.query.RegistrationStatusQuery;
import com.nxp.appxplorer.repository.RegistrationStatusRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Registration Status Details.
 * 
 */
@Singleton
public class RegistrationStatusDao extends AbstractDao<RegistrationStatus> implements RegistrationStatusRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public RegistrationStatusDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get all the Registration Statues from the Data
     * base.
     * 
     * @see com.nxp.appxplorer.repository.RegistrationStatusRepository#
     * getAllRegistrationStatuses()
     */
    @Override
    public List<RegistrationStatus> getAllRegistrationStatuses() {
	RegistrationStatusQuery rsq = domainQueryFactory.newRegistrationStatusQuery();
	return rsq.getResultList();
    }

    /*
     * This method is used to get the Registration Status from the Data base by
     * id.
     * 
     * @see
     * com.nxp.appxplorer.repository.RegistrationStatusRepository#findById()
     */
    @Override
    public Optional<RegistrationStatus> findById(long id) {
	RegistrationStatusQuery rsq = domainQueryFactory.newRegistrationStatusQuery();
	rsq.withId(id);
	return rsq.getSingleResult();
    }
}
