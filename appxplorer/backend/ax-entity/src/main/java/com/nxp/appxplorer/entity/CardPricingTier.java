package com.nxp.appxplorer.entity;

import java.util.Objects;

import javax.persistence.Entity;

/**
 * This class maps all ApprovalType attributes from application to Data base
 * table.
 * 
 * @author nxa30710
 *
 */
@Entity
public class CardPricingTier extends AbstractStrongEntity {

    /**
     * <column name="pricingTierTypeId" type="BIGINT" /> <column name="cardId"
     * type="BIGINT" /> <column name="numberOfInstalls" type="BIGINT" /> <column
     * name="pricePerInstall" type="NUMBER(5,5)" /> <column name=
     * "isoCurrencyCode" type="VARCHAR(3)" /> <column name=
     * "cardPartitionDetailsId" type="BIGINT" />
     */

    private PricingTierType pricingTierType;
    private Card card;
    private long numberOfInstalls;
    private double pricePerInstall;
    private String isoCurrencyCode;
    private long cardPartitionDetailsId;
    private String isActive;

    CardPricingTier() {

    }

    private CardPricingTier(Builder builder) {
	this.card = builder.card;
	this.numberOfInstalls = builder.numberOfInstalls;
	this.pricePerInstall = builder.pricePerInstall;
	this.isoCurrencyCode = builder.isoCurrencyCode;
	this.cardPartitionDetailsId = builder.cardPartitionDetailsId;
	this.isActive = builder.isActive;
    }

    /**
     * @return the pricingTierType
     */
    public PricingTierType getPricingTierType() {
	return pricingTierType;
    }

    /**
     * @param pricingTierType
     *            the pricingTierType to set
     */
    public void setPricingTierType(PricingTierType pricingTierType) {
	this.pricingTierType = pricingTierType;
    }

    /**
     * @return the card
     */
    public Card getCard() {
	return card;
    }

    /**
     * @param card
     *            the card to set
     */
    public void setCard(Card card) {
	this.card = card;
    }

    /**
     * @return the numberOfInstalls
     */
    public long getNumberOfInstalls() {
	return numberOfInstalls;
    }

    /**
     * @param numberOfInstalls
     *            the numberOfInstalls to set
     */
    public void setNumberOfInstalls(long numberOfInstalls) {
	this.numberOfInstalls = numberOfInstalls;
    }

    /**
     * @return the pricePerInstall
     */
    public double getPricePerInstall() {
	return pricePerInstall;
    }

    /**
     * @param pricePerInstall
     *            the pricePerInstall to set
     */
    public void setPricePerInstall(double pricePerInstall) {
	this.pricePerInstall = pricePerInstall;
    }

    /**
     * @return the isoCurrencyCode
     */
    public String getIsoCurrencyCode() {
	return isoCurrencyCode;
    }

    /**
     * @param isoCurrencyCode
     *            the isoCurrencyCode to set
     */
    public void setIsoCurrencyCode(String isoCurrencyCode) {
	this.isoCurrencyCode = isoCurrencyCode;
    }

    /**
     * @return the cardPartitionDetailsId
     */
    public long getCardPartitionDetailsId() {
	return cardPartitionDetailsId;
    }

    /**
     * @param cardPartitionDetailsId
     *            the cardPartitionDetailsId to set
     */
    public void setCardPartitionDetailsId(long cardPartitionDetailsId) {
	this.cardPartitionDetailsId = cardPartitionDetailsId;
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
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "CardPricingTier [pricingTierType=" + pricingTierType + ", card=" + card + ", numberOfInstalls="
		+ numberOfInstalls + ", pricePerInstall=" + pricePerInstall + ", isoCurrencyCode=" + isoCurrencyCode
		+ ", cardPartitionDetailsId=" + cardPartitionDetailsId + ", isActive=" + isActive + "]";
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

	final CardPricingTier that = (CardPricingTier) o;
	return Objects.equals(getId(), that.getId());
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private Card card;
	private long numberOfInstalls;
	private double pricePerInstall;
	private String isoCurrencyCode;
	private long cardPartitionDetailsId;
	private String isActive;

	public Builder card(Card val) {
	    this.card = val;
	    return this;
	}

	public Builder numberOfInstalls(long val) {
	    this.numberOfInstalls = val;
	    return this;
	}

	public Builder pricePerInstall(double val) {
	    this.pricePerInstall = val;
	    return this;
	}

	public Builder isoCurrencyCode(String val) {
	    this.isoCurrencyCode = val;
	    return this;
	}

	public Builder cardPartitionDetailsId(long val) {
	    this.cardPartitionDetailsId = val;
	    return this;
	}

	public Builder isActive(String val) {
	    this.isActive = val;
	    return this;
	}

	public CardPricingTier build() {
	    return new CardPricingTier(this);
	}

    }

}
