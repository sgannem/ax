package com.nxp.appxplorer.services.model.beans;

/**
 * This class maintains Data Transfer during login service invocation.
 * 
 * @author nxa30710
 *
 */
public class LoginRequestDto {

    private LoginRequest loginRequest;

    public LoginRequestDto() {

    }

    private LoginRequestDto(Builder builder) {
	this.loginRequest = builder.loginRequest;
    }

    /**
     * @return the loginRequest
     */
    public LoginRequest getLoginRequest() {
	return loginRequest;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "LoginRequestDto [loginRequest=" + loginRequest + "]";
    }

    /**
     * @param loginRequest
     *            the loginRequest to set
     */
    public void setLoginRequest(LoginRequest loginRequest) {
	this.loginRequest = loginRequest;
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private LoginRequest loginRequest;

	public Builder loginRequest(LoginRequest val) {
	    this.loginRequest = val;
	    return this;
	}

	public LoginRequestDto build() {
	    return new LoginRequestDto(this);
	}

    }

}
