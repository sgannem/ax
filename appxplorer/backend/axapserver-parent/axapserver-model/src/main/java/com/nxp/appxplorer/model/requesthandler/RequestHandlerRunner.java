package com.nxp.trustid.model.requesthandler;


import com.nxp.trustid.communication.dto.message.DesFireCommandDto;
import com.nxp.trustid.communication.dto.message.DesFireResponseDto;
import com.nxp.trustid.communication.dto.message.RemoteMessageDto;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public interface RequestHandlerRunner {

  void run(RequestHandler requestHandler);

  RemoteMessageDto receive();

  void send(RemoteMessageDto remoteMessage);

  void sendDesFireCommand(DesFireCommandDto desFireCommand);

  void handle(RemoteMessageDto remoteMessage);

  void handleDesFireResponse(DesFireResponseDto desFireResponseDto);

  void stop();
}
