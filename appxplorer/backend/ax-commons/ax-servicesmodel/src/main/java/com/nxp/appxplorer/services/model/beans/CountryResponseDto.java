package com.nxp.appxplorer.services.model.beans;

/**
 * @author nxa30710
 *
 */
public class CountryResponseDto {

    private String status;
    private CountryResponse countryResponse;
    private ErrorDetails errorDetails;

    /**
     * 
     */
    public CountryResponseDto() {

    }

    public CountryResponseDto(Builder builder) {
	this.status = builder.status;
	this.countryResponse = builder.countryResponse;
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
     * @return the countryResponse
     */
    public CountryResponse getCountryResponse() {
	return countryResponse;
    }

    /**
     * @param countryResponse
     *            the countryResponse to set
     */
    public void setCountryResponse(CountryResponse countryResponse) {
	this.countryResponse = countryResponse;
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
	return "CountryResponseDto [status=" + status + ", countryResponse=" + countryResponse + ", errorDetails="
		+ errorDetails + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {
	private String status;
	private CountryResponse countryResponse;
	private ErrorDetails errorDetails;

	public Builder status(String val) {
	    this.status = val;
	    return this;
	}

	public Builder countryResponse(CountryResponse val) {
	    this.countryResponse = val;
	    return this;
	}

	public Builder errorDetails(ErrorDetails val) {
	    this.errorDetails = val;
	    return this;
	}

	public CountryResponseDto build() {
	    return new CountryResponseDto(this);
	}
    }
}
