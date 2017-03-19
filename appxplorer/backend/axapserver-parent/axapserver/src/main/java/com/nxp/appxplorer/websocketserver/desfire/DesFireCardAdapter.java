package com.nxp.trustid.websocketserver.desfire;

import static com.nxp.trustid.communication.util.Utils.getByteArray;
import static com.nxp.trustid.communication.util.Utils.getHexString;
import static com.nxp.trustid.communication.util.Utils.hasApduStatusWord;
import static com.nxp.trustid.model.util.AidUtils.asHexString;
import static com.nxp.trustid.websocketserver.desfire.ApduMagicWrapper.KEY_NUMBER_APP_DAM_DEFAULT_KEY;
import static com.nxp.trustid.websocketserver.desfire.ApduMagicWrapper.KEY_NUMBER_SECRET_AUTH_KEY;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nxp.trustid.model.desfire.ApduHandler;
import com.nxp.trustid.model.desfire.CommunicationTypeEnum;
import com.nxp.trustid.model.desfire.DesFireCard;
import com.nxp.trustid.model.desfire.FailedDesFireCommandException;
import com.nxp.trustid.model.desfire.MISmartKeyReference;
import com.nxp.trustid.model.desfire.UnsupportedCardException;
import com.nxp.trustid.model.desfire.param.ChangeKeyParam;
import com.nxp.trustid.model.desfire.param.CreateStdDataFileParam;
import com.nxp.nfclib.KeyType;
import com.nxp.nfclib.defaultimpl.KeyData;
import com.nxp.nfclib.desfire.DESFireFile;
import com.nxp.nfclib.desfire.IDESFireEV1.CommunicationType;
import com.nxp.nfclib.desfire.IDESFireEV2;
import com.nxp.nfclib.exceptions.NxpNfcLibException;
import com.nxp.nfclib.interfaces.IKeyData;

