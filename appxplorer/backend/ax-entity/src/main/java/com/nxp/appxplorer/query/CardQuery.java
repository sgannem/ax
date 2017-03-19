package com.nxp.appxplorer.query;

import javax.persistence.EntityManager;

import com.nxp.appxplorer.entity.Card;

/**
 * This class helps to create a hibernate hql to fetch Card details.
 * 
 */

public class CardQuery extends AbstractDomainQuery<Card> implements DomainQuery<Card> {

	/**
	 * This constructors is being called by the DAOs by providing the entity
	 * manager.
	 * 
	 * @param entityManager
	 */
	private CardQuery(final EntityManager entityManager) {
		super(Card.class, entityManager, "c");
	}

	/**
	 * This method helps to get runtime instance of the Card Query.
	 * 
	 * @param entityManager
	 * @return
	 */
	public static CardQuery newInstance(final EntityManager entityManager) {
		return new CardQuery(entityManager);
	}

	/**
	 * This method helps to write some conditions on the Card Query.
	 * 
	 * @param cardIssuerId
	 * @return
	 */
	public CardQuery withIds(final long cardIssuerId) {
		addCondition("c.cardIssuerId=:cardIssuerId", "cardIssuerId", cardIssuerId);
		addCondition("ld.isActive=:isActive", "isActive", "Y");
		return this;
	}

	/**
	 * This method helps to write some conditions on the Card Details Query.
	 * 
	 * @param id
	 * @return
	 */
	public CardQuery withId(final long id) {
		addCondition("c.id=:id", "id", id);
		addCondition("c.isActive=:isActive", "isActive", "Y");
		return this;
	}
}
