package com.nxp.trustid.communication.dto.message;


import com.nxp.trustid.communication.util.Utils;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class DesFireResponseDto implements Message {

  private final byte[] response;

  private DesFireResponseDto(final byte[] response) {
    this.response = response;
  }

  public static DesFireResponseDto newInstance(final byte[] response) {
    return new DesFireResponseDto(response);
  }

  public byte[] getResponse() {
    return response;
  }

  @Override
  public String toString() {
    return "DesFireResponseDto{" +
        "response=0x" + Utils.getHexString(response) +
        '}';
  }
}
