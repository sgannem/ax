package com.nxp.appxplorer;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.nxp.appxplorer.query.APAddressQuery;
import com.nxp.appxplorer.query.AddressTypeQuery;
import com.nxp.appxplorer.query.ApplicationProviderQuery;
import com.nxp.appxplorer.query.ApplicationProviderTncQuery;
import com.nxp.appxplorer.query.ApplicationQuery;
import com.nxp.appxplorer.query.BusinessSegmentQuery;
import com.nxp.appxplorer.query.CIAddressQuery;
import com.nxp.appxplorer.query.CardIssuerQuery;
import com.nxp.appxplorer.query.CardIssuerTncQuery;
import com.nxp.appxplorer.query.CardQuery;
import com.nxp.appxplorer.query.CountryQuery;
import com.nxp.appxplorer.query.DomainQuery;
import com.nxp.appxplorer.query.LoginDetailsQuery;
import com.nxp.appxplorer.query.LoginTokenDetailsQuery;
import com.nxp.appxplorer.query.LoginTypeQuery;
import com.nxp.appxplorer.query.PasswordTypeQuery;
import com.nxp.appxplorer.query.RegistrationStatusQuery;
import com.nxp.appxplorer.query.SmartMediumSizeQuery;
import com.nxp.appxplorer.query.SmartMediumTypeQuery;
import com.nxp.appxplorer.query.TechnologyUsedQuery;

/**
 * This class acts as a Query factory for all the appxplorer hibernate pojo
 * entities. Factory for {@link DomainQuery}s.
 *
 */
@Singleton
public class DomainQueryFactory {

    /** This is an EntityManager Provider for hibernate **/
    private final Provider<EntityManager> entityManagerProvider;

    /**
     *
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param entityManagerProvider
     */
    @Inject
    DomainQueryFactory(final Provider<EntityManager> entityManagerProvider) {
	this.entityManagerProvider = entityManagerProvider;
    }

    /**
     * This method is being used by DAOs for creating Domain Query Factory
     * object.
     * 
     * @param entityManagerProvider
     * @return DomainQueryFactory
     */
    public static DomainQueryFactory newInstance(final Provider<EntityManager> entityManagerProvider) {
	return new DomainQueryFactory(entityManagerProvider);
    }

    /**
     * This method used to get Provider for entity manager.
     * 
     * @return Provider<EntityManager>
     */
    public Provider<EntityManager> getEntityManagerProvider() {
	return entityManagerProvider;
    }

    /**
     * This method used to create new LoginType Query.
     * 
     * @return LoginTypeQuery
     */
    public LoginTypeQuery newLoginTypeQuery() {
	return LoginTypeQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new Login Details Query.
     * 
     * @return LoginDetailsQuery
     */
    public LoginDetailsQuery newLoginDetailsQuery() {
	return LoginDetailsQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new Login Token Details Query.
     * 
     * @return
     */
    public LoginTokenDetailsQuery newLoginTokenDetailsQuery() {
	return LoginTokenDetailsQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new Password Type Query.
     * 
     * @return
     */
    public PasswordTypeQuery newPasswordTypeQuery() {
	return PasswordTypeQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new Business Segment Query.
     * 
     * @return
     */
    public BusinessSegmentQuery newBusinessSegmentQuery() {
	return BusinessSegmentQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new Small Medium Type Query.
     * 
     * @return
     */
    public SmartMediumTypeQuery newSmartMediumTypeQuery() {
	return SmartMediumTypeQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new Small Medium Size Query.
     * 
     * @return
     */
    public SmartMediumSizeQuery newSmartMediumSizeQuery() {
	return SmartMediumSizeQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new Technology Used Query.
     * 
     * @return
     */
    public TechnologyUsedQuery newTechnologyUsedQuery() {
	return TechnologyUsedQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new Registration Status Query.
     * 
     * @return
     */
    public RegistrationStatusQuery newRegistrationStatusQuery() {
	return RegistrationStatusQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new Card Issuer Query.
     * 
     * @return
     */
    public CardIssuerQuery newCardIssuerQuery() {
	return CardIssuerQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new Card Query.
     * 
     * @return
     */
    public CardQuery newCardQuery() {
	return CardQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new Application Provider fetch.
     * 
     * @return
     */
    public ApplicationProviderQuery newApplicationProviderQuery() {
	return ApplicationProviderQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new Application Query.
     * 
     * @return
     */
    public ApplicationQuery newApplicationQuery() {
	return ApplicationQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new Country Query.
     * 
     * 
     * @return
     */
    public CountryQuery newCountryQuery() {
	return CountryQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new Address Type Query.
     * 
     * @return
     */
    public AddressTypeQuery newAddressTypeQuery() {
	return AddressTypeQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new CI Address Query.
     * 
     * @return
     */
    public CIAddressQuery newCIAddressQuery() {
	return CIAddressQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new AP Address Query.
     * 
     * @return
     */
    public APAddressQuery newAPAddressQuery() {
	return APAddressQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new Card Issuer Tnc Query.
     * 
     * @return CardIssuerTncQuery
     */
    public CardIssuerTncQuery newCardIssuerTncQuery() {
	return CardIssuerTncQuery.newInstance(entityManagerProvider.get());
    }

    /**
     * This method used to create new Application Provider Tnc Query.
     * 
     * @return ApplicationProviderTncQuery
     */
    public ApplicationProviderTncQuery newApplicationProviderTncQuery() {
	return ApplicationProviderTncQuery.newInstance(entityManagerProvider.get());
    }

}
