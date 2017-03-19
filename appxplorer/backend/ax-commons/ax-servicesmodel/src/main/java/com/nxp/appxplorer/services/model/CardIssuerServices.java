package com.nxp.appxplorer.services.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.entity.CardIssuer;
import com.nxp.appxplorer.repository.CardIssuerRepository;
import com.nxp.appxplorer.services.model.beans.CardIssuerResponseDto;
import com.nxp.appxplorer.services.model.utils.ServiceResponseBuilder;

@Singleton
public class CardIssuerServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardIssuerServices.class);

    private CardIssuerRepository cardIssuerRepository;

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param cardIssuerResitory
     */
    @Inject
    public CardIssuerServices(CardIssuerRepository cardIssuerRepository) {
	this.cardIssuerRepository = cardIssuerRepository;
    }

    /**
     * @return
     */
    public CardIssuerResponseDto getCardIssuers() {
	LOGGER.info("#getCardIssuers method is called");
	CardIssuerResponseDto cardIssuerResponseDto = null;
	try {
	    List<CardIssuer> lCI = cardIssuerRepository.getCardIssuers();
	    if (lCI != null && !lCI.isEmpty()) {
		cardIssuerResponseDto = ServiceResponseBuilder.buildCardIssuerDtoForSuccess(lCI);
	    } else {
		cardIssuerResponseDto = ServiceResponseBuilder.buildCardIssuerDtoForError("AX80000",
			"Card Issuer Details doesn't exist");
	    }
	} catch (Exception e) {
	    cardIssuerResponseDto = ServiceResponseBuilder.buildCardIssuerDtoForError("AX80001",
		    "Error occured during account details fetching from db");
	    LOGGER.error("Error occured during account details fetching from db", e);
	}
	return cardIssuerResponseDto;
    }
}
