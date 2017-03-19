package com.nxp.appxplorer.marketservices.ci.rest.config;

import static com.nxp.appxplorer.commons.utils.Constants.JPA_PERSISTENCE_UNIT;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.persist.jpa.JpaPersistModule;
import com.nxp.appxplorer.commons.services.filter.SilentPersistFilter;
import com.nxp.appxplorer.commons.services.rest.json.JsonGuiceModule;
import com.nxp.appxplorer.services.model.config.ServicesModelGuiceModule;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class CIServletModule extends JerseyServletModule {

    @Override
    protected void configureServlets() {
	super.configureServlets();

	install(new CIGuiceModule());
	/** services model **/
	install(new ServicesModelGuiceModule());

	install(new JpaPersistModule(JPA_PERSISTENCE_UNIT));
	filter("/*").through(SilentPersistFilter.class);
	install(new JsonGuiceModule());

	final Map<String, String> map = new HashMap<String, String>();
	map.put("com.sun.jersey.spi.container.ResourceFilters",
		"com.sun.jersey.api.container.filter.RolesAllowedResourceFilterFactory");
	map.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
	serve("/*").with(GuiceContainer.class, map);
	filter("/rest/*").through(CORSFilter.class);
	// filter("/*").through(GuiceShiroFilter.class);
	// serve("/rest/*").with(GuiceContainer.class, params);

    }
}
