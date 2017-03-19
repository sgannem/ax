package com.nxp.appxplorer.entity;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * This class maps all application attributes from application to Data base
 * table.
 * 
 * @author nxa30710
 *
 */
@Entity
public class Application extends AbstractStrongEntity {

    private ApplicationProvider applicationProvider;
    private String providerName;
    private String contactEmail;
    private String phoneNumber;
    private Short businessSegmentId;
    private byte[] companyPromotionalLogo;
    private String applicationName;
    private String shortDescription;
    private String detailedDescription;
    private Long applicationId;
    private Short numberOfKeys;
    private String keepDefaultKeys;
    private String changeMasterKey;
    private Short applicationSizeInBytes;
    private String dfName;
    private String isoFileId;
    private String persoViaNxpAppxplorer;
    private String encryptedPersonalizationBasedOnUid;
    private String persoUsingOwnMobileApplication;
    private String endpointForSessionEstablishment;
    private String personalizationUrl;
    private String deletionUrl;
    private String isDraft;
    private String isActive;

    Application() {

    }

    private Application(Builder builder) {
	this.applicationProvider = builder.applicationProvider;
	this.providerName = builder.providerName;
	this.contactEmail = builder.contactEmail;
	this.phoneNumber = builder.phoneNumber;
	this.businessSegmentId = builder.businessSegmentId;
	this.companyPromotionalLogo = builder.companyPromotionalLogo;
	this.applicationName = builder.applicationName;
	this.shortDescription = builder.shortDescription;
	this.detailedDescription = builder.detailedDescription;
	this.applicationId = builder.applicationId;
	this.numberOfKeys = builder.numberOfKeys;
	this.keepDefaultKeys = builder.keepDefaultKeys;
	this.changeMasterKey = builder.changeMasterKey;
	this.applicationSizeInBytes = builder.applicationSizeInBytes;
	this.dfName = builder.dfName;
	this.isoFileId = builder.isoFileId;
	this.persoViaNxpAppxplorer = builder.persoViaNxpAppxplorer;
	this.encryptedPersonalizationBasedOnUid = builder.encryptedPersonalizationBasedOnUid;
	this.persoUsingOwnMobileApplication = builder.persoUsingOwnMobileApplication;
	this.endpointForSessionEstablishment = builder.endpointForSessionEstablishment;
	this.personalizationUrl = builder.personalizationUrl;
	this.deletionUrl = builder.deletionUrl;
	this.isDraft = builder.isDraft;
	this.isActive = builder.isActive;
    }

    /**
     * @return the applicationProvider
     */
    @ManyToOne(optional = false, targetEntity = ApplicationProvider.class)
    @JoinColumn(name = "applicationProviderId", insertable = false, updatable = false)
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
     * @return the providerName
     */
    public String getProviderName() {
	return providerName;
    }

    /**
     * @param providerName
     *            the providerName to set
     */
    public void setProviderName(String providerName) {
	this.providerName = providerName;
    }

    /**
     * @return
     */
    public String getContactEmail() {
	return contactEmail;
    }

    /**
     * @param contactEmail
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
     * @return the applicationName
     */
    public String getApplicationName() {
	return applicationName;
    }

    /**
     * @param applicationName
     *            the applicationName to set
     */
    public void setApplicationName(String applicationName) {
	this.applicationName = applicationName;
    }

    /**
     * @return the shortDescription
     */
    public String getShortDescription() {
	return shortDescription;
    }

    /**
     * @param shortDescription
     *            the shortDescription to set
     */
    public void setShortDescription(String shortDescription) {
	this.shortDescription = shortDescription;
    }

    /**
     * @return the detailedDescription
     */
    public String getDetailedDescription() {
	return detailedDescription;
    }

    /**
     * @param detailedDescription
     *            the detailedDescription to set
     */
    public void setDetailedDescription(String detailedDescription) {
	this.detailedDescription = detailedDescription;
    }

    /**
     * @return the applicationId
     */
    public Long getApplicationId() {
	return applicationId;
    }

    /**
     * @param applicationId
     *            the applicationId to set
     */
    public void setApplicationId(Long applicationId) {
	this.applicationId = applicationId;
    }

    /**
     * @return the numberOfKeys
     */
    public Short getNumberOfKeys() {
	return numberOfKeys;
    }

