package com.nxp.trustid.communication.dto.message;

import static com.nxp.trustid.communication.util.Utils.aidAsHexString;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class UpdateApplicationResponseDto implements Message {

  final int aid;

  private UpdateApplicationResponseDto(final int aid) {
    this.aid = aid;
  }

  public static UpdateApplicationResponseDto newInstance(final int aid) {
    return new UpdateApplicationResponseDto(aid);
  }

  public int getAid() {
    return aid;
  }

  @Override
  public String toString() {
    return "UpdateApplicationResponseDto{" +
        "aid=0x" + aidAsHexString(aid) +
        "}";
  }
}
