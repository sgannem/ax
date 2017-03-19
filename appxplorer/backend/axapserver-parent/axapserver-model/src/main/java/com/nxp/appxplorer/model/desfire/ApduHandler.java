package com.nxp.trustid.model.desfire;

/**
 * Processes APDUs. (see {@code ApduHandlerAdapter})
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public interface ApduHandler {

  byte[] apduExchange(byte[] output);
}
