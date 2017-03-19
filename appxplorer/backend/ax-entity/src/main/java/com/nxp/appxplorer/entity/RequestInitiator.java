package com.nxp.appxplorer.entity;

import java.util.Objects;

import javax.persistence.Entity;

/**
 * This class maps all RequestStatus attributes from application to Data base
 * table.
 * 
 * @author nxa30710
 *
 */
@Entity
public class RequestInitiator extends AbstractStrongEntity {

    RequestInitiator() {

    }

    private RequestInitiator(Builder builder) {

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

	final RequestInitiator that = (RequestInitiator) o;
	return Objects.equals(getId(), that.getId());
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	public RequestInitiator build() {
	    return new RequestInitiator(this);
	}

    }

}
