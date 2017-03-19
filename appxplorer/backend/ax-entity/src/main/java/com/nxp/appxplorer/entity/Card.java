package com.nxp.appxplorer.entity;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class maps CardIssuer table into the Data Base.
 * 
 */
@Entity
public class Card extends AbstractStrongEntity {

    private String cardName;
    private String contactEmail;
    private String phoneNumber;
    private Short businessSegmentId;
    private byte[] companyPromotionalLogo;
    private Short smartMediumTypeId;
    private Short technologyUsedId;
    private Short smartMediumSizeId;
    private String ndefToken;
    private CardIssuer cardIssuer;
    private String isDraft;
    private String isActive;

    Card() {
	super();
    }

    private Card(Builder builder) {
	this.cardName = builder.cardName;
	this.contactEmail = builder.contactEmail;
	this.phoneNumber = builder.phoneNumber;
	this.businessSegmentId = builder.businessSegmentId;
	this.companyPromotionalLogo = builder.companyPromotionalLogo;
	this.smartMediumTypeId = builder.smartMediumTypeId;
	this.technologyUsedId = builder.technologyUsedId;
	this.smartMediumSizeId = builder.smartMediumSizeId;
	this.ndefToken = builder.ndefToken;
	this.cardIssuer = builder.cardIssuer;
	this.isDraft = builder.isDraft;
	this.isActive = builder.isActive;
    }

    /**
     * @return the cardName
     */
    public String getCardName() {
	return cardName;
    }

    /**
     * @param cardName
     *            the cardName to set
     */
    public void setCardName(String cardName) {
	this.cardName = cardName;
    }

    /**
     * @return the contactEmail
     */
    public String getContactEmail() {
	return contactEmail;
    }

    /**
     * @param contactEmail
     *            the contactEmail to set
     */
    public void setContactEmail(String contactEmail) {
	this.contactEmail = contactEmail;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
	return phoneNumber;
    }

    /**
     * @param phoneNumber
     *            the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    /**
     * @return the businessSegmentId
     */
    public Short getBusinessSegmentId() {
	return businessSegmentId;
    }

    /**
     * @param businessSegmentId
     *            the businessSegmentId to set
     */
    public void setBusinessSegmentId(Short businessSegmentId) {
	this.businessSegmentId = businessSegmentId;
    }

    /**
     * @return the companyPromotionalLogo
     */
    public byte[] getCompanyPromotionalLogo() {
	return companyPromotionalLogo;
    }

    /**
     * @param companyPromotionalLogo
     *            the companyPromotionalLogo to set
     */
    public void setCompanyPromotionalLogo(byte[] companyPromotionalLogo) {
	this.companyPromotionalLogo = companyPromotionalLogo;
    }

    /**
     * @return the smartMediumTypeId
     */
    public Short getSmartMediumTypeId() {
	return smartMediumTypeId;
    }

    /**
     * @param smartMediumTypeId
     *            the smartMediumTypeId to set
     */
    public void setSmartMediumTypeId(Short smartMediumTypeId) {
	this.smartMediumTypeId = smartMediumTypeId;
    }

    /**
     * @return the technologyUsedId
     */
    public Short getTechnologyUsedId() {
	return technologyUsedId;
    }

    /**
     * @param technologyUsedId
     *            the technologyUsedId to set
     */
    public void setTechnologyUsedId(Short technologyUsedId) {
	this.technologyUsedId = technologyUsedId;
    }

    /**
     * @return the smartMediumSizeId
     */
    public Short getSmartMediumSizeId() {
	return smartMediumSizeId;
    }

    /**
     * @param smartMediumSizeId
     *            the smartMediumSizeId to set
     */
    public void setSmartMediumSizeId(Short smartMediumSizeId) {
	this.smartMediumSizeId = smartMediumSizeId;
    }

    /**
     * @return the ndefToken
     */
    public String getNdefToken() {
	return ndefToken;
    }

    /**
     * @param ndefToken
     *            the ndefToken to set
     */
    public void setNdefToken(String ndefToken) {
	this.ndefToken = ndefToken;
    }

    /**
     * @return the cardIssuer
     */
    // @ManyToOne(fetch=FetchType.LAZY)
    // @JoinColumn(name="cardIssuerId", insertable=false, updatable=false)
    @ManyToOne(optional = false, targetEntity = CardIssuer.class)
    @JoinColumn(name = "cardIssuerId", insertable = false, updatable = false)
    @JsonIgnoreProperties(value = "Card")
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
     * @return the isDraft
     */
    public String getIsDraft() {
	return isDraft;
    }

    /**
     * @param isDraft
     *            the isDraft to set
     */
    public void setIsDraft(String isDraft) {
	this.isDraft = isDraft;
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

	final Card that = (Card) o;
	return Objects.equals(getId(), that.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Card [cardName=" + cardName + ", contactEmail=" + contactEmail + ", phoneNumber=" + phoneNumber
		+ ", businessSegmentId=" + businessSegmentId + ", companyPromotionalLogo="
		+ Arrays.toString(companyPromotionalLogo) + ", smartMediumTypeId=" + smartMediumTypeId
		+ ", technologyUsedId=" + technologyUsedId + ", smartMediumSizeId=" + smartMediumSizeId + ", ndefToken="
		+ ndefToken + ", cardIssuer=" + cardIssuer + ", isActive=" + isActive + "]";
    }

    /**
     * It builds Card Builder object.
     * 
     * @return
     */
    public static Builder builder() {
	return new Builder();
    }

    /**
     * This Builder class helps to build Card Builder objects.
     * 
     */
    public static class Builder {
	private String cardName;
	private String contactEmail;
	private String phoneNumber;
	private Short businessSegmentId;
	private byte[] companyPromotionalLogo;
	private Short smartMediumTypeId;
	private Short technologyUsedId;
	private Short smartMediumSizeId;
	private String ndefToken;
	private CardIssuer cardIssuer;
	private String isDraft;
	private String isActive;

	public Builder cardName(String val) {
	    this.cardName = val;
	    return this;
	}

	public Builder contactEmail(String val) {
	    this.contactEmail = val;
	    return this;
	}

	public Builder phoneNumber(String val) {
	    this.phoneNumber = val;
	    return this;
	}

	public Builder businessSegmentId(Short val) {
	    this.businessSegmentId = val;
	    return this;
	}

	public Builder companyPromotionalLogo(byte[] val) {
	    this.companyPromotionalLogo = val;
	    return this;
	}

	public Builder smartMediumTypeId(Short val) {
	    this.smartMediumTypeId = val;
	    return this;
	}

	public Builder technologyUsedId(Short val) {
	    this.technologyUsedId = val;
	    return this;
	}

	public Builder smartMediumSizeId(Short val) {
	    this.smartMediumSizeId = val;
	    return this;
	}

	public Builder ndefToken(String val) {
	    this.ndefToken = val;
	    return this;
	}

	public Builder cardIssuer(CardIssuer val) {
	    this.cardIssuer = val;
	    return this;
	}

	public Builder isDraft(String val) {
	    this.isDraft = val;
	    return this;
	}

	public Builder isActive(String val) {
	    this.isActive = val;
	    return this;
	}

	public Card build() {
	    return new Card(this);
	}
    }
}
