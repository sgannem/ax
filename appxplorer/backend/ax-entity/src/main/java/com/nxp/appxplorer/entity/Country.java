package com.nxp.appxplorer.entity;

import java.util.Objects;

import javax.persistence.Entity;

/**
 * This class maps AddressType table into the Data Base.
 * 
 */
@Entity
public class Country extends AbstractStrongShortEntity {

    private String countryName;
    private String alpha2ISOCountry;
    private String alpha3ISOCountry;
    private String isActive;

    Country() {
	super();
    }

    private Country(Builder builder) {
	setId(builder.id);
	this.countryName = builder.countryName;
	this.alpha2ISOCountry = builder.alpha2ISOCountry;
	this.alpha3ISOCountry = builder.alpha3ISOCountry;
	this.isActive = builder.isActive;
    }

    /**
     * @return the countryName
     */
    public String getCountryName() {
	return countryName;
    }

    /**
     * @param countryName
     *            the countryName to set
     */
    public void setCountryName(String countryName) {
	this.countryName = countryName;
    }

    /**
     * @return the alpha2ISOCountry
     */
    public String getAlpha2ISOCountry() {
	return alpha2ISOCountry;
    }

    /**
     * @param alpha2isoCountry
     *            the alpha2ISOCountry to set
     */
    public void setAlpha2ISOCountry(String alpha2isoCountry) {
	alpha2ISOCountry = alpha2isoCountry;
    }

    /**
     * @return the alpha3ISOCountry
     */
    public String getAlpha3ISOCountry() {
	return alpha3ISOCountry;
    }

    /**
     * @param alpha3isoCountry
     *            the alpha3ISOCountry to set
     */
    public void setAlpha3ISOCountry(String alpha3isoCountry) {
	alpha3ISOCountry = alpha3isoCountry;
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

	final Country that = (Country) o;
	return Objects.equals(getId(), that.getId());
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
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Country [countryName=" + countryName + ", alpha2ISOCountry=" + alpha2ISOCountry + ", alpha3ISOCountry="
		+ alpha3ISOCountry + ", isActive=" + isActive + "]";
    }

    /**
     * It builds Country Builder object.
     * 
     * @return
     */
    public static Builder builder() {
	return new Builder();
    }

    /**
     * This Builder class helps to build Country objects.
     * 
     */
    public static class Builder {
	private Short id;
	private String countryName;
	private String alpha2ISOCountry;
	private String alpha3ISOCountry;
	private String isActive;

	public Builder id(Short val) {
	    this.id = val;
	    return this;
	}

	public Builder countryName(String val) {
	    this.countryName = val;
	    return this;
	}

	public Builder alpha2ISOCountry(String val) {
	    this.alpha2ISOCountry = val;
	    return this;
	}

	public Builder alpha3ISOCountry(String val) {
	    this.alpha3ISOCountry = val;
	    return this;
	}

	public Builder isActive(String val) {
	    this.isActive = val;
	    return this;
	}

	public Country build() {
	    return new Country(this);
	}

    }

}
