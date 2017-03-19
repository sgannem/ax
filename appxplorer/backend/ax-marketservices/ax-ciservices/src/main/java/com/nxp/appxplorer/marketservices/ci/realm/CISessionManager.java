package com.nxp.appxplorer.marketservices.ci.realm;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CISessionManager extends DefaultSessionManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CISessionManager.class);
	
	protected Serializable getSessionId(SessionKey sessionKey) {
		LOGGER.info("####################################################### getSessionId() ###########");
		Serializable sessionId = getSessionId(sessionKey);
		if (sessionId == null) {
			LOGGER.debug(
					"Unable to resolve session ID from SessionKey [{}].  Returning null to indicate a session could not be found.",
					sessionKey);

			return null;
		}
		Session s = retrieveSessionFromDataSource(sessionId);
		if (s == null) {
			String msg = "Could not find session with ID [" + sessionId + "]";
			throw new UnknownSessionException(msg);
		}
		LOGGER.info("#attributes:"+s.getAttributeKeys());
		return sessionId;
	}

}
