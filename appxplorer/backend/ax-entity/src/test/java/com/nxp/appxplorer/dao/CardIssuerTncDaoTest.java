package com.nxp.appxplorer.dao;

import java.util.List;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.CardIssuer;
import com.nxp.appxplorer.entity.CardIssuerTnc;
import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class CardIssuerTncDaoTest extends AbstractDaoTest {

    private CardIssuerTncDao cardIssuerTncDao;

    @Test
    public void testCardIssuerTncDetails() throws EntityNotFoundInRepoException {
	final List<CardIssuerTnc> cardIssuersTncList = cardIssuerTncDao.getCardIssuersTnc();
	System.out.println("############ [tnc]Getting card issuers tnc Details ################");
	System.out.println("######################################################");
	for (CardIssuerTnc temp : cardIssuersTncList)
	    System.out.println(temp + ", cardIssuer:" + temp.getCardIssuer());

    }

    @Override
    protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
	cardIssuerTncDao = new CardIssuerTncDao(getDomainQueryFactory());
	// for (int i = 0; i < 5; i++) {
	final CardIssuer cardIssuer = TestDataGenerator.createCardIssuer();
	final CardIssuerTnc cardIssuerTnc = TestDataGenerator.createCardIssuerTnc();
	System.out.println("############ inserting cardissuers into DB ################");
	// Card card = TestDataGenerator.createCard(cardIssuer.getId());
	// System.out.println("#cardisser.getCards:" + cardIssuer.getCards());
	// cardIssuer.getCards().add(card);
	cardIssuer.setCardIssuerTnc(cardIssuerTnc);
	cardIssuerTnc.setCardIssuer(cardIssuer);
	entityManagerHelper.persist(cardIssuer);
	entityManagerHelper.persist(cardIssuerTnc);
	// }
    }

}
