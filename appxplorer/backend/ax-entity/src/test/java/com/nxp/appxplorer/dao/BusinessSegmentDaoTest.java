package com.nxp.appxplorer.dao;

import java.util.List;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.BusinessSegment;
import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class BusinessSegmentDaoTest extends AbstractDaoTest {

    private BusinessSegmentDao businessSegmentDao;

    @Test
    public void testBusinessSegment() throws EntityNotFoundInRepoException {
	final List<BusinessSegment> businessSegment = businessSegmentDao.getAllBusinessSegments();
	System.out.println("############ Getting Business Segment Details - Begin ################");
	System.out.println(businessSegment.isEmpty() ? "Business Segment does not exist" : "Business Segment exists.");
	System.out.println("############ Getting Business Segment Details - End ################");
    }

    @Override
    protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
	businessSegmentDao = new BusinessSegmentDao(getDomainQueryFactory());
	final BusinessSegment businessSegment = TestDataGenerator.createBusinessSegment();
	System.out.println("############ inserting into DB - Begin ################");
	entityManagerHelper.persist(businessSegment);
	System.out.println("############ inserting into DB - End ################");
    }

}
