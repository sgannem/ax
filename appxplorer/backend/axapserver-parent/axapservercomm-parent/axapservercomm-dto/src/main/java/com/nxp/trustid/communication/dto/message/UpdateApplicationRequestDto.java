package com.nxp.trustid.communication.dto.message;

import static com.nxp.trustid.communication.util.Utils.aidAsHexString;
import static com.nxp.trustid.communication.util.Utils.getHexString;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class UpdateApplicationRequestDto implements Message {

  private final byte[] uid;
  private final int aid;

  private UpdateApplicationRequestDto(final byte[] uid, final int aid) {
    this.uid = uid;
    this.aid = aid;
  }

  public static UpdateApplicationRequestDto newInstance(final byte[] uid, final int aid) {
    return new UpdateApplicationRequestDto(uid, aid);
  }

  public byte[] getUid() {
    return uid;
  }

  public int getAid() {
    return aid;
  }

  @Override
  public String toString() {
    return "UpdateApplicationRequestDto{" +
        "uid=0x" + getHexString(uid) +
        ", aid=" + aidAsHexString(aid) +
        '}';
  }
}
