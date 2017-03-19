package com.nxp.appxplorer.dao;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.CardIssuerTnc;
import com.nxp.appxplorer.query.CardIssuerTncQuery;
import com.nxp.appxplorer.repository.CardIssuerTncRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Card Issuer Tnc Details.
 * 
 */
@Singleton
public class CardIssuerTncDao extends AbstractDao<CardIssuerTnc> implements CardIssuerTncRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public CardIssuerTncDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get all CardIssuers Tnc details from the Data
     * Base.
     * 
     * @see
     * com.nxp.appxplorer.repository.CardIssuerTncRepository#getCardIssuersTnc()
     */
    @Override
    public List<CardIssuerTnc> getCardIssuersTnc() {
	CardIssuerTncQuery citncq = domainQueryFactory.newCardIssuerTncQuery();
	return citncq.getResultList();
    }

    /*
     * This method helps to write some conditions on the CardIssuer Details Tnc
     * Query.
     * 
     * @see com.nxp.appxplorer.repository.CardIssuerTncRepository#findById(long)
     */
    @Override
    public Optional<CardIssuerTnc> findById(long cardIssuerId) {
	CardIssuerTncQuery citncq = domainQueryFactory.newCardIssuerTncQuery();
	citncq.withId(cardIssuerId);
	return citncq.getSingleResult();
    }

}
