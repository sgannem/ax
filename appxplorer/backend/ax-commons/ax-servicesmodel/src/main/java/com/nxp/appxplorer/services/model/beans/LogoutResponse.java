package com.nxp.appxplorer.services.model.beans;

/**
 * This class holds Logout response during Logout processing.
 * 
 * @author nxa30710
 *
 */
public class LogoutResponse {

    private String userName;

    public LogoutResponse() {

    }

    public LogoutResponse(Builder builder) {
	this.userName = builder.userName;
    }

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
	this.userName = userName;
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private String userName;

	public Builder userName(String val) {
	    this.userName = val;
	    return this;
	}

	public LogoutResponse build() {
	    return new LogoutResponse(this);
	}
    }

}
