package com.nxp.appxplorer.dao;

import java.util.List;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.TechnologyUsed;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class TechnologyUsedDaoTest extends AbstractDaoTest {

    private TechnologyUsedDao technologyUsedDao;

    @Test
    public void testTechnologyUsed() throws EntityNotFoundInRepoException {
	final List<TechnologyUsed> technologyUsed = technologyUsedDao.getAllTechnologyUsed();
	System.out.println("############ Getting Technology Used Details - Begin ################");
	System.out.println(technologyUsed.isEmpty() ? "Technology Used does not exist" : "Smart Medium Size exists.");
	System.out.println("############ Getting Technology Used Details - End ################");
    }

    @Override
    protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
	technologyUsedDao = new TechnologyUsedDao(getDomainQueryFactory());
	final TechnologyUsed technologyUsed = TestDataGenerator.createTechnologyUsed();
	System.out.println("############ inserting into DB - Begin ################");
	entityManagerHelper.persist(technologyUsed);
	System.out.println("############ inserting into DB - End ################");
    }

}
