package com.nxp.trustid.model.requesthandler;


import com.nxp.trustid.communication.dto.message.RemoteMessageDto;
import com.nxp.trustid.model.desfire.ApduHandler;

/**
 * Handles application requests from the client.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public interface RequestHandler {

  RemoteMessageDto handle(ApduHandler apduHandler, RemoteMessageDto remoteMessage);

  RemoteMessageDto handleException(Exception exception);
}
