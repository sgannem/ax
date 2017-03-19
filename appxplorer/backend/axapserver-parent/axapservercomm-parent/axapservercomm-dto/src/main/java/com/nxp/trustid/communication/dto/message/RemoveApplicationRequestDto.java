package com.nxp.trustid.communication.dto.message;

import com.nxp.trustid.communication.util.Utils;

/**
 * @author Alfred Kaltenecker (alfred.kaltenecker@rise-world.com)
 */
public class RemoveApplicationRequestDto implements Message {

  private final byte[] uid;

  private final int aid;

  private RemoveApplicationRequestDto(final byte[] uid, final int aid) {
    this.uid = uid;
    this.aid = aid;
  }

  public static RemoveApplicationRequestDto newInstance(final byte[] uid, final int aid) {
    return new RemoveApplicationRequestDto(uid, aid);
  }

  public byte[] getUid() {
    return uid;
  }

  public int getAid() {
    return aid;
  }

  @Override
  public String toString() {
    return "RemoveApplicationRequestDto{" +
        "uid=0x" + Utils.getHexString(uid) +
        ", aid=0x" + Utils.aidAsHexString(aid) +
        '}';
  }
}
