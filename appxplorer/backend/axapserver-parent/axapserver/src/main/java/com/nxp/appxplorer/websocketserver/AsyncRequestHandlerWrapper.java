package com.nxp.trustid.websocketserver;


import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import com.nxp.trustid.communication.dto.message.DesFireCommandDto;
import com.nxp.trustid.communication.dto.message.DesFireResponseDto;
import com.nxp.trustid.communication.dto.message.RemoteMessageDto;
import com.nxp.trustid.model.desfire.ApduHandler;
import com.nxp.trustid.model.requesthandler.RequestHandler;
import com.nxp.trustid.model.requesthandler.StopMessage;

/**
 * A {@link Runnable} that builds the bridge between a {@link RequestHandler} and an {@link ApduHandler}. It handles APDUs to and from the
 * {@link RequestHandler} in an asynchronous way.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class AsyncRequestHandlerWrapper implements ApduHandler, Runnable {

  private static final Logger LOGGER = LoggerFactory.getLogger(AsyncRequestHandlerWrapper.class);

  private final AsyncRequestHandlerRunner asyncRequestHandlerRunner;
  private final RequestHandler requestHandler;
  private final BlockingQueue<RemoteMessageDto> queue;
  private final BlockingQueue<DesFireResponseDto> apduQueue;

  private final Map<String, String> mdcContextMap;

  private AsyncRequestHandlerWrapper(final AsyncRequestHandlerRunner asyncRequestHandlerRunner, final RequestHandler requestHandler,
                                     final Map<String, String> mdcContextMap) {
    this.asyncRequestHandlerRunner = asyncRequestHandlerRunner;
    this.requestHandler = requestHandler;
    this.queue = new ArrayBlockingQueue<>(1);
    this.apduQueue = new ArrayBlockingQueue<>(1);

    this.mdcContextMap = mdcContextMap;
  }

  public static AsyncRequestHandlerWrapper newInstance(final AsyncRequestHandlerRunner asyncRequestHandlerRunner, final RequestHandler
      requestHandler, final Map<String, String> mdcContextMap) {
    return new AsyncRequestHandlerWrapper(asyncRequestHandlerRunner, requestHandler, mdcContextMap);
  }

  public void handle(final RemoteMessageDto remoteMessage) {
    LOGGER.debug("handle {}", remoteMessage);

    try {
      queue.put(remoteMessage);
    } catch (final InterruptedException e) {
      throw new AsyncRequestHandlingInterruptedException("Handling of message was interrupted: " + remoteMessage, e);
    }
  }

  public void handleDesFireResponse(final DesFireResponseDto desFireResponseDto) {
    LOGGER.debug("sendDesFireCommand {}", desFireResponseDto);

    try {
      apduQueue.put(desFireResponseDto);
    } catch (final InterruptedException e) {
      LOGGER.error("Handling of {} by {} was interrupted", DesFireResponseDto.class.getSimpleName(), AsyncRequestHandlerWrapper.class
          .getSimpleName(), e);
      throw new AsyncRequestHandlingInterruptedException("Handling of " + desFireResponseDto.toString() + " by was interrupted", e);
    }
  }

  @Override
  public void run() {
    final Map<String, String> previousMdcContextMap = propagateThreadLocalsToCurrentThread();

    try {
      boolean stopThread = false;

      while (!stopThread) {
        final RemoteMessageDto message = queue.take();

        if (StopMessage.INSTANCE.equals(message)) {
          LOGGER.debug("stop message received");
          stopThread = true;
          continue;
        }

        final RemoteMessageDto outgoingMessage = handleMessage(message);

        asyncRequestHandlerRunner.send(outgoingMessage);
      }
    } catch (final Exception e) {
      final String errorMessage = "request handler stopped due to an error";

      // Since we are on a worker thread, we log the error AND throw an exception. Otherwise the stacktrace is lost.
      LOGGER.error(errorMessage, e);
      throw new AsyncRequestHandlingInterruptedException(errorMessage, e);
    } finally {
      if (previousMdcContextMap == null) {
        MDC.clear();
      } else {
        MDC.setContextMap(previousMdcContextMap);
      }
    }

    LOGGER.debug("request handler stopped");
  }

  private RemoteMessageDto handleMessage(final RemoteMessageDto message) {
    RemoteMessageDto outgoingMessage;

    try {
      outgoingMessage = requestHandler.handle(this, message);
    } catch (final Exception e) {
      LOGGER.error("request handling failed", e);
      outgoingMessage = requestHandler.handleException(e);
    }

    return outgoingMessage;
  }

  @Override
  public byte[] apduExchange(final byte[] output) {
    final DesFireCommandDto desFireCommandDto = DesFireCommandDto.newInstance(output);

    try {
      asyncRequestHandlerRunner.sendDesFireCommand(desFireCommandDto);
      final DesFireResponseDto desFireResponseDto = apduQueue.take();
      return desFireResponseDto.getResponse();
    } catch (final InterruptedException e) {
      throw new AsyncRequestHandlingInterruptedException("request handler was interrupted", e);
    }
  }

  protected Map<String, String> propagateThreadLocalsToCurrentThread() {
    final Map<String, String> previousMdcContextMap = MDC.getCopyOfContextMap();
    MDC.setContextMap(mdcContextMap);

    return previousMdcContextMap;
  }
}
