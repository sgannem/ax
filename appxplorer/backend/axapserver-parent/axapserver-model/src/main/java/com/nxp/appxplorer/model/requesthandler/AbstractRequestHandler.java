package com.nxp.trustid.model.requesthandler;

import com.nxp.trustid.communication.dto.message.StatusCodeDto.ErrorCodeEnum;
import com.nxp.trustid.model.desfire.AbstractDesFireException;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
abstract class AbstractRequestHandler implements RequestHandler {

  protected final ErrorCodeEnum mapExceptionToErrorCode(final Exception e) {
    if (e instanceof AbstractDesFireException) {
      return ((AbstractDesFireException) e).getErrorCode();
    }

    return ErrorCodeEnum.UNKNOWN_ERROR;
  }
}
