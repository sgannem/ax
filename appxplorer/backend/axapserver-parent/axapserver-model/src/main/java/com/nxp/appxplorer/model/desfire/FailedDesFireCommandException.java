package com.nxp.trustid.model.desfire;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class FailedDesFireCommandException extends RuntimeException {

  private static final String ERROR_MESSAGE = "Failed DESFire operation: ";

  public FailedDesFireCommandException(final String desFireCommand, final Throwable cause) {
    super(ERROR_MESSAGE + desFireCommand, cause);
  }
}
