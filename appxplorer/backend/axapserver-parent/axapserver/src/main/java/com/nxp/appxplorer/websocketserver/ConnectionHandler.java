package com.nxp.trustid.websocketserver;

import static com.nxp.trustid.communication.protobuf.converter.DtoConverter.toDto;
import static com.nxp.trustid.communication.protobuf.converter.ProtobufConverter.toProtobuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.nxp.trustid.communication.dto.message.RemoteMessageDto;
import com.nxp.trustid.communication.protobuf.CommunicationProtocol.RemoteMessage;
import com.nxp.trustid.model.MessageHandler;
import com.nxp.trustid.model.factory.MessageHandlerFactory;

/**
 * Forwarding messages to a {@link MessageHandler} converting between protobuf objects and DTO objects.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class ConnectionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionHandler.class);

  private final MessageHandler messageHandler;

  @Inject
  ConnectionHandler(final MessageHandlerFactory messageHandlerFactory) {
    this.messageHandler = messageHandlerFactory.newInstance();
  }

  public RemoteMessage handleMessage(final RemoteMessage incomingRemoteMessage) {
    final RemoteMessageDto incomingRemoteMessageDto = toDto(incomingRemoteMessage);

    LOGGER.info("Handling message {}", incomingRemoteMessageDto);
    final RemoteMessageDto outgoingRemoteMessageDto = messageHandler.handle(incomingRemoteMessageDto);
    LOGGER.info("Returning message: {}", outgoingRemoteMessageDto);

    return toProtobuf(outgoingRemoteMessageDto);
  }

  public void close() {
    LOGGER.info("Close connection handler");

    messageHandler.close();
  }
}
