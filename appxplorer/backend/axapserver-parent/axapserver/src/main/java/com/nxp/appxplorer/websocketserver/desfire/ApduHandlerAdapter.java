package com.nxp.trustid.websocketserver.desfire;


import static com.nxp.trustid.communication.util.Utils.getHexString;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nxp.trustid.model.desfire.ApduHandler;
import com.nxp.nfclib.interfaces.IApduHandler;
import com.nxp.nfclib.interfaces.IReader;

/**
 * Adapter between ApduMagic's {@code IApduHandler} and {@link ApduHandler}.
 *
 * @author nxa30710 ( Srinivasu.Gannem@nxp.com )
 */
class ApduHandlerAdapter implements IApduHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApduHandlerAdapter.class);

  private final ApduHandler apduHandler;

  public ApduHandlerAdapter(final ApduHandler apduHandler) {
    this.apduHandler = apduHandler;
  }

  @Override
  public byte[] apduExchange(final byte[] bytes) {
    LOGGER.debug("apduExchange in: {}", getHexString(bytes));
    final byte[] result = apduHandler.apduExchange(bytes);
    LOGGER.debug("apduExchange out: {}", getHexString(result));
    return result;
  }

  @Override
  public IReader getReader() {
    throw new UnsupportedOperationException();
  }
}
