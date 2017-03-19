package com.nxp.appxplorer.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.MappedSuperclass;

/**
 * Abstract base class for all entity classes.
 *
 */
@MappedSuperclass
public abstract class AbstractEntity implements Entity {

	private String createdBy;
	private Date creationDate;
	private Time creationTime;
	private String changedBy;
	private Date changedDate;
	private Time changedTime;

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the creationTime
	 */
	public Time getCreationTime() {
		return creationTime;
	}

	/**
	 * @param creationTime
	 *            the creationTime to set
	 */
	public void setCreationTime(Time creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * @return the changedBy
	 */
	public String getChangedBy() {
		return changedBy;
	}

	/**
	 * @param changedBy
	 *            the changedBy to set
	 */
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	/**
	 * @return the changedDate
	 */
	public Date getChangedDate() {
		return changedDate;
	}

	/**
	 * @param changedDate
	 *            the changedDate to set
	 */
	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}

	/**
	 * @return the changedTime
	 */
	public Time getChangedTime() {
		return changedTime;
	}

	/**
	 * @param changedTime
	 *            the changedTime to set
	 */
	public void setChangedTime(Time changedTime) {
		this.changedTime = changedTime;
	}

}
