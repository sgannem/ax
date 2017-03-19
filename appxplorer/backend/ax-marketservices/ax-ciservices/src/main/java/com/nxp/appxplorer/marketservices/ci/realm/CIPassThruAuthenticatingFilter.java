package com.nxp.appxplorer.marketservices.ci.realm;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CIPassThruAuthenticatingFilter extends PassThruAuthenticationFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CIPassThruAuthenticatingFilter.class);
	
	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		LOGGER.info("#isAccessAllowed() is called");
		boolean status = true;
//		Subject subject = SecurityUtils.getSubject();
//		LOGGER.info("#Got the Subject:"+subject);
//		UsernamePasswordToken t = new UsernamePasswordToken("nxp", "nxp123");
//		subject.login(t);
//		return subject.isAuthenticated();
		BearerAuthenticationToken b = new BearerAuthenticationToken();
		System.out.println("#Creating bearer token and storing it to data base..."+b.getBearerToken());
		return status;
	}


}
