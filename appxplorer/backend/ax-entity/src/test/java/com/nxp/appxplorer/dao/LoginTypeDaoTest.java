package com.nxp.appxplorer.dao;

import java.util.List;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.LoginType;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class LoginTypeDaoTest extends AbstractDaoTest {

	private LoginTypeDao loginTypeDao;

	@Test
	public void testLoginTypeDaoTest() throws EntityNotFoundInRepoException {
		final List<LoginType> loginTypeList = loginTypeDao.getAllLoginTypes();
		System.out.println("############ Getting all the LoginTypes ################");
		System.out.println(loginTypeList);
		// assertTrue(monorailTicket.isPresent());
	}

	@Override
	protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
		// init card issuer dao
		loginTypeDao = new LoginTypeDao(getDomainQueryFactory());
		final LoginType loginType1 = TestDataGenerator.createLoginType((short)10);
		final LoginType loginType2 = TestDataGenerator.createLoginType((short)11);
		System.out.println("############ inserting into DB ################");
		entityManagerHelper.persist(loginType1);
		entityManagerHelper.persist(loginType2);
	}

}
