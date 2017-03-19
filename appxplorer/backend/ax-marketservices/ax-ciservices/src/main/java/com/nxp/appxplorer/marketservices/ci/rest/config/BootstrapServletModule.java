package com.nxp.appxplorer.marketservices.ci.rest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.servlet.ServletModule;
import com.nxp.appxplorer.marketservices.ci.shiro.modules.BootstrapShiroModule;
import com.nxp.appxplorer.marketservices.ci.shiro.modules.ShiroAnnotationsModule;

/**
 * This class bootstraps the application Servlet (Jersey 1.18.1). If you want
 * the Shiro annotations to work, you will need to inject every Web Service's
 * constructor, so Guice's injector can handle the creation of the WS.
 * 
 */
public class BootstrapServletModule extends ServletModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(BootstrapServletModule.class);

    private static final String propertyPackages = GenericBootstrapConstants.JERSEY_PROPERTY_PACKAGES;

    @Override
    protected void configureServlets() {
	super.configureServlets();

	LOGGER.info("#installing BootstrapPropertiesModule into container");
	// get the bootstrapping Properties file
	install(new BootstrapPropertiesModule());

	// Initialize Persistence JPA Unit of Work if present
	// install(new MyUnitOfWorkModule());
	LOGGER.info("Installing BootstrapShiroModule into container");
	// Initialize Apache Shiro if present
	install(new BootstrapShiroModule(getServletContext()));
	LOGGER.info("installing ShiroAnnotationsModule into container");
	// This allows Shiro AOP Annotations
	// http://shiro.apache.org/java-authorization-guide.html
	install(new ShiroAnnotationsModule());

	// Map<String, String> params = new HashMap<String, String>();
	// params.put(PackagesResourceConfig.PROPERTY_PACKAGES,
	// propertyPackages);
	// if you had a Persistence Service like JPA Unit of Work you would need
	// to add this PersistFilter also.
	// filter("/*").through(PersistFilter.class);
	// if you had a ShiroWebModule installed above you would need to add
	// this GuiceShiroFilter also.
	// filter("/*").through(GuiceShiroFilter.class);
	// serve("/rest/*").with(GuiceContainer.class, params);

    }
}
