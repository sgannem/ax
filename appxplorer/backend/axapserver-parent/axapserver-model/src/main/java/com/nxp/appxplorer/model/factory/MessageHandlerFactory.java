package com.nxp.trustid.model.factory;


import com.nxp.trustid.model.MessageHandler;

/**
 * Creates {@link MessageHandler}s.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public interface MessageHandlerFactory {

  MessageHandler newInstance();
}
