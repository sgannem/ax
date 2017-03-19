package com.nxp.trustid.model.requesthandler;

import static com.nxp.trustid.communication.dto.message.StatusCodeDto.ErrorCodeEnum.AUTHENTICATION_FAILED;

import com.nxp.trustid.model.desfire.AbstractDesFireException;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class FailedAuthenticationException extends AbstractDesFireException {

  public FailedAuthenticationException(final Throwable cause) {
    super(AUTHENTICATION_FAILED, cause);
  }
}
