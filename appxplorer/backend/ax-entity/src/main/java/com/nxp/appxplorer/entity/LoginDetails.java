package com.nxp.appxplorer.entity;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Entity;

/**
 * This class maps LoginDetails table into the Data Base.
 * 
 * @author nxa30710
 *
 */
@Entity
public class LoginDetails extends AbstractStrongEntity {

    private Short accountTypeId;
    private Short loginTypeId;
    private Short roleId;
    private Short accountStatusId;
    private Short passwordTypeId;
    private byte[] passwordHash;
    private byte[] salt;
    private String userName;
    private String userId;
    private String externalUserId;
    private String isActive;

    LoginDetails() {
	super();
    }

    private LoginDetails(Builder builder) {
	this.accountTypeId = builder.accountTypeId;
	this.loginTypeId = builder.loginTypeId;
	this.roleId = builder.roleId;
	this.accountStatusId = builder.accountStatusId;
	this.passwordTypeId = builder.passwordTypeId;
	this.passwordHash = builder.passwordHash;
	this.salt = builder.salt;
	this.userName = builder.userName;
	this.userId = builder.userId;
	this.externalUserId = builder.externalUserId;
	this.isActive = builder.isActive;
    }

    /**
     * @return the accountTypeId
     */
    public Short getAccountTypeId() {
	return accountTypeId;
    }

    /**
     * @param accountTypeId
     *            the accountTypeId to set
     */
    public void setAccountTypeId(Short accountTypeId) {
	this.accountTypeId = accountTypeId;
    }

    /**
     * @return the loginTypeId
     */
    public Short getLoginTypeId() {
	return loginTypeId;
    }

    /**
     * @param loginTypeId
     *            the loginTypeId to set
     */
    public void setLoginTypeId(Short loginTypeId) {
	this.loginTypeId = loginTypeId;
    }

    /**
     * @return the roleId
     */
    public Short getRoleId() {
	return roleId;
    }

    /**
     * @param roleId
     *            the roleId to set
     */
    public void setRoleId(Short roleId) {
	this.roleId = roleId;
    }

    /**
     * @return the accountStatusId
     */
    public short getAccountStatusId() {
	return accountStatusId;
    }

    /**
     * @param accountStatusId
     *            the accountStatusId to set
     */
    public void setAccountStatusId(Short accountStatusId) {
	this.accountStatusId = accountStatusId;
    }

    /**
     * @return the passwordTypeId
     */
    public Short getPasswordTypeId() {
	return passwordTypeId;
    }

    /**
     * @param passwordTypeId
     *            the passwordTypeId to set
     */
    public void setPasswordTypeId(Short passwordTypeId) {
	this.passwordTypeId = passwordTypeId;
    }

    /**
     * @return the passwordHash
     */
    public byte[] getPasswordHash() {
	return passwordHash;
    }

    /**
     * @param passwordHash
     *            the passwordHash to set
     */
    public void setPasswordHash(byte[] passwordHash) {
	this.passwordHash = passwordHash;
    }

    /**
     * @return the salt
     */
    public byte[] getSalt() {
	return salt;
    }

    /**
     * @param salt
     *            the salt to set
     */
    public void setSalt(byte[] salt) {
	this.salt = salt;
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
     * @return the externalUserId
     */
    public String getExternalUserId() {
	return externalUserId;
    }

    /**
     * @param externalUserId
     *            the externalUserId to set
     */
    public void setExternalUserId(String externalUserId) {
	this.externalUserId = externalUserId;
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

	final LoginDetails that = (LoginDetails) o;
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
	return "LoginDetails [accountTypeId=" + accountTypeId + ", loginTypeId=" + loginTypeId + ", roleId=" + roleId
		+ ", accountStatusId=" + accountStatusId + ", passwordTypeId=" + passwordTypeId + ", passwordHash="
		+ Arrays.toString(passwordHash) + ", salt=" + Arrays.toString(salt) + ", userName=" + userName
		+ ", userId=" + userId + ", externalUserId=" + externalUserId + ", isActive=" + isActive + ", getId()="
		+ getId() + "]";
    }

    /**
     * It builds LoginDetails Builder object.
     * 
     * @return
     */
    public static Builder builder() {
	return new Builder();
    }

    /**
     * This Builder class helps to build LoginDetails objects.
     * 
     * @author nxa30710
     *
     */
    public static class Builder {

	private Short accountTypeId;
	private Short loginTypeId;
	private Short roleId;
	private short accountStatusId;
	private short passwordTypeId;
	private byte[] passwordHash;
	private byte[] salt;
	private String userName;
	private String userId;
	private String externalUserId;
	private String isActive;

	public Builder accountTypeId(Short val) {
	    this.accountTypeId = val;
	    return this;
	}

	public Builder loginTypeId(Short val) {
	    this.loginTypeId = val;
	    return this;
	}

	public Builder roleId(Short val) {
	    this.roleId = val;
	    return this;
	}

	public Builder accountStatusId(Short val) {
	    this.accountStatusId = val;
	    return this;
	}

	public Builder passwordTypeId(Short val) {
	    this.passwordTypeId = val;
	    return this;
	}

	public Builder salt(byte[] val) {
	    this.salt = val;
	    return this;
	}

	public Builder passwordHash(byte[] val) {
	    this.passwordHash = val;
	    return this;
	}

	public Builder userName(String val) {
	    this.userName = val;
	    return this;
	}

	public Builder userId(String val) {
	    this.userId = val;
	    return this;
	}

	public Builder externalUserId(String val) {
	    this.externalUserId = val;
	    return this;
	}

	public Builder isActive(String val) {
	    this.isActive = val;
	    return this;
	}

	public LoginDetails build() {
	    return new LoginDetails(this);
	}

    }

}
