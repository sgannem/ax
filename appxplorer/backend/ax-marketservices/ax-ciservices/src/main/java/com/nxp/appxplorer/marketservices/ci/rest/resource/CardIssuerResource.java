package com.nxp.appxplorer.marketservices.ci.rest.resource;

import static com.nxp.appxplorer.commons.utils.Constants.REST_URI_PREFIX;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.nxp.appxplorer.services.model.AccountServices;
import com.nxp.appxplorer.services.model.CardIssuerServices;
import com.nxp.appxplorer.services.model.beans.CardIssuerResponseDto;
import com.nxp.appxplorer.services.model.utils.ServiceResponseBuilder;

@Path(REST_URI_PREFIX + "/cardIssuer")
public class CardIssuerResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardResource.class);

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final AccountServices accountServices;
    private final CardIssuerServices cardIssuerServices;

    @Inject
    public CardIssuerResource(HttpServletRequest request, HttpServletResponse response, AccountServices accountServices,
	    CardIssuerServices cardIssuerServices) {
	this.request = request;
	this.response = response;
	this.accountServices = accountServices;
	this.cardIssuerServices = cardIssuerServices;
	LOGGER.info("#accountServices injected successfully:" + this.accountServices);
	LOGGER.info("#cardIssuerServices injected successfully:" + this.cardIssuerServices);
    }

    @GET
    // @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public CardIssuerResponseDto getCardIssuer() {
	LOGGER.info("#getCardIssuer() is called");
	response.setHeader("Access-Control-Allow-Origin", "*");
	String authorizationHeader = request.getHeader("Authorization");
	LOGGER.info("#Got header info:" + authorizationHeader);
	String bearerToken = extractBearerToken(authorizationHeader);
	LOGGER.info("#Got header bearerToken:" + bearerToken);
	String bearertoken = "6hdeu9boj746aocdbh2v3346pm";
	if (accountServices.validateBearerToken(bearertoken)) {
	    return cardIssuerServices.getCardIssuers();
	} else {
	    return ServiceResponseBuilder.buildCardIssuerDtoForError("AX9000", "Invalid bearer token");
	}
    }

    public HttpServletRequest getRequest() {
	return request;
    }

    public HttpServletResponse getResponse() {
	return response;
    }

    protected String extractBearerToken(String authorizationHeader) {
	if (authorizationHeader == null) {
	    return null;
	}
	String[] authTokens = authorizationHeader.split(" ");
	if ((authTokens == null) || (authTokens.length < 2)) {
	    return null;
	}
	return authTokens[1];
    }
}
