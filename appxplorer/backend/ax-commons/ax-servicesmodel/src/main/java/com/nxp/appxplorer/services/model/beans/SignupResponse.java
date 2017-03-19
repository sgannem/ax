package com.nxp.appxplorer.services.model.beans;

/**
 * This class holds sign up response during sign up processing.
 * 
 * @author nxa30710
 *
 */
public class SignupResponse {

    private String userName;
    private String externalUserId;

    public SignupResponse() {

    }

    public SignupResponse(Builder builder) {
	this.userName = builder.userName;
	this.externalUserId = builder.externalUserId;
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
	return "SignupResponse [userName=" + userName + ", externalUserId=" + externalUserId + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private String userName;
	private String externalUserId;

	public Builder userName(String val) {
	    this.userName = val;
	    return this;
	}

	public Builder externalUserId(String val) {
	    this.externalUserId = val;
	    return this;
	}

	public SignupResponse build() {
	    return new SignupResponse(this);
	}
    }

}
