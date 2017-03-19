package com.nxp.appxplorer.entity;

import java.util.Objects;

import javax.persistence.Entity;

/**
 * This class maps LoginTokenDetails table into the Data Base.
 * 
 * @author nxa30710
 *
 */
@Entity
public class LoginTokenDetails extends AbstractStrongEntity {

    private long loginDetailsId;
    private String bearerToken;
    private long bearerTokenCreatedOn;
    private long loginOn;
    private long logoutOn;
    private long expiryInSeconds;
    private String reserve1;
    private String reserve2;
    private String reserve3;
    private String isActive;

    LoginTokenDetails() {
	super();
    }

    private LoginTokenDetails(Builder builder) {
	this.loginDetailsId = builder.loginDetailsId;
	this.bearerToken = builder.bearerToken;
	this.bearerTokenCreatedOn = builder.bearerTokenCreatedOn;
	this.loginOn = builder.loginOn;
	this.logoutOn = builder.logoutOn;
	this.expiryInSeconds = builder.expiryInSeconds;
	this.reserve1 = builder.reserve1;
	this.reserve2 = builder.reserve2;
	this.reserve3 = builder.reserve3;
	this.isActive = builder.isActive;
    }

    /**
     * @return the loginDetailsId
     */
    public long getLoginDetailsId() {
	return loginDetailsId;
    }

    /**
     * @param loginDetailsId
     *            the loginDetailsId to set
     */
    public void setLoginDetailsId(long loginDetailsId) {
	this.loginDetailsId = loginDetailsId;
    }

    /**
     * @return the bearerToken
     */
    public String getBearerToken() {
	return bearerToken;
    }

    /**
     * @param bearerToken
     *            the bearerToken to set
     */
    public void setBearerToken(String bearerToken) {
	this.bearerToken = bearerToken;
    }

    /**
     * @return the bearerTokenCreatedOn
     */
    public long getBearerTokenCreatedOn() {
	return bearerTokenCreatedOn;
    }

    /**
     * @param bearerTokenCreatedOn
     *            the bearerTokenCreatedOn to set
     */
    public void setBearerTokenCreatedOn(long bearerTokenCreatedOn) {
	this.bearerTokenCreatedOn = bearerTokenCreatedOn;
    }

    /**
     * @return the loginOn
     */
    public long getLoginOn() {
	return loginOn;
    }

    /**
     * @param loginOn
     *            the loginOn to set
     */
    public void setLoginOn(long loginOn) {
	this.loginOn = loginOn;
    }

    /**
     * @return the logoutOn
     */
    public long getLogoutOn() {
	return logoutOn;
    }

    /**
     * @param logoutOn
     *            the logoutOn to set
     */
    public void setLogoutOn(long logoutOn) {
	this.logoutOn = logoutOn;
    }

    /**
     * @return the expiryInSeconds
     */
    public long getExpiryInSeconds() {
	return expiryInSeconds;
    }

    /**
     * @param expiryInSeconds
     *            the expiryInSeconds to set
     */
    public void setExpiryInSeconds(long expiryInSeconds) {
	this.expiryInSeconds = expiryInSeconds;
    }

    /**
     * @return the reserve1
     */
    public String getReserve1() {
	return reserve1;
    }

    /**
     * @param reserve1
     *            the reserve1 to set
     */
    public void setReserve1(String reserve1) {
	this.reserve1 = reserve1;
    }

    /**
     * @return the reserve2
     */
    public String getReserve2() {
	return reserve2;
    }

    /**
     * @param reserve2
     *            the reserve2 to set
     */
    public void setReserve2(String reserve2) {
	this.reserve2 = reserve2;
    }

    /**
     * @return the reserve3
     */
    public String getReserve3() {
	return reserve3;
    }

    /**
     * @param reserve3
     *            the reserve3 to set
     */
    public void setReserve3(String reserve3) {
	this.reserve3 = reserve3;
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

	final LoginTokenDetails that = (LoginTokenDetails) o;
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
	return "LoginTokenDetails [loginDetailsId=" + loginDetailsId + ", bearerToken=" + bearerToken
		+ ", bearerTokenCreatedOn=" + bearerTokenCreatedOn + ", loginOn=" + loginOn + ", logoutOn=" + logoutOn
		+ ", expiryInSeconds=" + expiryInSeconds + ", reserve1=" + reserve1 + ", reserve2=" + reserve2
		+ ", reserve3=" + reserve3 + ", isActive=" + isActive + ", getId()=" + getId() + "]";
    }

    /**
     * It builds LoginTokenDetails Builder object.
     * 
     * @return
     */
    public static Builder builder() {
	return new Builder();
    }

    /**
     * This Builder class helps to build LoginTokenDetails objects.
     * 
     * @author nxa30710
     *
     */
    public static class Builder {

	private long loginDetailsId;
	private String bearerToken;
	private long bearerTokenCreatedOn;
	private long loginOn;
	private long logoutOn;
	private long expiryInSeconds;
	private String reserve1;
	private String reserve2;
	private String reserve3;
	private String isActive;

	public Builder loginDetailsId(long val) {
	    this.loginDetailsId = val;
	    return this;
	}

	public Builder bearerToken(String val) {
	    this.bearerToken = val;
	    return this;
	}

	public Builder bearerTokenCreatedOn(long val) {
	    this.bearerTokenCreatedOn = val;
	    return this;
	}

	public Builder loginOn(long val) {
	    this.loginOn = val;
	    return this;
	}

	public Builder logoutOn(long val) {
	    this.logoutOn = val;
	    return this;
	}

	public Builder expiryInSeconds(long val) {
	    this.expiryInSeconds = val;
	    return this;
	}

	public Builder reserve1(String val) {
	    this.reserve1 = val;
	    return this;
	}

	public Builder reserve2(String val) {
	    this.reserve2 = val;
	    return this;
	}

	public Builder reserve3(String val) {
	    this.reserve3 = val;
	    return this;
	}

	public Builder isActive(String val) {
	    this.isActive = val;
	    return this;
	}

	public LoginTokenDetails build() {
	    return new LoginTokenDetails(this);
	}

    }

}
