package com.nxp.appxplorer.dao;

import java.util.List;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.SmartMediumSize;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class SmartMediumSizeDaoTest extends AbstractDaoTest {

    private SmartMediumSizeDao smartMediumSizeDao;

    @Test
    public void testSmartMediumSize() throws EntityNotFoundInRepoException {
	final List<SmartMediumSize> smartMediumSize = smartMediumSizeDao.getAllSmartMediumSizes();
	System.out.println("############ Getting Smart Medium Size Details - Begin ################");
	System.out
		.println(smartMediumSize.isEmpty() ? "Smart Medium Size does not exist" : "Smart Medium Size exists.");
	System.out.println("############ Getting Smart Medium Size Details - End ################");
    }

    @Override
    protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
	smartMediumSizeDao = new SmartMediumSizeDao(getDomainQueryFactory());
	final SmartMediumSize smartMediumSize = TestDataGenerator.createSmartMediumSize();
	System.out.println("############ inserting into DB - Begin ################");
	entityManagerHelper.persist(smartMediumSize);
	System.out.println("############ inserting into DB - End ################");
    }

}
