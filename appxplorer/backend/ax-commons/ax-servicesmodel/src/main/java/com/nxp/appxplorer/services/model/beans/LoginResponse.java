package com.nxp.appxplorer.services.model.beans;

/**
 * This class holds login response during login processing.
 * 
 * @author nxa30710
 *
 */
public class LoginResponse {

    private String token;
    private String externalUserId;

    public LoginResponse() {

    }

    public LoginResponse(Builder builder) {
	this.token = builder.token;
	this.externalUserId = builder.externalUserId;
    }

    /**
     * @return the token
     */
    public String getToken() {
	return token;
    }

    /**
     * @param token
     *            the token to set
     */
    public void setToken(String token) {
	this.token = token;
    }

    /**
     * @return the externalUserId
     */
    public String getExternalUserId() {
	return externalUserId;
    }

    /**
     * @param externalUserId
     *            the externalUserId to set
     */
    public void setExternalUserId(String externalUserId) {
	this.externalUserId = externalUserId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "LoginResponse [token=" + token + ", externalUserId=" + externalUserId + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private String token;
	private String externalUserId;

	public Builder token(String val) {
	    this.token = val;
	    return this;
	}

	public Builder externalUserId(String val) {
	    this.externalUserId = val;
	    return this;
	}

	public LoginResponse build() {
	    return new LoginResponse(this);
	}
    }

}
