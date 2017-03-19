package com.nxp.trustid.communication.dto.message;

import com.nxp.trustid.communication.util.Utils;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class DesFireCommandDto {

  private final byte[] command;

  private DesFireCommandDto(final byte[] command) {
    this.command = command;
  }

  public static DesFireCommandDto newInstance(final byte[] command) {
    return new DesFireCommandDto(command);
  }

  public byte[] getCommand() {
    return command;
  }

  @Override
  public String toString() {
    return "DesFireCommandDto{" +
        "command=0x" + Utils.getHexString(command) +
        '}';
  }
}
