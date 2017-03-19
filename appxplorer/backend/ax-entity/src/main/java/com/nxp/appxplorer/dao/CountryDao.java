package com.nxp.appxplorer.dao;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.Country;
import com.nxp.appxplorer.query.CountryQuery;
import com.nxp.appxplorer.repository.CountryRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Country details.
 * 
 */
@Singleton
public class CountryDao extends AbstractDao<Country> implements CountryRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public CountryDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get all the Countries from the Data base.
     * 
     * @see com.nxp.appxplorer.repository.CountryRepository#getAllCountries()
     */
    @Override
    public List<Country> getAllCountries() {
	CountryQuery cq = domainQueryFactory.newCountryQuery();
	return cq.getResultList();
    }

    /*
     * This method is used to get the Country from the Data base by id.
     * 
     * @see com.nxp.appxplorer.repository.CountryRepository#findById()
     */
    @Override
    public Optional<Country> findById(short id) {
	CountryQuery cq = domainQueryFactory.newCountryQuery();
	cq.withId(id);
	return cq.getSingleResult();
    }
}
