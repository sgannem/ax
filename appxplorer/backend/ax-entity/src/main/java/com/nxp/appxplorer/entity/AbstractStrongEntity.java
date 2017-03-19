package com.nxp.appxplorer.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

/**
 * This class maintains generation of entity id during entity persistence.
 * 
 * @author nxa30710
 *
 */
@MappedSuperclass
public abstract class AbstractStrongEntity extends AbstractEntity {

    /** entity identifier **/
    private Long id;

    public static final int TABLE_GENERATOR_ALLOCATION_SIZE = 50;

    /**
     * This constructor will be invoked by the classes, which are extended this
     * class.
     */
    protected AbstractStrongEntity() {
	super();
    }

    /**
     * @param id
     */
    AbstractStrongEntity(final Long id) {
	this.id = id;
    }

    /**
     * This method maps the id column into the Data base table.
     * 
     * @return
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "SEQUENCE_STORE")
    @TableGenerator(name = "SEQUENCE_STORE", allocationSize = TABLE_GENERATOR_ALLOCATION_SIZE)
    public Long getId() {
	return id;
    }

    /**
     * @param id
     */
    void setId(final long id) {
	this.id = id;
    }
}