    /**
     * @param numberOfKeys
     *            the numberOfKeys to set
     */
    public void setNumberOfKeys(Short numberOfKeys) {
	this.numberOfKeys = numberOfKeys;
    }

    /**
     * @return the keepDefaultKeys
     */
    public String getKeepDefaultKeys() {
	return keepDefaultKeys;
    }

    /**
     * @param keepDefaultKeys
     *            the keepDefaultKeys to set
     */
    public void setKeepDefaultKeys(String keepDefaultKeys) {
	this.keepDefaultKeys = keepDefaultKeys;
    }

    /**
     * @return the changeMasterKey
     */
    public String getChangeMasterKey() {
	return changeMasterKey;
    }

    /**
     * @param changeMasterKey
     *            the changeMasterKey to set
     */
    public void setChangeMasterKey(String changeMasterKey) {
	this.changeMasterKey = changeMasterKey;
    }

    /**
     * @return the applicationSizeInBytes
     */
    public Short getApplicationSizeInBytes() {
	return applicationSizeInBytes;
    }

    /**
     * @param applicationSizeInBytes
     *            the applicationSizeInBytes to set
     */
    public void setApplicationSizeInBytes(Short applicationSizeInBytes) {
	this.applicationSizeInBytes = applicationSizeInBytes;
    }

    /**
     * @return the dfName
     */
    public String getDfName() {
	return dfName;
    }

    /**
     * @param dfName
     *            the dfName to set
     */
    public void setDfName(String dfName) {
	this.dfName = dfName;
    }

    /**
     * @return the isoFileId
     */
    public String getIsoFileId() {
	return isoFileId;
    }

    /**
     * @param isoFileId
     *            the isoFileId to set
     */
    public void setIsoFileId(String isoFileId) {
	this.isoFileId = isoFileId;
    }

    /**
     * @return the persoViaNxpAppxplorer
     */
    public String getPersoViaNxpAppxplorer() {
	return persoViaNxpAppxplorer;
    }

    /**
     * @param persoViaNxpAppxplorer
     *            the persoViaNxpAppxplorer to set
     */
    public void setPersoViaNxpAppxplorer(String persoViaNxpAppxplorer) {
	this.persoViaNxpAppxplorer = persoViaNxpAppxplorer;
    }

    /**
     * @return the encryptedPersonalizationBasedOnUid
     */
    public String getEncryptedPersonalizationBasedOnUid() {
	return encryptedPersonalizationBasedOnUid;
    }

    /**
     * @param encryptedPersonalizationBasedOnUid
     *            the encryptedPersonalizationBasedOnUid to set
     */
    public void setEncryptedPersonalizationBasedOnUid(String encryptedPersonalizationBasedOnUid) {
	this.encryptedPersonalizationBasedOnUid = encryptedPersonalizationBasedOnUid;
    }

    /**
     * @return the persoUsingOwnMobileApplication
     */
    public String getPersoUsingOwnMobileApplication() {
	return persoUsingOwnMobileApplication;
    }

    /**
     * @param persoUsingOwnMobileApplication
     *            the persoUsingOwnMobileApplication to set
     */
    public void setPersoUsingOwnMobileApplication(String persoUsingOwnMobileApplication) {
	this.persoUsingOwnMobileApplication = persoUsingOwnMobileApplication;
    }

    /**
     * @return the endpointForSessionEstablishment
     */
    public String getEndpointForSessionEstablishment() {
	return endpointForSessionEstablishment;
    }

    /**
     * @param endpointForSessionEstablishment
     *            the endpointForSessionEstablishment to set
     */
    public void setEndpointForSessionEstablishment(String endpointForSessionEstablishment) {
	this.endpointForSessionEstablishment = endpointForSessionEstablishment;
    }

    /**
     * @return the personalizationUrl
     */
    public String getPersonalizationUrl() {
	return personalizationUrl;
    }

    /**
     * @param personalizationUrl
     *            the personalizationUrl to set
     */
    public void setPersonalizationUrl(String personalizationUrl) {
	this.personalizationUrl = personalizationUrl;
    }

    /**
     * @return the deletionUrl
     */
    public String getDeletionUrl() {
	return deletionUrl;
    }

