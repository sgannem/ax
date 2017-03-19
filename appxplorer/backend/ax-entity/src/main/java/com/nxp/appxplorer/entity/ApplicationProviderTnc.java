package com.nxp.appxplorer.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * This class maps all CardIssuerTnc attributes from application to Data base
 * table.
 * 
 * @author nxa30710
 *
 */
@Entity
public class ApplicationProviderTnc extends AbstractStrongEntity {

    private ApplicationProvider applicationProvider;
    private byte[] tnc;
    private String isActive;

    public ApplicationProviderTnc() {
	super();
    }

    private ApplicationProviderTnc(Builder builder) {
	this.tnc = builder.tnc;
	this.isActive = builder.isActive;
    }

    // /**
    // * @return the cardIssuer
    // */
    // // @OneToOne(optional = false, targetEntity = CardIssuer.class)
    // // @JoinColumn(name = "cardIssuerId", insertable = false, updatable =
    // false)
    // // @OneToOne(cascade = CascadeType.ALL)
    // // @JoinColumn(name = "cardIssuerId")
    // @OneToOne
    // @JoinColumn(name = "cardIssuerId")
    // public CardIssuer getCardIssuer() {
    // return cardIssuer;
    // }

    // /**
    // * @param cardIssuer
    // * the cardIssuer to set
    // */
    // public void setCardIssuer(CardIssuer cardIssuer) {
    // this.cardIssuer = cardIssuer;
    // }

    /**
     * @return the applicationProvider
     */
    @OneToOne
    @JoinColumn(name = "applicationProviderId")
    public ApplicationProvider getApplicationProvider() {
	return applicationProvider;
    }

    /**
     * @param applicationProvider
     *            the applicationProvider to set
     */
    public void setApplicationProvider(ApplicationProvider applicationProvider) {
	this.applicationProvider = applicationProvider;
    }

    /**
     * @return the tnc
     */
    public byte[] getTnc() {
	return tnc;
    }

    /**
     * @param tnc
     *            the tnc to set
     */
    public void setTnc(byte[] tnc) {
	this.tnc = tnc;
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

	final ApplicationProviderTnc that = (ApplicationProviderTnc) o;
	return Objects.equals(getId(), that.getId());
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private byte[] tnc;
	private String isActive;

	public Builder tnc(byte[] tnc) {
	    this.tnc = tnc;
	    return this;
	}

	public Builder isActive(String isActive) {
	    this.isActive = isActive;
	    return this;
	}

	public ApplicationProviderTnc build() {
	    return new ApplicationProviderTnc(this);
	}

    }

}
