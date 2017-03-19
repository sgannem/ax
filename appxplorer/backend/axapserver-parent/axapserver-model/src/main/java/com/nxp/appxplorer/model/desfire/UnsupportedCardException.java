package com.nxp.trustid.model.desfire;

import com.nxp.trustid.communication.dto.message.StatusCodeDto;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class UnsupportedCardException extends AbstractDesFireException {

  public UnsupportedCardException(final String message) {
    super(message, StatusCodeDto.ErrorCodeEnum.UNSUPPORTED_CARD);
  }
}
