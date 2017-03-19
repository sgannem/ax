package com.nxp.appxplorer.dao;

import java.util.Optional;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.LoginDetails;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class LoginDetailsDaoTest extends AbstractDaoTest {

    private LoginDetailsDao loginDetailsDao;

    @Test
    public void testLoginDetails() throws EntityNotFoundInRepoException {
	final Optional<LoginDetails> loginDetails = loginDetailsDao.getLoginDetails((short) 1, (short) 1,
		"gsrini.in@gmail.com");
	System.out.println("############ Getting Login Details ################");
	System.out.println(loginDetails.isPresent() ? loginDetails : "Login details does not exist");
	// assertTrue(monorailTicket.isPresent());
    }

    @Override
    protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
	// init card issuer dao
	loginDetailsDao = new LoginDetailsDao(getDomainQueryFactory());
	// Insert sample monorail ticket to db.
	final LoginDetails loginDetails = TestDataGenerator.generateLoginDetails();
	System.out.println("############ inserting into DB ################");
	entityManagerHelper.persist(loginDetails);
    }

}
