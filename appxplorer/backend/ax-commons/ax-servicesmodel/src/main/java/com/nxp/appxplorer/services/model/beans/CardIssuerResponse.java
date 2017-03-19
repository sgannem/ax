package com.nxp.appxplorer.services.model.beans;

import java.util.HashSet;
import java.util.Set;

import com.nxp.appxplorer.entity.Card;

public class CardIssuerResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    private long registrationStatusId;
    private long registrationConfirmOn;
    private String isActive;
    private Set<Card> cards = new HashSet<Card>();

    CardIssuerResponse() {
    }

    private CardIssuerResponse(Builder builder) {
	this.id = builder.id;
	this.firstName = builder.firstName;
	this.lastName = builder.lastName;
	this.email = builder.email;
	this.companyName = builder.companyName;
	this.registrationStatusId = builder.registrationStatusId;
	this.registrationConfirmOn = builder.registrationConfirmOn;
	this.cards = builder.cards;
	this.isActive = builder.isActive;
    }

    /**
     * @return the id
     */
    public Long getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
	return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
	return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
	return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
	this.email = email;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
	return companyName;
    }

    /**
     * @param companyName
     *            the companyName to set
     */
    public void setCompanyName(String companyName) {
	this.companyName = companyName;
    }

    /**
     * @return the registrationStatusId
     */
    public long getRegistrationStatusId() {
	return registrationStatusId;
    }

    /**
     * @param registrationStatusId
     *            the registrationStatusId to set
     */
    public void setRegistrationStatusId(long registrationStatusId) {
	this.registrationStatusId = registrationStatusId;
    }

    /**
     * @return the registrationConfirmOn
     */
    public long getRegistrationConfirmOn() {
	return registrationConfirmOn;
    }

    /**
     * @param registrationConfirmOn
     *            the registrationConfirmOn to set
     */
    public void setRegistrationConfirmOn(long registrationConfirmOn) {
	this.registrationConfirmOn = registrationConfirmOn;
    }

    /**
     * @return the cards
     */
    public Set<Card> getCards() {
	return cards;
    }

    /**
     * @param cardIssuers
     *            the cardIssuers to set
     */
    public void setCards(Set<Card> cards) {
	this.cards = cards;
    }

    /**
     * @return the isActive
     */
    public String getIsActive() {
	return isActive;
    }

    /**
     * @param isActive
     *            the isActive to set
     */
    public void setIsActive(String isActive) {
	this.isActive = isActive;
    }

    public String toString() {
	return "CardIssuerResponse [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
		+ ", companyName=" + companyName + ", registrationStatusId=" + registrationStatusId
		+ ", registrationConfirmOn=" + registrationConfirmOn + ", isActive=" + isActive + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String companyName;
	private long registrationStatusId;
	private long registrationConfirmOn;
	private Set<Card> cards;
	private String isActive;

	public Builder id(Long val) {
	    this.id = val;
	    return this;
	}

	public Builder firstName(String val) {
	    this.firstName = val;
	    return this;
	}

	public Builder lastName(String val) {
	    this.lastName = val;
	    return this;
	}

	public Builder email(String val) {
	    this.email = val;
	    return this;
	}

	public Builder companyName(String val) {
	    this.companyName = val;
	    return this;
	}

	public Builder registrationStatusId(long val) {
	    this.registrationStatusId = val;
	    return this;
	}

	public Builder registrationConfirmOn(long val) {
	    this.registrationConfirmOn = val;
	    return this;
	}

	public Builder cards(Set<Card> val) {
	    this.cards = val;
	    return this;
	}

	public Builder isActive(String val) {
	    this.isActive = val;
	    return this;
	}

	public CardIssuerResponse build() {
	    return new CardIssuerResponse(this);
	}
    }

}
