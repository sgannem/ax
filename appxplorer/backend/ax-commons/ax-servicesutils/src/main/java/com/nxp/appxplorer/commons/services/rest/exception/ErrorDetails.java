package com.nxp.appxplorer.commons.services.rest.exception;

import static com.nxp.appxplorer.commons.services.rest.exception.ErrorCodeEnum.INVALID_DATA;
import static java.util.Collections.unmodifiableMap;
import static java.util.Objects.requireNonNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains details of an error. An instance of this object is returned by the REST interface in the case of an error.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class ErrorDetails {

  private final ErrorCodeEnum errorCode;
  private final Map<String, Object> additionalData;

  private ErrorDetails(final ErrorCodeEnum errorCode, final Map<String, Object> additionalData) {
    requireNonNull(errorCode);

    if (errorCode.needsAdditionalData() && (isEmpty(additionalData))) {
      throw new IllegalArgumentException(errorCode + " needs additional data but parameter \"additionalData\" is empty");
    }

    this.errorCode = errorCode;
    this.additionalData = additionalData;
  }

  ErrorDetails() {
    this.errorCode = null;
    this.additionalData = null;
  }

  private boolean isEmpty(final Map<String, Object> additionalData) {
    return (additionalData == null) || additionalData.isEmpty();
  }

  public static ErrorDetails newInstance(final ErrorCodeEnum errorCode) {
    return new ErrorDetails(errorCode, null);
  }

  public static ErrorDetails newInvalidDataInstance(final List<String> invalidFields) {
    return ErrorDetails.builder(INVALID_DATA).addData("invalidFields", invalidFields).build();
  }

  public static ErrorDetails newInvalidDataInstance(final String... invalidFields) {
    return ErrorDetails.builder(INVALID_DATA).addData("invalidFields", invalidFields).build();
  }

  public ErrorCodeEnum getErrorCode() {
    return errorCode;
  }

  public Map<String, Object> getAdditionalData() {
    if (additionalData == null) {
      return null;
    }

    return unmodifiableMap(additionalData);
  }

  @Override
  public String toString() {
    return "ErrorDetails{" +
        "errorCode=" + errorCode +
        ", additionalData=" + additionalData +
        '}';
  }

  public static Builder builder(final ErrorCodeEnum errorCode) {
    return new Builder(errorCode);
  }

  public static class Builder {
    private final ErrorCodeEnum errorCode;
    private Map<String, Object> additionalData;

    private Builder(final ErrorCodeEnum errorCode) {
      this.errorCode = errorCode;
      this.additionalData = null;
    }

    public Builder addData(final String key, final Object value) {
      if (additionalData == null) {
        additionalData = new HashMap<>();
      }

      additionalData.put(key, value);
      return this;
    }

    public ErrorDetails build() {
      return new ErrorDetails(errorCode, additionalData);
    }
  }
}
