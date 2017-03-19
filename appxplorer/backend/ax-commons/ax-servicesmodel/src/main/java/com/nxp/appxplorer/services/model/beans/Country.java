package com.nxp.appxplorer.services.model.beans;

public class Country {

    private String twoCharIsoCountryCode;
    private String threeCharIsoCountryCode;
    private String countryName;
    private String description;

    public Country() {

    }

    public Country(Builder builder) {
	this.twoCharIsoCountryCode = builder.twoCharIsoCountryCode;
	this.threeCharIsoCountryCode = builder.threeCharIsoCountryCode;
	this.countryName = builder.countryName;
	this.description = builder.description;

    }

    /**
     * @return the twoCharIsoCountryCode
     */
    public String getTwoCharIsoCountryCode() {
	return twoCharIsoCountryCode;
    }

    /**
     * @param twoCharIsoCountryCode
     *            the twoCharIsoCountryCode to set
     */
    public void setTwoCharIsoCountryCode(String twoCharIsoCountryCode) {
	this.twoCharIsoCountryCode = twoCharIsoCountryCode;
    }

    /**
     * @return the threeCharIsoCountryCode
     */
    public String getThreeCharIsoCountryCode() {
	return threeCharIsoCountryCode;
    }

    /**
     * @param threeCharIsoCountryCode
     *            the threeCharIsoCountryCode to set
     */
    public void setThreeCharIsoCountryCode(String threeCharIsoCountryCode) {
	this.threeCharIsoCountryCode = threeCharIsoCountryCode;
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
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Country [twoCharIsoCountryCode=" + twoCharIsoCountryCode + ", threeCharIsoCountryCode="
		+ threeCharIsoCountryCode + ", countryName=" + countryName + ", description=" + description + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {
	private String twoCharIsoCountryCode;
	private String threeCharIsoCountryCode;
	private String countryName;
	private String description;

	public Builder twoCharIsoCountryCode(String val) {
	    this.twoCharIsoCountryCode = val;
	    return this;
	}

	public Builder threeCharIsoCountryCode(String val) {
	    this.threeCharIsoCountryCode = val;
	    return this;
	}

	public Builder countryName(String val) {
	    this.countryName = val;
	    return this;
	}

	public Builder description(String val) {
	    this.description = val;
	    return this;
	}

	public Country build() {
	    return new Country(this);
	}
    }

}
