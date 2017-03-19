package com.nxp.appxplorer.marketservices.ci.rest.resource;

import static com.nxp.appxplorer.commons.utils.Constants.REST_URI_PREFIX;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.nxp.appxplorer.services.model.AccountServices;
import com.nxp.appxplorer.services.model.beans.BusinessSegmentResponseDto;
import com.nxp.appxplorer.services.model.beans.CountryResponseDto;
import com.nxp.appxplorer.services.model.beans.UpdateAccountDetailsRequestDto;
import com.nxp.appxplorer.services.model.beans.UpdateAccountDetailsResponseDto;
import com.nxp.appxplorer.services.model.beans.ViewAccountDetailsResponseDto;
import com.nxp.appxplorer.services.model.utils.ServiceResponseBuilder;

/**
 * @author nxa30710
 *
 */
@Path(REST_URI_PREFIX + "/ci")
public class DashboardResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardResource.class);

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final AccountServices accountServices;

    @Inject
    public DashboardResource(HttpServletRequest request, HttpServletResponse response,
	    AccountServices accountServices) {
	this.request = request;
	this.response = response;
	this.accountServices = accountServices;
	// LOGGER.info("#accountServices injected successfully:" +
	// this.accountServices);
    }

    @GET
    @Path("/account/{externalUserId:[0-9][0-9]*}")
    // @RequiresPermissions("viewaccount")
    // @RequiresAuthentication
    @Produces(MediaType.APPLICATION_JSON)
    public ViewAccountDetailsResponseDto viewAccountDetails(
	    @PathParam(value = "externalUserId") String externalUserId) {
	LOGGER.info("#viewAccountDetails({}) is called", externalUserId);
	LOGGER.info("CI Accounts Details are here... ");
	String authorizationHeader = request.getHeader("Authorization");
	LOGGER.info("#Got header info:" + authorizationHeader);
	String bearerToken = extractBearerToken(authorizationHeader);
	LOGGER.info("#Got header bearerToken:" + bearerToken);
	// bearertoken = "6hdeu9boj746aocdbh2v3346pm";
	if (accountServices.validateBearerToken(bearerToken)) {
	    LOGGER.info("#bearar token validation is successful.");
	    return accountServices.viewAccountDetails(externalUserId);
	} else {
	    LOGGER.info("#bearer token validation is failed");
	    return ServiceResponseBuilder.buildViewAccountDetailsDtoForError("AX9000", "Invalid bearer token");
	}
    }

    @GET
    @Path("/businesssegments")
    // @RequiresPermissions("viewaccount")
    // @RequiresAuthentication
    @Produces(MediaType.APPLICATION_JSON)
    public BusinessSegmentResponseDto businessSegments() {
	LOGGER.info("#businessSegments() is called");
	LOGGER.info("Business segments are here... ");
	String authorizationHeader = request.getHeader("Authorization");
	LOGGER.info("#Got header info:" + authorizationHeader);
	String bearerToken = extractBearerToken(authorizationHeader);
	LOGGER.info("#Got header bearerToken:" + bearerToken);
	// bearertoken = "6hdeu9boj746aocdbh2v3346pm";
	if (accountServices.validateBearerToken(bearerToken)) {
	    LOGGER.info("#bearar token validation is successful.");
	    return accountServices.businessSegments();
	} else {
	    LOGGER.info("#bearer token validation is failed");
	    return ServiceResponseBuilder.buildBusinessSegmentResponseDtoForError("AX9000", "Invalid bearer token");
	}
    }

    @GET
    @Path("/countries")
    // @RequiresPermissions("viewaccount")
    // @RequiresAuthentication
    @Produces(MediaType.APPLICATION_JSON)
    public CountryResponseDto countries() {
	LOGGER.info("#countries() is called");
	LOGGER.info("countries are here... ");
	String authorizationHeader = request.getHeader("Authorization");
	LOGGER.info("#Got header info:" + authorizationHeader);
	String bearerToken = extractBearerToken(authorizationHeader);
	LOGGER.info("#Got header bearerToken:" + bearerToken);
	// bearertoken = "6hdeu9boj746aocdbh2v3346pm";
	if (accountServices.validateBearerToken(bearerToken)) {
	    LOGGER.info("#bearar token validation is successful.");
	    return accountServices.countries();
	} else {
	    LOGGER.info("#bearer token validation is failed");
	    return ServiceResponseBuilder.buildCountryResponseDtoForError("AX9000", "Invalid bearer token");
	}
    }

    @PUT
    @Path("/account/{externalUserId:[0-9][0-9]*}")
    // @RequiresPermissions("viewaccount")
    // @RequiresAuthentication
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public UpdateAccountDetailsResponseDto updateAccountDetails(
	    @PathParam(value = "externalUserId") String externalUserId,
	    UpdateAccountDetailsRequestDto updateAccountDetailsRequestDto) {
	LOGGER.info("#viewAccountDetails({}) is called", externalUserId);
	LOGGER.info("CI Accounts Details are here... ");
	String authorizationHeader = request.getHeader("Authorization");
	LOGGER.info("#Got header info:" + authorizationHeader);
	String bearerToken = extractBearerToken(authorizationHeader);
	LOGGER.info("#Got header bearerToken:" + bearerToken);
	// bearertoken = "6hdeu9boj746aocdbh2v3346pm";
	if (accountServices.validateBearerToken(bearerToken)) {
	    return accountServices.updateAccountDetails(externalUserId, updateAccountDetailsRequestDto);
	} else {
	    return ServiceResponseBuilder.buildUpdateAccountDetailsResponseDtoForError("AX9000",
		    "Invalid bearer token");
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
