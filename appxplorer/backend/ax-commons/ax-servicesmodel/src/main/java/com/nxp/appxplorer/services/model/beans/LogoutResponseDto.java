package com.nxp.appxplorer.services.model.beans;

/**
 * This class maintains Data Transfer during Logout service invocation.
 * 
 * @author nxa30710
 *
 */
public class LogoutResponseDto {

    private String status;
    private LogoutResponse logoutResponse;
    private ErrorDetails errorDetails;

    public LogoutResponseDto() {

    }

    private LogoutResponseDto(Builder builder) {
	this.status = builder.status;
	this.logoutResponse = builder.logoutResponse;
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
     * @return the logoutResponse
     */
    public LogoutResponse getLogoutResponse() {
	return logoutResponse;
    }

    /**
     * @param logoutResponse
     *            the logoutResponse to set
     */
    public void setLogoutResponse(LogoutResponse logoutResponse) {
	this.logoutResponse = logoutResponse;
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
	return "LogoutResponseDto [status=" + status + ", logoutResponse=" + logoutResponse + ", errorDetails="
		+ errorDetails + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private String status;
	private LogoutResponse logoutResponse;
	private ErrorDetails errorDetails;

	public Builder status(String val) {
	    this.status = val;
	    return this;
	}

	public Builder logoutResponse(LogoutResponse val) {
	    this.logoutResponse = val;
	    return this;
	}

	public Builder errorDetails(ErrorDetails val) {
	    this.errorDetails = val;
	    return this;
	}

	public LogoutResponseDto build() {
	    return new LogoutResponseDto(this);
	}
    }

}
