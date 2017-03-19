package com.nxp.appxplorer.dao;

import java.util.Optional;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.Card;
import com.nxp.appxplorer.entity.CardIssuer;
import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class CardDaoTest extends AbstractDaoTest {

    private CardDao cardDao;

    @Test
    public void testCard() throws EntityNotFoundInRepoException {
	final Optional<Card> co = cardDao.findById(1L);
	System.out.println("############ Got Card ################");
	System.out.println("######################################################");
	System.out.println(co);
    }

    @Override
    protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
	cardDao = new CardDao(getDomainQueryFactory());
	final CardIssuer ci = TestDataGenerator.createCardIssuer();
	final Card card = TestDataGenerator.createCard(ci.getId());
	ci.getCards().add(card);
	System.out.println("############ inserting card into DB - Begin ################");
	System.out.println("#card :" + card);
	entityManagerHelper.persist(ci);
	System.out.println("############ inserting card into DB - End ################");
    }

}
