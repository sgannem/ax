package com.nxp.appxplorer.services.model.beans;

import java.util.Arrays;

public class ViewAccountDetailsResponse {

    private String userName;
    private String accountType;
    private String loginType;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String companyName;
    private String companyAddress;
    private String zipCode;
    private String country;
    private String businessSegment;
    private byte[] companyOrBusinessLogo;
    private byte[] uploadYourTermsAndConditions;

    public ViewAccountDetailsResponse() {

    }

    public ViewAccountDetailsResponse(Builder builder) {
	this.userName = builder.userName;
	this.accountType = builder.accountType;
	this.loginType = builder.loginType;
	this.userId = builder.userId;
	this.firstName = builder.firstName;
	this.lastName = builder.lastName;
	this.email = builder.email;
	this.password = builder.password;
	this.companyName = builder.companyName;
	this.companyAddress = builder.companyAddress;
	this.zipCode = builder.zipCode;
	this.country = builder.country;
	this.businessSegment = builder.businessSegment;
	this.companyOrBusinessLogo = builder.companyOrBusinessLogo;
	this.uploadYourTermsAndConditions = builder.uploadYourTermsAndConditions;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
	return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
	this.userName = userName;
    }

    /**
     * @return the accountType
     */
    public String getAccountType() {
	return accountType;
    }

    /**
     * @param accountType
     *            the accountType to set
     */
    public void setAccountType(String accountType) {
	this.accountType = accountType;
    }

    /**
     * @return the loginType
     */
    public String getLoginType() {
	return loginType;
    }

    /**
     * @param loginType
     *            the loginType to set
     */
    public void setLoginType(String loginType) {
	this.loginType = loginType;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
	return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
	this.userId = userId;
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
     * @return the password
     */
    public String getPassword() {
	return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
	this.password = password;
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
     * @return the companyAddress
     */
    public String getCompanyAddress() {
	return companyAddress;
    }

    /**
     * @param companyAddress
     *            the companyAddress to set
     */
    public void setCompanyAddress(String companyAddress) {
	this.companyAddress = companyAddress;
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
     * @return the businessSegment
     */
    public String getBusinessSegment() {
	return businessSegment;
    }

    /**
     * @param businessSegment
     *            the businessSegment to set
     */
    public void setBusinessSegment(String businessSegment) {
	this.businessSegment = businessSegment;
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
     * @return the uploadYourTermsAndConditions
     */
    public byte[] getUploadYourTermsAndConditions() {
	return uploadYourTermsAndConditions;
    }

    /**
     * @param uploadYourTermsAndConditions
     *            the uploadYourTermsAndConditions to set
     */
    public void setUploadYourTermsAndConditions(byte[] uploadYourTermsAndConditions) {
	this.uploadYourTermsAndConditions = uploadYourTermsAndConditions;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ViewAccountDetailsResponse [userName=" + userName + ", accountType=" + accountType + ", loginType="
		+ loginType + ", userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
		+ email + ", password=" + password + ", companyName=" + companyName + ", companyAddress="
		+ companyAddress + ", zipCode=" + zipCode + ", country=" + country + ", businessSegment="
		+ businessSegment + ", companyOrBusinessLogo=" + Arrays.toString(companyOrBusinessLogo)
		+ ", uploadYourTermsAndConditions=" + Arrays.toString(uploadYourTermsAndConditions) + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {
	private String userName;
	private String accountType;
	private String loginType;
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String companyName;
	private String companyAddress;
	private String zipCode;
	private String country;
	private String businessSegment;
	private byte[] companyOrBusinessLogo;
	private byte[] uploadYourTermsAndConditions;

	public Builder userName(String val) {
	    this.userName = val;
	    return this;
	}

	public Builder accountType(String val) {
	    this.accountType = val;
	    return this;
	}

	public Builder loginType(String val) {
	    this.loginType = val;
	    return this;
	}

	public Builder userId(String val) {
	    this.userId = val;
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

	public Builder password(String val) {
	    this.password = val;
	    return this;
	}

	public Builder companyName(String val) {
	    this.companyName = val;
	    return this;
	}

	public Builder companyAddress(String val) {
	    this.companyAddress = val;
	    return this;
	}

	public Builder zipCode(String val) {
	    this.zipCode = val;
	    return this;
	}

	public Builder country(String val) {
	    this.country = val;
	    return this;
	}

	public Builder businessSegment(String val) {
	    this.businessSegment = val;
	    return this;
	}

	public Builder companyOrBusinessLogo(byte[] val) {
	    this.companyOrBusinessLogo = val;
	    return this;
	}

	public Builder uploadYourTermsAndConditions(byte[] val) {
	    this.uploadYourTermsAndConditions = val;
	    return this;
	}

	public ViewAccountDetailsResponse build() {
	    return new ViewAccountDetailsResponse(this);
	}
    }

}