/**
 * Adapter between ApduMagic's {@code IDESFireEV2} and {@link DesFireCard}.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class DesFireCardAdapter implements DesFireCard {

  private static final Logger LOGGER = LoggerFactory.getLogger(DesFireCardAdapter.class);

  private static final String ISO_AID_MIFARE_DESFIRE = "D2760000850100";
  private static final byte[] ISO_SELECT_MIFARE_DESFIRE = getByteArray("00A40400" + String.format("%02x", ISO_AID_MIFARE_DESFIRE.length()
      / 2) + ISO_AID_MIFARE_DESFIRE + "00");
  private static final byte[] ISO_STATUS_WORD_NO_ERROR = {(byte) 0x90, 0x00};

  private final IDESFireEV2 desFireEV2;
  private final ApduHandler apduHandler;
  private final int cardKeyNo;
  private final byte cardKeyVersion;
  private final boolean doDiversification;
  private final byte[] diversificationInput;
  
  public static Map<String, MISmartKeyReference> keys = new HashMap<>();

  private DesFireCardAdapter(final Builder builder) {
    desFireEV2 = builder.desFireEV2;
    apduHandler = builder.apduHandler;
    cardKeyNo = builder.cardKeyNo;
    cardKeyVersion = builder.cardKeyVersion;
    doDiversification = builder.doDiversification;
    diversificationInput = builder.diversificationInput;
  }

  @Override
  public void isoSelect() {
    LOGGER.debug("isoSelect");

    final byte[] responseApdu;
    final int length;

    try {
      responseApdu = apduHandler.apduExchange(ISO_SELECT_MIFARE_DESFIRE);
      length = responseApdu.length;
    } catch (final Exception e) {
      throw new UnsupportedCardException("ISO Select failed: " + e.getMessage());
    }

    if (length < 2) {
      throw new UnsupportedCardException("response APDU too short: " + getHexString(responseApdu));
    }

    if (!hasApduStatusWord(responseApdu, ISO_STATUS_WORD_NO_ERROR)) {
      throw new UnsupportedCardException("invalid response APDU: " + getHexString(responseApdu));
    }
  }

  @Override
  public void selectApplication(final int aid) {
    try {
      desFireEV2.selectApplication(aid);
    } catch (final NxpNfcLibException e) {
      throw new FailedDesFireCommandException("select application", e);
    }
  }

  @Override
  public void authenticateWithAppDamDefaultKey() {
    LOGGER.debug("authenticate with app dam default key");
//    authenticate(KEY_NUMBER_APP_DAM_DEFAULT_KEY);
    authenticateDefaultKey(KEY_NUMBER_APP_DAM_DEFAULT_KEY);
  }

  @Override
  public void authenticateWithSecretAuthKey() {
    LOGGER.debug("authenticate with app provider's secret key");
//    authenticate(KEY_NUMBER_SECRET_AUTH_KEY);
    authenticateNewSecretKey(KEY_NUMBER_SECRET_AUTH_KEY);
  }
  
  private void authenticateDefaultKey(final int keyNo) {
	    try {
//	      if (doDiversification) {
//	        desFireEV2.authenticate(AuthType.AES, keyNo, cardKeyVersion, cardKeyNo, DIV_OPTION_DESFIRE, diversificationInput);
//	      } else {
//	        desFireEV2.authenticate(AuthType.AES, keyNo, cardKeyVersion, cardKeyNo, (byte) 0, null);
//	      }
	    	desFireEV2.authenticate(keyNo, IDESFireEV2.AuthType.AES, KeyType.AES128, keys.get(Constants.MISMARTDEFAULTAPPKEY).getKeyData());
	    } catch (final NxpNfcLibException e) {
	      throw new FailedDesFireCommandException("authenticate", e);
	    }
	  }

  private void authenticateNewSecretKey(final int keyNo) {
    try {
//      if (doDiversification) {
//        desFireEV2.authenticate(AuthType.AES, keyNo, cardKeyVersion, cardKeyNo, DIV_OPTION_DESFIRE, diversificationInput);
//      } else {
//        desFireEV2.authenticate(AuthType.AES, keyNo, cardKeyVersion, cardKeyNo, (byte) 0, null);
//      }
    	desFireEV2.authenticate(keyNo, IDESFireEV2.AuthType.AES, KeyType.AES128, keys.get(Constants.MISMARTSECRETAPPKEY).getKeyData());
    } catch (final NxpNfcLibException e) {
      throw new FailedDesFireCommandException("authenticate", e);
    }
  }

  @Override
  public List<Integer> getApplicationIDs() {
    LOGGER.debug("getApplicationIDs");

    final int[] applicationIDs;

    try {
      applicationIDs = desFireEV2.getApplicationIDs();

      if (LOGGER.isDebugEnabled()) {
        LOGGER.debug("applicationIDs={}", asHexString(applicationIDs));
      }
    } catch (final NxpNfcLibException e) {
      throw new FailedDesFireCommandException("getApplicationIDs", e);
    }

    final List<Integer> result = new ArrayList<>(applicationIDs.length);
    Arrays.stream(applicationIDs).forEach(result::add);
    return result;
  }

  @Override
  public boolean isApplicationInstalled(final int aid) {
    LOGGER.debug("isApplicationInstalled({})", aid);
    final int[] applicationIDs;

    try {
      applicationIDs = desFireEV2.getApplicationIDs();
    } catch (final NxpNfcLibException e) {
      throw new FailedDesFireCommandException("getApplicationIDs", e);
    }

    LOGGER.debug("applicationIDs: {}", asHexString(applicationIDs));

    return Arrays.stream(applicationIDs).anyMatch(x -> (x == aid));
  }

  @Override
  public int getFreeMem() {
    LOGGER.debug("getFreeMem");

    try {
      return desFireEV2.getFreeMemory();
    } catch (final NxpNfcLibException e) {
      throw new FailedDesFireCommandException("getFreeMem", e);
    }
  }

  @Override
  public void deleteApplication(final int aid) {
    LOGGER.debug("deleteApplication, aid={}", asHexString(aid));

    try {
      desFireEV2.deleteApplication(aid);
    } catch (final NxpNfcLibException e) {
      throw new FailedDesFireCommandException("deleteApplication", e);
    }
  }

  @Override
  public void changeKey(final ChangeKeyParam changeKeyParam) {
    final int cardKeyNoForChangeKey = changeKeyParam.getCardKeyNo();
    final int oldKeyNo = changeKeyParam.getOldKeyNo();
    final byte oldKeyVersion = changeKeyParam.getOldKeyVersion();
    final int newKeyNumber = changeKeyParam.getNewKeyNumber();
    final byte newKeyVersion = changeKeyParam.getNewKeyVersion();
    final byte oldKeyDivOption = changeKeyParam.getOldKeyDivOption();
    final byte newKeyDivOption = changeKeyParam.getNewKeyDivOption();
    final byte[] divInput = changeKeyParam.getDivInput();

    LOGGER.debug("changeKey, params={}", changeKeyParam);

    try {
//      desFireEV2.changeKey(cardKeyNoForChangeKey, oldKeyNo, oldKeyVersion, newKeyNumber, newKeyVersion, AuthType.AES, oldKeyDivOption,
//          newKeyDivOption, divInput);
    	desFireEV2.changeKey(cardKeyNoForChangeKey, KeyType.AES128, keys.get(Constants.MISMARTDEFAULTAPPKEY).getKey(), keys.get(Constants.MISMARTSECRETAPPKEY).getKey(), (byte) 0);
    } catch (final NxpNfcLibException e) {
      throw new FailedDesFireCommandException("changeKey", e);
    }
  }

  @Override
  public void createStdDataFile(final CreateStdDataFileParam createStdDataFileParam) {
    final int fileNo = createStdDataFileParam.getFileNo();
    final byte[] isoFileID = createStdDataFileParam.getIsoFileID();
    final CommunicationTypeEnum communicationType = createStdDataFileParam.getCommunicationType();
    final int readAccess = createStdDataFileParam.getReadAccess();
    final int writeAccess = createStdDataFileParam.getWriteAccess();
    final int readWriteAccess = createStdDataFileParam.getReadWriteAccess();
    final int changeAccess = createStdDataFileParam.getChangeAccess();
    final int fileSize = createStdDataFileParam.getFileSize();

    final CommunicationType desFireEv1CommunicationType = mapCommunicationType(communicationType);
    final DESFireFile.StdDataFileSettings fileSettings = new DESFireFile.StdDataFileSettings(desFireEv1CommunicationType, (byte)readAccess,
    		(byte)writeAccess, (byte)readWriteAccess, (byte)changeAccess, fileSize);
    
    try {
      if (isoFileID == null) {
        desFireEV2.createFile(fileNo, fileSettings);
      } else {
        desFireEV2.createFile(fileNo, isoFileID, fileSettings);
      }
    } catch (final NxpNfcLibException e) {
      throw new FailedDesFireCommandException("createFile", e);
    }

  }

  @Override
  public void writeData(final int fileNo, final int offset, final byte[] data, final CommunicationTypeEnum communicationType) {
    final CommunicationType desFireEv1CommunicationType = mapCommunicationType(communicationType);

    try {
      desFireEV2.writeData(fileNo, offset, data, desFireEv1CommunicationType);
    } catch (final NxpNfcLibException e) {
      throw new FailedDesFireCommandException("writeData", e);
    }
  }

  @Override
  public void writeData(final int fileNo, final int offset, final byte[] data) {
    try {
      desFireEV2.writeData(fileNo, offset, data);
    } catch (final NxpNfcLibException e) {
      throw new FailedDesFireCommandException("writeData", e);
    }
  }

  @Override
  public byte[] readData(final int fileNo, final int offset, final int length) {
    try {
      return desFireEV2.readData(fileNo, offset, length);
    } catch (final NxpNfcLibException e) {
      throw new FailedDesFireCommandException("readData", e);
    }
  }

  @Override
  public byte[] readData(final int fileNo, final int offset, final int length, final CommunicationTypeEnum communicationType) {
    final CommunicationType desFireEv1CommunicationType = mapCommunicationType(communicationType);

    try {
//      return desFireEV2.readData(fileNo, offset, length, desFireEv1CommunicationType);
    	return desFireEV2.readData(fileNo, offset, length);
    } catch (final NxpNfcLibException e) {
      throw new FailedDesFireCommandException("readData", e);
    }
  }

  @Override
  public byte[] readData(final int fileNo, final int offset, final int length, final CommunicationTypeEnum communicationType, final int
      fileSize) {
    final CommunicationType desFireEv1CommunicationType = mapCommunicationType(communicationType);

    try {
      return desFireEV2.readData(fileNo, offset, length, desFireEv1CommunicationType, fileSize);
    } catch (final NxpNfcLibException e) {
      throw new FailedDesFireCommandException("readData", e);
    }
  }

  @Override
  public void changeKeyFromAppDamDefaultKeyToSecretAuthKey() {
    try {
//      desFireEV2.changeKey(cardKeyNo, KEY_NUMBER_APP_DAM_DEFAULT_KEY, (byte) 0x00, KEY_NUMBER_SECRET_AUTH_KEY, (byte) 0x00, AES,
//          DIV_OPTION_NODIVERSIFICATION, DIV_OPTION_NODIVERSIFICATION, null);
    	desFireEV2.changeKey(0, KeyType.AES128, keys.get(Constants.MISMARTDEFAULTAPPKEY).getKey(), keys.get(Constants.MISMARTSECRETAPPKEY).getKey(), (byte) 0);
    } catch (final NxpNfcLibException e) {
      throw new FailedDesFireCommandException("changeKey", e);
    }
  }

  @Override
  public byte[] getFileIDs() {
    try {
      return desFireEV2.getFileIDs();
    } catch (final NxpNfcLibException e) {
      throw new FailedDesFireCommandException("getFileIDs", e);
    }
  }

  private CommunicationType mapCommunicationType(final CommunicationTypeEnum communicationType) {
    switch (communicationType) {
      case ENCIPHERED:
        return CommunicationType.Enciphered;

      case MACED:
        return CommunicationType.MACed;

      case PLAIN:
        return CommunicationType.Plain;

      default:
        throw new IllegalStateException("unhandled communication type: " + communicationType);
    }
  }

  public static Builder builder(final IDESFireEV2 idesFireEV2, final ApduHandler apduHandler) {
    return new Builder(idesFireEV2, apduHandler);
  }

  public static final class Builder {
    private final IDESFireEV2 desFireEV2;
    private final ApduHandler apduHandler;
    private int cardKeyNo = 0;
    private byte cardKeyVersion = 0;
    private boolean doDiversification = false;
    private byte[] diversificationInput = null;

    private Builder(final IDESFireEV2 desFireEV2, final ApduHandler apduHandler) {
      this.desFireEV2 = desFireEV2;
      this.apduHandler = apduHandler;
    }

    public Builder cardKeyNo(final int val) {
      cardKeyNo = val;
      return this;
    }

    public Builder cardKeyVersion(final byte val) {
      cardKeyVersion = val;
      return this;
    }

    public Builder doDiversification(final boolean val) {
      doDiversification = val;
      return this;
    }

    public Builder diversificationInput(final byte[] val) {
      diversificationInput = val;
      return this;
    }

    public DesFireCardAdapter build() {
      return new DesFireCardAdapter(this);
    }
  }

	/* (non-Javadoc)
	 * @see com.nxp.trustid.model.desfire.DesFireCard#miSmartKeys(int, byte[], byte[], byte)
	 */
	@Override
	public Map<String, MISmartKeyReference> miSmartKeys(int cardKeyNo, byte[] appDefaultKey, byte[] appSecretKey, byte keyVersion) {
		initKey(Constants.MISMARTSECRETAPPKEY, cardKeyNo,keyVersion, appSecretKey);
		initKey(Constants.MISMARTDEFAULTAPPKEY, cardKeyNo,keyVersion, appDefaultKey);
		return keys;
	}


	/**
	 * @param miSmartKeys
	 * @param name
	 * @param keyNo
	 * @param keyVersion
	 * @param damAuthKeyRef
	 */
	private void initKey(String name, int keyNo , byte keyVersion, byte[] keyData) {
		Key key = new SecretKeySpec(keyData, "AES");
		KeyData tempKeyInfo = new KeyData();
		tempKeyInfo.setKey(key);
		IKeyData piccNewMasterKey = tempKeyInfo;
		MISmartKeyReference temp = MISmartKeyReference.builder().setKeyNo(keyNo).setKeyVersion(keyVersion).setKeyVersion(keyVersion).setKeyData(piccNewMasterKey).setKey(keyData).build();
		keys.put(name, temp);
	}

}
