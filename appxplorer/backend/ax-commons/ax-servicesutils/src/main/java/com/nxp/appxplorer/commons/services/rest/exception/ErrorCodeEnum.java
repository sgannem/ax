package com.nxp.appxplorer.commons.services.rest.exception;

/**
 * Error codes of errors that occur at the REST interface.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public enum ErrorCodeEnum {

  INVALID_DATA(true),
  USER_ID_MISMATCH(false),
  AID_ALREADY_IN_USE(false),
  INVALID_APPLICATION_STATUS(false),
  ILLEGAL_APPROVAL_OPTION(false),
  CARD_ISSUER_HAS_APPROVAL_FOR_SHARED_SLOT(false);

  private final boolean hasAdditionalData;

  ErrorCodeEnum(final boolean hasAdditionalData) {
    this.hasAdditionalData = hasAdditionalData;
  }

  public boolean needsAdditionalData() {
    return hasAdditionalData;
  }
}
