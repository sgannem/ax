package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.CardIssuerTnc;

/**
 * This class helps to create a hibernate hql to fetch Card Issuer tnc details.
 * 
 */
public class CardIssuerTncQuery extends AbstractDomainQuery<CardIssuerTnc> implements DomainQuery<CardIssuerTnc> {
    /**
     * This constructors is being called by the DAOs by providing the entity
     * manager.
     * 
     * @param entityManager
     */
    private CardIssuerTncQuery(final EntityManager entityManager) {
	super(CardIssuerTnc.class, entityManager, "citnc");
    }

    /**
     * This method helps to get runtime instance of the CardIssuerTnc Query.
     * 
     * @param entityManager
     * @return
     */
    public static CardIssuerTncQuery newInstance(final EntityManager entityManager) {
	return new CardIssuerTncQuery(entityManager);
    }

    /**
     * This method helps to write some conditions on the CardIssuer Details Tnc
     * Query.
     * 
     * @param cardIssuerId
     * @return
     */
    public CardIssuerTncQuery withId(final long cardIssuerId) {
	addCondition("citnc.cardIssuer.id=:id", "id", cardIssuerId);
	addCondition("citnc.isActive=:isActive", "isActive", "Y");
	return this;
    }

}
