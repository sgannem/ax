package com.nxp.appxplorer.dao;

import java.util.List;

import org.testng.annotations.Test;

import com.nxp.appxplorer.entity.Country;
import com.nxp.appxplorer.entity.EntityManagerHelper;
import com.nxp.appxplorer.entity.TestDataGenerator;
import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;

public class CountryDaoTest extends AbstractDaoTest {

    private CountryDao countryDao;

    @Test
    public void testCountry() throws EntityNotFoundInRepoException {

	final List<Country> country = countryDao.getAllCountries();
	System.out.println("############ Getting Country Details - Begin ################");
	System.out.println(country.isEmpty() ? "Countries does not exists" : "Countries Exists");
	System.out.println("############ Getting Country Details - End ################");
    }

    @Override
    protected void populateDatabase(final EntityManagerHelper entityManagerHelper) {
	countryDao = new CountryDao(getDomainQueryFactory());
	final Country cntr1 = TestDataGenerator.createCountry("TestCountry1");
	System.out.println("############ inserting country into DB - Begin ################");
	System.out.println("#country1 :" + cntr1);
	entityManagerHelper.persist(cntr1);
	System.out.println("############ inserting country into DB - End ################");
    }

}
