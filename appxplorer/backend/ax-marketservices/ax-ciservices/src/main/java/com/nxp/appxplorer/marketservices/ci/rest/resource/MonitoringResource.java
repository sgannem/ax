package com.nxp.appxplorer.marketservices.ci.rest.resource;

import static com.nxp.appxplorer.commons.utils.Constants.MONITORING_URL;
import static com.nxp.appxplorer.commons.utils.Constants.REST_URI_PREFIX;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.nxp.appxplorer.services.model.AccountServices;

@Path(REST_URI_PREFIX + MONITORING_URL)
public class MonitoringResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardResource.class);

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final AccountServices accountServices;

    @Inject
    public MonitoringResource(HttpServletRequest request, HttpServletResponse response,
	    AccountServices accountServices) {
	this.request = request;
	this.response = response;
	this.accountServices = accountServices;
	LOGGER.info("#accountServices injected successfully:" + this.accountServices);
    }

    @GET
    public Response monotoring() {
	LOGGER.info("#monotoring() is called");
	// Response.ok("ax-ci services are up and running.
	// "+SecurityUtils.getSubject().getPrincipal()+"!").build();
	response.setHeader("Access-Control-Allow-Origin", "*");
	return Response.ok("ax-ci services are up and running").build();
    }

    public HttpServletRequest getRequest() {
	return request;
    }

    public HttpServletResponse getResponse() {
	return response;
    }

}
