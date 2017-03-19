package com.nxp.trustid.websocketserver;

import static com.nxp.trustid.communication.protobuf.CommunicationProtocol.StatusCode.ErrorCodeEnum.UNKNOWN_ERROR;
import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import com.google.inject.Inject;
import com.nxp.trustid.communication.protobuf.CommunicationProtocol.RemoteMessage;
import com.nxp.trustid.communication.protobuf.CommunicationProtocol.StatusCode;

/**
 * The WebSocket endpoint.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class WebSocketServer {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);
  private static final String CONNECTION_HANDLER_KEY = "CONNECTION_HANDLER";
  private static final String MDC_SESSION_ID = "sessionId";

  private final ConnectionHandlerFactory connectionHandlerFactory;

  @Inject
  WebSocketServer(final ConnectionHandlerFactory connectionHandlerFactory) {
    this.connectionHandlerFactory = connectionHandlerFactory;
  }

  @OnOpen
  public void onOpen(final Session session) throws IOException {
    final String sessionId = session.getId();

    enterSession(session.getId());

    LOGGER.info("Open connection");

    final ConnectionHandler connectionHandler = connectionHandlerFactory.newInstance(sessionId);
    session.getUserProperties().put(CONNECTION_HANDLER_KEY, connectionHandler);
  }

  @OnMessage
  public RemoteMessage onMessage(final RemoteMessage remoteMessage, final Session session) throws IOException {
    setSessionId(session.getId());

    try {
      return getConnectionHandler(session).handleMessage(remoteMessage);
    } catch (final Exception e) {
      LOGGER.error("request handling failed", e);

      final StatusCode statusCode = StatusCode.newBuilder().setSuccess(false).setErrorCode(UNKNOWN_ERROR).build();
      return RemoteMessage.newBuilder().setStatusCode(statusCode).build();
    }
  }

  @OnError
  public void onError(final Session session, final Throwable t) {
    setSessionId(session.getId());
    LOGGER.error("", t);

    // FIXME stop running thread
  }

  @OnClose
  public void onClose(final Session session) {
    final String sessionId = session.getId();
    setSessionId(sessionId);

    getConnectionHandler(session).close();
    LOGGER.info("Close connection");

    leaveSession(sessionId);
  }

  private ConnectionHandler getConnectionHandler(final Session session) {
    return (ConnectionHandler) session.getUserProperties().get(CONNECTION_HANDLER_KEY);
  }

  private void enterSession(final String sessionId) {
    setSessionId(sessionId);

    LOGGER.info("entered session with id {}", sessionId);
  }

  private void setSessionId(final String sessionId) {
    MDC.put(MDC_SESSION_ID, sessionId);
  }

  private void leaveSession(final String sessionId) {
    LOGGER.info("left session with id {}", sessionId);

    MDC.clear();
  }
}
