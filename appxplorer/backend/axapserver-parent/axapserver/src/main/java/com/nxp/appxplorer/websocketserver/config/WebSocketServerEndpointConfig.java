package com.nxp.trustid.websocketserver.config;

import static com.nxp.trustid.websocketserver.config.ApplicationConfiguration.ENDPOINT_URI;
import static java.util.Collections.singletonList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.websocket.Decoder;
import javax.websocket.Encoder;
import javax.websocket.Extension;
import javax.websocket.server.ServerEndpointConfig;
import com.google.inject.Injector;
import com.nxp.trustid.websocketserver.WebSocketServer;
import com.nxp.trustid.websocketserver.protobuf.RemoteMessageDecoder;
import com.nxp.trustid.websocketserver.protobuf.RemoteMessageEncoder;

/**
 * Defines the endpoint configuration for the endpoint provided by {@link WebSocketServer}.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class WebSocketServerEndpointConfig implements ServerEndpointConfig {

  private static final Class<?> ENDPOINT_CLASS = WebSocketServer.class;

  private final Configurator configurator;
  private final List<String> subProtocols;
  private final List<Extension> extensions;
  private final Map<String, Object> userProperties;
  private final List<Class<? extends Encoder>> encoders;
  private final List<Class<? extends Decoder>> decoders;

  public WebSocketServerEndpointConfig(final Injector injector) {
    this.configurator = new WebSocketServerEndPointConfigurator(injector);
    this.subProtocols = new ArrayList<>();
    this.extensions = new ArrayList<>();
    this.userProperties = new HashMap<String, Object>();
    this.encoders = singletonList(RemoteMessageEncoder.class);
    this.decoders = singletonList(RemoteMessageDecoder.class);
  }

  @Override
  public Class<?> getEndpointClass() {
    return ENDPOINT_CLASS;
  }

  @Override
  public String getPath() {
    return ENDPOINT_URI;
  }

  @Override
  public List<String> getSubprotocols() {
    return subProtocols;
  }

  @Override
  public List<Extension> getExtensions() {
    return extensions;
  }

  @Override
  public Configurator getConfigurator() {
    return configurator;
  }

  @Override
  public List<Class<? extends Encoder>> getEncoders() {
    return encoders;
  }

  @Override
  public List<Class<? extends Decoder>> getDecoders() {
    return decoders;
  }

  @Override
  public Map<String, Object> getUserProperties() {
    return userProperties;
  }
}
