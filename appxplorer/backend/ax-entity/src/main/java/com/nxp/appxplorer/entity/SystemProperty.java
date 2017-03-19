package com.nxp.appxplorer.entity;

import java.util.Objects;

import javax.persistence.Entity;

/**
 * This class maps all SystemProperty attributes from application to Data base
 * table.
 * 
 * @author nxa30710
 *
 */
@Entity
public class SystemProperty extends AbstractStrongShortEntity {

    private String propertyName;
    private String propertyValue;

    SystemProperty() {

    }

    private SystemProperty(Builder builder) {
	this.propertyName = builder.propertyName;
	this.propertyValue = builder.propertyValue;
    }

    /**
     * @return the propertyName
     */
    public String getPropertyName() {
	return propertyName;
    }

    /**
     * @param propertyName
     *            the propertyName to set
     */
    public void setPropertyName(String propertyName) {
	this.propertyName = propertyName;
    }

    /**
     * @return the propertyValue
     */
    public String getPropertyValue() {
	return propertyValue;
    }

    /**
     * @param propertyValue
     *            the propertyValue to set
     */
    public void setPropertyValue(String propertyValue) {
	this.propertyValue = propertyValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "SystemProperty [propertyName=" + propertyName + ", propertyValue=" + propertyValue + "]";
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

	final SystemProperty that = (SystemProperty) o;
	return Objects.equals(getId(), that.getId());
    }

    public static Builder builder() {
	return new Builder();
    }

    /**
     * Its a builder class to Build System Property attributes.
     * 
     * @author nxa30710
     *
     */
    public static class Builder {

	private String propertyName;
	private String propertyValue;

	public Builder propertyName(String val) {
	    this.propertyName = val;
	    return this;
	}

	public Builder propertyValue(String val) {
	    this.propertyValue = val;
	    return this;
	}

	public SystemProperty build() {
	    return new SystemProperty(this);
	}

    }

}
