package com.nxp.appxplorer.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class maps CIAddress table into the Data Base.
 * 
 */
@Entity
public class CIAddress extends AbstractStrongEntity {

    private Short addressTypeId;
    private CardIssuer cardIssuer;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressEmail;
    private String addressPhoneNumber;
    private String city;
    private String country;
    private String zipCode;
    private String isActive;

    CIAddress() {
	super();
    }

    private CIAddress(Builder builder) {
	this.addressTypeId = builder.addressTypeId;
	this.addressLine1 = builder.addressLine1;
	this.addressLine2 = builder.addressLine2;
	this.addressLine3 = builder.addressLine3;
	this.addressEmail = builder.addressEmail;
	this.addressPhoneNumber = builder.addressPhoneNumber;
	this.city = builder.city;
	this.country = builder.country;
	this.zipCode = builder.zipCode;
	this.isActive = builder.isActive;
    }

    /**
     * @return the addressTypeId
     */
    public Short getAddressTypeId() {
	return addressTypeId;
    }

    /**
     * @param addressTypeId
     *            the addressTypeId to set
     */
    public void setAddressTypeId(Short addressTypeId) {
	this.addressTypeId = addressTypeId;
    }

    /**
     * @return the cardIssuer
     */
    @ManyToOne(optional = false, targetEntity = CardIssuer.class)
    @JoinColumn(name = "cardIssuerId", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = "CIAddress")
    public CardIssuer getCardIssuer() {
	return cardIssuer;
    }

    /**
     * @param cardIssuer
     *            the cardIssuer to set
     */
    public void setCardIssuer(CardIssuer cardIssuer) {
	this.cardIssuer = cardIssuer;
    }

    /**
     * @return the addressLine1
     */
    public String getAddressLine1() {
	return addressLine1;
    }

    /**
     * @param addressLine1
     *            the addressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
	this.addressLine1 = addressLine1;
    }

    /**
     * @return the addressLine2
     */
    public String getAddressLine2() {
	return addressLine2;
    }

    /**
     * @param addressLine2
     *            the addressLine2 to set
     */
    public void setAddressLine2(String addressLine2) {
	this.addressLine2 = addressLine2;
    }

    /**
     * @return the addressLine3
     */
    public String getAddressLine3() {
	return addressLine3;
    }

    /**
     * @param addressLine3
     *            the addressLine3 to set
     */
    public void setAddressLine3(String addressLine3) {
	this.addressLine3 = addressLine3;
    }

    /**
     * @return the addressEmail
     */
    public String getAddressEmail() {
	return addressEmail;
    }

    /**
     * @param addressEmail
     *            the addressEmail to set
     */
    public void setAddressEmail(String addressEmail) {
	this.addressEmail = addressEmail;
    }

    /**
     * @return the addressPhoneNumber
     */
    public String getAddressPhoneNumber() {
	return addressPhoneNumber;
    }

    /**
     * @param addressPhoneNumber
     *            the addressPhoneNumber to set
     */
    public void setAddressPhoneNumber(String addressPhoneNumber) {
	this.addressPhoneNumber = addressPhoneNumber;
    }

    /**
     * @return the city
     */
    public String getCity() {
	return city;
    }

    /**
     * @param city
     *            the city to set
     */
    public void setCity(String city) {
	this.city = city;
    }

    /**
     * @return the country
     */
    public String getCountry() {
	return country;
    }

    /**
     * @param country
     *            the country to set
     */
    public void setCountry(String country) {
	this.country = country;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
	return zipCode;
    }

    /**
     * @param zipCode
     *            the zipCode to set
     */
    public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
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
     * This is method used to get entity hash value during entity persistence
     * processing.
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	return Objects.hash(getId());
    }

    /*
     * This is method used to maintain equals property during entity
     * persistence.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object o) {
	if (this == o) {
	    return true;
	}

	if ((o == null) || (getClass() != o.getClass())) {
	    return false;
	}

	final CIAddress that = (CIAddress) o;
	return Objects.equals(getId(), that.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "CIAddress [addressTypeId=" + addressTypeId + ", cardIssuer=" + cardIssuer + ", addressLine1="
		+ addressLine1 + ", addressLine2=" + addressLine2 + ", addressLine3=" + addressLine3 + ", addressEmail="
		+ addressEmail + ", addressPhoneNumber=" + addressPhoneNumber + ", city=" + city + ", country="
		+ country + ", zipCode=" + zipCode + ", isActive=" + isActive + "]";
    }

    /**
     * It builds CI Address object.
     * 
     * @return
     */
    public static Builder builder() {
	return new Builder();
    }

    /**
     * This Builder class helps to build CI Address Builder objects.
     * 
     */
    public static class Builder {
	private Short addressTypeId;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String addressEmail;
	private String addressPhoneNumber;
	private String city;
	private String country;
	private String zipCode;
	private String isActive;

	public Builder addressTypeId(Short val) {
	    this.addressTypeId = val;
	    return this;
	}

	public Builder addressLine1(String val) {
	    this.addressLine1 = val;
	    return this;
	}

	public Builder addressLine2(String val) {
	    this.addressLine2 = val;
	    return this;
	}

	public Builder addressLine3(String val) {
	    this.addressLine3 = val;
	    return this;
	}

	public Builder addressEmail(String val) {
	    this.addressEmail = val;
	    return this;
	}

	public Builder addressPhoneNumber(String val) {
	    this.addressPhoneNumber = val;
	    return this;
	}

	public Builder city(String val) {
	    this.city = val;
	    return this;
	}

	public Builder country(String val) {
	    this.country = val;
	    return this;
	}

	public Builder zipCode(String val) {
	    this.zipCode = val;
	    return this;
	}

	public Builder isActive(String val) {
	    this.isActive = val;
	    return this;
	}

	public CIAddress build() {
	    return new CIAddress(this);
	}
    }

}
