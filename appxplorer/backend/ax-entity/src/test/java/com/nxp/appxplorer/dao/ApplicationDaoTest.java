package com.nxp.appxplorer.dao;

import java.util.Optional;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.Application;
import com.nxp.appxplorer.entity.ApplicationProvider;
import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class ApplicationDaoTest extends AbstractDaoTest {

    private ApplicationDao applicationDao;

    @Test
    public void testApplication() throws EntityNotFoundInRepoException {
	final Optional<Application> ao = applicationDao.findById(1L);
	System.out.println("############ Got Application ################");
	System.out.println("######################################################");
	System.out.println(ao);
	// assertTrue(monorailTicket.isPresent());
    }

    @Override
    protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
	// init card issuer dao
	applicationDao = new ApplicationDao(getDomainQueryFactory());
	// for (int i = 0; i < 10; i++) {
	final ApplicationProvider ap = TestDataGenerator.createApplicationProvider();
	final Application application = TestDataGenerator.createApplication("BMTC");
	ap.getApplications().add(application);
	System.out.println("############ inserting applicationprovider into DB ################");
	// Card card = TestDataGenerator.createCard(application.getId());
	// System.out.println("#cardisser.getCards:" + cardIssuer.getCards());
	// cardIssuer.getCards().add(card);
	System.out.println("#application provider:" + ap);
	entityManagerHelper.persist(ap);
    }

}
