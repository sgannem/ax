package com.nxp.trustid.websocketserver.config;

import javax.websocket.server.ServerEndpointConfig;
import com.google.inject.Injector;

/**
 * Configurator for the server endpoint.
 *
 * It uses the provided Guice {@link Injector} to create the endpoint instances.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class WebSocketServerEndPointConfigurator extends ServerEndpointConfig.Configurator {

  private final Injector injector;

  public WebSocketServerEndPointConfigurator(final Injector injector) {
    this.injector = injector;
  }

  @Override
  public <T> T getEndpointInstance(final Class<T> clazz) throws InstantiationException {
    return injector.getInstance(clazz);
  }
}
