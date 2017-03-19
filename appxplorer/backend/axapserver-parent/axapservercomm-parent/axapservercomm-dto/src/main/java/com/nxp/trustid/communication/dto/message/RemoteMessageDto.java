package com.nxp.trustid.communication.dto.message;


import static com.nxp.trustid.communication.dto.message.RemoteMessageDto.TypeEnum.DES_FIRE_COMMAND;
import static com.nxp.trustid.communication.dto.message.RemoteMessageDto.TypeEnum.DES_FIRE_RESPONSE;
import static com.nxp.trustid.communication.dto.message.RemoteMessageDto.TypeEnum.REMOVE_APPLICATION_REQUEST;
import static com.nxp.trustid.communication.dto.message.RemoteMessageDto.TypeEnum.REMOVE_APPLICATION_RESPONSE;
import static com.nxp.trustid.communication.dto.message.RemoteMessageDto.TypeEnum.UPDATE_APPLICATION_REQUEST;
import static com.nxp.trustid.communication.dto.message.RemoteMessageDto.TypeEnum.UPDATE_APPLICATION_RESPONSE;
import com.nxp.trustid.communication.dto.message.StatusCodeDto.ErrorCodeEnum;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class RemoteMessageDto {

  private TypeEnum type;
  private StatusCodeDto statusCode;
  private DesFireCommandDto desFireCommand;
  private DesFireResponseDto desFireResponse;
  private RemoveApplicationRequestDto removeApplicationRequest;
  private RemoveApplicationResponseDto removeApplicationResponse;
  private UpdateApplicationRequestDto updateApplicationRequest;
  private UpdateApplicationResponseDto updateApplicationResponse;

  protected RemoteMessageDto() {
    // empty
  }

  private RemoteMessageDto(final Builder builder) {
    type = builder.type;
    statusCode = builder.statusCode;
    desFireCommand = builder.desFireCommand;
    desFireResponse = builder.desFireResponse;
    removeApplicationRequest = builder.removeApplicationRequest;
    removeApplicationResponse = builder.removeApplicationResponse;
    updateApplicationRequest = builder.updateApplicationRequest;
    updateApplicationResponse = builder.updateApplicationResponse;
  }

  public static RemoteMessageDto newInstanceWithSuccess(final DesFireCommandDto desFireCommand) {
    final StatusCodeDto statusCode = StatusCodeDto.success();
    return builder().type(DES_FIRE_COMMAND).statusCode(statusCode).desFireCommand(desFireCommand).build();
  }

  public static RemoteMessageDto newInstance(final DesFireResponseDto desFireResponseDto) {
    return builder().type(DES_FIRE_RESPONSE).desFireResponse(desFireResponseDto).build();
  }

  public static RemoteMessageDto newInstance(final UpdateApplicationRequestDto updateApplicationRequestDto) {
    return builder().type(UPDATE_APPLICATION_REQUEST).updateApplicationRequest(updateApplicationRequestDto).build();
  }

  public static RemoteMessageDto newInstance(final RemoveApplicationRequestDto removeApplicationRequestDto) {
    return builder().type(REMOVE_APPLICATION_REQUEST).removeApplicationRequest(removeApplicationRequestDto).build();
  }

  public static RemoteMessageDto newInstanceWithSuccess(final RemoveApplicationResponseDto removeApplicationResponseDto) {
    final StatusCodeDto statusCode = StatusCodeDto.success();
    return builder().type(REMOVE_APPLICATION_RESPONSE).statusCode(statusCode).removeApplicationResponse(removeApplicationResponseDto)
        .build();
  }

  public static RemoteMessageDto newInstanceWithError(final RemoveApplicationResponseDto removeApplicationResponseDto, final
  ErrorCodeEnum errorCode) {
    final StatusCodeDto statusCode = StatusCodeDto.error(errorCode);
    return builder().type(REMOVE_APPLICATION_RESPONSE).statusCode(statusCode).removeApplicationResponse(removeApplicationResponseDto)
        .build();
  }

  public static RemoteMessageDto newInstanceWithSuccess(final UpdateApplicationResponseDto updateApplicationResponseDto) {
    final StatusCodeDto statusCode = StatusCodeDto.success();
    return builder().type(UPDATE_APPLICATION_RESPONSE).statusCode(statusCode).updateApplicationResponse(updateApplicationResponseDto)
        .build();
  }

  public static RemoteMessageDto newInstanceWithError(final UpdateApplicationResponseDto updateApplicationResponseDto, final
  ErrorCodeEnum errorCode) {
    final StatusCodeDto statusCode = StatusCodeDto.error(errorCode);
    return builder().type(UPDATE_APPLICATION_RESPONSE).statusCode(statusCode).updateApplicationResponse(updateApplicationResponseDto)
        .build();
  }

  public TypeEnum getType() {
    return type;
  }

  public StatusCodeDto getStatusCode() {
    return statusCode;
  }

  public boolean hasStatusCode() {
    return statusCode != null;
  }

  public boolean hasDesFireCommand() {
    return (desFireCommand != null);
  }

  public DesFireCommandDto getDesFireCommand() {
    return desFireCommand;
  }

  public boolean hasDesFireResponse() {
    return (desFireResponse != null);
  }

  public DesFireResponseDto getDesFireResponse() {
    return desFireResponse;
  }

  public boolean hasRemoveApplicationRequest() {
    return (removeApplicationRequest != null);
  }

  public RemoveApplicationRequestDto getRemoveApplicationRequest() {
    return removeApplicationRequest;
  }

  public boolean hasRemoveApplicationResponse() {
    return (removeApplicationResponse != null);
  }

  public RemoveApplicationResponseDto getRemoveApplicationResponse() {
    return removeApplicationResponse;
  }

  public boolean hasUpdateApplicationRequest() {
    return (updateApplicationRequest != null);
  }

  public UpdateApplicationRequestDto getUpdateApplicationRequest() {
    return updateApplicationRequest;
  }

  public boolean hasUpdateApplicationResponse() {
    return (updateApplicationResponse != null);
  }

  public UpdateApplicationResponseDto getUpdateApplicationResponse() {
    return updateApplicationResponse;
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("RemoteMessageDto{");
    sb.append("type=").append(type);

    appendIfNotNull(sb, statusCode, "statusCode");
    appendIfNotNull(sb, desFireCommand, "desFireCommand");
    appendIfNotNull(sb, desFireResponse, "desFireResponse");
    appendIfNotNull(sb, removeApplicationRequest, "removeApplicationRequest");
    appendIfNotNull(sb, removeApplicationResponse, "removeApplicationResponse");
    appendIfNotNull(sb, removeApplicationResponse, "removeApplicationResponse");
    appendIfNotNull(sb, updateApplicationRequest, "updateApplicationRequest");
    appendIfNotNull(sb, updateApplicationResponse, "updateApplicationResponse");

    return sb.toString();
  }

  private void appendIfNotNull(final StringBuilder sb, final Object dto, final String label) {
    if (dto != null) {
      sb.append(", ");
      sb.append(label);
      sb.append('=');
      sb.append(dto);
    }
  }

  public enum TypeEnum {
    DES_FIRE_COMMAND,
    DES_FIRE_RESPONSE,
    UPDATE_APPLICATION_REQUEST,
    UPDATE_APPLICATION_RESPONSE,
    REMOVE_APPLICATION_REQUEST,
    REMOVE_APPLICATION_RESPONSE,

  }


  public static final class Builder {
    private TypeEnum type;
    private StatusCodeDto statusCode;
    private DesFireCommandDto desFireCommand;
    private DesFireResponseDto desFireResponse;
    private RemoveApplicationRequestDto removeApplicationRequest;
    private RemoveApplicationResponseDto removeApplicationResponse;
    private UpdateApplicationRequestDto updateApplicationRequest;
    private UpdateApplicationResponseDto updateApplicationResponse;

    private Builder() {
      // empty
    }

    public Builder type(final TypeEnum val) {
      type = val;
      return this;
    }

    public Builder statusCode(final StatusCodeDto val) {
      statusCode = val;
      return this;
    }

    public Builder desFireCommand(final DesFireCommandDto val) {
      desFireCommand = val;
      return this;
    }

    public Builder desFireResponse(final DesFireResponseDto val) {
      desFireResponse = val;
      return this;
    }

    public Builder removeApplicationRequest(final RemoveApplicationRequestDto val) {
      removeApplicationRequest = val;
      return this;
    }

    public Builder removeApplicationResponse(final RemoveApplicationResponseDto val) {
      removeApplicationResponse = val;
      return this;
    }

    public Builder updateApplicationRequest(final UpdateApplicationRequestDto val) {
      updateApplicationRequest = val;
      return this;
    }

    public Builder updateApplicationResponse(final UpdateApplicationResponseDto val) {
      updateApplicationResponse = val;
      return this;
    }

    public RemoteMessageDto build() {
      return new RemoteMessageDto(this);
    }
  }
}
