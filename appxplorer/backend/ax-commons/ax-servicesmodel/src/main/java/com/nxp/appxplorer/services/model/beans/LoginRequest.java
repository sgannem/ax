package com.nxp.appxplorer.services.model.beans;

/**
 * This class being used during login from market portals.
 * 
 * @author nxa30710
 *
 */
public class LoginRequest {

    private String userName;
    private String password;
    private String loginType;
    private String accountType;

    /**
     * Default constructor;
     */
    public LoginRequest() {

    }

    private LoginRequest(Builder builder) {
	this.userName = builder.userName;
	this.password = builder.password;
	this.loginType = builder.loginType;
	this.accountType = builder.accountType;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "LoginRequestDto [userName=" + userName + ", password=" + password + ", loginType=" + loginType
		+ ", accountType=" + accountType + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private String userName;
	private String password;
	private String loginType;
	private String accountType;

	public Builder userName(String val) {
	    this.userName = val;
	    return this;
	}

	public Builder password(String val) {
	    this.password = val;
	    return this;
	}

	public Builder loginType(String val) {
	    this.loginType = val;
	    return this;
	}

	public Builder accountType(String val) {
	    this.accountType = val;
	    return this;
	}

	public LoginRequest build() {
	    return new LoginRequest(this);
	}
    }

}
