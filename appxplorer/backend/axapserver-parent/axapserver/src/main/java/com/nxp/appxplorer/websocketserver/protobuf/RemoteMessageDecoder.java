package com.nxp.trustid.websocketserver.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nxp.trustid.communication.protobuf.CommunicationProtocol.RemoteMessage;

/**
 * Decodes binary data to protobuf objects.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class RemoteMessageDecoder implements Decoder.BinaryStream<RemoteMessage> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RemoteMessageDecoder.class);

  @Override
  public RemoteMessage decode(final InputStream inputStream) throws DecodeException {
    try {
      return RemoteMessage.parseDelimitedFrom(inputStream);
    } catch (final IOException e) {
      LOGGER.error("Failed to decode binary message", e);
      throw new DecodeException((ByteBuffer) null, "Failed to decode binary message", e);
    }
  }

  @Override
  public void init(final EndpointConfig endpointConfig) {
    // does nothing
  }

  @Override
  public void destroy() {
    // does nothing
  }
}
