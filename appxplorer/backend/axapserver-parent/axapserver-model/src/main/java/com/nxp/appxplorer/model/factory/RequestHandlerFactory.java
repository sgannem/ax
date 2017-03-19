package com.nxp.trustid.model.factory;


import com.nxp.trustid.model.MessageHandler;
import com.nxp.trustid.model.requesthandler.RemoveApplicationRequestHandler;
import com.nxp.trustid.model.requesthandler.RequestHandler;
import com.nxp.trustid.model.requesthandler.UpdateApplicationRequestHandler;

/**
 * Creates {@link RequestHandler}s.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public interface RequestHandlerFactory {

  RemoveApplicationRequestHandler newRemoveApplicationRequestHandler(MessageHandler messageHandler);

  UpdateApplicationRequestHandler newUpdateApplicationRequestHandler(MessageHandler messageHandler);
}
