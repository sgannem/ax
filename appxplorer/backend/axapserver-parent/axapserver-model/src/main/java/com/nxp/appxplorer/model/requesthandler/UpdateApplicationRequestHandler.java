package com.nxp.trustid.model.requesthandler;

import static com.nxp.trustid.model.util.AidUtils.aidFromString;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.nxp.trustid.communication.dto.message.RemoteMessageDto;
import com.nxp.trustid.communication.dto.message.StatusCodeDto.ErrorCodeEnum;
import com.nxp.trustid.communication.dto.message.UpdateApplicationResponseDto;
import com.nxp.trustid.model.UpdateApplicationCallback;
import com.nxp.trustid.model.desfire.ApduHandler;
import com.nxp.trustid.model.desfire.CardFactory;
import com.nxp.trustid.model.desfire.DesFireCard;
import com.nxp.trustid.model.desfire.FailedDesFireCommandException;
import com.nxp.trustid.model.util.Utils1;

/**
 * Handles the UPDATE APPLICATION REQUEST.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class UpdateApplicationRequestHandler extends AbstractRequestHandler implements RequestHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(UpdateApplicationRequestHandler.class);
  private final CardFactory cardFactory;
  private final int aid;
  private final UpdateApplicationCallback updateApplicationCallback;

  @Inject
  public UpdateApplicationRequestHandler(final CardFactory cardFactory, @Named("AID") final String aidAsString, final
  UpdateApplicationCallback updateApplicationCallback) {
    this.cardFactory = cardFactory;
    this.aid = aidFromString(aidAsString);
    this.updateApplicationCallback = updateApplicationCallback;
  }

  @Override
  public RemoteMessageDto handle(final ApduHandler apduHandler, final RemoteMessageDto remoteMessage) {
    // Temporarily take the AID of the request instead of the AID in system.properties (injected into the constructor).
    // This is done for convenience during the development phase.
    final int aidInRequest = Utils1.convertToLSBFirstAID(remoteMessage.getUpdateApplicationRequest().getAid());
    final DesFireCard card = cardFactory.newDesFireCard(apduHandler);
    final byte[] uid = remoteMessage.getUpdateApplicationRequest().getUid();

    List<Integer> listOfApps = card.getApplicationIDs();
    for(Integer app:listOfApps) {
    	LOGGER.info("#Application id:"+Integer.parseInt(String.valueOf(app),16));
    }
    card.selectApplication(aidInRequest);
    authenticate(card);

    updateApplicationCallback.perform(aidInRequest, uid, card);

    final UpdateApplicationResponseDto updateApplicationResponseDto = UpdateApplicationResponseDto.newInstance(aidInRequest);
    return RemoteMessageDto.newInstanceWithSuccess(updateApplicationResponseDto);
  }

	private void authenticate(final DesFireCard desFireCard) {
		try {
			System.out.println("###########################################");
			System.out.println("#authenticating with authenticateWithSecretAuthKey()");
			desFireCard.authenticateWithSecretAuthKey();
			System.out.println("#now Application auth is successful with new third party app key" );
			System.out.println("###########################################");
		} catch (final FailedDesFireCommandException e) {
			System.out.println("#authenticating with authenticateWithSecretAuthKey is failed due to 1st time update");
			System.out.println("#now it is doing authenticateWithAppDamDefaultKey()");
			desFireCard.authenticateWithAppDamDefaultKey();
			System.out.println("#now it is changing key from Store Server key to third party specific key");
			desFireCard.changeKeyFromAppDamDefaultKeyToSecretAuthKey();
			System.out.println("#now Application key has been changed successfully" );
			System.out.println("#now it is doing authenticateWithSecretAuthKey() after changing the key");
			desFireCard.authenticateWithSecretAuthKey();
			System.out.println("#now Application auth is successful with new third party app key" );
			System.out.println("###########################################");
		}
	}

  @Override
  public RemoteMessageDto handleException(final Exception exception) {
    final UpdateApplicationResponseDto updateApplicationResponseDto = UpdateApplicationResponseDto.newInstance(aid);
    final ErrorCodeEnum errorCode = mapExceptionToErrorCode(exception);
    return RemoteMessageDto.newInstanceWithError(updateApplicationResponseDto, errorCode);
  }
}
