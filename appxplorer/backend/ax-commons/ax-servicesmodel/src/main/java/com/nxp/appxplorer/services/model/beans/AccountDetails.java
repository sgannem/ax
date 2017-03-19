package com.nxp.appxplorer.services.model.beans;

/**
 * This class holds all the Account Details of the logged in user.
 * 
 * @author nxa30710
 *
 */
public class AccountDetails {

    private String userName;
    private String accountType;
    private String loginType;
    private String userId;

    public AccountDetails() {

    }

    public AccountDetails(Builder builder) {
	this.userName = builder.userName;
	this.accountType = builder.accountType;
	this.loginType = builder.loginType;
	this.userId = builder.userId;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "AccountDetails [userName=" + userName + ", accountType=" + accountType + ", loginType=" + loginType
		+ ", userId=" + userId + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {
	private String userName;
	private String accountType;
	private String loginType;
	private String userId;

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

	public AccountDetails build() {
	    return new AccountDetails(this);
	}
    }

}
