package com.nxp.trustid.model;

import static com.nxp.trustid.communication.dto.message.RemoteMessageDto.TypeEnum.DES_FIRE_RESPONSE;
import static com.nxp.trustid.communication.dto.message.StatusCodeDto.ErrorCodeEnum.UNKNOWN_ERROR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.nxp.trustid.communication.dto.message.DesFireResponseDto;
import com.nxp.trustid.communication.dto.message.RemoteMessageDto;
import com.nxp.trustid.communication.dto.message.RemoteMessageDto.TypeEnum;
import com.nxp.trustid.communication.dto.message.RemoveApplicationResponseDto;
import com.nxp.trustid.communication.dto.message.UpdateApplicationResponseDto;
import com.nxp.trustid.model.factory.RequestHandlerFactory;
import com.nxp.trustid.model.factory.RequestHandlerRunnerFactory;
import com.nxp.trustid.model.requesthandler.RequestHandler;
import com.nxp.trustid.model.requesthandler.RequestHandlerRunner;

/**
 * Forwards messages to specific {@code RequestHandler}s.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class MessageHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandler.class);

  private final RequestHandlerFactory requestHandlerFactory;
  private final RequestHandlerRunner requestHandlerRunner;

  @Inject
  MessageHandler(final RequestHandlerFactory requestHandlerFactory, final RequestHandlerRunnerFactory requestHandlerRunnerFactory) {
    this.requestHandlerFactory = requestHandlerFactory;
    this.requestHandlerRunner = requestHandlerRunnerFactory.newInstance(this);
  }

  @SuppressWarnings({"squid:S1151", "squid:MethodCyclomaticComplexity"})
  public RemoteMessageDto handle(final RemoteMessageDto remoteMessageDto) {
    final TypeEnum remoteMessageDtoType = remoteMessageDto.getType();

    if (remoteMessageDtoType == DES_FIRE_RESPONSE) {
      if (requestHandlerRunner == null) {
        throw new IllegalStateException();
      }

      final DesFireResponseDto desFireResponseDto = remoteMessageDto.getDesFireResponse();
      requestHandlerRunner.handleDesFireResponse(desFireResponseDto);
    } else {
      final RequestHandler requestHandler;

      switch (remoteMessageDtoType) {
        case REMOVE_APPLICATION_REQUEST:
          if (!remoteMessageDto.hasRemoveApplicationRequest()) {
            return RemoteMessageDto.newInstanceWithError((RemoveApplicationResponseDto) null, UNKNOWN_ERROR);
          }

          requestHandler = requestHandlerFactory.newRemoveApplicationRequestHandler(this);
          break;

        case UPDATE_APPLICATION_REQUEST:
          if (!remoteMessageDto.hasUpdateApplicationRequest()) {
            return RemoteMessageDto.newInstanceWithError((UpdateApplicationResponseDto) null, UNKNOWN_ERROR);
          }

          requestHandler = requestHandlerFactory.newUpdateApplicationRequestHandler(this);
          break;

        default:
          throw new IllegalArgumentException("Unhandled remote message: " + remoteMessageDto);
      }

      requestHandlerRunner.run(requestHandler);
      requestHandlerRunner.handle(remoteMessageDto);
    }

    return requestHandlerRunner.receive();
  }

  public void close() {
    LOGGER.info("Close message handler");

    requestHandlerRunner.stop();
  }

}
