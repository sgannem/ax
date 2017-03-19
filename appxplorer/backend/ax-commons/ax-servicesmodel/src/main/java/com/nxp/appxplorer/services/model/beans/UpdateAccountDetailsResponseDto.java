package com.nxp.appxplorer.services.model.beans;

public class UpdateAccountDetailsResponseDto {

    private String status;
    private UpdateAccountDetailsResponse updateAccountDetailsResponse;
    private ErrorDetails errorDetails;

    public UpdateAccountDetailsResponseDto() {

    }

    public UpdateAccountDetailsResponseDto(Builder builder) {
	this.status = builder.status;
	this.updateAccountDetailsResponse = builder.updateAccountDetailsResponse;
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
     * @return the updateAccountDetailsResponse
     */
    public UpdateAccountDetailsResponse getUpdateAccountDetailsResponse() {
	return updateAccountDetailsResponse;
    }

    /**
     * @param updateAccountDetailsResponse
     *            the updateAccountDetailsResponse to set
     */
    public void setUpdateAccountDetailsResponse(UpdateAccountDetailsResponse updateAccountDetailsResponse) {
	this.updateAccountDetailsResponse = updateAccountDetailsResponse;
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
	return "UpdateAccountDetailsResponseDto [status=" + status + ", updateAccountDetailsResponse="
		+ updateAccountDetailsResponse + ", errorDetails=" + errorDetails + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {
	private String status;
	private UpdateAccountDetailsResponse updateAccountDetailsResponse;
	private ErrorDetails errorDetails;

	public Builder status(String val) {
	    this.status = val;
	    return this;
	}

	public Builder updateAccountDetailsResponse(UpdateAccountDetailsResponse val) {
	    this.updateAccountDetailsResponse = val;
	    return this;
	}

	public Builder errorDetails(ErrorDetails val) {
	    this.errorDetails = val;
	    return this;
	}

	public UpdateAccountDetailsResponseDto build() {
	    return new UpdateAccountDetailsResponseDto(this);
	}
    }
}
