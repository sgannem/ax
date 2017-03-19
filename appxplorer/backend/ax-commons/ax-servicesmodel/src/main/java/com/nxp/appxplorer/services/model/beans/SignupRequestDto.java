package com.nxp.appxplorer.services.model.beans;

/**
 * This class maintains Data Transfer during signup service invocation.
 * 
 * @author nxa30710
 *
 */
public class SignupRequestDto {

    private SignupRequest signupRequest;

    public SignupRequestDto() {

    }

    private SignupRequestDto(Builder builder) {
	this.signupRequest = builder.signupRequest;
    }

    /**
     * @return the signupRequest
     */
    public SignupRequest getSignupRequest() {
	return signupRequest;
    }

    /**
     * @param signupRequest
     *            the signupRequest to set
     */
    public void setSignupRequest(SignupRequest signupRequest) {
	this.signupRequest = signupRequest;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "SignupRequestDto [signupRequest=" + signupRequest + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private SignupRequest signupRequest;

	public Builder signupRequest(SignupRequest val) {
	    this.signupRequest = val;
	    return this;
	}

	public SignupRequestDto build() {
	    return new SignupRequestDto(this);
	}

    }

}
