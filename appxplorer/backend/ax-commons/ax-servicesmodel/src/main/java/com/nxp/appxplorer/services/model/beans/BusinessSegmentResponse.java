package com.nxp.appxplorer.services.model.beans;

import java.util.List;

/**
 * @author nxa30710
 *
 */
public class BusinessSegmentResponse {

    private List<BusinessSegment> businessSegments;

    public BusinessSegmentResponse() {

    }

    public BusinessSegmentResponse(Builder builder) {
	this.businessSegments = builder.businessSegments;
    }

    /**
     * @return the businessSegments
     */
    public List<BusinessSegment> getBusinessSegments() {
	return businessSegments;
    }

    /**
     * @param businessSegments
     *            the businessSegments to set
     */
    public void setBusinessSegments(List<BusinessSegment> businessSegments) {
	this.businessSegments = businessSegments;
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private List<BusinessSegment> businessSegments;

	public Builder businessSegments(List<BusinessSegment> val) {
	    this.businessSegments = val;
	    return this;
	}

	public BusinessSegmentResponse build() {
	    return new BusinessSegmentResponse(this);
	}
    }

}
