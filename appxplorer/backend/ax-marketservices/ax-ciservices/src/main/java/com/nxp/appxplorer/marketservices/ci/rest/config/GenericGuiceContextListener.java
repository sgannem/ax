package com.nxp.appxplorer.marketservices.ci.rest.config;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.servlet.GuiceServletContextListener;
import com.nxp.appxplorer.marketservices.ci.rest.json.JacksonModule;

/**
 * This class goes mapped in web.xml and is used to inject Google Guice's
 * Injector into the Web Application Context.
 * 
 *
 */
public class GenericGuiceContextListener extends GuiceServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericGuiceContextListener.class);

    private Injector injector;

    @Override
    protected Injector getInjector() {
	// LOGGER.info("#GenericGuiceContextListener is intialised");
	// this.injector = Guice.createInjector(new BootstrapServletModule(),
	// new CIServletModule());
	// return Guice.createInjector(new BootstrapServletModule(), new
	// CIServletModule());
	return injector;
    }

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
	LOGGER.info("#GenericGuiceContextListener.contextInitialized is intialised");
	// , new BootstrapServletModule()
	injector = Guice.createInjector(
		new CIServletModule()/* , new BootstrapServletModule() */);
	// LOGGER.info("#injector:"+injector);
	LOGGER.info("Start PersistService");
	injector.getInstance(PersistService.class).start();
	LOGGER.info("PersistService started");
	configureObjectMapper();
	super.contextInitialized(servletContextEvent);
    }

    @Override
    public void contextDestroyed(final ServletContextEvent servletContextEvent) {
	super.contextDestroyed(servletContextEvent);
    }

    private void configureObjectMapper() {
	final ObjectMapper objectMapper = injector.getInstance(ObjectMapper.class);

	objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	objectMapper.configure(DeserializationFeature.WRAP_EXCEPTIONS, false);
	objectMapper.registerModule(new JacksonModule());
    }

}
