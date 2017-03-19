package com.nxp.appxplorer.dao;

import java.util.List;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.AddressType;
import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class AddressTypeDaoTest extends AbstractDaoTest {
    private AddressTypeDao addressTypeDao;

    @Test
    public void testAddressType() throws EntityNotFoundInRepoException {
	final List<AddressType> at = addressTypeDao.getAllAddressTypes();
	System.out.println("############ Getting Address Type Details - Begin ################");
	System.out.println(at.isEmpty() ? "Address Type does not exists" : "Address Type Exists");
	System.out.println("############ Getting Address Type Details - End ################");
    }

    @Override
    protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
	addressTypeDao = new AddressTypeDao(getDomainQueryFactory());
	final AddressType addrType = TestDataGenerator.createAddressType("TestCompany");
	System.out.println("############ inserting Address Type into DB - Begin ################");
	System.out.println("#AddressType :" + addrType);
	entityManagerHelper.persist(addrType);
	System.out.println("############ inserting country into DB - End ################");
    }

}
