package com.nxp.appxplorer.repository;

import com.nxp.appxplorer.entity.Entity;

/**
 * Interface which each repository need to implement regardless of the entity
 * stored.
 *
 */
public interface Repository<T extends Entity> {

    /**
     * This method is used to insert given entity into the the DB.
     * 
     * @param entity
     */
    void add(T entity);

    /**
     * This method is used to remove given entity from the DB.
     * 
     * @param entity
     */
    void remove(T entity);

    /**
     * This method is used to merge given entity into the DB.
     * 
     * @param entity
     * @return
     */
    T merge(T entity);
}
