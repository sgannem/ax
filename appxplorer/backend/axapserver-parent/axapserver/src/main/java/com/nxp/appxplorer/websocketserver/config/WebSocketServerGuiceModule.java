package com.nxp.trustid.websocketserver.config;


import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.nxp.trustid.model.DefaultUpdateApplicationCallback;
import com.nxp.trustid.model.MessageHandler;
import com.nxp.trustid.model.UpdateApplicationCallback;
import com.nxp.trustid.model.desfire.CardFactory;
import com.nxp.trustid.model.factory.MessageHandlerFactory;
import com.nxp.trustid.model.factory.RequestHandlerFactory;
import com.nxp.trustid.model.factory.RequestHandlerRunnerFactory;
import com.nxp.trustid.model.requesthandler.RequestHandlerRunner;
import com.nxp.trustid.websocketserver.AsyncRequestHandlerRunner;
import com.nxp.trustid.websocketserver.ConnectionHandlerFactory;
import com.nxp.trustid.websocketserver.desfire.ApduMagicWrapper;

/**
 * Guice Module for the server.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
class WebSocketServerGuiceModule extends AbstractModule {

  public static final String SYSTEM_PROPERTIES_FILE = "system.properties";

  @Override
  protected void configure() {
    installFactories();
    install(new PropertiesGuiceModule(SYSTEM_PROPERTIES_FILE));

    bind(CardFactory.class).to(ApduMagicWrapper.class);
    bind(UpdateApplicationCallback.class).to(DefaultUpdateApplicationCallback.class);
  }

  private void installFactories() {
    install(new FactoryModuleBuilder().build(ConnectionHandlerFactory.class));
    install(new FactoryModuleBuilder().implement(MessageHandler.class, MessageHandler.class).build(MessageHandlerFactory.class));
    install(new FactoryModuleBuilder().build(RequestHandlerFactory.class));
    install(new FactoryModuleBuilder().implement(RequestHandlerRunner.class, AsyncRequestHandlerRunner.class).build
        (RequestHandlerRunnerFactory.class));
  }
}
