package com.nxp.appxplorer.services.model.beans;

public class ViewAccountDetailsResponseDto {

    private String status;
    private ViewAccountDetailsResponse viewAccountDetailsResponse;
    private ErrorDetails errorDetails;

    public ViewAccountDetailsResponseDto() {

    }

    public ViewAccountDetailsResponseDto(Builder builder) {
	this.status = builder.status;
	this.viewAccountDetailsResponse = builder.viewAccountDetailsResponse;
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
     * @return the viewAccountDetailsResponse
     */
    public ViewAccountDetailsResponse getViewAccountDetailsResponse() {
	return viewAccountDetailsResponse;
    }

    /**
     * @param viewAccountDetailsResponse
     *            the viewAccountDetailsResponse to set
     */
    public void setViewAccountDetailsResponse(ViewAccountDetailsResponse viewAccountDetailsResponse) {
	this.viewAccountDetailsResponse = viewAccountDetailsResponse;
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
	return "ViewAccountDetailsResponseDto [viewAccountDetailsResponse=" + viewAccountDetailsResponse + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {
	private String status;
	private ViewAccountDetailsResponse viewAccountDetailsResponse;
	private ErrorDetails errorDetails;

	public Builder status(String val) {
	    this.status = val;
	    return this;
	}

	public Builder viewAccountDetailsResponse(ViewAccountDetailsResponse val) {
	    this.viewAccountDetailsResponse = val;
	    return this;
	}

	public Builder errorDetails(ErrorDetails val) {
	    this.errorDetails = val;
	    return this;
	}

	public ViewAccountDetailsResponseDto build() {
	    return new ViewAccountDetailsResponseDto(this);
	}
    }
}
