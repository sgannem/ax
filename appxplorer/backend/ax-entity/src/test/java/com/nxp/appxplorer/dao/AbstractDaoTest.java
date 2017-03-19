package com.nxp.appxplorer.dao;

import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.testng.annotations.BeforeClass;

import com.google.inject.Provider;
import com.nxp.appxplorer.DomainQueryFactory;
import com.nxp.appxplorer.entity.EntityManagerHelper;

public abstract class AbstractDaoTest {

	private EntityManagerHelper entityManagerHelper;

	private DomainQueryFactory domainQueryFactory;

	@BeforeClass
	public void beforeClass() throws SQLException {
		entityManagerHelper = EntityManagerHelper.newInstance();
		domainQueryFactory = buildDomainQueryFactory();

		entityManagerHelper.beginTransaction();
		populateDatabase(entityManagerHelper);
		entityManagerHelper.commitTransaction();
	}

	protected abstract void populateDatabase(final EntityManagerHelper entityManagerHelper);

	private DomainQueryFactory buildDomainQueryFactory() {
		final Provider<EntityManager> entityManagerProvider = entityManagerHelper.getEntityManagerProvider();
		return DomainQueryFactory.newInstance(entityManagerProvider);
	}

	private EntityManagerHelper getEntityManagerHelper() {
		return entityManagerHelper;
	}

	//
	public DomainQueryFactory getDomainQueryFactory() {
		return domainQueryFactory;
	}
}
