package com.nxp.appxplorer.entity;

import java.util.Objects;

import javax.persistence.Entity;

/**
 * This class maps all SendEmailQueueEntry attributes from application to Data
 * base table.
 * 
 * @author nxa30710
 *
 */
@Entity
public class SendEmailQueueEntry extends AbstractStrongEntity {

    /**
     * id emailAddress textFileAttachmentFileName textFileAttachmentDescription
     * insertedOn subject message textFileAttachmentContent
     */

    private String emailAddress;
    private String textFileAttachmentFileName;
    private String textFileAttachmentDescription;
    private long insertedOn;
    private String subject;
    private String message;
    private String textFileAttachmentContent;
    private String isActive;

    SendEmailQueueEntry() {

    }

    private SendEmailQueueEntry(Builder builder) {
	this.emailAddress = builder.emailAddress;
	this.textFileAttachmentFileName = builder.textFileAttachmentFileName;
	this.textFileAttachmentDescription = builder.textFileAttachmentDescription;
	this.insertedOn = builder.insertedOn;
	this.subject = builder.subject;
	this.message = builder.message;
	this.textFileAttachmentContent = builder.textFileAttachmentContent;
	this.isActive = builder.isActive;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
	return emailAddress;
    }

    /**
     * @param emailAddress
     *            the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
    }

    /**
     * @return the textFileAttachmentFileName
     */
    public String getTextFileAttachmentFileName() {
	return textFileAttachmentFileName;
    }

    /**
     * @param textFileAttachmentFileName
     *            the textFileAttachmentFileName to set
     */
    public void setTextFileAttachmentFileName(String textFileAttachmentFileName) {
	this.textFileAttachmentFileName = textFileAttachmentFileName;
    }

    /**
     * @return the textFileAttachmentDescription
     */
    public String getTextFileAttachmentDescription() {
	return textFileAttachmentDescription;
    }

    /**
     * @param textFileAttachmentDescription
     *            the textFileAttachmentDescription to set
     */
    public void setTextFileAttachmentDescription(String textFileAttachmentDescription) {
	this.textFileAttachmentDescription = textFileAttachmentDescription;
    }

    /**
     * @return the insertedOn
     */
    public long getInsertedOn() {
	return insertedOn;
    }

    /**
     * @param insertedOn
     *            the insertedOn to set
     */
    public void setInsertedOn(long insertedOn) {
	this.insertedOn = insertedOn;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
	return subject;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject(String subject) {
	this.subject = subject;
    }

    /**
     * @return the message
     */
    public String getMessage() {
	return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
	this.message = message;
    }

    /**
     * @return the textFileAttachmentContent
     */
    public String getTextFileAttachmentContent() {
	return textFileAttachmentContent;
    }

    /**
     * @param textFileAttachmentContent
     *            the textFileAttachmentContent to set
     */
    public void setTextFileAttachmentContent(String textFileAttachmentContent) {
	this.textFileAttachmentContent = textFileAttachmentContent;
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
	return "SendEmailQueueEntry [emailAddress=" + emailAddress + ", textFileAttachmentFileName="
		+ textFileAttachmentFileName + ", textFileAttachmentDescription=" + textFileAttachmentDescription
		+ ", insertedOn=" + insertedOn + ", subject=" + subject + ", message=" + message
		+ ", textFileAttachmentContent=" + textFileAttachmentContent + ", isActive=" + isActive + "]";
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

	final SendEmailQueueEntry that = (SendEmailQueueEntry) o;
	return Objects.equals(getId(), that.getId());
    }

    public static Builder builder() {
	return new Builder();
    }

    public static class Builder {

	private String emailAddress;
	private String textFileAttachmentFileName;
	private String textFileAttachmentDescription;
	private long insertedOn;
	private String subject;
	private String message;
	private String textFileAttachmentContent;
	private String isActive;

	public Builder emailAddress(String val) {
	    this.emailAddress = val;
	    return this;
	}

	public Builder textFileAttachmentFileName(String val) {
	    this.textFileAttachmentFileName = val;
	    return this;
	}

	public Builder textFileAttachmentDescription(String val) {
	    this.textFileAttachmentDescription = val;
	    return this;
	}

	public Builder insertedOn(long val) {
	    this.insertedOn = val;
	    return this;
	}

	public Builder subject(String val) {
	    this.subject = val;
	    return this;
	}

	public Builder message(String val) {
	    this.message = val;
	    return this;
	}

	public Builder textFileAttachmentContent(String val) {
	    this.textFileAttachmentContent = val;
	    return this;
	}

	public Builder isActive(String val) {
	    this.isActive = val;
	    return this;
	}

	public SendEmailQueueEntry build() {
	    return new SendEmailQueueEntry(this);
	}

    }

}
