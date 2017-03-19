package com.nxp.appxplorer.dao;

import java.util.Optional;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.LoginDetails;
import com.nxp.appxplorer.entity.LoginTokenDetails;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class LoginTokenDetailsDaoTest extends AbstractDaoTest {

    private LoginTokenDetailsDao loginTokenDetailsDao;

    @Test
    public void testLoginTokenDetails() throws EntityNotFoundInRepoException {
	boolean isTokenExists = loginTokenDetailsDao.isBearerTokeAlreadyExists("test-bearer-token");
	System.out.println("# is bearerToken exists :"+isTokenExists);
	Optional<LoginTokenDetails> ltd = loginTokenDetailsDao.getLoginTokenDetails("test-bearer-token");
	System.out.println(ltd.isPresent() ? ltd : "Login Token details does not exist");
	final Optional<LoginDetails> loginDetails = loginTokenDetailsDao.getLoginDetails("test-bearer-token");
	System.out.println("############ Getting Login Details ################");
	System.out.println(loginDetails.isPresent() ? loginDetails : "Login details does not exist");
	// assertTrue(monorailTicket.isPresent());
    }

    @Override
    protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
	// init card issuer dao
	loginTokenDetailsDao = new LoginTokenDetailsDao(getDomainQueryFactory());
	final LoginTokenDetails loginTokenDetails = TestDataGenerator.generateLoginTokenDetails();
	System.out.println("############ inserting into DB ################");
	entityManagerHelper.persist(loginTokenDetails);
    }

}
