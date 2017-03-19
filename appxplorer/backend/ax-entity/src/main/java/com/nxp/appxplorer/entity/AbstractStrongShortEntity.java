package com.nxp.appxplorer.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * This class maintains entity id during entity persistence.
 *
 */
@MappedSuperclass
public abstract class AbstractStrongShortEntity extends AbstractEntity {

    /** entity identifier **/
    private Short id;

    /**
     * This constructor will be invoked by the classes, which are extended this
     * class.
     */
    protected AbstractStrongShortEntity() {
	super();
    }

    /**
     * @param id
     */
    AbstractStrongShortEntity(final Short id) {
	this.id = id;
    }

    /**
     * This method maps the id column into the Data base table.
     * 
     * @return
     */
    @Id
    public Short getId() {
	return id;
    }

    /**
     * @param id
     */
    void setId(final short id) {
	this.id = id;
    }
}
