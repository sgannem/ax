package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.CardIssuer;

/**
 * This class helps to create a hibernate hql to fetch Card Issuer details.
 * 
 */
public class CardIssuerQuery extends AbstractDomainQuery<CardIssuer> implements DomainQuery<CardIssuer> {
    /**
     * This constructors is being called by the DAOs by providing the entity
     * manager.
     * 
     * @param entityManager
     */
    private CardIssuerQuery(final EntityManager entityManager) {
	super(CardIssuer.class, entityManager, "ci");
    }

    /**
     * This method helps to get runtime instance of the CardIssuer Query.
     * 
     * @param entityManager
     * @return
     */
    public static CardIssuerQuery newInstance(final EntityManager entityManager) {
	return new CardIssuerQuery(entityManager);
    }

    /**
     * This method helps to write some conditions on the CardIssuer Query.
     * 
     * @param firstName
     * @param lastName
     * @param email
     * @return
     */
    public CardIssuerQuery withIds(final String firstName, final String lastName, final String email) {
	addCondition("ci.firstName=:firstName", "firstName", firstName);
	addCondition("ci.lastName=:lastName", "lastName", lastName);
	addCondition("ci.email=:email", "email", email);
	addCondition("ci.isActive=:isActive", "isActive", "Y");
	return this;
    }

    /**
     * This method helps to write some conditions on the CardIssuer Query.
     * 
     * @param companyName
     * @return
     */
    public CardIssuerQuery withIds(final String companyName) {
	addCondition("ci.companyName=:companyName", "companyName", companyName);
	addCondition("ci.isActive=:isActive", "isActive", "Y");
	return this;
    }

    /**
     * This method helps to write some conditions on the CardIssuer Details
     * Query.
     * 
     * @param id
     * @return
     */
    public CardIssuerQuery withId(final long id) {
	addCondition("ci.id=:id", "id", id);
	addCondition("ci.isActive=:isActive", "isActive", "Y");
	return this;
    }

    /**
     * This method helps to write some conditions on the CardIssuer Details
     * Query.
     * 
     * @param id
     * @return
     */
    public CardIssuerQuery withId(final String id) {
	addCondition("ci.id=:id", "id", Long.parseLong(id));
	addCondition("ci.isActive=:isActive", "isActive", "Y");
	return this;
    }
}
