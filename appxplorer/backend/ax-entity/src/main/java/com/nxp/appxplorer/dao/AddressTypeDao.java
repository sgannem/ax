package com.nxp.appxplorer.dao;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.AddressType;
import com.nxp.appxplorer.query.AddressTypeQuery;
import com.nxp.appxplorer.repository.AddressTypeRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Address Type details.
 * 
 */
@Singleton
public class AddressTypeDao extends AbstractDao<AddressType> implements AddressTypeRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public AddressTypeDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get all the Address Types from the Data base.
     * 
     * @see
     * com.nxp.appxplorer.repository.AddressTypeRepository#getAllAddressTypes()
     */
    @Override
    public List<AddressType> getAllAddressTypes() {
	AddressTypeQuery ltq = domainQueryFactory.newAddressTypeQuery();
	return ltq.getResultList();
    }

    /*
     * This method is used to get the Address Type from the Data base by id.
     * 
     * @see com.nxp.appxplorer.repository.AddressTypeRepository#findById()
     */
    @Override
    public Optional<AddressType> findById(short id) {
	AddressTypeQuery atq = domainQueryFactory.newAddressTypeQuery();
	atq.withId(id);
	return atq.getSingleResult();
    }

}
