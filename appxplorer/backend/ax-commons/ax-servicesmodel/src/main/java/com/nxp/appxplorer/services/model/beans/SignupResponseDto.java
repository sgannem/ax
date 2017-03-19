package com.nxp.appxplorer.services.model.beans;

/**
 * This class maintains Data Transfer during sign up service invocation.
 * 
 * @author nxa30710
 *
 */
public class SignupResponseDto {

    private String status;
    private SignupResponse signupResponse;
    private ErrorDetails errorDetails;

    public SignupResponseDto() {

    }

    private SignupResponseDto(Builder builder) {
	this.status = builder.status;
	this.signupResponse = builder.signupResponse;
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
     * @return the signupResponse
     */
    public SignupResponse getSignupResponse() {
	return signupResponse;
    }

    /**
     * @param signupResponse
     *            the signupResponse to set
     */
    public void setSignupResponse(SignupResponse signupResponse) {
	this.signupResponse = signupResponse;
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
	return "SignupResponseDto [status=" + status + ", signupResponse=" + signupResponse + ", errorDetails="
		+ errorDetails + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private String status;
	private SignupResponse signupResponse;
	private ErrorDetails errorDetails;

	public Builder status(String val) {
	    this.status = val;
	    return this;
	}

	public Builder signupResponse(SignupResponse val) {
	    this.signupResponse = val;
	    return this;
	}

	public Builder errorDetails(ErrorDetails val) {
	    this.errorDetails = val;
	    return this;
	}

	public SignupResponseDto build() {
	    return new SignupResponseDto(this);
	}
    }

}
