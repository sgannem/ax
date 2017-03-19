package com.nxp.trustid.model;

import static com.nxp.trustid.communication.util.Utils.getHexString;
import static com.nxp.trustid.model.desfire.CommunicationTypeEnum.PLAIN;
import static com.nxp.trustid.model.desfire.param.CreateStdDataFileParam.FREE_ACCESS;
import static com.nxp.trustid.model.util.AidUtils.asHexString;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.nxp.trustid.model.desfire.DesFireCard;
import com.nxp.trustid.model.desfire.param.CreateStdDataFileParam;
import com.nxp.trustid.model.util.Utils;

/**
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class DefaultUpdateApplicationCallback implements UpdateApplicationCallback {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUpdateApplicationCallback.class);
  private static final int FILE_SIZE = 3;
  private static final int RADIX_HEX = 16;

  private final byte[] isoFileIdOfStandardDataFile;
  private final int fileNumber;
  private final int cardKeyNo;

  @Inject
  public DefaultUpdateApplicationCallback(@Named("FILE_ISO_FILE_ID") final String isoFileIdOfStandardDataFileAsHexString, @Named
      ("FILE_NUMBER") final String fileNumberAsString, @Named("CARD_KEY_NO") final String cardKeyNoAsString) {
    this.isoFileIdOfStandardDataFile = isoFileIdFromHexString(isoFileIdOfStandardDataFileAsHexString);
    this.fileNumber = Integer.parseInt(fileNumberAsString);
    this.cardKeyNo = Integer.parseInt(cardKeyNoAsString);
  }

  private byte[] isoFileIdFromHexString(final String isoFileIdAsHexString) {
    if (isoFileIdAsHexString == null) {
      return null;
    }

    final String trimmedIsoFileIdAsHexString = isoFileIdAsHexString.trim();

    if (trimmedIsoFileIdAsHexString.isEmpty()) {
      return null;
    }

    if (!trimmedIsoFileIdAsHexString.startsWith("0x")) {
      throw new IllegalArgumentException("ISO file id must be a hex string  starting with '0x'");
    }

    if (trimmedIsoFileIdAsHexString.length() != 6) {
      throw new IllegalArgumentException("ISO file id must have a length of 6 characters: 0xHHHH");
    }

    final byte highByte = (byte) (Integer.parseInt(trimmedIsoFileIdAsHexString.substring(2, 4), RADIX_HEX) & 0xFF);
    final byte lowByte = (byte) (Integer.parseInt(trimmedIsoFileIdAsHexString.substring(4, 6), RADIX_HEX) & 0xFF);
    return new byte[]{highByte, lowByte};
  }

  @Override
  public void perform(final int aid, final byte[] uid, final DesFireCard desFireCard) {
    LOGGER.info("updated application, aid=0x{}, uid=0x{}", asHexString(aid), getHexString(uid));

//    if (fileDoesNotExist(desFireCard)) {
//      createFile(desFireCard);
//    }
//
//    int value = readValue(desFireCard);
//    value += 1;
//    writeValue(value, desFireCard);
  }

  private boolean fileDoesNotExist(final DesFireCard desFireCard) {
    final byte[] fileIDs = desFireCard.getFileIDs();
    LOGGER.debug("fileIDs=0x{}", Utils.getHexString(fileIDs));

    for (final byte fileID : fileIDs) {
      if (fileID == fileNumber) {
        LOGGER.debug("file already exist");
        return false;
      }
    }

    LOGGER.debug("file does not exist");

    return true;
  }

  private void createFile(final DesFireCard desFireCard) {
    final CreateStdDataFileParam stdDataFileParam = CreateStdDataFileParam.builder().
        fileNo(fileNumber).
        isoFileID(isoFileIdOfStandardDataFile).
        communicationType(PLAIN).
        readAccess(FREE_ACCESS).
        writeAccess(FREE_ACCESS).
        readWriteAccess(FREE_ACCESS).
        changeAccess(cardKeyNo). // authentication required using the key with the given number
        fileSize(FILE_SIZE).build();

    LOGGER.debug("try to create file, fileNo={}", fileNumber);
    desFireCard.createStdDataFile(stdDataFileParam);
    LOGGER.debug("created file successfully, fileNo={}", fileNumber);
  }

  private int readValue(final DesFireCard desFireCard) {
    LOGGER.debug("try to read file, fileNo={}", fileNumber);
    final byte[] data = desFireCard.readData(fileNumber, 0, FILE_SIZE);
    final int dataAsLong = (int) Utils.getLong(data);

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("read file successfully, fileNo={}, data=0x{}, dataAsLong={}", fileNumber, Utils.getHexString(data), dataAsLong);
    }

    return dataAsLong;
  }

  private void writeValue(final int value, final DesFireCard desFireCard) {
    LOGGER.debug("try to write file, fileNo={}", fileNumber);
    final byte[] data = Utils.getByteArray(value, FILE_SIZE);
    desFireCard.writeData(fileNumber, 0, data);
    LOGGER.debug("wrote file successfully, fileNo={}", fileNumber);
  }
}
