package com.nxp.trustid.model.requesthandler;

import com.nxp.trustid.communication.dto.message.RemoteMessageDto;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public final class StopMessage extends RemoteMessageDto {

  public static final StopMessage INSTANCE = new StopMessage();

  @Override
  public String toString() {
    return "StopMessage{}";
  }
}
