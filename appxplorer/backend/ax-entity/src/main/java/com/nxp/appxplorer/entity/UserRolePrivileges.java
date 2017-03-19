package com.nxp.appxplorer.entity;

import java.util.Objects;

import javax.persistence.Entity;

/**
 * This class maps UserRolePrivileges table into the Data Base.
 * 
 * @author nxa30710
 *
 */
@Entity
public class UserRolePrivileges extends AbstractStrongEntity {

    private Short roleId;
    private Short privilegeId;
    private String isActive;

    UserRolePrivileges() {
	super();
    }

    private UserRolePrivileges(Builder builder) {
	this.roleId = builder.roleId;
	this.privilegeId = builder.privilegeId;
	this.isActive = builder.isActive;
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
     * @return the privilegeId
     */
    public Short getPrivilegeId() {
	return privilegeId;
    }

    /**
     * @param privilegeId
     *            the privilegeId to set
     */
    public void setPrivilegeId(Short privilegeId) {
	this.privilegeId = privilegeId;
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

	final UserRolePrivileges that = (UserRolePrivileges) o;
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
	return "UserRolePrivileges [roleId=" + roleId + ", privilegeId=" + privilegeId + ", isActive=" + isActive
		+ ", getId()=" + getId() + "]";
    }

    /**
     * It builds UserRolePrivileges Builder object.
     * 
     * @return
     */
    public static Builder builder() {
	return new Builder();
    }

    /**
     * This Builder class helps to build UserRolePrivileges objects.
     * 
     * @author nxa30710
     *
     */
    public static class Builder {

	private Short roleId;
	private Short privilegeId;
	private String isActive;

	public Builder roleId(Short val) {
	    this.roleId = val;
	    return this;
	}

	public Builder privilegeId(Short val) {
	    this.privilegeId = val;
	    return this;
	}

	public Builder isActive(String val) {
	    this.isActive = val;
	    return this;
	}

	public UserRolePrivileges build() {
	    return new UserRolePrivileges(this);
	}

    }

}
