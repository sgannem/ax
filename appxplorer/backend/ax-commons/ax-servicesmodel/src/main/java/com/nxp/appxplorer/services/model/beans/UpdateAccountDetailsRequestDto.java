package com.nxp.appxplorer.services.model.beans;

/**
 * This class maintains Data Transfer during update account service invocation.
 * 
 * @author nxa30710
 *
 */
public class UpdateAccountDetailsRequestDto {

    private UpdateAccountDetailsRequest updateAccountDetailsRequest;

    public UpdateAccountDetailsRequestDto() {

    }

    private UpdateAccountDetailsRequestDto(Builder builder) {
	this.updateAccountDetailsRequest = builder.updateAccountDetailsRequest;
    }

    /**
     * @return the updateAccountDetailsRequest
     */
    public UpdateAccountDetailsRequest getUpdateAccountDetailsRequest() {
	return updateAccountDetailsRequest;
    }

    /**
     * @param updateAccountDetailsRequest
     *            the updateAccountDetailsRequest to set
     */
    public void setUpdateAccountDetailsRequest(UpdateAccountDetailsRequest updateAccountDetailsRequest) {
	this.updateAccountDetailsRequest = updateAccountDetailsRequest;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "UpdateAccountDetailsRequestDto [updateAccountDetailsRequest=" + updateAccountDetailsRequest + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private UpdateAccountDetailsRequest updateAccountDetailsRequest;

	public Builder updateAccountDetailsRequest(UpdateAccountDetailsRequest val) {
	    this.updateAccountDetailsRequest = val;
	    return this;
	}

	public UpdateAccountDetailsRequestDto build() {
	    return new UpdateAccountDetailsRequestDto(this);
	}

    }

}
