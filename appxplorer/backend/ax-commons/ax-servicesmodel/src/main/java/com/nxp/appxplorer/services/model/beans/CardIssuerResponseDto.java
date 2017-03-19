package com.nxp.appxplorer.services.model.beans;

import java.util.List;

public class CardIssuerResponseDto {

    private String status;
    private List<CardIssuerResponse> cardIssuerResponse;
    private ErrorDetails errorDetails;

    public CardIssuerResponseDto() {

    }

    public CardIssuerResponseDto(Builder builder) {
	this.status = builder.status;
	this.cardIssuerResponse = builder.cardIssuerResponse;
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
     * @return the cardIssuerResponse
     */
    public List<CardIssuerResponse> getCardIssuerResponse() {
	return cardIssuerResponse;
    }

    /**
     * @param cardIssuerResponse
     *            the cardIssuerResponse to set
     */
    public void setCardIssuerResponse(List<CardIssuerResponse> cardIssuerResponse) {
	this.cardIssuerResponse = cardIssuerResponse;
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
	return "CardIssuerResponseDto [cardIssuerResponse=" + cardIssuerResponse + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {
	private String status;
	private List<CardIssuerResponse> cardIssuerResponse;
	private ErrorDetails errorDetails;

	public Builder status(String val) {
	    this.status = val;
	    return this;
	}

	public Builder cardIssuerResponse(List<CardIssuerResponse> val) {
	    this.cardIssuerResponse = val;
	    return this;
	}

	public Builder errorDetails(ErrorDetails val) {
	    this.errorDetails = val;
	    return this;
	}

	public CardIssuerResponseDto build() {
	    return new CardIssuerResponseDto(this);
	}
    }

}
