package com.nxp.trustid.model.desfire.param;

import java.util.Arrays;

import com.nxp.trustid.model.desfire.CommunicationTypeEnum;
import com.nxp.trustid.model.desfire.DesFireCard;

/**
 * Encapsulates the parameters of {@link DesFireCard#createStdDataFile(CreateStdDataFileParam)}.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class CreateStdDataFileParam {

  public static final byte FREE_ACCESS = 0x0E;

  private final int fileNo;
  private final byte[] isoFileID;
  private final CommunicationTypeEnum communicationType;
  private final int readAccess;
  private final int writeAccess;
  private final int readWriteAccess;
  private final int changeAccess;
  private final int fileSize;

  private CreateStdDataFileParam(final Builder builder) {
    fileNo = builder.fileNo;
    isoFileID = builder.isoFileID;
    communicationType = builder.communicationType;
    readAccess = builder.readAccess;
    writeAccess = builder.writeAccess;
    readWriteAccess = builder.readWriteAccess;
    changeAccess = builder.changeAccess;
    fileSize = builder.fileSize;
  }

  public int getFileNo() {
    return fileNo;
  }

  public byte[] getIsoFileID() {
    return isoFileID;
  }

  public CommunicationTypeEnum getCommunicationType() {
    return communicationType;
  }

  public int getReadAccess() {
    return readAccess;
  }

  public int getWriteAccess() {
    return writeAccess;
  }

  public int getReadWriteAccess() {
    return readWriteAccess;
  }

  public int getChangeAccess() {
    return changeAccess;
  }

  public int getFileSize() {
    return fileSize;
  }

  @Override
  public String toString() {
    return "CreateStdDataFileParam{" +
        "fileNo=" + fileNo +
        ", isoFileID=" + Arrays.toString(isoFileID) +
        ", communicationType=" + communicationType +
        ", readAccess=" + readAccess +
        ", writeAccess=" + writeAccess +
        ", readWriteAccess=" + readWriteAccess +
        ", changeAccess=" + changeAccess +
        ", fileSize=" + fileSize +
        '}';
  }

  public static Builder builder() {
    return new Builder();
  }


  public static final class Builder {
    private int fileNo;
    private boolean isFileNoSet = false;

    private byte[] isoFileID;

    private CommunicationTypeEnum communicationType;
    private boolean isCommunicationTypeSet = false;

    private int readAccess;
    private boolean isReadAccessSet = false;

    private int writeAccess;
    private boolean isWriteAccessSet = false;

    private int readWriteAccess;
    private boolean isReadWriteAccessSet = false;

    private int changeAccess;
    private boolean isChangeAccessSet = false;

    private int fileSize;
    private boolean isFileSizeSet = false;


    private Builder() {
      // empty
    }

    public Builder fileNo(final int val) {
      fileNo = val;
      isFileNoSet = true;
      return this;
    }

    public Builder isoFileID(final byte[] val) {
      isoFileID = val;
      return this;
    }

    public Builder communicationType(final CommunicationTypeEnum val) {
      communicationType = val;
      isCommunicationTypeSet = true;
      return this;
    }

    public Builder readAccess(final int val) {
      readAccess = val;
      isReadAccessSet = true;
      return this;
    }

    public Builder writeAccess(final int val) {
      writeAccess = val;
      isWriteAccessSet = true;
      return this;
    }

    public Builder readWriteAccess(final int val) {
      readWriteAccess = val;
      isReadWriteAccessSet = true;
      return this;
    }

    public Builder changeAccess(final int val) {
      changeAccess = val;
      isChangeAccessSet = true;
      return this;
    }

    public Builder fileSize(final int val) {
      fileSize = val;
      isFileSizeSet = true;
      return this;
    }

    public CreateStdDataFileParam build() {
      final boolean notAllAccessSet = !isReadAccessSet || !isReadWriteAccessSet || !isWriteAccessSet || !isChangeAccessSet;

      if (notAllAccessSet || !isFileNoSet || !isCommunicationTypeSet || !isFileSizeSet) {
        throw new IllegalStateException("not all parameters are set");
      }

      return new CreateStdDataFileParam(this);
    }
  }
}
