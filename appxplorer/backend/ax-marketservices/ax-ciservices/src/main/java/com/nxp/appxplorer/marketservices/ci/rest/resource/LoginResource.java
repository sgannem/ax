package com.nxp.appxplorer.marketservices.ci.rest.resource;

import static com.nxp.appxplorer.commons.utils.Constants.REST_URI_PREFIX;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.nxp.appxplorer.services.model.AccountServices;
import com.nxp.appxplorer.services.model.beans.LoginRequestDto;
import com.nxp.appxplorer.services.model.beans.LoginResponseDto;
import com.nxp.appxplorer.services.model.beans.LogoutResponseDto;
import com.nxp.appxplorer.services.model.beans.SignupRequestDto;
import com.nxp.appxplorer.services.model.beans.SignupResponseDto;

/**
 * @author nxa30710
 *
 */
@Path(REST_URI_PREFIX + "/ciaccount")
public class LoginResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginResource.class);

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final AccountServices accountServices;

    @Inject
    public LoginResource(HttpServletRequest request, HttpServletResponse response, AccountServices accountServices) {
	this.request = request;
	this.response = response;
	this.accountServices = accountServices;
	LOGGER.info("#accountServices injected successfully:" + this.accountServices);
    }

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public SignupResponseDto signup(SignupRequestDto signupDto) {
	LOGGER.info("#signup() is called" + signupDto.toString());
	response.setHeader("Access-Control-Allow-Origin", "*");
	return accountServices.createAccount(signupDto);
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public LoginResponseDto login(LoginRequestDto loginDto) {
	LOGGER.info("#login() is called" + loginDto.toString());
	return accountServices.validateLogin(loginDto);
    }

    @GET
    @Path("/logout")
    @Transactional
    // @RequiresPermissions("logout")
    // @RequiresAuthentication
    @Produces(MediaType.APPLICATION_JSON)
    public LogoutResponseDto logout() {
	LOGGER.info("#logout() is called");
	String authorizationHeader = request.getHeader("Authorization");
	LOGGER.info("#Got header info:" + authorizationHeader);
	String bearertoken = extractBearerToken(authorizationHeader);
	LOGGER.info("#Got header bearerToken:" + bearertoken);
	response.setHeader("Access-Control-Allow-Origin", "*");
	return accountServices.logout(bearertoken);
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
