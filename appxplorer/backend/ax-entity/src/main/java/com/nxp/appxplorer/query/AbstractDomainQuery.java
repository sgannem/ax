package com.nxp.appxplorer.query;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nxp.appxplorer.exception.EntityNotFoundInRepoException;
//import com.nxp.appstore.commons.web.exception.EntityNotFoundInRepoException;

/**
 * This class providing many helper methods to build and execute queries.
 *
 */
public abstract class AbstractDomainQuery<T> implements DomainQuery<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDomainQuery.class);

    private Map<String, Object> parameterMap = new HashMap<>();
    private StringBuilder conditions = new StringBuilder();
    private List<String> orderBy = new ArrayList<>();
    private List<String> groupBy = new ArrayList<>();
    private String defaultOrderBy;
    private Integer maxResults;
    private Integer firstResult;

    private final Class<T> mainClass;
    private final EntityManager entityManager;
    private final String entityPrefix;
    private String baseQuery;
    private String countQuery;

    /**
     * @param mainClass
     * @param entityManager
     * @param entityPrefix
     * @param baseQuery
     */
    protected AbstractDomainQuery(final Class<T> mainClass, final EntityManager entityManager,
	    final String entityPrefix, @Nullable final String baseQuery) {
	this.mainClass = requireNonNull(mainClass);
	this.entityManager = requireNonNull(entityManager);
	this.entityPrefix = requireNonNull(entityPrefix);
	this.baseQuery = baseQuery;
    }

    /**
     * @param mainClass
     * @param entityManager
     * @param entityPrefix
     * @param baseQuery
     * @param countQuery
     */
    protected AbstractDomainQuery(final Class<T> mainClass, final EntityManager entityManager,
	    final String entityPrefix, @Nullable final String baseQuery, final String countQuery) {
	this.mainClass = requireNonNull(mainClass);
	this.entityManager = requireNonNull(entityManager);
	this.entityPrefix = requireNonNull(entityPrefix);
	this.baseQuery = baseQuery;
	this.countQuery = countQuery;
    }

    /**
     * @param mainClass
     * @param entityManager
     * @param entityPrefix
     */
    protected AbstractDomainQuery(final Class<T> mainClass, final EntityManager entityManager,
	    final String entityPrefix) {
	this(mainClass, entityManager, entityPrefix, "FROM " + mainClass.getSimpleName() + " " + entityPrefix,
		"SELECT COUNT(" + entityPrefix + ") FROM " + mainClass.getSimpleName() + " " + entityPrefix);
    }

    /**
     * @param condition
     */
    protected void addCondition(final String condition) {
	conditions.append(" AND ").append(condition);
    }

    /**
     * @param condition
     * @param parameterName
     * @param parameterValue
     */
    protected void addCondition(final String condition, final String parameterName, final Object parameterValue) {
	addCondition(condition);
	addParameter(parameterName, parameterValue);
    }

    /**
     * @param name
     * @param value
     */
    protected void addParameter(final String name, final Object value) {
	parameterMap.put(name, value);
    }

    /**
     * @param name
     */
    protected void addAscOrderBy(final String name) {
	orderBy.add(name + " ASC");
    }

    /**
     * @param name
     */
    protected void addDescOrderBy(final String name) {
	orderBy.add(name + " DESC");
    }

    /**
     * @return
     */
    protected String getDefaultOrderBy() {
	return defaultOrderBy;
    }

    /**
     * @param defaultOrderBy
     */
    protected void setDefaultOrderBy(final String defaultOrderBy) {
	this.defaultOrderBy = defaultOrderBy;
    }

    /**
     * @param name
     */
    protected void addGroupBy(final String name) {
	groupBy.add(name);
    }

    /**
     * @param baseQuery
     */
    protected void setBaseQuery(final String baseQuery) {
	this.baseQuery = baseQuery;
    }

    /*
     * Execute query and returns the result list or empty list
     * 
     * @see com.nxp.appxplorer.query.DomainQuery#getResultList()
     */
    @Override
    public List<T> getResultList() {
	final TypedQuery<T> query = createQuery(mainClass, false);
	return query.getResultList();
    }

    /*
     * Execute query and applies the given function to the result list.
     * 
     * @see
     * com.nxp.appxplorer.query.DomainQuery#getResultList(java.util.function.
     * Function)
     */
    @Override
    public <S> List<S> getResultList(final Function<? super T, S> mapper) {
	final List<T> resultList = getResultList();
	return resultList.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * Execute query and applies the given function to the result list.
     * 
     * @param clazz
     * @param mapper
     * @return
     */
    protected <S> List<T> getResultList(final Class<S> clazz, final Function<S, T> mapper) {
	final TypedQuery<S> query = createQuery(clazz, false);
	final List<S> resultList = query.getResultList();
	return resultList.stream().map(mapper).collect(Collectors.toList());
    }

    /*
     * Execute query and returns a single result.
     * 
     * @see com.nxp.appxplorer.query.DomainQuery#getSingleResult()
     */
    @Override
    public Optional<T> getSingleResult() {
	final TypedQuery<T> query = createQuery(mainClass, false);
	final List<T> result = query.getResultList();

	if (result.isEmpty()) {
	    return Optional.empty();
	}

	if (result.size() == 1) {
	    return Optional.of(result.get(0));
	}

	throw new IllegalStateException("inconsistent DB. query returns more than one row: " + query.toString());
    }

    /*
     * Execute query and returns a single result or throws an {@link
     * EntityNotFoundInRepoException}
     * 
     * @see com.nxp.appxplorer.query.DomainQuery#getSingleResultOrThrow()
     */
    @Override
    public T getSingleResultOrThrow() throws EntityNotFoundInRepoException {
	return getSingleResult().orElseThrow(() -> EntityNotFoundInRepoException.newInstance(mainClass));
    }

    /*
     * Execute query and returns a single result or throws Exception {@link E}
     * that is returned by the given {@link Supplier}.
     * 
     * @see
     * com.nxp.appxplorer.query.DomainQuery#getSingleResultOrThrow(java.util.
     * function.Supplier)
     */
    @Override
    public <E extends Throwable> T getSingleResultOrThrow(final Supplier<? extends E> exceptionSupplier) throws E {
	return getSingleResult().orElseThrow(exceptionSupplier);
    }

    /*
     * Return count of selected rows
     * 
     * @see com.nxp.appxplorer.query.DomainQuery#getResultSize()
     */
    @Override
    public int getResultSize() {
	final Number resultSize = createQuery(Long.class, true).getSingleResult();
	return (resultSize == null) ? 0 : resultSize.intValue();
    }

    /**
     * 
     */
    protected void beforeCreateQuery() {
	// empty
    }

    /**
     * @param base
     * @param count
     * @return
     */
    protected String buildStatement(final String base, final boolean count) {
	final StringBuilder stmt = new StringBuilder(base).append(" WHERE 1 = 1 ").append(conditions);

	if (!groupBy.isEmpty()) {
	    stmt.append(" GROUP BY ").append(StringUtils.join(groupBy, ","));
	}

	if (!count) {
	    if (!orderBy.isEmpty()) {
		stmt.append(" ORDER BY ").append(StringUtils.join(orderBy, ','));
	    } else if (!StringUtils.isEmpty(defaultOrderBy)) {
		stmt.append(" ORDER BY ").append(defaultOrderBy);
	    }
	}

	return stmt.toString();
    }

    /**
     * @param clazz
     * @param count
     * @return
     */
    protected <S> TypedQuery<S> createQuery(final Class<S> clazz, final boolean count) {
	final Map<String, Object> oldParameterMap = parameterMap;
	final StringBuilder oldConditions = conditions;
	final List<String> oldOrderBy = orderBy;
	final List<String> oldGroupBy = groupBy;
	parameterMap = new HashMap<String, Object>(oldParameterMap);
	conditions = new StringBuilder(oldConditions);
	orderBy = new ArrayList<String>(oldOrderBy);
	groupBy = new ArrayList<String>(oldGroupBy);
	beforeCreateQuery();

	String queryString;

	if (count) {
	    if (isNull(this.countQuery)) {
		throw new IllegalStateException("query object does not specify a count query");
	    } else {
		queryString = this.countQuery;
	    }
	} else {
	    queryString = this.baseQuery;
	}

	final String queryAsString = buildStatement(queryString, count);

	LOGGER.debug("query={}", queryAsString);

	final TypedQuery<S> query = entityManager.createQuery(queryAsString, clazz);

	for (final Map.Entry<String, Object> entry : parameterMap.entrySet()) {
	    query.setParameter(entry.getKey(), entry.getValue());
	}

	parameterMap = oldParameterMap;
	conditions = oldConditions;
	orderBy = oldOrderBy;
	groupBy = oldGroupBy;

	if (!count) {
	    if (firstResult != null) {
		query.setFirstResult(firstResult);
	    }

	    if (maxResults != null) {
		query.setMaxResults(maxResults);
	    }
	}

	return query;
    }

    /*
     * Add ascending sort criteria to the query
     * 
     * @see
     * com.nxp.appxplorer.query.DomainQuery#addAscSortedBy(java.lang.String)
     */
    @Override
    public void addAscSortedBy(final String propertyName) {
	addAscOrderBy(entityPrefix + "." + propertyName);
    }

    /*
     * Add descending sort criteria to the query
     * 
     * @see
     * com.nxp.appxplorer.query.DomainQuery#addDescSortedBy(java.lang.String)
     */
    @Override
    public void addDescSortedBy(final String propertyName) {
	addDescOrderBy(entityPrefix + "." + propertyName);
    }

    /*
     * Set the position of the first result to retrieve.
     * 
     * @see com.nxp.appxplorer.query.DomainQuery#setFirstResult(int)
     */
    @Override
    public void setFirstResult(final int firstResult) {
	this.firstResult = firstResult;
    }

    /*
     * Set the maximum number of results to retrieve.
     * 
     * @see com.nxp.appxplorer.query.DomainQuery#setMaxResults(int)
     */
    @Override
    public void setMaxResults(final int maxResults) {
	this.maxResults = maxResults;
    }

    /*
     * Finds an entity by id.
     * 
     * @see com.nxp.appxplorer.query.DomainQuery#findById(long)
     */
    @Override
    public Optional<T> findById(final long id) {
	return Optional.ofNullable(entityManager.find(mainClass, id));
    }

    /*
     * Finds an entity by id or throws a {@link EntityNotFoundInRepoException}.
     * 
     * @see com.nxp.appxplorer.query.DomainQuery#findByIdOrThrow(long)
     */
    @Override
    public T findByIdOrThrow(final long id) throws EntityNotFoundInRepoException {
	return findById(id).orElseThrow(() -> EntityNotFoundInRepoException.newInstance(mainClass, id));
    }

    /**
     * @param row
     * @param columnIndex
     * @return
     */
    @SuppressWarnings("unchecked")
    protected static <T> T getColumn(final Object row, final int columnIndex) {
	return (T) ((Object[]) row)[columnIndex];
    }
}
