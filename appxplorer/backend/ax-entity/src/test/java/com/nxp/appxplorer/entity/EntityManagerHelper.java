package com.nxp.appxplorer.entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.google.inject.Provider;

/**
 * Helper class for dealing with an {@link EntityManager}.
 *
 */
public class EntityManagerHelper {

  private static final String PERSISTENCE_UNIT = "appxplorer";

  private final EntityManager entityManager;
  private final Provider<EntityManager> entityManagerProvider;

  private EntityManagerHelper(final EntityManager entityManager) {
    this.entityManager = entityManager;
    this.entityManagerProvider = () -> entityManager;
  }

  public static EntityManagerHelper newInstance() {
    final EntityManager entityManager = createEntityManager();
    return new EntityManagerHelper(entityManager);
  }

  private static EntityManager createEntityManager() {
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    entityManagerFactory.getCache().evictAll();
    return entityManagerFactory.createEntityManager();
  }

  public Provider<EntityManager> getEntityManagerProvider() {
    return entityManagerProvider;
  }

  public void beginTransaction() {
    entityManager.getTransaction().begin();
  }

  public void commitTransaction() {
    entityManager.getTransaction().commit();
  }

  public void closeEntityManager() {
    entityManager.close();
  }

  public Query createNativeQuery(final String query) {
    return entityManager.createNativeQuery(query);
  }

  public void persist(final Entity entity) {
    entityManager.persist(entity);
  }

  public void remove(final Entity entity) {
    entityManager.remove(entity);
  }
}
