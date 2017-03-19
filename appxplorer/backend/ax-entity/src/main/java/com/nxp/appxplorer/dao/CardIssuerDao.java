package com.nxp.appxplorer.dao;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.CardIssuer;
import com.nxp.appxplorer.query.CardIssuerQuery;
import com.nxp.appxplorer.repository.CardIssuerRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Card Issuer Details.
 * 
 */
@Singleton
public class CardIssuerDao extends AbstractDao<CardIssuer> implements CardIssuerRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public CardIssuerDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get all the Card Issuer details from the Data
     * Base.
     * 
     * @see com.nxp.appxplorer.repository.CardIssuerRepository#getCardIssuers()
     */
    @Override
    public List<CardIssuer> getCardIssuers() {
	CardIssuerQuery ciq = domainQueryFactory.newCardIssuerQuery();
	return ciq.getResultList();
    }

    /*
     * This method validates the Business Segment against the Data base.
     * 
     * @see com.nxp.appxplorer.repository.CardIssuerRepository#
     * isSmallMediumTypeExists(int, int, java.lang.String)
     */
    @Override
    public boolean isCardIssuerExistsByName(String firstName, String lastName, String email) {
	boolean status = true;
	CardIssuerQuery ciq = domainQueryFactory.newCardIssuerQuery();
	ciq.withIds(firstName, lastName, email);
	Optional<CardIssuer> ci = ciq.getSingleResult();
	if (!ci.isPresent()) {
	    status = false;
	}
	return status;
    }

    /*
     * This method validates the Business Segment against the Data base.
     * 
     * @see com.nxp.appxplorer.repository.CardIssuerRepository#
     * isCardIssuerExistsByCompanyName(java.lang.String)
     */
    @Override
    public boolean isCardIssuerExistsByCompanyName(String companyName) {
	boolean status = true;
	CardIssuerQuery ciq = domainQueryFactory.newCardIssuerQuery();
	ciq.withIds(companyName);
	Optional<CardIssuer> ci = ciq.getSingleResult();
	if (!ci.isPresent()) {
	    status = false;
	}
	return status;
    }

    /*
     * This method is used to get all the Card from the Data base by id.
     * 
     * @see com.nxp.appxplorer.repository.CardIssuerRepository#findById()
     */
    @Override
    public Optional<CardIssuer> findById(long id) {
	CardIssuerQuery ciq = domainQueryFactory.newCardIssuerQuery();
	ciq.withId(id);
	return ciq.getSingleResult();
    }

    /*
     * This method is used to get all the Card from the Data base by id.
     * 
     * @see com.nxp.appxplorer.repository.CardIssuerRepository#findById()
     */
    @Override
    public Optional<CardIssuer> findById(String id) {
	CardIssuerQuery ciq = domainQueryFactory.newCardIssuerQuery();
	ciq.withId(id);
	return ciq.getSingleResult();
    }

}
