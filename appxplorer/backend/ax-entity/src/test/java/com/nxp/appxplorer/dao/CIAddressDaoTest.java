package com.nxp.appxplorer.dao;

import java.util.List;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.CIAddress;
import com.nxp.appxplorer.entity.Card;
import com.nxp.appxplorer.entity.CardIssuer;
import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class CIAddressDaoTest extends AbstractDaoTest {

    private CIAddressDao ciAddressDao;

    @Test
    public void testCIAddress() throws EntityNotFoundInRepoException {
	final List<CIAddress> cia = ciAddressDao.getCIAddresses(1L);
	System.out.println("############ Got CI Address ################");
	System.out.println("######################################################");
	System.out.println(cia);
    }

    @Override
    protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
	ciAddressDao = new CIAddressDao(getDomainQueryFactory());
	final CardIssuer ci = TestDataGenerator.createCardIssuer();
	final Card card = TestDataGenerator.createCard(ci.getId());
	final CIAddress ciAddress = TestDataGenerator.createCIAddress("TestCIAddressOne");
	ci.getCards().add(card);
	ci.getCiAddress().add(ciAddress);
	System.out.println("############ inserting card into DB - Begin ################");
	System.out.println("#card :" + card);
	entityManagerHelper.persist(ci);
	System.out.println("############ inserting card into DB - End ################");
    }

}
