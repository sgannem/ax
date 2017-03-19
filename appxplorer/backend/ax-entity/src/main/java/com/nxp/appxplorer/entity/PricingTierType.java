package com.nxp.appxplorer.entity;

import java.util.Objects;

import javax.persistence.Entity;

/**
 * This class maps all ApprovalType attributes from application to Data base
 * table.
 * 
 * @author nxa30710
 *
 */
@Entity
public class PricingTierType extends AbstractStrongEntity {

    private String name;
    private String description;
    private String isActive;

    PricingTierType() {

    }

    private PricingTierType(Builder builder) {
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
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
	return description;
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
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PricingTierType [name=" + name + ", description=" + description + ", isActive=" + isActive + "]";
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

	final PricingTierType that = (PricingTierType) o;
	return Objects.equals(getId(), that.getId());
    }

    public static Builder builder() {
	return new Builder();
    }

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

	public PricingTierType build() {
	    return new PricingTierType(this);
	}

    }

}
