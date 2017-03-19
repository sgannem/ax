package com.nxp.trustid.websocketserver;


import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import com.google.inject.Inject;
import com.nxp.trustid.communication.dto.message.DesFireCommandDto;
import com.nxp.trustid.communication.dto.message.DesFireResponseDto;
import com.nxp.trustid.communication.dto.message.RemoteMessageDto;
import com.nxp.trustid.model.requesthandler.RequestHandler;
import com.nxp.trustid.model.requesthandler.RequestHandlerRunner;
import com.nxp.trustid.model.requesthandler.StopMessage;

/**
 * Runs a {@code RequestHandler} asynchronously.
 *
 * The {@code RequestHandler} is handed over to {@code AsyncRequestHandlerWrapper} which is then run on a separate thread.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class AsyncRequestHandlerRunner implements RequestHandlerRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(AsyncRequestHandlerRunner.class);

  private final BlockingQueue<RemoteMessageDto> queue;
  private final ExecutorService executorService;
  private AsyncRequestHandlerWrapper asyncRequestHandlerWrapper;

  @Inject
  AsyncRequestHandlerRunner() {
    this.queue = new ArrayBlockingQueue<>(1);
    this.executorService = Executors.newSingleThreadExecutor();
  }

  @Override
  public void run(final RequestHandler requestHandler) {
    final Map<String, String> mdcContextMap = MDC.getCopyOfContextMap();
    asyncRequestHandlerWrapper = AsyncRequestHandlerWrapper.newInstance(this, requestHandler, mdcContextMap);
    executorService.submit(asyncRequestHandlerWrapper);
  }

  @Override
  public RemoteMessageDto receive() {
    if (asyncRequestHandlerWrapper == null) {
      throw new IllegalStateException("call the method \"run\" first");
    }

    try {
      return queue.take();
    } catch (final InterruptedException e) {
      throw new AsyncRequestHandlingInterruptedException("Taking from queue interrupted", e);
    }
  }

  @Override
  public void handle(final RemoteMessageDto remoteMessage) {
    asyncRequestHandlerWrapper.handle(remoteMessage);
  }

  @Override
  public void send(final RemoteMessageDto remoteMessage) {
    try {
      queue.put(remoteMessage);
    } catch (final InterruptedException e) {
      throw new AsyncRequestHandlingInterruptedException("Putting on queue was interrupted", e);
    }
  }

  @Override
  public void sendDesFireCommand(final DesFireCommandDto desFireCommand) {
    final RemoteMessageDto remoteMessageDto = RemoteMessageDto.newInstanceWithSuccess(desFireCommand);

    try {
      queue.put(remoteMessageDto);
    } catch (final InterruptedException e) {
      throw new AsyncRequestHandlingInterruptedException("Putting on queue was interrupted", e);
    }
  }

  @Override
  public void handleDesFireResponse(final DesFireResponseDto desFireResponseDto) {
    asyncRequestHandlerWrapper.handleDesFireResponse(desFireResponseDto);
  }

  @Override
  public void stop() {
    executorService.shutdown();

    if (asyncRequestHandlerWrapper == null) {
      LOGGER.debug("asyncRequestHandlerWrapper is not set");
    } else {
      asyncRequestHandlerWrapper.handle(StopMessage.INSTANCE);
    }
  }
}
