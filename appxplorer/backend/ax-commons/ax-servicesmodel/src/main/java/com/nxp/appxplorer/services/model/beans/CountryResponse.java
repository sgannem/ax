package com.nxp.appxplorer.services.model.beans;

import java.util.List;

public class CountryResponse {

    private List<Country> countries;

    public CountryResponse() {

    }

    public CountryResponse(Builder builder) {
	this.countries = builder.countries;
    }

    /**
     * @return the countries
     */
    public List<Country> getCountries() {
	return countries;
    }

    /**
     * @param countries
     *            the countries to set
     */
    public void setCountries(List<Country> countries) {
	this.countries = countries;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "CountryResponse [countries=" + countries + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private List<Country> countries;

	public Builder countries(List<Country> val) {
	    this.countries = val;
	    return this;
	}

	public CountryResponse build() {
	    return new CountryResponse(this);
	}
    }

}
