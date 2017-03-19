package com.nxp.appxplorer.marketservices.ci.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CIDBRealm extends AuthorizingRealm {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CIDBRealm.class);

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection paramPrincipalCollection) {
		LOGGER.info("#doGetAuthorizationInfo() is called with paramPrincipalCollection:"+paramPrincipalCollection.toString());
				AuthenticationToken a1 = new UsernamePasswordToken("nxp", "nxp123");
				AuthenticationInfo a = doGetAuthenticationInfo(a1); 
		return (AuthorizationInfo) a;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken t)
			throws AuthenticationException {
//		AuthenticationInfo
		return null;
	}

}
