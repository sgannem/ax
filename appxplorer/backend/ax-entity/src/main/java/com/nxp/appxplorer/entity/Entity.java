package com.nxp.appxplorer.entity;

/**
 * This is an Entity, which needs to be implemented by all the entity pojos.
 * 
 * @author nxa30710a
 *
 */
public interface Entity {

    /**
     * This method must be implemented by all the entity pojos.
     * 
     * @param object
     * @return
     */
    @Override
    boolean equals(Object object);

    /**
     * This method must be implemented by all the entity pojos.
     * 
     * @return
     */
    @Override
    int hashCode();
}
