package com.nxp.trustid.communication.protobuf.converter;

import static com.nxp.trustid.communication.protobuf.converter.EnumMapper.mapEnum;
import com.nxp.trustid.communication.dto.message.DesFireCommandDto;
import com.nxp.trustid.communication.dto.message.DesFireResponseDto;
import com.nxp.trustid.communication.dto.message.RemoteMessageDto;
import com.nxp.trustid.communication.dto.message.RemoveApplicationRequestDto;
import com.nxp.trustid.communication.dto.message.RemoveApplicationResponseDto;
import com.nxp.trustid.communication.dto.message.StatusCodeDto;
import com.nxp.trustid.communication.dto.message.UpdateApplicationRequestDto;
import com.nxp.trustid.communication.dto.message.UpdateApplicationResponseDto;
import com.nxp.trustid.communication.protobuf.CommunicationProtocol.DesFireCommand;
import com.nxp.trustid.communication.protobuf.CommunicationProtocol.DesFireResponse;
import com.nxp.trustid.communication.protobuf.CommunicationProtocol.RemoteMessage;
import com.nxp.trustid.communication.protobuf.CommunicationProtocol.RemoveApplicationRequest;
import com.nxp.trustid.communication.protobuf.CommunicationProtocol.RemoveApplicationResponse;
import com.nxp.trustid.communication.protobuf.CommunicationProtocol.StatusCode;
import com.nxp.trustid.communication.protobuf.CommunicationProtocol.UpdateApplicationRequest;
import com.nxp.trustid.communication.protobuf.CommunicationProtocol.UpdateApplicationResponse;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
@SuppressWarnings({"OverlyCoupledClass", "WeakerAccess"})
public class DtoConverter {

  private DtoConverter() {
    // empty
  }

  public static RemoteMessageDto toDto(final RemoteMessage remoteMessage) {
    final RemoteMessageDto.TypeEnum type = mapEnum(remoteMessage.getType(), RemoteMessageDto.TypeEnum.class);
    final StatusCodeDto statusCodeDto = remoteMessage.hasStatusCode() ? toDto(remoteMessage.getStatusCode()) : null;
    final DesFireCommandDto desFireCommandDto = remoteMessage.hasDesFireCommand() ? toDto(remoteMessage.getDesFireCommand()) : null;
    final DesFireResponseDto desFireResponseDto = remoteMessage.hasDesFireResponse() ? toDto(remoteMessage.getDesFireResponse()) : null;

    final RemoveApplicationRequestDto removeApplicationRequestDto = remoteMessage.hasRemoveApplicationRequest() ? toDto(remoteMessage
        .getRemoveApplicationRequest()) : null;

    final RemoveApplicationResponseDto removeApplicationResponseDto = remoteMessage.hasRemoveApplicationResponse() ? toDto(remoteMessage
        .getRemoveApplicationResponse()) : null;

    final UpdateApplicationRequestDto updateApplicationRequestDto = remoteMessage.hasUpdateApplicationRequest() ? toDto(remoteMessage
        .getUpdateApplicationRequest()) : null;

    final UpdateApplicationResponseDto updateApplicationResponseDto = remoteMessage.hasUpdateApplicationResponse() ? toDto(remoteMessage
        .getUpdateApplicationResponse()) : null;

    return RemoteMessageDto.builder().
        type(type).
        statusCode(statusCodeDto).
        desFireCommand(desFireCommandDto).
        desFireResponse(desFireResponseDto).
        removeApplicationRequest(removeApplicationRequestDto).
        removeApplicationResponse(removeApplicationResponseDto).
        updateApplicationRequest(updateApplicationRequestDto).
        updateApplicationResponse(updateApplicationResponseDto).
        build();
  }

  public static StatusCodeDto toDto(final StatusCode statusCode) {
    final StatusCodeDto.ErrorCodeEnum errorCode;

    if (statusCode.hasErrorCode()) {
      errorCode = mapEnum(statusCode.getErrorCode(), StatusCodeDto.ErrorCodeEnum.class);
    } else {
      errorCode = null;
    }

    return StatusCodeDto.newInstance(statusCode.getSuccess(), errorCode);
  }

  public static DesFireCommandDto toDto(final DesFireCommand desFireCommand) {
    return DesFireCommandDto.newInstance(desFireCommand.getCommand().toByteArray());
  }

  public static DesFireResponseDto toDto(final DesFireResponse desFireResponse) {
    return DesFireResponseDto.newInstance(desFireResponse.getResponse().toByteArray());
  }

  public static RemoveApplicationRequestDto toDto(final RemoveApplicationRequest removeApplicationRequest) {
    return RemoveApplicationRequestDto.newInstance(removeApplicationRequest.getUid().toByteArray(), removeApplicationRequest.getAid());
  }

  public static RemoveApplicationResponseDto toDto(final RemoveApplicationResponse removeApplicationResponse) {
    return RemoveApplicationResponseDto.newInstance(removeApplicationResponse.getAid());
  }

  public static UpdateApplicationRequestDto toDto(final UpdateApplicationRequest updateApplicationRequest) {
    return UpdateApplicationRequestDto.newInstance(updateApplicationRequest.getUid().toByteArray(), updateApplicationRequest.getAid());
  }

  public static UpdateApplicationResponseDto toDto(final UpdateApplicationResponse updateApplicationResponse) {
    return UpdateApplicationResponseDto.newInstance(updateApplicationResponse.getAid());
  }
}
