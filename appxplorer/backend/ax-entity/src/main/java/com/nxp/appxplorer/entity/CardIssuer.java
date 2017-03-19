package com.nxp.appxplorer.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class maps CardIssuer table into the Data Base.
 * 
 */
@Entity
public class CardIssuer extends AbstractStrongEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    private short registrationStatusId;
    private long registrationConfirmOn;
    private short businessSegmentId;
    private byte[] companyOrBusinessLogo;
    private String isActive;
    private CardIssuerTnc cardIssuerTnc;
    private Set<CIAddress> addresses = new HashSet<>();
    private Set<Card> cards = new HashSet<>();
    private Set<CIAddress> ciAddress = new HashSet<>();

    CardIssuer() {
	super();
    }

    private CardIssuer(Builder builder) {
	this.firstName = builder.firstName;
	this.lastName = builder.lastName;
	this.email = builder.email;
	this.companyName = builder.companyName;
	this.registrationStatusId = builder.registrationStatusId;
	this.registrationConfirmOn = builder.registrationConfirmOn;
	this.businessSegmentId = builder.businessSegmentId;
	this.companyOrBusinessLogo = builder.companyOrBusinessLogo;
	this.isActive = builder.isActive;
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
    public short getRegistrationStatusId() {
	return registrationStatusId;
    }

    /**
     * @param registrationStatusId
     *            the registrationStatusId to set
     */
    public void setRegistrationStatusId(short registrationStatusId) {
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
     * @return the businessSegmentId
     */
    public short getBusinessSegmentId() {
	return businessSegmentId;
    }

    /**
     * @param businessSegmentId
     *            the businessSegmentId to set
     */
    public void setBusinessSegmentId(short businessSegmentId) {
	this.businessSegmentId = businessSegmentId;
    }

    /**
     * @return the companyOrBusinessLogo
     */
    public byte[] getCompanyOrBusinessLogo() {
	return companyOrBusinessLogo;
    }

    /**
     * @param companyOrBusinessLogo
     *            the companyOrBusinessLogo to set
     */
    public void setCompanyOrBusinessLogo(byte[] companyOrBusinessLogo) {
	this.companyOrBusinessLogo = companyOrBusinessLogo;
    }

    /**
     * @return the addresses
     */
    @OneToMany(mappedBy = "cardIssuer")
    public Set<CIAddress> getAddresses() {
	return addresses;
    }

    /**
     * @param addresses
     *            the addresses to set
     */
    public void setAddresses(Set<CIAddress> addresses) {
	this.addresses = addresses;
    }

    /**
     * @return the cardIssuerTnc
     */
    @OneToOne(mappedBy = "cardIssuer")
    // @OneToOne
    // @JoinColumn(name = "cardIssuerId")
    // @OneToOne(cascade = CascadeType.ALL)
    public CardIssuerTnc getCardIssuerTnc() {
	return cardIssuerTnc;
    }

    /**
     * @param cardIssuerTnc
     *            the cardIssuerTnc to set
     */
    public void setCardIssuerTnc(CardIssuerTnc cardIssuerTnc) {
	this.cardIssuerTnc = cardIssuerTnc;
    }

    /**
     * @return the cards
     */
    @JsonIgnore
    @OneToMany(mappedBy = "cardIssuer")
    @JsonIgnoreProperties(value = "CardIssuer")
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
     * @return the ciAddress
     */
    @JsonIgnore
    @OneToMany(mappedBy = "cardIssuer")
    @JsonIgnoreProperties(value = "CardIssuer")
    public Set<CIAddress> getCiAddress() {
	return ciAddress;
    }

    /**
     * @param ciAddress
     *            the ciAddress to set
     */
    public void setCiAddress(Set<CIAddress> ciAddress) {
	this.ciAddress = ciAddress;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	return Objects.hash(getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
	if (this == o) {
	    return true;
	}

	if ((o == null) || (getClass() != o.getClass())) {
	    return false;
	}

	final CardIssuer that = (CardIssuer) o;
	return Objects.equals(getId(), that.getId());
    }

    /**
     * It builds Card Issuer Builder object.
     * 
     * @return
     */
    public static Builder builder() {
	return new Builder();
    }

    /**
     * This Builder class helps to build Card Issuer Builder objects.
     * 
     */
    public static class Builder {
	private String firstName;
	private String lastName;
	private String email;
	private String companyName;
	private short registrationStatusId;
	private long registrationConfirmOn;
	private short businessSegmentId;
	private byte[] companyOrBusinessLogo;
	private String isActive;

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

	public Builder registrationStatusId(short val) {
	    this.registrationStatusId = val;
	    return this;
	}

	public Builder registrationConfirmOn(long val) {
	    this.registrationConfirmOn = val;
	    return this;
	}

	public Builder businessSegmentId(short val) {
	    this.businessSegmentId = val;
	    return this;
	}

	public Builder companyOrBusinessLogo(byte[] val) {
	    this.companyOrBusinessLogo = val;
	    return this;
	}

	public Builder isActive(String val) {
	    this.isActive = val;
	    return this;
	}

	public CardIssuer build() {
	    return new CardIssuer(this);
	}
    }

}
