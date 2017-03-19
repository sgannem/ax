package com.nxp.appxplorer.dao;

import javax.persistence.EntityManager;

import com.google.inject.Provider;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.Entity;
import com.nxp.appxplorer.repository.Repository;

/**
 * Abstract Class for the Repositories (DAO Layer). Derived from this Class
 * there are {@code Repositories} for each {@code Entity}.
 *
 */
public abstract class AbstractDao<T extends Entity> implements Repository<T> {

    /** This is a Domain Query Factory **/
    protected final DomainQueryFactory domainQueryFactory;
    /** This is an EntityManager Provider for hibernate **/
    protected final Provider<EntityManager> entityManagerProvider;

    /**
     * This constructor is being used by the all the Dao classes, which are
     * extended by this DAO.
     * 
     * @param domainQueryFactory
     */
    protected AbstractDao(final DomainQueryFactory domainQueryFactory) {
	this.domainQueryFactory = domainQueryFactory;
	this.entityManagerProvider = domainQueryFactory.getEntityManagerProvider();
    }

    /**
     * This method is being used by the DAO, where DAOs wanted to extract
     * specific columns from the ResultSet.
     * 
     * @param row
     * @param columnIndex
     * @return T
     */
    @SuppressWarnings("unchecked")
    protected static <T> T getColumn(final Object row, final int columnIndex) {
	return (T) ((Object[]) row)[columnIndex];
    }

    /**
     * Method for Storing an Entity in the DB.
     *
     * @param entity
     *            The entity which will be persisted into the database.
     */

    @Override
    public void add(final T entity) {
	entityManagerProvider.get().persist(entity);
    }

    /**
     * Method for removing an entity from the database.
     *
     * @param entity
     *            The entity to be removed from the database.
     */

    @Override
    public void remove(final T entity) {
	entityManagerProvider.get().remove(entity);
    }

    /**
     * Method for updating an Entity within the database.
     *
     * @param entity
     *            The entity to be updated in the database.
     */
    @Override
    public T merge(final T entity) {
	return entityManagerProvider.get().merge(entity);
    }
}
