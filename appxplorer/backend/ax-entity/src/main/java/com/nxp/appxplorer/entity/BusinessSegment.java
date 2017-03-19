package com.nxp.appxplorer.entity;

import java.util.Objects;

import javax.persistence.Entity;

/**
 * This class maps Business Segment table into the Data Base.
 * 
 */
@Entity
public class BusinessSegment extends AbstractStrongShortEntity {

    private String name;
    private String description;
    private Short priority;
    private String isActive;

    BusinessSegment() {
	super();
    }

    private BusinessSegment(Builder builder) {
	setId(builder.id);
	this.name = builder.name;
	this.description = builder.description;
	this.priority = builder.priority;
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
     * @return the priority
     */
    public Short getPriority() {
	return priority;
    }

    /**
     * @param priority
     *            the priority to set
     */
    public void setPriority(Short priority) {
	this.priority = priority;
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

	final BusinessSegment that = (BusinessSegment) o;
	return Objects.equals(getId(), that.getId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "BusinessSegment [id=" + getId() + ", name=" + name + ", description=" + description + ", priority="
		+ priority + ", isActive=" + isActive + "]";
    }

    /**
     * It builds Business Segment Builder object.
     * 
     * @return
     */
    public static Builder builder() {
	return new Builder();
    }

    /**
     * This Builder class helps to build Business Segment objects.
     * 
     */
    public static class Builder {
	private Short id;
	private String name;
	private String description;
	private Short priority;
	private String isActive;

	public Builder id(Short val) {
	    this.id = val;
	    return this;
	}

	public Builder name(String val) {
	    this.name = val;
	    return this;
	}

	public Builder description(String val) {
	    this.description = val;
	    return this;
	}

	public Builder priority(Short val) {
	    this.priority = val;
	    return this;
	}

	public Builder isActive(String val) {
	    this.isActive = val;
	    return this;
	}

	public BusinessSegment build() {
	    return new BusinessSegment(this);
	}
    }

}
