package com.nxp.appxplorer.services.model.config;

import com.google.inject.AbstractModule;
import com.nxp.appxplorer.dao.BusinessSegmentDao;
import com.nxp.appxplorer.dao.CardIssuerDao;
import com.nxp.appxplorer.dao.CountryDao;
import com.nxp.appxplorer.dao.LoginDetailsDao;
import com.nxp.appxplorer.dao.LoginTokenDetailsDao;
import com.nxp.appxplorer.dao.PasswordTypeDao;
import com.nxp.appxplorer.repository.BusinessSegmentRepository;
import com.nxp.appxplorer.repository.CardIssuerRepository;
import com.nxp.appxplorer.repository.CountryRepository;
import com.nxp.appxplorer.repository.LoginDetailsRepository;
import com.nxp.appxplorer.repository.LoginTokenDetailsRepository;
import com.nxp.appxplorer.repository.PasswordTypeRepository;
import com.nxp.appxplorer.services.model.AccountServices;
import com.nxp.appxplorer.services.model.CardIssuerServices;

/**
 * This class defines Guice binding for the Services model. Its very similarly
 * to load all the singleton objects to memory.
 * 
 * @author nxa30710
 *
 */
public class ServicesModelGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
	bindServices();
	bindRepositories();
    }

    private void bindServices() {
	bind(AccountServices.class);
	bind(CardIssuerServices.class);
    }

    private void bindRepositories() {
	bind(LoginDetailsRepository.class).to(LoginDetailsDao.class);
	bind(LoginTokenDetailsRepository.class).to(LoginTokenDetailsDao.class);
	bind(PasswordTypeRepository.class).to(PasswordTypeDao.class);
	bind(CardIssuerRepository.class).to(CardIssuerDao.class);
	bind(CountryRepository.class).to(CountryDao.class);
	bind(BusinessSegmentRepository.class).to(BusinessSegmentDao.class);

    }

}
