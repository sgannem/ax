package com.nxp.trustid.websocketserver.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * Reads the ready-only system properties from a properties file.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class PropertiesGuiceModule extends AbstractModule {

  private final String[] propertyFileNames;

  public PropertiesGuiceModule(final String... propertyFileNames) {
    this.propertyFileNames = propertyFileNames;
  }

  @Override
  protected void configure() {
    for (final String propertyFile : propertyFileNames) {
      final URL resource = getClass().getResource("/" + propertyFile);

      if (resource != null) {
        try (final InputStream propertiesFileStream = resource.openStream()) {
          final Properties properties = new Properties();
          properties.load(propertiesFileStream);
          Names.bindProperties(binder(), properties);
        } catch (final IOException e) {
          throw new IllegalStateException(e);
        }
      }
    }
  }
}
