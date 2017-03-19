package com.nxp.trustid.communication.protobuf.converter;

import static com.nxp.trustid.communication.protobuf.converter.EnumMapper.mapEnum;
import com.google.protobuf.ByteString;
import com.google.protobuf.Message;
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
public class ProtobufConverter {

  private ProtobufConverter() {
    // empty
  }

  public static RemoteMessage toProtobuf(final RemoteMessageDto remoteMessageDto) {
    final RemoteMessage.Builder remoteMessage = RemoteMessage.newBuilder();
    final RemoteMessage.TypeEnum type = mapEnum(remoteMessageDto.getType(), RemoteMessage.TypeEnum.class);
    remoteMessage.setType(type);

    if (remoteMessageDto.hasStatusCode()) {
      remoteMessage.setStatusCode(toProtobuf(remoteMessageDto.getStatusCode()));
    }

    if (remoteMessageDto.hasDesFireCommand()) {
      remoteMessage.setDesFireCommand(toProtobuf(remoteMessageDto.getDesFireCommand()));
    }

    if (remoteMessageDto.hasDesFireResponse()) {
      remoteMessage.setDesFireResponse(toProtobuf(remoteMessageDto.getDesFireResponse()));
    }

    if (remoteMessageDto.hasRemoveApplicationRequest()) {
      remoteMessage.setRemoveApplicationRequest(toProtobuf(remoteMessageDto.getRemoveApplicationRequest()));
    }

    if (remoteMessageDto.hasRemoveApplicationResponse()) {
      remoteMessage.setRemoveApplicationResponse(toProtobuf(remoteMessageDto.getRemoveApplicationResponse()));
    }

    if (remoteMessageDto.hasUpdateApplicationRequest()) {
      remoteMessage.setUpdateApplicationRequest(toProtobuf(remoteMessageDto.getUpdateApplicationRequest()));
    }

    if (remoteMessageDto.hasUpdateApplicationResponse()) {
      remoteMessage.setUpdateApplicationResponse(toProtobuf(remoteMessageDto.getUpdateApplicationResponse()));
    }

    return remoteMessage.build();
  }

  public static RemoteMessage toProtobuf(final Message message) {
    final RemoteMessage.Builder remoteMessage = RemoteMessage.newBuilder();
    final StatusCode statusCode = StatusCode.newBuilder().setSuccess(true).build();
    remoteMessage.setStatusCode(statusCode);

    //noinspection ChainOfInstanceofChecks
    if (message instanceof DesFireResponseDto) {
      remoteMessage.setType(RemoteMessage.TypeEnum.DES_FIRE_RESPONSE);
      remoteMessage.setDesFireResponse(toProtobuf((DesFireResponseDto) message));
      throw new IllegalArgumentException("Unhandled RemoteMessageDto: " + message);
    }

    return remoteMessage.build();
  }

  public static StatusCode toProtobuf(final StatusCodeDto statusCodeDto) {
    final StatusCode.Builder statusCode = StatusCode.newBuilder();
    statusCode.setSuccess(statusCodeDto.isSuccess());
    final StatusCodeDto.ErrorCodeEnum errorCodeInDto = statusCodeDto.getErrorCode();

    if (errorCodeInDto != null) {
      final StatusCode.ErrorCodeEnum errorCode = mapEnum(errorCodeInDto, StatusCode.ErrorCodeEnum.class);
      statusCode.setErrorCode(errorCode);
    }

    return statusCode.build();
  }

  public static DesFireCommand toProtobuf(final DesFireCommandDto desFireCommandDto) {
    final DesFireCommand.Builder desFireCommand = DesFireCommand.newBuilder();
    desFireCommand.setCommand(ByteString.copyFrom(desFireCommandDto.getCommand()));
    return desFireCommand.build();
  }

  public static DesFireResponse toProtobuf(final DesFireResponseDto desFireResponseDto) {
    final DesFireResponse.Builder desFireResponse = DesFireResponse.newBuilder();
    desFireResponse.setResponse(ByteString.copyFrom(desFireResponseDto.getResponse()));
    return desFireResponse.build();
  }

  public static RemoveApplicationRequest toProtobuf(final RemoveApplicationRequestDto removeApplicationRequestDto) {
    final ByteString uid = ByteString.copyFrom(removeApplicationRequestDto.getUid());
    final int aid = removeApplicationRequestDto.getAid();

    return RemoveApplicationRequest.newBuilder().setUid(uid).setAid(aid).build();
  }

  public static RemoveApplicationResponse toProtobuf(final RemoveApplicationResponseDto removeApplicationResponseDto) {
    final int aid = removeApplicationResponseDto.getAid();
    return RemoveApplicationResponse.newBuilder().setAid(aid).build();
  }

  public static UpdateApplicationRequest toProtobuf(final UpdateApplicationRequestDto updateApplicationRequestDto) {
    final ByteString uid = ByteString.copyFrom(updateApplicationRequestDto.getUid());
    final int aid = updateApplicationRequestDto.getAid();

    return UpdateApplicationRequest.newBuilder().setUid(uid).setAid(aid).build();
  }

  public static UpdateApplicationResponse toProtobuf(final UpdateApplicationResponseDto updateApplicationResponseDto) {
    final int aid = updateApplicationResponseDto.getAid();
    return UpdateApplicationResponse.newBuilder().setAid(aid).build();
  }
}
