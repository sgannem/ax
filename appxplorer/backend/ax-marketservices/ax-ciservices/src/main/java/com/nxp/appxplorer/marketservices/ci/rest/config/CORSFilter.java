package com.nxp.appxplorer.marketservices.ci.rest.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Singleton;

@Singleton
public class CORSFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CORSFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
	LOGGER.info("#initializating CORSFilter...");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	LOGGER.info("#CORSFilter.doFilter() is called");
	HttpServletResponse httpServletResponse = (HttpServletResponse) response;
	httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
	httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
	httpServletResponse.setHeader("Access-Control-Allow-Headers",
		"Origin, Content-Type, X-Auth-Token, Authorization");
	chain.doFilter(request, response);
    }

    public void destroy() {
	LOGGER.info("#destroying CORSFilter...");
    }

}
