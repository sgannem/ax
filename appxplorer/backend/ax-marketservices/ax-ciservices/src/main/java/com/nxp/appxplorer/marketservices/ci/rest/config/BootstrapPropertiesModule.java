package com.nxp.appxplorer.marketservices.ci.rest.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * Guice Module to load Properties file and bind it to Guice Injector.<br>
 * Properties can later be used in constructor or field injection by using: <br>
 * <code>@Inject @Named("name.of.the.key") private String propValue;</code>
 * 
 *
 */
public class BootstrapPropertiesModule extends AbstractModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(BootstrapPropertiesModule.class);

    @Override
    protected void configure() {
	Properties bootstrapProperties = new Properties();
	try {
	    LOGGER.info("#calling BootstrapPropertiesModule.configure() to load file:"
		    + GenericBootstrapConstants.BOOTSTRAP_PROPERTIES_FILE);
	    InputStream is = getClass().getResourceAsStream("/" + GenericBootstrapConstants.BOOTSTRAP_PROPERTIES_FILE);
	    bootstrapProperties.load(is);
	    LOGGER.info("#All the loaded properties :" + bootstrapProperties);
	    Names.bindProperties(binder(), bootstrapProperties);
	} catch (FileNotFoundException e) {
	    LOGGER.error("The configuration file " + GenericBootstrapConstants.BOOTSTRAP_PROPERTIES_FILE
		    + " can not be found", e);
	} catch (IOException e) {
	    LOGGER.error("I/O Exception during loading configuration", e);
	}
    }

}
