package com.nxp.appxplorer.marketservices.ci.realm;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BearerTokenAuthenticatingFilter extends AuthenticatingFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BearerTokenAuthenticatingFilter.class);
	
	 /**
     * HTTP Authorization header, equal to <code>Authorization</code>
     */
    protected static final String AUTHORIZATION_HEADER = "Authorization";

	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response)
			throws Exception {
		String authorizationHeader = getAuthzHeader(request);
		LOGGER.info("#Got authheader :"+authorizationHeader);
//		if ((authorizationHeader == null) || (authorizationHeader.length() == 0)) {
//			return createToken("", "", request, response);
//		}
//
//		if (LOGGER.isDebugEnabled()) {
//			LOGGER.debug("Attempting to execute login with headers [" + authorizationHeader + "]");
//		}
		return new BearerAuthenticationToken();
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

	protected String[] getPrincipalsAndCredentials(String scheme, String encoded) {
		String decoded = Base64.decodeToString(encoded);
		return decoded.split(":", 2);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
			throws Exception {
		String bearerToken = extractBearerToken(getAuthzHeader(request));
		return isValid(bearerToken);
	}
	
	/** actual business logic has to be implemented **/
	 private boolean isValid(String bearerToken) {
		 //TODO
		return true;
	}


	/**
     * Returns the {@link #AUTHORIZATION_HEADER AUTHORIZATION_HEADER} from the specified ServletRequest.
     * <p/>
     * This implementation merely casts the request to an <code>HttpServletRequest</code> and returns the header:
     * <p/>
     * <code>HttpServletRequest httpRequest = {@link WebUtils#toHttp(javax.servlet.ServletRequest) toHttp(reaquest)};<br/>
     * return httpRequest.getHeader({@link #AUTHORIZATION_HEADER AUTHORIZATION_HEADER});</code>
     *
     * @param request the incoming <code>ServletRequest</code>
     * @return the <code>Authorization</code> header's value.
     */
    protected String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return httpRequest.getHeader(AUTHORIZATION_HEADER);
    }

}