    /**
     * @param deletionUrl
     *            the deletionUrl to set
     */
    public void setDeletionUrl(String deletionUrl) {
	this.deletionUrl = deletionUrl;
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

	final Application that = (Application) o;
	return Objects.equals(getId(), that.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Application [applicationProvider=" + applicationProvider + ", providerName=" + providerName
		+ ", contactEmailAddress=" + contactEmail + ", phoneNumber=" + phoneNumber + ", businessSegmentId="
		+ businessSegmentId + ", companyPromotionalLogo=" + Arrays.toString(companyPromotionalLogo)
		+ ", applicationName=" + applicationName + ", shortDescription=" + shortDescription
		+ ", detailedDescription=" + detailedDescription + ", applicationId=" + applicationId
		+ ", numberOfKeys=" + numberOfKeys + ", keepDefaultKeys=" + keepDefaultKeys + ", changeMasterKey="
		+ changeMasterKey + ", applicationSizeInBytes=" + applicationSizeInBytes + ", dfName=" + dfName
		+ ", isoFileId=" + isoFileId + ", persoViaNxpAppxplorer=" + persoViaNxpAppxplorer
		+ ", encryptedPersonalizationBasedOnUid=" + encryptedPersonalizationBasedOnUid
		+ ", persoUsingOwnMobileApplication=" + persoUsingOwnMobileApplication
		+ ", endpointForSessionEstablishment=" + endpointForSessionEstablishment + ", personalizationUrl="
		+ personalizationUrl + ", deletionUrl=" + deletionUrl + ", isActive=" + isActive + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    /**
     * Its a builder class to Build Application attributes.
     * 
     * @author nxa30710
     *
     */
    public static class Builder {

	private ApplicationProvider applicationProvider;
	private String providerName;
	private String contactEmail;
	private String phoneNumber;
	private Short businessSegmentId;
	private byte[] companyPromotionalLogo;
	private String applicationName;
	private String shortDescription;
	private String detailedDescription;
	private Long applicationId;
	private Short numberOfKeys;
	private String keepDefaultKeys;
	private String changeMasterKey;
	private Short applicationSizeInBytes;
	private String dfName;
	private String isoFileId;
	private String persoViaNxpAppxplorer;
	private String encryptedPersonalizationBasedOnUid;
	private String persoUsingOwnMobileApplication;
	private String endpointForSessionEstablishment;
	private String personalizationUrl;
	private String deletionUrl;
	private String isDraft;
	private String isActive;

	public Builder applicationProvider(ApplicationProvider val) {
	    this.applicationProvider = val;
	    return this;
	}

	public Builder providerName(String val) {
	    this.providerName = val;
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

	public Builder applicationName(String val) {
	    this.applicationName = val;
	    return this;
	}

	public Builder shortDescription(String val) {
	    this.shortDescription = val;
	    return this;
	}

	public Builder detailedDescription(String val) {
	    this.detailedDescription = val;
	    return this;
	}

	public Builder applicationId(Long val) {
	    this.applicationId = val;
	    return this;
	}

	public Builder numberOfKeys(Short val) {
	    this.numberOfKeys = val;
	    return this;
	}

	public Builder keepDefaultKeys(String val) {
	    this.keepDefaultKeys = val;
	    return this;
	}

	public Builder changeMasterKey(String val) {
	    this.changeMasterKey = val;
	    return this;
	}

	public Builder applicationSizeInBytes(Short val) {
	    this.applicationSizeInBytes = val;
	    return this;
	}

	public Builder dfName(String val) {
	    this.dfName = val;
	    return this;
	}

	public Builder isoFileId(String val) {
	    this.isoFileId = val;
	    return this;
	}

	public Builder persoViaNxpAppxplorer(String val) {
	    this.persoViaNxpAppxplorer = val;
	    return this;
	}

	public Builder encryptedPersonalizationBasedOnUid(String val) {
	    this.encryptedPersonalizationBasedOnUid = val;
	    return this;
	}

	public Builder persoUsingOwnMobileApplication(String val) {
	    this.persoUsingOwnMobileApplication = val;
	    return this;
	}

	public Builder endpointForSessionEstablishment(String val) {
	    this.endpointForSessionEstablishment = val;
	    return this;
	}

	public Builder personalizationUrl(String val) {
	    this.personalizationUrl = val;
	    return this;
	}

	public Builder deletionUrl(String val) {
	    this.deletionUrl = val;
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

	public Application build() {
	    return new Application(this);
	}

    }

}
