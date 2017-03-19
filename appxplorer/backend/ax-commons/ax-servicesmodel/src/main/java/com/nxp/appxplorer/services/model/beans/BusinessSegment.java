package com.nxp.appxplorer.services.model.beans;

/**
 * @author nxa30710
 *
 */
public class BusinessSegment {

    private String id;
    private String name;

    public BusinessSegment() {

    }

    public BusinessSegment(Builder builder) {
	this.id = builder.id;
	this.name = builder.name;

    }

    /**
     * @return the id
     */
    public String getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
	this.id = id;
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "BusinessSegment [id=" + id + ", name=" + name + "]";
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private String id;
	private String name;

	public Builder id(String val) {
	    this.id = val;
	    return this;
	}

	public Builder name(String val) {
	    this.name = val;
	    return this;
	}

	public BusinessSegment build() {
	    return new BusinessSegment(this);
	}
    }

}
