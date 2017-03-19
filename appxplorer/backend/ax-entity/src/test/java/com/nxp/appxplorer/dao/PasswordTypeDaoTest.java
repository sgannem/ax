package com.nxp.appxplorer.dao;

import java.util.Optional;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.PasswordType;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class PasswordTypeDaoTest extends AbstractDaoTest {

	private PasswordTypeDao passwordTypeDao;

	@Test
	public void testPasswordTypeDaoTest() throws EntityNotFoundInRepoException {
		final Optional<PasswordType> password = passwordTypeDao.getById((short)1);
		System.out.println("############ Getting all the LoginTypes ################");
		System.out.println(password.get());
		// assertTrue(monorailTicket.isPresent());
	}

	@Override
	protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
		// init card issuer dao
	    passwordTypeDao = new PasswordTypeDao(getDomainQueryFactory());
		final PasswordType passwordType = TestDataGenerator.createPassword();
		System.out.println("############ inserting into DB ################");
		entityManagerHelper.persist(passwordType);
	}

}
