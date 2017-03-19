package com.nxp.trustid.websocketserver;

/**
 * During the asynchronous handling of a request an {@link InterruptedException} was thrown.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class AsyncRequestHandlingInterruptedException extends RuntimeException {

  public AsyncRequestHandlingInterruptedException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
