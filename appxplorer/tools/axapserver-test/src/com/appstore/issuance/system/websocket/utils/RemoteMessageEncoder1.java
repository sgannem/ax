package com.appstore.issuance.system.websocket.utils;

import java.io.IOException;
import java.io.OutputStream;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.nxp.appstore.monorail.communication.protobuf.CommunicationProtocol.RemoteMessage;

/**
 * Encodes protobuf objects to binary data.
 *
 */
public class RemoteMessageEncoder1 implements Encoder.BinaryStream<RemoteMessage> {

//  @Override
  public void encode(final RemoteMessage remoteMessage, final OutputStream outputStream) throws EncodeException, IOException {
    remoteMessage.writeDelimitedTo(outputStream);
  }

//  @Override
  public void init(final EndpointConfig endpointConfig) {
    // does nothing
  }

//  @Override
  public void destroy() {
    // does nothing
  }
}
