package com.nxp.trustid.websocketserver.desfire;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.nxp.trustid.communication.util.Utils;
import com.nxp.trustid.model.desfire.ApduHandler;
import com.nxp.trustid.model.desfire.CardFactory;
import com.nxp.trustid.model.desfire.DesFireCard;
import com.nxp.nfclib.LibraryManager;
import com.nxp.nfclib.desfire.DESFireFactory;
import com.nxp.nfclib.desfire.IDESFireEV1.CommandSet;
import com.nxp.nfclib.desfire.IDESFireEV2;

/**
 * A wrapper for the ApduMagic library.
 *
 * @author nxa30710 ( Srinivasu.Gannem@nxp.com )
 */
@Singleton
public class ApduMagicWrapper implements CardFactory {

  static final int KEY_NUMBER_APP_DAM_DEFAULT_KEY = 0;
  static final int KEY_NUMBER_SECRET_AUTH_KEY = 0;

  private final int cardKeyNo;
  private final byte cardKeyVersion;
  private final byte[] appDamDefaultKey;
  private final boolean doDiversification;
  private final byte[] diversificationInput;
  private final byte[] secretAuthKey;

  @Inject
  public ApduMagicWrapper(@Named("CARD_KEY_NO") final String cardKeyNoAsString, @Named("CARD_KEY_VERSION") final String
      cardKeyVersionAsString, @Named("APP_DAM_DEFAULT_KEY") final String appDamDefaultKey, @Named("DIVERSIFICATION") final String
      diversificationAsString, @Named("DIVERSIFICATION_INPUT") final String diversificationInputAsString, @Named("SECRET_AUTH_KEY") final
  String secretAuthKey) {
    this.cardKeyNo = Integer.parseInt(cardKeyNoAsString);
    this.cardKeyVersion = Byte.parseByte(cardKeyVersionAsString);
    this.appDamDefaultKey = Utils.getByteArray(appDamDefaultKey);
    this.doDiversification = Boolean.parseBoolean(diversificationAsString);
    this.diversificationInput = Utils.getByteArray(diversificationInputAsString);
    this.secretAuthKey = Utils.getByteArray(secretAuthKey);
  }

  @Override
  public DesFireCard newDesFireCard(final ApduHandler apduHandler) {
	  
    final LibraryManager libraryManager = new LibraryManager();
    final ApduHandlerAdapter apduHandlerAdapter = new ApduHandlerAdapter(apduHandler);
    libraryManager.setApduHandler(apduHandlerAdapter);

    final IDESFireEV2 desFireCard = DESFireFactory.getInstance().getDESFireEV2(libraryManager.getSupportModules());
    desFireCard.setCommandSet(CommandSet.ISO);

    final DesFireCardAdapter desFireCardAdapter = DesFireCardAdapter.builder(desFireCard, apduHandler).
        cardKeyNo(cardKeyNo).
        cardKeyVersion(cardKeyVersion).
        doDiversification(doDiversification).
        diversificationInput(diversificationInput).build();

    // Perform an ISO Select in order to reset the card's state.
    // Without this ISO Select the card could still be in a certain state (e.g. authenticated) and subsequent DESFire commands (sent by
    // the back end) could fail.
    desFireCardAdapter.isoSelect();
    
    // Initialize the keys
    desFireCardAdapter.miSmartKeys(cardKeyNo, appDamDefaultKey, secretAuthKey, cardKeyVersion);

    return desFireCardAdapter;
  }

}
