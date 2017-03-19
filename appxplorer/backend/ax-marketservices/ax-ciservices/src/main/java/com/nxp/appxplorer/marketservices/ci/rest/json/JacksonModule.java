package com.nxp.appxplorer.marketservices.ci.rest.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Registers {@link JsonSerializer} that map entity classes to json.
 *
 */
public class JacksonModule extends SimpleModule {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public JacksonModule() {
    super(JacksonModule.class.getSimpleName(), new Version(1, 0, 0, null, null, null));

//    addSerializer(new ApplicationSerializer());
//    addSerializer(new CardIssuerSerializer());
  }
}
