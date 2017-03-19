package com.nxp.appxplorer.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class maps all application provider attributes from application to Data
 * base table.
 * 
 * @author nxa30710
 *
 */
@Entity
public class ApplicationProvider extends AbstractStrongEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    private short registrationStatusId;
    private long registrationConfirmOn;
    private short businessSegmentId;
    private byte[] companyOrBusinessLogo;
    private String isActive;
    private ApplicationProviderTnc applicationProviderTnc;
    private Set<APAddress> addresses = new HashSet<>();
    private Set<Application> applications = new HashSet<>();
    private Set<APAddress> apAddress = new HashSet<>();

    ApplicationProvider() {

    }

    private ApplicationProvider(Builder builder) {
	this.firstName = builder.firstName;
	this.lastName = builder.lastName;
	this.email = builder.email;
	this.companyName = builder.companyName;
	this.registrationStatusId = builder.registrationStatusId;
	this.registrationConfirmOn = builder.registrationConfirmOn;
	this.isActive = builder.isActive;
	this.businessSegmentId = builder.businessSegmentId;
	this.companyOrBusinessLogo = builder.companyOrBusinessLogo;

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

    /**
     * @return the applicationProviderTnc
     */
    @OneToOne(mappedBy = "applicationProvider")
    public ApplicationProviderTnc getApplicationProviderTnc() {
	return applicationProviderTnc;
    }

    /**
     * @param applicationProviderTnc
     *            the applicationProviderTnc to set
     */
    public void setApplicationProviderTnc(ApplicationProviderTnc applicationProviderTnc) {
	this.applicationProviderTnc = applicationProviderTnc;
    }

    /**
     * @return the addresses
     */
    @OneToMany(mappedBy = "applicationProvider")
    public Set<APAddress> getAddresses() {
	return addresses;
    }

    /**
     * @param addresses
     *            the addresses to set
     */
    public void setAddresses(Set<APAddress> addresses) {
	this.addresses = addresses;
    }

    /**
     * @return the applications
     */
    @OneToMany(mappedBy = "applicationProvider")
    public Set<Application> getApplications() {
	return applications;
    }

    /**
     * @param applications
     *            the applications to set
     */
    public void setApplications(Set<Application> applications) {
	this.applications = applications;
    }

    /**
     * @return the apAddress
     */
    @JsonIgnore
    @OneToMany(mappedBy = "applicationProvider")
    @JsonIgnoreProperties(value = "ApplicationProvider")
    public Set<APAddress> getApAddress() {
	return apAddress;
    }

    /**
     * @param apAddress
     *            the apAddress to set
     */
    public void setApAddress(Set<APAddress> apAddress) {
	this.apAddress = apAddress;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ApplicationProvider [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
		+ ", companyName=" + companyName + ", registrationStatusId=" + registrationStatusId
		+ ", registrationConfirmOn=" + registrationConfirmOn + ", businessSegmentId=" + businessSegmentId
		+ ", companyOrBusinessLogo=" + Arrays.toString(companyOrBusinessLogo) + ", isActive=" + isActive
		+ ", applicationProviderTnc=" + applicationProviderTnc + ", addresses=" + addresses + ", applications="
		+ applications + ", apAddress=" + apAddress + "]";
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

	final ApplicationProvider that = (ApplicationProvider) o;
	return Objects.equals(getId(), that.getId());
    }

    public static Builder builder() {
	return new Builder();
    }

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

	public Builder businessSegmentId(Short val) {
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

	public ApplicationProvider build() {
	    return new ApplicationProvider(this);
	}

    }

}
