package com.nxp.appxplorer.dao;

import java.util.List;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.SmartMediumType;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class SmartMediumTypeDaoTest extends AbstractDaoTest {

    private SmartMediumTypeDao smartMediumTypeDao;

    @Test
    public void testSmartMediumType() throws EntityNotFoundInRepoException {
	final List<SmartMediumType> smartMediumType = smartMediumTypeDao.getAllSmartMediumTypes();
	System.out.println("############ Getting Smart Medium Type Details - Begin ################");
	System.out
		.println(smartMediumType.isEmpty() ? "Smart Medium Type does not exist" : "Smart Medium Size exists.");
	System.out.println("############ Getting Smart Medium Type Details - End ################");
    }

    @Override
    protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
	smartMediumTypeDao = new SmartMediumTypeDao(getDomainQueryFactory());
	final SmartMediumType smartMediumType = TestDataGenerator.createSmartMediumType();
	System.out.println("############ inserting into DB - Begin ################");
	entityManagerHelper.persist(smartMediumType);
	System.out.println("############ inserting into DB - End ################");
    }

}
