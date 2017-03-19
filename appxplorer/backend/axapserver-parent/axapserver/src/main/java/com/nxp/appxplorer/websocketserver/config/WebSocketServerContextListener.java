package com.nxp.trustid.websocketserver.config;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.nxp.trustid.websocketserver.WebSocketServer;

/**
 * Publish endpoint provided by {@link WebSocketServer}.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
@WebListener
public class WebSocketServerContextListener extends GuiceServletContextListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServerContextListener.class);

  private static final String SERVER_CONTAINER_ATTRIBUTE = "javax.websocket.server.ServerContainer";

  private Injector injector = null;

  @Override
  protected Injector getInjector() {
    if (injector == null) {
      injector = Guice.createInjector(new WebSocketServerGuiceModule());
    }

    return injector;
  }

  @Override
  public void contextInitialized(final ServletContextEvent servletContextEvent) {
    super.contextInitialized(servletContextEvent);
    final ServletContext container = servletContextEvent.getServletContext();
    final ServerContainer serverContainer = (ServerContainer) container.getAttribute(SERVER_CONTAINER_ATTRIBUTE);

    try {
      serverContainer.addEndpoint(new WebSocketServerEndpointConfig(injector));
    } catch (final DeploymentException e) {
      LOGGER.error("Failed to initialize context", e);
    }
  }

}
