package com.nxp.appxplorer.entity;

import java.util.Objects;

import javax.persistence.Entity;

/**
 * This class maps AccountStatus table into the Data Base.
 * 
 * @author nxa30710
 *
 */
@Entity
public class AccountStatus extends AbstractStrongShortEntity {

    /** AccountStatus name **/
    private String name;
    /** AccountStatus description **/
    private String description;
    /** AccountStatus active flat **/
    private String isActive;

    /**
     * Default Constructor.
     */
    AccountStatus() {
	super();
    }

    /**
     * @param id
     */
    public AccountStatus(Short id) {
	super(id);
	// TODO Auto-generated constructor stub
    }

    /**
     * Its a constructor being used by the Account Status Builder.
     * 
     * @param builder
     */
    private AccountStatus(Builder builder) {
	this.name = builder.name;
	this.description = builder.description;
	this.isActive = builder.isActive;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
	this.description = description;
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

	final AccountStatus that = (AccountStatus) o;
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
	return "AccountStatus [name=" + name + ", description=" + description + ", isActive=" + isActive + ", getId()="
		+ getId() + "]";
    }

    /**
     * It builds AccountStatus Builder object.
     * 
     * @return
     */
    public static Builder builder() {
	return new Builder();
    }

    /**
     * This Builder class helps to build AccountStatus objects.
     * 
     * @author nxa30710
     *
     */
    public static class Builder {

	private String name;
	private String description;
	private String isActive;

	public Builder name(String val) {
	    this.name = val;
	    return this;
	}

	public Builder description(String val) {
	    this.description = val;
	    return this;
	}

	public Builder isActive(String val) {
	    this.isActive = val;
	    return this;
	}

	public AccountStatus build() {
	    return new AccountStatus(this);
	}

    }

}
