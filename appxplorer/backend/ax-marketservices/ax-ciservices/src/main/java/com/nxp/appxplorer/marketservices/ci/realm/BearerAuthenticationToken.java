package com.nxp.appxplorer.marketservices.ci.realm;

import org.apache.shiro.authc.AuthenticationToken;

import com.nxp.appxplorer.commons.utils.TokenGeneration;

public class BearerAuthenticationToken implements AuthenticationToken {

	private String userName;
	private char[] password;
	private String bearerToken;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
//		this.userName = nxp;
		this.userName = "nxp";
	}

	public char[] getPassword() {
		return this.password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	/**
	 * @return the bearerToken
	 */
	public String getBearerToken() {
		return TokenGeneration.getBearerToken();
	}

	/**
	 * @param bearerToken
	 *            the bearerToken to set
	 */
	public void setBearerToken(String bearerToken) {
		this.bearerToken = bearerToken;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return getUserName();
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return getPassword();
	}

}
