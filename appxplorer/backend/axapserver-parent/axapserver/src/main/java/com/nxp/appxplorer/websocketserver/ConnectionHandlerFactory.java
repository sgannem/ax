package com.nxp.trustid.websocketserver;

/**
 * Creates {@link ConnectionHandler}s.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public interface ConnectionHandlerFactory {

  ConnectionHandler newInstance(String sessionId);
}
