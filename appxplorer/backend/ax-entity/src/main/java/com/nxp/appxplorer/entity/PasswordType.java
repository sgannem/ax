package com.nxp.appxplorer.entity;

import java.util.Objects;

import javax.persistence.Entity;

/**
 * This class maps PasswordType table into the Data Base.
 * 
 * @author nxa30710
 *
 */
@Entity
public class PasswordType extends AbstractStrongShortEntity {

    private String algorithmName;
    private String description;
    private Short iterations;
    private Short lengthOfSalt;
    private Short desiredLength;
    private String isActive;

    PasswordType() {
	super();
    }

    private PasswordType(Builder builder) {
	this.algorithmName = builder.algorithmName;
	this.description = builder.description;
	this.iterations = builder.iterations;
	this.lengthOfSalt = builder.lengthOfSalt;
	this.desiredLength = builder.desiredLength;
	this.isActive = builder.isActive;
    }

    /**
     * @return the algorithmName
     */
    public String getAlgorithmName() {
	return algorithmName;
    }

    /**
     * @param algorithmName
     *            the algorithmName to set
     */
    public void setAlgorithmName(String algorithmName) {
	this.algorithmName = algorithmName;
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
     * @return the iterations
     */
    public Short getIterations() {
	return iterations;
    }

    /**
     * @param iterations
     *            the iterations to set
     */
    public void setIterations(Short iterations) {
	this.iterations = iterations;
    }

    /**
     * @return the lengthOfSalt
     */
    public Short getLengthOfSalt() {
	return lengthOfSalt;
    }

    /**
     * @param lengthOfSalt
     *            the lengthOfSalt to set
     */
    public void setLengthOfSalt(Short lengthOfSalt) {
	this.lengthOfSalt = lengthOfSalt;
    }

    /**
     * @return the desiredLength
     */
    public Short getDesiredLength() {
	return desiredLength;
    }

    /**
     * @param desiredLength
     *            the desiredLength to set
     */
    public void setDesiredLength(Short desiredLength) {
	this.desiredLength = desiredLength;
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

	final PasswordType that = (PasswordType) o;
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
	return "PasswordType [algorithmName=" + algorithmName + ", description=" + description + ", iterations="
		+ iterations + ", lengthOfSalt=" + lengthOfSalt + ", desiredLength=" + desiredLength + ", isActive="
		+ isActive + ", getId()=" + getId() + "]";
    }

    /**
     * It builds PasswordType Builder object.
     * 
     * @return
     */
    public static Builder builder() {
	return new Builder();
    }

    /**
     * This Builder class helps to build PasswordType objects.
     * 
     * @author nxa30710
     *
     */
    public static class Builder {

	private String algorithmName;
	private String description;
	private Short iterations;
	private Short lengthOfSalt;
	private Short desiredLength;
	private String isActive;

	public Builder algorithmName(String val) {
	    this.algorithmName = val;
	    return this;
	}

	public Builder description(String val) {
	    this.description = val;
	    return this;
	}

	public Builder iterations(Short val) {
	    this.iterations = val;
	    return this;
	}

	public Builder lengthOfSalt(Short val) {
	    this.iterations = val;
	    return this;
	}

	public Builder desiredLength(Short val) {
	    this.iterations = val;
	    return this;
	}

	public Builder isActive(String val) {
	    this.isActive = val;
	    return this;
	}

	public PasswordType build() {
	    return new PasswordType(this);
	}

    }

}
