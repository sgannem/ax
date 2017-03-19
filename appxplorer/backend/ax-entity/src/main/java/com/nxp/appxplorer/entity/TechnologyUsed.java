package com.nxp.appxplorer.entity;

import java.util.Objects;

import javax.persistence.Entity;

/**
 * This class maps Technology Used table into the Data Base.
 * 
 */
@Entity
public class TechnologyUsed extends AbstractStrongShortEntity {

	private String name;
	private String description;
	private String isActive;

	TechnologyUsed() {
		super();
	}

	private TechnologyUsed(Builder builder) {
		setId(builder.id);
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

		final TechnologyUsed that = (TechnologyUsed) o;
		return Objects.equals(getId(), that.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TechnologyUsed [id=" + getId() + ", name=" + name + ", description=" + description + ", isActive="
				+ isActive + "]";
	}

	/**
	 * It builds Technology Used Builder object.
	 * 
	 * @return
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * This Builder class helps to build Technology Used Builder objects.
	 * 
	 */
	public static class Builder {
		private Short id;
		private String name;
		private String description;
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

		public Builder isActive(String val) {
			this.isActive = val;
			return this;
		}

		public TechnologyUsed build() {
			return new TechnologyUsed(this);
		}
	}

}
