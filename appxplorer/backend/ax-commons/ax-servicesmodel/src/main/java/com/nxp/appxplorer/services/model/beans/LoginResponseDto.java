package com.nxp.appxplorer.services.model.beans;

/**
 * This class maintains Data Transfer during login service invocation.
 * 
 * @author nxa30710
 *
 */
public class LoginResponseDto {

    private String status;
    private LoginResponse loginResponse;
    private ErrorDetails errorDetails;

    public LoginResponseDto() {

    }

    private LoginResponseDto(Builder builder) {
	this.status = builder.status;
	this.loginResponse = builder.loginResponse;
	this.errorDetails = builder.errorDetails;
    }

    /**
     * @return the status
     */
    public String getStatus() {
	return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
	this.status = status;
    }

    /**
     * @return the loginResponse
     */
    public LoginResponse getLoginResponse() {
	return loginResponse;
    }

    /**
     * @param loginResponse
     *            the loginResponse to set
     */
    public void setLoginResponse(LoginResponse loginResponse) {
	this.loginResponse = loginResponse;
    }

    /**
     * @return the errorDetails
     */
    public ErrorDetails getErrorDetails() {
	return errorDetails;
    }

    /**
     * @param errorDetails
     *            the errorDetails to set
     */
    public void setErrorDetails(ErrorDetails errorDetails) {
	this.errorDetails = errorDetails;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "LoginResponseDto [status=" + status + ", loginResponse=" + loginResponse + ", errorDetails="
		+ errorDetails + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private String status;
	private LoginResponse loginResponse;
	private ErrorDetails errorDetails;

	public Builder status(String val) {
	    this.status = val;
	    return this;
	}

	public Builder loginResponse(LoginResponse val) {
	    this.loginResponse = val;
	    return this;
	}

	public Builder errorDetails(ErrorDetails val) {
	    this.errorDetails = val;
	    return this;
	}

	public LoginResponseDto build() {
	    return new LoginResponseDto(this);
	}
    }

}
