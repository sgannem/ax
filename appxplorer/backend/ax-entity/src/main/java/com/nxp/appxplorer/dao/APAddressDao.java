package com.nxp.appxplorer.dao;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.APAddress;
import com.nxp.appxplorer.query.APAddressQuery;
import com.nxp.appxplorer.repository.APAddressRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Card Issuer Address Details.
 * 
 */
@Singleton
public class APAddressDao extends AbstractDao<APAddress> implements APAddressRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public APAddressDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get all the APAddress details from the Data Base.
     * 
     * @see com.nxp.appxplorer.repository.APAddressRepository#getAPAddresses()
     */
    @Override
    public List<APAddress> getAPAddresses(long applicationProviderId) {
	APAddressQuery apaq = domainQueryFactory.newAPAddressQuery();
	apaq.withApplicationProviderId(applicationProviderId);
	return apaq.getResultList();
    }

    /*
     * This method validates the APAddress against the Data base.
     * 
     * @see com.nxp.appxplorer.repository.APAddressRepository#
     * isAPAddressExistsByName(java.lang.String)
     */
    @Override
    public boolean isAPAddressExistsByName(String name) {
	boolean status = true;
	APAddressQuery apaq = domainQueryFactory.newAPAddressQuery();
	apaq.withIds(name);
	Optional<APAddress> apa = apaq.getSingleResult();
	if (!apa.isPresent()) {
	    status = false;
	}
	return status;
    }

    /*
     * This method is used to get the APAddress from the Data base by id.
     * 
     * @see com.nxp.appxplorer.repository.APAddressRepository#findById()
     */
    @Override
    public Optional<APAddress> findById(long id) {
	APAddressQuery apaq = domainQueryFactory.newAPAddressQuery();
	apaq.withId(id);
	return apaq.getSingleResult();
    }

}
