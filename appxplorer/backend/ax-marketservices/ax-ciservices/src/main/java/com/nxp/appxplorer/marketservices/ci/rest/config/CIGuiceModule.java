package com.nxp.appxplorer.marketservices.ci.rest.config;

import com.nxp.appxplorer.commons.services.guice.AbstractGuiceModuleWithPropertyBinding;
import com.nxp.appxplorer.commons.services.utils.IRandomStringGenerator;
import com.nxp.appxplorer.commons.services.utils.RandomStringGenerator;
import com.nxp.appxplorer.dao.APAddressDao;
import com.nxp.appxplorer.dao.ApplicationDao;
import com.nxp.appxplorer.dao.ApplicationProviderDao;
import com.nxp.appxplorer.dao.ApplicationProviderTncDao;
import com.nxp.appxplorer.dao.CIAddressDao;
import com.nxp.appxplorer.dao.CardDao;
import com.nxp.appxplorer.dao.CardIssuerDao;
import com.nxp.appxplorer.dao.CardIssuerTncDao;
import com.nxp.appxplorer.dao.LoginDetailsDao;
import com.nxp.appxplorer.marketservices.ci.rest.resource.CardIssuerResource;
import com.nxp.appxplorer.marketservices.ci.rest.resource.DashboardResource;
import com.nxp.appxplorer.marketservices.ci.rest.resource.LoginResource;
import com.nxp.appxplorer.marketservices.ci.rest.resource.MonitoringResource;
import com.nxp.appxplorer.repository.APAddressRepository;
import com.nxp.appxplorer.repository.ApplicationProviderRepository;
import com.nxp.appxplorer.repository.ApplicationProviderTncRepository;
import com.nxp.appxplorer.repository.ApplicationRepository;
import com.nxp.appxplorer.repository.CIAddressRepository;
import com.nxp.appxplorer.repository.CardIssuerRepository;
import com.nxp.appxplorer.repository.CardIssuerTncRepository;
import com.nxp.appxplorer.repository.CardRepository;
import com.nxp.appxplorer.repository.LoginDetailsRepository;

public class CIGuiceModule extends AbstractGuiceModuleWithPropertyBinding {

    @Override
    protected void configure() {
	super.configure();
	bindResources();
	bindRepositories();
	bindUtilities();
	// install(new BootstrapPropertiesModule());
	// install(new ShiroAnnotationsModule());
    }

    private void bindResources() {
	bind(MonitoringResource.class);
	bind(DashboardResource.class);
	bind(LoginResource.class);
	bind(CardIssuerResource.class);
    }

    private void bindRepositories() {
	bind(LoginDetailsRepository.class).to(LoginDetailsDao.class);
	bind(CardIssuerRepository.class).to(CardIssuerDao.class);
	bind(CardRepository.class).to(CardDao.class);
	bind(ApplicationProviderRepository.class).to(ApplicationProviderDao.class);
	bind(ApplicationRepository.class).to(ApplicationDao.class);
	bind(CardIssuerTncRepository.class).to(CardIssuerTncDao.class);
	bind(ApplicationProviderTncRepository.class).to(ApplicationProviderTncDao.class);
	bind(CIAddressRepository.class).to(CIAddressDao.class);
	bind(APAddressRepository.class).to(APAddressDao.class);
    }

    private void bindUtilities() {
	bind(IRandomStringGenerator.class).to(RandomStringGenerator.class);
    }

}
