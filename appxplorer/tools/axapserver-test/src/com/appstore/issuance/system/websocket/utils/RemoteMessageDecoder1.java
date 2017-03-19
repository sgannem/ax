package com.appstore.issuance.system.websocket.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol;


/**
 * Decodes binary data to protobuf objects.
 *
 */
public class RemoteMessageDecoder1 implements Decoder.BinaryStream<CommunicationProtocol.RemoteMessage> {


  public CommunicationProtocol.RemoteMessage decode(final InputStream inputStream) throws DecodeException {
    try {
      return CommunicationProtocol.RemoteMessage.parseDelimitedFrom(inputStream);
    } catch (final IOException e) {
      System.out.println("Failed to decode binary message"+ e);
      throw new DecodeException((ByteBuffer) null, "Failed to decode binary message", e);
    }
  }

  public void init(final EndpointConfig endpointConfig) {
    // does nothing
  }

  public void destroy() {
    // does nothing
  }
}
