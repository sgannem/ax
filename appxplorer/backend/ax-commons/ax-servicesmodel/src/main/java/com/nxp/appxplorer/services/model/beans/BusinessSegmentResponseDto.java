package com.nxp.appxplorer.services.model.beans;

/**
 * @author nxa30710
 *
 */
public class BusinessSegmentResponseDto {

    private String status;
    private BusinessSegmentResponse businessSegmentResponse;
    private ErrorDetails errorDetails;

    /**
     * 
     */
    public BusinessSegmentResponseDto() {

    }

    public BusinessSegmentResponseDto(Builder builder) {
	this.status = builder.status;
	this.businessSegmentResponse = builder.businessSegmentResponse;
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
     * @return the businessSegmentResponse
     */
    public BusinessSegmentResponse getBusinessSegmentResponse() {
	return businessSegmentResponse;
    }

    /**
     * @param businessSegmentResponse
     *            the businessSegmentResponse to set
     */
    public void setBusinessSegmentResponse(BusinessSegmentResponse businessSegmentResponse) {
	this.businessSegmentResponse = businessSegmentResponse;
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
	return "BusinessSegmentResponseDto [status=" + status + ", businessSegmentResponse=" + businessSegmentResponse
		+ ", errorDetails=" + errorDetails + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {
	private String status;
	private BusinessSegmentResponse businessSegmentResponse;
	private ErrorDetails errorDetails;

	public Builder status(String val) {
	    this.status = val;
	    return this;
	}

	public Builder businessSegmentResponse(BusinessSegmentResponse val) {
	    this.businessSegmentResponse = val;
	    return this;
	}

	public Builder errorDetails(ErrorDetails val) {
	    this.errorDetails = val;
	    return this;
	}

	public BusinessSegmentResponseDto build() {
	    return new BusinessSegmentResponseDto(this);
	}
    }
}
