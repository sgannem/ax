package com.nxp.trustid.model.desfire.param;

import java.util.Arrays;

import com.nxp.trustid.model.desfire.DesFireCard;

/**
 * Encapsulates the parameters of {@link DesFireCard#changeKey(ChangeKeyParam)}.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class ChangeKeyParam {

  private final int cardKeyNo;
  private final int oldKeyNo;
  private final byte oldKeyVersion;
  private final int newKeyNumber;
  private final byte newKeyVersion;
  private final byte oldKeyDivOption;
  private final byte newKeyDivOption;
  private final byte[] divInput;

  private ChangeKeyParam(final Builder builder) {
    cardKeyNo = builder.cardKeyNo;
    oldKeyNo = builder.oldKeyNo;
    oldKeyVersion = builder.oldKeyVersion;
    newKeyNumber = builder.newKeyNumber;
    newKeyVersion = builder.newKeyVersion;
    oldKeyDivOption = builder.oldKeyDivOption;
    newKeyDivOption = builder.newKeyDivOption;
    divInput = builder.divInput;
  }

  public int getCardKeyNo() {
    return cardKeyNo;
  }

  public int getOldKeyNo() {
    return oldKeyNo;
  }

  public byte getOldKeyVersion() {
    return oldKeyVersion;
  }

  public int getNewKeyNumber() {
    return newKeyNumber;
  }

  public byte getNewKeyVersion() {
    return newKeyVersion;
  }

  public byte getOldKeyDivOption() {
    return oldKeyDivOption;
  }

  public byte getNewKeyDivOption() {
    return newKeyDivOption;
  }

  public byte[] getDivInput() {
    return divInput;
  }

  @Override
  public String toString() {
    return "ChangeKeyParam{" +
        "cardKeyNo=" + cardKeyNo +
        ", oldKeyNo=" + oldKeyNo +
        ", oldKeyVersion=" + oldKeyVersion +
        ", newKeyNumber=" + newKeyNumber +
        ", newKeyVersion=" + newKeyVersion +
        ", oldKeyDivOption=" + oldKeyDivOption +
        ", newKeyDivOption=" + newKeyDivOption +
        ", divInput=" + Arrays.toString(divInput) +
        '}';
  }

  public static Builder builder() {
    return new Builder();
  }


  public static final class Builder {
    private int cardKeyNo;
    private boolean isCardKeyNoSet = false;

    private int oldKeyNo;
    private boolean isOldKeyNoSet = false;

    private byte oldKeyVersion;
    private boolean isOldKeyVersionSet = false;

    private int newKeyNumber;
    private boolean isNewKeyNumberSet = false;

    private byte newKeyVersion;
    private boolean isNewKeyVersionSet = false;

    private byte oldKeyDivOption;
    private boolean isOldKeyDivOptionSet = false;

    private byte newKeyDivOption;
    private boolean isNewKeyDivOptionSet = false;

    private byte[] divInput;
    private boolean isDivInputSet = false;

    private Builder() {
      // empty
    }

    public Builder cardKeyNo(final int val) {
      cardKeyNo = val;
      isCardKeyNoSet = true;
      return this;
    }

    public Builder oldKeyNo(final int val) {
      oldKeyNo = val;
      isOldKeyNoSet = true;
      return this;
    }

    public Builder oldKeyVersion(final byte val) {
      oldKeyVersion = val;
      isOldKeyVersionSet = true;
      return this;
    }

    public Builder newKeyNumber(final int val) {
      newKeyNumber = val;
      isNewKeyNumberSet = true;
      return this;
    }

    public Builder newKeyVersion(final byte val) {
      newKeyVersion = val;
      isNewKeyVersionSet = true;
      return this;
    }

    public Builder oldKeyDivOption(final byte val) {
      oldKeyDivOption = val;
      isOldKeyDivOptionSet = true;
      return this;
    }

    public Builder newKeyDivOption(final byte val) {
      newKeyDivOption = val;
      isNewKeyDivOptionSet = true;
      return this;
    }

    public Builder divInput(final byte[] val) {
      divInput = val;
      isDivInputSet = true;
      return this;
    }

    public ChangeKeyParam build() {
      final boolean notAllKeyNoParamsSet = !isCardKeyNoSet || !isOldKeyNoSet || !isNewKeyNumberSet;
      final boolean notAllKeyVersionsParamsSet = !isOldKeyVersionSet || !isNewKeyVersionSet;
      final boolean notAllDivParamsSet = !isOldKeyDivOptionSet || !isNewKeyDivOptionSet || !isDivInputSet;

      if (notAllKeyNoParamsSet || notAllKeyVersionsParamsSet || notAllDivParamsSet) {
        throw new IllegalStateException("not all parameters are set");
      }

      return new ChangeKeyParam(this);
    }
  }
}
