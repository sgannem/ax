package com.nxp.trustid.model.requesthandler;

import static com.nxp.trustid.communication.dto.message.StatusCodeDto.ErrorCodeEnum.APPLICATION_DOES_NOT_EXIST;
import static com.nxp.trustid.model.util.AidUtils.aidFromString;
import static com.nxp.trustid.model.util.AidUtils.asHexString;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.nxp.trustid.communication.dto.message.RemoteMessageDto;
import com.nxp.trustid.communication.dto.message.RemoveApplicationResponseDto;
import com.nxp.trustid.communication.dto.message.StatusCodeDto.ErrorCodeEnum;
import com.nxp.trustid.model.desfire.ApduHandler;
import com.nxp.trustid.model.desfire.CardFactory;
import com.nxp.trustid.model.desfire.DesFireCard;
import com.nxp.trustid.model.util.Utils1;

/**
 * Handles the REMOVE APPLICATION REQUEST.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
public class RemoveApplicationRequestHandler extends AbstractRequestHandler implements RequestHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(RemoveApplicationRequestHandler.class);

  private final CardFactory cardFactory;
  private final int aid;

  @Inject
  public RemoveApplicationRequestHandler(final CardFactory cardFactory, @Named("AID") final String aidAsString) {
    this.cardFactory = cardFactory;
    this.aid = aidFromString(aidAsString);
  }

  @Override
  public RemoteMessageDto handle(final ApduHandler apduHandler, final RemoteMessageDto remoteMessage) {
    final DesFireCard card = cardFactory.newDesFireCard(apduHandler);

    // Temporarily take the AID of the request instead of the AID in system.properties (injected into the constructor).
    // This is done for convenience during the development phase.
//    final int aidInRequest = com.nxp.appstore.common.util.Utils.convertToLSBFirstAID(remoteMessage.getRemoveApplicationRequest().getAid());
    final int aidInRequest = Utils1.convertToLSBFirstAID(remoteMessage.getRemoveApplicationRequest().getAid());
    final boolean isApplicationInstalled = card.isApplicationInstalled(aidInRequest);
    final RemoteMessageDto result;

    if (isApplicationInstalled) {
      LOGGER.info("application is installed");

      card.selectApplication(aidInRequest);
      authenticate(card);

      deleteApplication(aidInRequest, card);

      final RemoveApplicationResponseDto removeApplicationResponseDto = RemoveApplicationResponseDto.newInstance(aidInRequest);
      result = RemoteMessageDto.newInstanceWithSuccess(removeApplicationResponseDto);
    } else {
      LOGGER.error("application is not installed");
      final RemoveApplicationResponseDto removeApplicationResponseDto = RemoveApplicationResponseDto.newInstance(aidInRequest);
      result = RemoteMessageDto.newInstanceWithError(removeApplicationResponseDto, APPLICATION_DOES_NOT_EXIST);
    }

    return result;
  }

  private void authenticate(final DesFireCard desFireCard) {
    LOGGER.info("authenticate");
    desFireCard.authenticateWithSecretAuthKey();
    LOGGER.info("authenticate successful");
  }

  private void deleteApplication(final int aid, final DesFireCard desFireCard) {
    LOGGER.info("deleteApplication, aid=0x{}", asHexString(aid));
    desFireCard.deleteApplication(aid);
    LOGGER.info("deleteApplication successful");
  }

  @Override
  public RemoteMessageDto handleException(final Exception exception) {
    final RemoveApplicationResponseDto removeApplicationResponseDto = RemoveApplicationResponseDto.newInstance(aid);
    final ErrorCodeEnum errorCode = mapExceptionToErrorCode(exception);
    return RemoteMessageDto.newInstanceWithError(removeApplicationResponseDto, errorCode);
  }
}
