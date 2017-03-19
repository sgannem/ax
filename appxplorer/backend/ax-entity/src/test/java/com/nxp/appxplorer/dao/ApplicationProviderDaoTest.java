package com.nxp.appxplorer.dao;

import java.util.List;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.Application;
import com.nxp.appxplorer.entity.ApplicationProvider;
import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class ApplicationProviderDaoTest extends AbstractDaoTest {

    private ApplicationProviderDao applicationProviderDao;

    @Test
    public void testApplicationProviders() throws EntityNotFoundInRepoException {
	final List<ApplicationProvider> applicationProviders = applicationProviderDao.getApplicationProviders();
	System.out.println("############ Getting application provider Details ################");
	System.out.println("######################################################");
	System.out.println(applicationProviders);
	// assertTrue(monorailTicket.isPresent());
    }

    @Override
    protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
	// init card issuer dao
	applicationProviderDao = new ApplicationProviderDao(getDomainQueryFactory());
	final ApplicationProvider applicationProvider = TestDataGenerator.createApplicationProvider();
	for (int i = 0; i < 10; i++) {
	    System.out.println("############ inserting cardissuers into DB ################");
	    Application application = TestDataGenerator.createApplication("APP-" + i);
	    System.out.println("#applicationProvider.getApplications:" + applicationProvider.getApplications());
	    applicationProvider.getApplications().add(application);
	}
	entityManagerHelper.persist(applicationProvider);
    }

}
