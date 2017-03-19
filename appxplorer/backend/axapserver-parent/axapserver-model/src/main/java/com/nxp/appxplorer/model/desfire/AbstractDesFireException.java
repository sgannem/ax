package com.nxp.trustid.model.desfire;


import com.nxp.trustid.communication.dto.message.StatusCodeDto.ErrorCodeEnum;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public abstract class AbstractDesFireException extends RuntimeException {

  private final ErrorCodeEnum errorCode;

  protected AbstractDesFireException(final ErrorCodeEnum errorCode, final Throwable cause) {
    super(cause);
    this.errorCode = errorCode;
  }

  protected AbstractDesFireException(final ErrorCodeEnum errorCode) {
    super();
    this.errorCode = errorCode;
  }

  protected AbstractDesFireException(final String message, final ErrorCodeEnum errorCode, final Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  protected AbstractDesFireException(final String message, final ErrorCodeEnum errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public ErrorCodeEnum getErrorCode() {
    return errorCode;
  }
}
