package com.nxp.appxplorer.dao;

import java.util.List;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.APAddress;
import com.nxp.appxplorer.entity.Application;
import com.nxp.appxplorer.entity.ApplicationProvider;
import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class APAddressDaoTest extends AbstractDaoTest {

    private APAddressDao apAddressDao;

    @Test
    public void testAPAddress() throws EntityNotFoundInRepoException {
	final List<APAddress> apa = apAddressDao.getAPAddresses(1L);
	System.out.println("############ Got AP Address ################");
	System.out.println("######################################################");
	System.out.println(apa);
    }

    @Override
    protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
	apAddressDao = new APAddressDao(getDomainQueryFactory());
	final ApplicationProvider ap = TestDataGenerator.createApplicationProvider();
	final Application application = TestDataGenerator.createApplication("BMTC");
	final APAddress apAddress = TestDataGenerator.createAPAddress("TestAPAddressOne");
	ap.getApplications().add(application);
	ap.getApAddress().add(apAddress);
	System.out.println("############ inserting AP Address into DB - Begin ################");
	System.out.println("#apAddress :" + apAddress);
	entityManagerHelper.persist(ap);
	System.out.println("############ inserting AP Address into DB - End ################");
    }

}
