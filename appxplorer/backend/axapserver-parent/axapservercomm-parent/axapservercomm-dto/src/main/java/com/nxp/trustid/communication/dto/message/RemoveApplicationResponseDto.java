package com.nxp.trustid.communication.dto.message;

import com.nxp.trustid.communication.util.Utils;

/**
 * @author Alfred Kaltenecker (alfred.kaltenecker@rise-world.com)
 */
public class RemoveApplicationResponseDto implements Message {

  final int aid;

  private RemoveApplicationResponseDto(final int aid) {
    this.aid = aid;
  }

  public static RemoveApplicationResponseDto newInstance(final int aid) {
    return new RemoveApplicationResponseDto(aid);
  }

  public int getAid() {
    return aid;
  }

  @Override
  public String toString() {
    return "RemoveApplicationResponseDto{" +
        "aid=0x" + Utils.aidAsHexString(aid) +
        "}";
  }
}
