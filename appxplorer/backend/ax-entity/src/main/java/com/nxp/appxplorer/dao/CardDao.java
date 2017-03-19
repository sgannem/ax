package com.nxp.appxplorer.dao;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.Card;
import com.nxp.appxplorer.query.CardQuery;
import com.nxp.appxplorer.repository.CardRepository;

/**
 * This class is being used by the Services Layer to interact with DB to get
 * Card Issuer Details.
 * 
 */
@Singleton
public class CardDao extends AbstractDao<Card> implements CardRepository {

    /** This is a Domain Query Factory **/
    private final DomainQueryFactory domainQueryFactory;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param domainQueryFactory
     */
    @Inject
    public CardDao(final DomainQueryFactory domainQueryFactory) {
	super(domainQueryFactory);
	this.domainQueryFactory = domainQueryFactory;
    }

    /*
     * This method is used to get Card details from the Data Base.
     * 
     * @see com.nxp.appxplorer.repository.CardRepository#getCardsByCardIssuerId(
     * short)
     */
    @Override
    public List<Card> getCardsByCardIssuerId(long cardissuerId) {
	return domainQueryFactory.newCardQuery().withIds(cardissuerId).getResultList();
    }

    /*
     * This method is used to get all the Card from the Data base by id.
     * 
     * @see com.nxp.appxplorer.repository.CardRepository#findById()
     */
    @Override
    public Optional<Card> findById(long id) {
	CardQuery cq = domainQueryFactory.newCardQuery();
	cq.withId(id);
	return cq.getSingleResult();
    }

}
