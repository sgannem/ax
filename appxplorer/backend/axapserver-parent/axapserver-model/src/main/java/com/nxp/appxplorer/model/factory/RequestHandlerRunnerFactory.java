package com.nxp.trustid.model.factory;


import com.nxp.trustid.model.MessageHandler;
import com.nxp.trustid.model.requesthandler.RequestHandlerRunner;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public interface RequestHandlerRunnerFactory {

  RequestHandlerRunner newInstance(MessageHandler messageHandler);
}
