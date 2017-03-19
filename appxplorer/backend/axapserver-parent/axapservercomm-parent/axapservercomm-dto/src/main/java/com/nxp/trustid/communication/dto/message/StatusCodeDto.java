package com.nxp.trustid.communication.dto.message;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class StatusCodeDto {

  private final boolean success;
  private final ErrorCodeEnum errorCode;

  private StatusCodeDto(final boolean success, final ErrorCodeEnum errorCode) {
    this.success = success;
    this.errorCode = errorCode;
  }

  public static StatusCodeDto newInstance(final boolean success, final ErrorCodeEnum errorCode) {
    return new StatusCodeDto(success, errorCode);
  }

  public static StatusCodeDto success() {
    return new StatusCodeDto(true, null);
  }

  public static StatusCodeDto error(final ErrorCodeEnum errorCode) {
    return new StatusCodeDto(false, errorCode);
  }

  public boolean isSuccess() {
    return success;
  }

  public ErrorCodeEnum getErrorCode() {
    return errorCode;
  }

  public enum ErrorCodeEnum {
    UNKNOWN_ERROR,
    AUTHENTICATION_FAILED,
    DES_FIRE_ERROR,
    APPLICATION_DOES_NOT_EXIST,
    UNSUPPORTED_CARD;
  }

  @Override
  public String toString() {
    if (errorCode == null) {
      return "StatusCodeDto{" +
          "success=" + success +
          '}';
    }

    return "StatusCodeDto{" +
        "success=" + success +
        ", errorCode=" + errorCode +
        '}';
  }
}
