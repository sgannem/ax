package com.nxp.appxplorer.dao;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.CIAddress;
import com.nxp.appxplorer.query.CIAddressQuery;
import com.nxp.appxplorer.repository.CIAddressRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Card Issuer Address Details.
 * 
 */
@Singleton
public class CIAddressDao extends AbstractDao<CIAddress> implements CIAddressRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public CIAddressDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get all the CIAddress details from the Data Base.
     * 
     * @see com.nxp.appxplorer.repository.CIAddressRepository#getCIAddresses()
     */
    @Override
    public List<CIAddress> getCIAddresses(long cardIssuerId) {
	CIAddressQuery ciaq = domainQueryFactory.newCIAddressQuery();
	ciaq.withCardIssuerId(cardIssuerId);
	return ciaq.getResultList();
    }

    /*
     * This method validates the CIAddress against the Data base.
     * 
     * @see com.nxp.appxplorer.repository.CIAddressRepository#
     * isCIAddressExistsByName(java.lang.String)
     */
    @Override
    public boolean isCIAddressExistsByName(String name) {
	boolean status = true;
	CIAddressQuery ciaq = domainQueryFactory.newCIAddressQuery();
	ciaq.withIds(name);
	Optional<CIAddress> cia = ciaq.getSingleResult();
	if (!cia.isPresent()) {
	    status = false;
	}
	return status;
    }

    /*
     * This method is used to get the CIAddress from the Data base by id.
     * 
     * @see com.nxp.appxplorer.repository.CIAddressRepository#findById()
     */
    @Override
    public Optional<CIAddress> findById(long id) {
	CIAddressQuery ciaq = domainQueryFactory.newCIAddressQuery();
	ciaq.withId(id);
	return ciaq.getSingleResult();
    }

}
