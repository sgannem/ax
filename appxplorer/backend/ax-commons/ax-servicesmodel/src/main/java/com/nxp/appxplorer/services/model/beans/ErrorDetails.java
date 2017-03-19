package com.nxp.appxplorer.services.model.beans;

/**
 * 
 * This class holds all the Error Details during services processing.
 * 
 * @author nxa30710
 *
 */

public class ErrorDetails {

    private String errorCode;
    private String errorDescription;

    public ErrorDetails() {

    }

    private ErrorDetails(Builder builder) {
	this.errorCode = builder.errorCode;
	this.errorDescription = builder.errorDescription;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
	return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
	this.errorCode = errorCode;
    }

    /**
     * @return the errorDescription
     */
    public String getErrorDescription() {
	return errorDescription;
    }

    /**
     * @param errorDescription
     *            the errorDescription to set
     */
    public void setErrorDescription(String errorDescription) {
	this.errorDescription = errorDescription;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ErrorDetails [errorCode=" + errorCode + ", errorDescription=" + errorDescription + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private String errorCode;
	private String errorDescription;

	public Builder errorCode(String val) {
	    this.errorCode = val;
	    return this;
	}

	public Builder errorDescription(String val) {
	    this.errorDescription = val;
	    return this;
	}

	public ErrorDetails build() {
	    return new ErrorDetails(this);
	}
    }

}
