package com.nxp.appxplorer.dao;

import java.util.List;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.RegistrationStatus;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class RegistrationStatusDaoTest extends AbstractDaoTest {

    private RegistrationStatusDao registrationStatusDao;

    @Test
    public void testRegistrationStatus() throws EntityNotFoundInRepoException {
	final List<RegistrationStatus> registrationStatus = registrationStatusDao.getAllRegistrationStatuses();
	System.out.println("############ Getting Registration Status Details - Begin ################");
	System.out.println(
		registrationStatus.isEmpty() ? "Registration Status does not exist" : "Registration Status exists.");
	System.out.println("############ Getting Registration Status Details - End ################");
    }

    @Override
    protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
	registrationStatusDao = new RegistrationStatusDao(getDomainQueryFactory());
	final RegistrationStatus registrationStatus = TestDataGenerator.createRegistrationStatus();
	System.out.println("############ inserting into DB - Begin ################");
	entityManagerHelper.persist(registrationStatus);
	System.out.println("############ inserting into DB - End ################");
    }

}
