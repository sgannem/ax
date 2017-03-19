package com.nxp.trustid.model.desfire;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class LoadKeyStoreException extends RuntimeException {

  public LoadKeyStoreException(final Throwable cause) {
    super("failed to load keystore", cause);
  }
}
