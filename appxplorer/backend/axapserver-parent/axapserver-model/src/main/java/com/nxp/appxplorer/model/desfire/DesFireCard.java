package com.nxp.trustid.model.desfire;

import java.util.List;
import java.util.Map;

import com.nxp.trustid.model.desfire.param.ChangeKeyParam;
import com.nxp.trustid.model.desfire.param.CreateStdDataFileParam;

/**
 * Represents a card. (see {@code DesFireEv1CardAdapter})
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public interface DesFireCard {

  void isoSelect();

  void selectApplication(int aid);

  void authenticateWithAppDamDefaultKey();

  void authenticateWithSecretAuthKey();

  List<Integer> getApplicationIDs();

  boolean isApplicationInstalled(int aid);

  int getFreeMem();

  void deleteApplication(int aid);

  void changeKey(ChangeKeyParam changeKeyParam);

  void changeKeyFromAppDamDefaultKeyToSecretAuthKey();

  void createStdDataFile(CreateStdDataFileParam createStdDataFileParam);

  void writeData(int fileNo, int offset, byte[] data, CommunicationTypeEnum communicationType);

  void writeData(int fileNo, int offset, byte[] data);

  byte[] readData(int fileNo, int offset, int length);

  byte[] readData(int fileNo, int offset, int length, CommunicationTypeEnum communicationType);

  byte[] readData(int fileNo, int offset, int length, CommunicationTypeEnum communicationType, int fileSize);

  byte[] getFileIDs();
  
  Map<String, MISmartKeyReference> miSmartKeys(int cardKeyNo, byte[] appDefaultKey, byte[] appSecretKey, byte keyVersion);
  
}
