package com.nxp.appxplorer.dao;

import java.util.List;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.Card;
import com.nxp.appxplorer.entity.CardIssuer;
import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class CardIssuerDaoTest extends AbstractDaoTest {

    private CardIssuerDao cardIssuerDao;

    @Test
    public void testCardIssuerDetails() throws EntityNotFoundInRepoException {
	final List<CardIssuer> cardIssuers = cardIssuerDao.getCardIssuers();
	System.out.println("############ Getting card issuers Details ################");
	System.out.println("######################################################");
	System.out.println(cardIssuers);
	// assertTrue(monorailTicket.isPresent());
    }

    @Override
    protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
	// init card issuer dao
	cardIssuerDao = new CardIssuerDao(getDomainQueryFactory());
	for (int i = 0; i < 10; i++) {
	    final CardIssuer cardIssuer = TestDataGenerator.createCardIssuer();
	    System.out.println("############ inserting cardissuers into DB ################");
	    Card card = TestDataGenerator.createCard(cardIssuer.getId());
	    System.out.println("#cardisser.getCards:" + cardIssuer.getCards());
	    cardIssuer.getCards().add(card);
	    entityManagerHelper.persist(cardIssuer);
	}
    }

}
