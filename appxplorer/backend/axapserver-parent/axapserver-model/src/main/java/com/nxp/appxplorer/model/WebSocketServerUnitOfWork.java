package com.nxp.trustid.model;

/**
 * A unit of work.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public interface WebSocketServerUnitOfWork {

  void begin();

  void end();

  void rollback();
}
