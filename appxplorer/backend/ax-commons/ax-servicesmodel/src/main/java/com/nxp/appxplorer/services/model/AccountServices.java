package com.nxp.appxplorer.services.model;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.nxp.appxplorer.commons.services.utils.IRandomStringGenerator;
import com.nxp.appxplorer.commons.utils.TokenGeneration;
import com.nxp.appxplorer.entity.APAddress;
import com.nxp.appxplorer.entity.ApplicationProvider;
import com.nxp.appxplorer.entity.ApplicationProviderTnc;
import com.nxp.appxplorer.entity.CIAddress;
import com.nxp.appxplorer.entity.CardIssuer;
import com.nxp.appxplorer.entity.CardIssuerTnc;
import com.nxp.appxplorer.entity.LoginDetails;
import com.nxp.appxplorer.entity.LoginTokenDetails;
import com.nxp.appxplorer.entity.PasswordType;
import com.nxp.appxplorer.repository.APAddressRepository;
import com.nxp.appxplorer.repository.ApplicationProviderRepository;
import com.nxp.appxplorer.repository.ApplicationProviderTncRepository;
import com.nxp.appxplorer.repository.BusinessSegmentRepository;
import com.nxp.appxplorer.repository.CIAddressRepository;
import com.nxp.appxplorer.repository.CardIssuerRepository;
import com.nxp.appxplorer.repository.CardIssuerTncRepository;
import com.nxp.appxplorer.repository.CountryRepository;
import com.nxp.appxplorer.repository.LoginDetailsRepository;
import com.nxp.appxplorer.repository.LoginTokenDetailsRepository;
import com.nxp.appxplorer.repository.PasswordTypeRepository;
import com.nxp.appxplorer.services.model.beans.BusinessSegmentResponseDto;
import com.nxp.appxplorer.services.model.beans.CountryResponseDto;
import com.nxp.appxplorer.services.model.beans.LoginRequestDto;
import com.nxp.appxplorer.services.model.beans.LoginResponseDto;
import com.nxp.appxplorer.services.model.beans.LogoutResponseDto;
import com.nxp.appxplorer.services.model.beans.SignupRequestDto;
import com.nxp.appxplorer.services.model.beans.SignupResponseDto;
import com.nxp.appxplorer.services.model.beans.UpdateAccountDetailsRequestDto;
import com.nxp.appxplorer.services.model.beans.UpdateAccountDetailsResponseDto;
import com.nxp.appxplorer.services.model.beans.ViewAccountDetailsResponseDto;
import com.nxp.appxplorer.services.model.utils.ServiceResponseBuilder;

/**
 * This class handles all the Account services Data Base interactions during
 * Service invocations.
 * 
 * @author nxa30710
 *
 */

@Singleton
public class AccountServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServices.class);
    private static final String ALLOWED_CHARS = "1234567890";

    /** This is a LoginDetails Repository **/
    private LoginDetailsRepository loginDetailsRepository;
    /** This is a LoginToken Repository **/
    private LoginTokenDetailsRepository loginTokenDetailsRepository;
    /** This is a PasswordType Repository **/
    private PasswordTypeRepository passwordTypeResitory;
    /** This is a CardIssuer Repository **/
    private CardIssuerRepository cardIssuerRepository;
    /** This is a Application Provider Repository **/
    private ApplicationProviderRepository applicationProviderRepository;
    /** This is a CardIssuer Tnc Repository **/
    private CardIssuerTncRepository cardIssuerTncRepository;
    /** This is a country Repository **/
    private CountryRepository countryRepository;
    /** This is a BusinessSegment Repository **/
    private BusinessSegmentRepository businessSegmentRepository;
    /** This is a ApplicationProvider Tnc Repository **/
    private ApplicationProviderTncRepository applicationProviderTncRepository;
    private CIAddressRepository ciAddressRepository;
    private APAddressRepository apAddressRepository;

    /** Random user id generator **/
    private IRandomStringGenerator randomStringGenerator;

    private static final String CODE_INVALID_EXTERNAL_USER_ID = "AX30001";
    private static final String CODE_INVALID_ACCOUNT_DETAILS = "AX10001";
    private static final String CODE_ACCOUNT_EXISTS = "AX10000";

    /**
     * This constructor is being invoked by the Guice Framework to inject the
     * following object params during runtime.
     * 
     * @param loginDetailsRepository
     * @param loginTokenDetailsRepository
     * @param passwordTypeResitory
     */
    @Inject
    public AccountServices(LoginDetailsRepository loginDetailsRepository,
	    LoginTokenDetailsRepository loginTokenDetailsRepository, PasswordTypeRepository passwordTypeResitory,
	    CardIssuerRepository cardIssuerRepository, ApplicationProviderRepository applicationProviderRepository,
	    CardIssuerTncRepository cardIssuerTncRepository, CountryRepository countryRepository,
	    BusinessSegmentRepository businessSegmentRepository,
	    ApplicationProviderTncRepository applicationProviderTncRepository, CIAddressRepository ciAddressRepository,
	    APAddressRepository apAddressRepository, IRandomStringGenerator randomStringGenerator) {
	this.loginDetailsRepository = loginDetailsRepository;
	this.loginTokenDetailsRepository = loginTokenDetailsRepository;
	this.passwordTypeResitory = passwordTypeResitory;
	this.cardIssuerRepository = cardIssuerRepository;
	this.applicationProviderRepository = applicationProviderRepository;
	this.cardIssuerTncRepository = cardIssuerTncRepository;
	this.countryRepository = countryRepository;
	this.businessSegmentRepository = businessSegmentRepository;
	this.applicationProviderTncRepository = applicationProviderTncRepository;
	this.ciAddressRepository = ciAddressRepository;
	this.apAddressRepository = apAddressRepository;
	this.randomStringGenerator = randomStringGenerator;
    }

    /**
     * This method invoked during sign up process to save all the account
     * details into Data Base. If the account creation is successful then users
     * can login into the market portals.
     * 
     * @param signupDto
     * @return SignupResponseDto
     * 
     */
    public SignupResponseDto createAccount(SignupRequestDto signupDto) {
	LOGGER.info("#account creation is in progress..");
	byte[] passwordHash = null;
	byte[] salt = null;
	SignupResponseDto signupResponseDto = null;
	try {
	    if (!isAccountAlreadyExists(signupDto)) {
		String password = signupDto.getSignupRequest().getPassword();
		Optional<PasswordType> passwordTypeObject = passwordTypeResitory.getById((short) 1);
		PasswordType passwordType = passwordTypeObject.isPresent() ? passwordTypeObject.get() : null;
		LOGGER.info("#Got Password type:{}", (passwordType != null ? passwordType : ""));
		if (passwordType != null) {
		    salt = new byte[passwordType.getLengthOfSalt()];
		    UserBuilder.SECURE_RANDOM.nextBytes(salt);
		    passwordHash = UserBuilder.pbkdf2(password, salt, passwordType.getIterations(),
			    passwordType.getDesiredLength(), passwordType.getAlgorithmName());
		}
		String externalUserId = generateExternalUserId();
		LoginDetails ld = LoginDetails.builder().accountStatusId((short) 1)
			.accountTypeId(ServiceResponseBuilder
				.extractAccountType(signupDto.getSignupRequest().getAccountType()))
			.loginTypeId(
				ServiceResponseBuilder.extractLoginType(signupDto.getSignupRequest().getLoginType()))
			.passwordTypeId((short) 1).roleId((short) 1)
			.userName(signupDto.getSignupRequest().getUserName()).passwordHash(passwordHash).salt(salt)
			.externalUserId(externalUserId).isActive("Y").build();
		loginDetailsRepository.add(ld);
		signupResponseDto = ServiceResponseBuilder
			.buildSignupResponseDtoForSuccess(signupDto.getSignupRequest().getUserName(), externalUserId);
	    } else {
		LOGGER.info("#user is alredy exists....{}", signupDto.toString());
		signupResponseDto = ServiceResponseBuilder.buildSignupResponseDtoForError(CODE_ACCOUNT_EXISTS,
			"Account Already exists");

	    }
	} catch (Exception e) {
	    LOGGER.info("#Error occured during account creation:" + e);
	    signupResponseDto = ServiceResponseBuilder.buildSignupResponseDtoForError(CODE_INVALID_ACCOUNT_DETAILS,
		    "internal error occured during account creation");
	}
	return signupResponseDto;
    }

    /**
     * It generates unique external userId
     * 
     * @return
     */
    private String generateExternalUserId() {
	String externalUserId = randomStringGenerator.randomString(20, ALLOWED_CHARS);
	boolean isExists = false;
	do {
	    if (loginDetailsRepository.isExternalUserIdExists(externalUserId)) {
		externalUserId = randomStringGenerator.randomString(20, ALLOWED_CHARS);
	    } else {
		isExists = true;
	    }
	} while (!isExists);
	LOGGER.info("#Generated external userId:{}", externalUserId);
	return externalUserId;
    }

    /**
     * This method validates whether userName is already with the Data base.
     * 
     * @param signupDto
     * @return boolean
     */
    private boolean isAccountAlreadyExists(SignupRequestDto signupDto) {
	boolean status;
	short accountType = ServiceResponseBuilder.extractAccountType(signupDto.getSignupRequest().getAccountType());
	LOGGER.info("#Account Type is {} mapped value is {}", signupDto.getSignupRequest().getAccountType(),
		accountType);
	short loginType = ServiceResponseBuilder.extractLoginType(signupDto.getSignupRequest().getLoginType());
	LOGGER.info("#Login Type is {} mapped value is {}", signupDto.getSignupRequest().getLoginType(), loginType);
	status = loginDetailsRepository.isLoginExists(accountType, loginType,
		signupDto.getSignupRequest().getUserName());
	LOGGER.info("#account status in DB:userName:{} status: {}", signupDto.getSignupRequest().getUserName(),
		(status ? "Already exists in the DB" : "User does not exists in DB"));
	return status;
    }

    /**
     * This method validates the login account details thru injected Account
     * repositories. If this is true. then account validation is successful.
     * 
     * @param loginDto
     * @return LoginResponseDto
     */
    public LoginResponseDto validateLogin(LoginRequestDto loginDto) {
	LOGGER.info("#validateLogin is called");
	LoginResponseDto loginResponseDto = null;
	try {
	    if (loginDetailsRepository.isLoginExists(
		    ServiceResponseBuilder.extractAccountType(loginDto.getLoginRequest().getAccountType()),
		    ServiceResponseBuilder.extractLoginType(loginDto.getLoginRequest().getLoginType()),
		    loginDto.getLoginRequest().getUserName())) {
		String password = loginDto.getLoginRequest().getPassword();
		Optional<PasswordType> passwordTypeObject = passwordTypeResitory.getById((short) 1);
		PasswordType passwordType = passwordTypeObject.isPresent() ? passwordTypeObject.get() : null;
		Short sIterations = 0;
		Short sDesiredLen = 0;
		String strAlgName = "";
		if (passwordType != null) {
		    sIterations = passwordType.getIterations();
		    sDesiredLen = passwordType.getDesiredLength();
		    strAlgName = passwordType.getAlgorithmName();
		}
		LOGGER.info("#Got Password type:{}", (passwordType != null ? passwordType : ""));
		Optional<LoginDetails> l = loginDetailsRepository.getLoginDetails(
			ServiceResponseBuilder.extractAccountType(loginDto.getLoginRequest().getAccountType()),
			ServiceResponseBuilder.extractLoginType(loginDto.getLoginRequest().getLoginType()),
			loginDto.getLoginRequest().getUserName());
		if (l.isPresent()) {
		    LoginDetails ld = l.get();
		    byte[] passwordHash = UserBuilder.pbkdf2(password, ld.getSalt(), sIterations, sDesiredLen,
			    strAlgName);
		    if (Arrays.equals(ld.getPasswordHash(), passwordHash)) {
			String bearerToken = TokenGeneration.getBearerToken();
			LOGGER.info("#Both passwords are matched...then creating bearer token...");
			LoginTokenDetails loginTokenDetails = LoginTokenDetails.builder().loginDetailsId(ld.getId())
				.bearerToken(bearerToken).bearerTokenCreatedOn(System.currentTimeMillis())
				.loginOn(System.currentTimeMillis()).expiryInSeconds(System.currentTimeMillis())
				.reserve1("reverve1").reserve2("reverve2").reserve3("reverve3").isActive("Y").build();
			loginTokenDetailsRepository.add(loginTokenDetails);
			loginResponseDto = ServiceResponseBuilder.buildLoginResponseDtoSuccess(bearerToken,
				ld.getExternalUserId());
		    } else {
			loginResponseDto = ServiceResponseBuilder.buildLoginResponseDtoForError("AX10003",
				"username and password does not match");
		    }
		}
	    } else {
		LOGGER.info("#user does not exists....{}", loginDto.toString());
		loginResponseDto = ServiceResponseBuilder.buildLoginResponseDtoForError("AX10002",
			"username and password does not exist");
	    }
	} catch (Exception e) {
	    LOGGER.info("#Error occured during account validation:" + e);
	    loginResponseDto = ServiceResponseBuilder.buildLoginResponseDtoForError(CODE_INVALID_ACCOUNT_DETAILS,
		    "internal error occured during account creation");

	}
	return loginResponseDto;
    }

    public LogoutResponseDto logout(String token) {
	LogoutResponseDto logoutResponseDto;
	String userName = "";
	Optional<LoginTokenDetails> loginTokenDetails = loginTokenDetailsRepository.getLoginTokenDetails(token);
	if (loginTokenDetails.isPresent()) {
	    LOGGER.info("#Got login Token details from the DB:" + loginTokenDetails.toString());
	    LoginTokenDetails ltd = loginTokenDetails.get();
	    ltd.setLogoutOn(System.currentTimeMillis());
	    ltd.setIsActive("N");
	    logoutResponseDto = ServiceResponseBuilder.buildLogoutResponseDtoForSuccess(userName);
	} else {
	    logoutResponseDto = ServiceResponseBuilder.buildLogoutResponseDtoForError(CODE_INVALID_ACCOUNT_DETAILS,
		    "User does not exist");
	}
	return logoutResponseDto;
    }

    public boolean validateBearerToken(String bearerToken) {
	boolean status = true;
	Optional<LoginTokenDetails> loginTokenDetails = loginTokenDetailsRepository.getLoginTokenDetails(bearerToken);
	if (loginTokenDetails.isPresent()) {
	    LOGGER.info("#Got login Token details from the DB:" + loginTokenDetails.toString());
	} else {
	    LOGGER.info("#bearer token does not exists in db");
	    status = false;
	}
	return status;
    }

    /**
     * @param externalUserId
     * @return
     */
    public ViewAccountDetailsResponseDto viewAccountDetails(String externalUserId) {
	LOGGER.info("#viewAccountDetails method is called");
	ViewAccountDetailsResponseDto viewAccountDetailsResponseDto = null;
	String firstName = "";
	String lastName = "";
	String email = "";
	String pd = "";
	String companyName = "";
	String companyAddress = "";
	String zipCode = "";
	String country = "";
	short businessSegmentId = (short) 0;
	byte[] companyOrBusinessLogo = new byte[] {};
	byte[] uploadYourTermsAndConditions = new byte[] {};
	LOGGER.info("#Got externalUserId:{}", externalUserId);
	try {
	    Optional<LoginDetails> ldo = loginDetailsRepository.findByExternalUserId(externalUserId);
	    if (ldo.isPresent()) {
		LOGGER.info("#Login Details fetched successfully by using external user id");
		LoginDetails ld = ldo.get();
		if (ld.getAccountTypeId() == 2) {
		    if (ld.getUserId() != null) {
			Optional<CardIssuer> cio = cardIssuerRepository.findById(ld.getUserId());
			if (cio.isPresent()) {
			    CardIssuer ci = cio.get();
			    firstName = ci.getFirstName();
			    lastName = ci.getLastName();
			    email = ci.getEmail();
			    pd = ServiceResponseBuilder.getHexString(ld.getPasswordHash());
			    companyName = ci.getCompanyName();
			    // companyAddress = ci.getCompanyAddress();
			    Set<CIAddress> a = ci.getAddresses();
			    for (CIAddress temp : a) {
				companyAddress = temp.getAddressLine1();
				zipCode = temp.getZipCode();
				country = temp.getCountry();
				break;
			    }
			    businessSegmentId = ci.getBusinessSegmentId();
			    companyOrBusinessLogo = ci.getCompanyOrBusinessLogo();
			    uploadYourTermsAndConditions = ci.getCardIssuerTnc().getTnc();
			}
		    } else {
			LOGGER.info("#Card issuer does not existing for this external user");
			LOGGER.info("#fetching and setting email and password from loginDetails table");
			email = ld.getUserName();
			pd = ServiceResponseBuilder.getHexString(ld.getPasswordHash());
		    }

		    viewAccountDetailsResponseDto = ServiceResponseBuilder.buildViewAccountDetailsDtoForSuccess(
			    firstName, lastName, email, pd, companyName, companyAddress, zipCode, country,
			    businessSegmentId, companyOrBusinessLogo, uploadYourTermsAndConditions);
		} else if (ld.getAccountTypeId() == 3) {
		    // TODO APP Provider
		    LOGGER.info("#Appprovider login");
		    if (ld.getUserId() != null) {
			Optional<ApplicationProvider> apo = applicationProviderRepository.findById(ld.getUserId());
			if (apo.isPresent()) {
			    ApplicationProvider ap = apo.get();
			    firstName = ap.getFirstName();
			    lastName = ap.getLastName();
			    email = ap.getEmail();
			    pd = ServiceResponseBuilder.getHexString(ld.getPasswordHash());
			    companyName = ap.getCompanyName();
			    Set<APAddress> a = ap.getAddresses();
			    for (APAddress temp : a) {
				companyAddress = temp.getAddressLine1();
				zipCode = temp.getZipCode();
				country = temp.getCountry();
				break;
			    }
			    businessSegmentId = ap.getBusinessSegmentId();
			    companyOrBusinessLogo = ap.getCompanyOrBusinessLogo();
			    uploadYourTermsAndConditions = ap.getApplicationProviderTnc().getTnc();
			}
		    } else {
			LOGGER.info("#Card issuer does not existing for this external user");
			LOGGER.info("#fetching and setting email and password from loginDetails table");
			email = ld.getUserName();
			pd = ServiceResponseBuilder.getHexString(ld.getPasswordHash());
		    }
		    viewAccountDetailsResponseDto = ServiceResponseBuilder.buildViewAccountDetailsDtoForSuccess(
			    firstName, lastName, email, pd, companyName, companyAddress, zipCode, country,
			    businessSegmentId, companyOrBusinessLogo, uploadYourTermsAndConditions);

		}
	    } else {
		viewAccountDetailsResponseDto = ServiceResponseBuilder.buildViewAccountDetailsDtoForError("AX30002",
			"User does not exists in the DB");
	    }

	} catch (Exception e) {
	    viewAccountDetailsResponseDto = ServiceResponseBuilder.buildViewAccountDetailsDtoForError(
		    CODE_INVALID_EXTERNAL_USER_ID, "Error occured during account details fetching from db");
	    LOGGER.error(e.getMessage(), e);
	}
	return viewAccountDetailsResponseDto;
    }

    /**
     * @param externalUserId
     * @return
     */
    public UpdateAccountDetailsResponseDto updateAccountDetails(String externalUserId,
	    UpdateAccountDetailsRequestDto updateAccountDetailsRequestDto) {
	LOGGER.info("#viewAccountDetails method is called");
	UpdateAccountDetailsResponseDto updateAccountDetailsResponseDto = null;
	try {
	    Optional<LoginDetails> ldo = loginDetailsRepository.findByExternalUserId(externalUserId);
	    if (ldo.isPresent()) {
		LoginDetails ld = ldo.get();
		switch (ld.getExternalUserId() != null ? ld.getAccountTypeId() : -1) {
		case 2:
		    updateAccountDetailsResponseDto = updateCardIssuerDetails(updateAccountDetailsRequestDto, ldo, ld);
		    break;
		case 3:
		    updateAccountDetailsResponseDto = updateApplicationProviderDetails(updateAccountDetailsRequestDto,
			    ldo, ld);
		    break;
		case -1:
		    updateAccountDetailsResponseDto = ServiceResponseBuilder
			    .buildUpdateAccountDetailsResponseDtoForError(CODE_INVALID_EXTERNAL_USER_ID,
				    "invalid external userId");
		    break;
		default:
		    LOGGER.info("#Account type is neither card issuer nor application provider");
		}
	    } else {
		updateAccountDetailsResponseDto = ServiceResponseBuilder
			.buildUpdateAccountDetailsResponseDtoForError("AX30000", "Account Details doesn't exist");
	    }
	} catch (Exception e) {
	    updateAccountDetailsResponseDto = ServiceResponseBuilder.buildUpdateAccountDetailsResponseDtoForError(
		    CODE_INVALID_EXTERNAL_USER_ID, "Error occured during account details fetching from db");
	    LOGGER.error(e.getMessage(), e);
	}
	return updateAccountDetailsResponseDto;
    }

    /**
     * @param updateAccountDetailsRequestDto
     * @param password
     * @param ldo
     * @param ld
     */
    private UpdateAccountDetailsResponseDto updateCardIssuerDetails(
	    UpdateAccountDetailsRequestDto updateAccountDetailsRequestDto, Optional<LoginDetails> ldo,
	    LoginDetails ld) {
	UpdateAccountDetailsResponseDto updateAccountDetailsResponseDto;
	String firstName;
	String lastName;
	String email;
	String companyName;
	String pd = "";
	String companyAddress = "";
	String zipCode = "";
	String country = "";
	short businessSegmentId;
	byte[] companyOrBusinessLogo;
	byte[] uploadYourTermsAndConditions;
	LOGGER.info("#Account type is CardIssuer");
	if (ld.getUserId() != null) {
	    LOGGER.info("#Card Issuer is already present" + ld.getUserId());
	    Optional<CardIssuer> cio = cardIssuerRepository.findById(ld.getUserId());
	    if (cio.isPresent()) {
		CardIssuer ci = cio.get();
		LOGGER.info("#CardIssuer Details are here...{}", ci);
		LOGGER.info("#CardIssuer account details are updating");
		ci.setFirstName(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getFirstName());
		ci.setLastName(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getLastName());
		ci.setEmail(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getEmail());
		ci.setCompanyName(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCompanyName());
		// CIAddress ciAddress = CIAddress.builder()
		// .addressLine1(
		// updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCompanyAddress())
		// .country(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCountry())
		// .zipCode(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getZipCode())
		// .addressTypeId((short) 1).isActive("Y").build();
		// ci.getAddresses().add(ciAddress);
		short businessSegmentId1 = (updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest()
			.getBusinessSegment() != null) ? Short.parseShort(
				updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getBusinessSegment())
				: (short) 0;
		ci.setBusinessSegmentId(businessSegmentId1);
		ci.setCompanyOrBusinessLogo(
			updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCompanyOrBusinessLogo());
		ci.getCardIssuerTnc().setTnc(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest()
			.getUploadYourTermsAndConditions());
		firstName = ci.getFirstName();
		lastName = ci.getLastName();
		email = ci.getEmail();
		companyName = ci.getCompanyName();
		Set<CIAddress> a = ci.getAddresses();
		for (CIAddress temp : a) {
		    temp.setAddressLine1(
			    updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCompanyAddress());
		    temp.setZipCode(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getZipCode());
		    temp.setCountry(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCountry());
		    companyAddress = temp.getAddressLine1();
		    zipCode = temp.getZipCode();
		    country = temp.getCountry();
		    break;
		}
		businessSegmentId = ci.getBusinessSegmentId();
		companyOrBusinessLogo = ci.getCompanyOrBusinessLogo();
		uploadYourTermsAndConditions = ci.getCardIssuerTnc().getTnc();
		pd = ServiceResponseBuilder.getHexString(ld.getPasswordHash());
		LOGGER.info("#CardIssuer account details are updated successfully.new account details are here {}", ci);
	    } else {
		LOGGER.info("#Since CardIssuer is not present, Creating new card issuer with following param {}",
			updateAccountDetailsRequestDto);
		CardIssuer cardIssuer = buildCardIssuer(updateAccountDetailsRequestDto);
		firstName = cardIssuer.getFirstName();
		lastName = cardIssuer.getLastName();
		email = cardIssuer.getEmail();
		companyName = cardIssuer.getCompanyName();
		ld.setUserId(String.valueOf(cardIssuer.getId()));
		Set<CIAddress> a = cardIssuer.getAddresses();
		for (CIAddress temp : a) {
		    companyAddress = temp.getAddressLine1();
		    zipCode = temp.getZipCode();
		    country = temp.getCountry();
		    break;
		}
		businessSegmentId = cardIssuer.getBusinessSegmentId();
		companyOrBusinessLogo = cardIssuer.getCompanyOrBusinessLogo();
		uploadYourTermsAndConditions = cardIssuer.getCardIssuerTnc().getTnc();
	    }
	} else {
	    LOGGER.info("#Since CardIssuer is not present, Creating new card issuer with following param {}",
		    updateAccountDetailsRequestDto);
	    CardIssuer cardIssuer = buildCardIssuer(updateAccountDetailsRequestDto);
	    firstName = cardIssuer.getFirstName();
	    lastName = cardIssuer.getLastName();
	    email = cardIssuer.getEmail();
	    companyName = cardIssuer.getCompanyName();
	    Set<CIAddress> a = cardIssuer.getAddresses();
	    for (CIAddress temp : a) {
		companyAddress = temp.getAddressLine1();
		zipCode = temp.getZipCode();
		country = temp.getCountry();
		break;
	    }
	    businessSegmentId = cardIssuer.getBusinessSegmentId();
	    companyOrBusinessLogo = cardIssuer.getCompanyOrBusinessLogo();
	    uploadYourTermsAndConditions = cardIssuer.getCardIssuerTnc().getTnc();
	    ld.setUserId(String.valueOf(cardIssuer.getId()));
	}
	updateAccountDetailsResponseDto = ServiceResponseBuilder.buildUpdateAccountDetailsResponseDtoForSuccess(
		ldo.isPresent() ? ldo.get() : null, firstName, lastName, email, pd, companyName, companyAddress,
		zipCode, country, businessSegmentId, companyOrBusinessLogo, uploadYourTermsAndConditions);
	return updateAccountDetailsResponseDto;
    }

    /**
     * @param updateAccountDetailsRequestDto
     * @param password
     * @param ldo
     * @param ld
     */
    private UpdateAccountDetailsResponseDto updateApplicationProviderDetails(
	    UpdateAccountDetailsRequestDto updateAccountDetailsRequestDto, Optional<LoginDetails> ldo,
	    LoginDetails ld) {
	UpdateAccountDetailsResponseDto updateAccountDetailsResponseDto;
	String firstName;
	String lastName;
	String email;
	String companyName;
	String pd = "";
	String companyAddress = "";
	String zipCode = "";
	String country = "";
	short businessSegmentId = (short) 0;
	byte[] companyOrBusinessLogo = new byte[] {};
	byte[] uploadYourTermsAndConditions = new byte[] {};
	LOGGER.info("#Account type is CardIssuer");
	if (ld.getUserId() != null) {
	    LOGGER.info("#Application Provider already present" + ld.getUserId());
	    Optional<ApplicationProvider> apo = applicationProviderRepository.findById(ld.getUserId());
	    if (apo.isPresent()) {
		ApplicationProvider ap = apo.get();
		LOGGER.info("#Application Provider details are here...{}", ap);
		LOGGER.info("#Application Provider account details are updating");
		ap.setFirstName(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getFirstName());
		ap.setLastName(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getLastName());
		ap.setEmail(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getEmail());
		ap.setCompanyName(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCompanyName());
		firstName = ap.getFirstName();
		lastName = ap.getLastName();
		email = ap.getEmail();
		companyName = ap.getCompanyName();
		pd = ServiceResponseBuilder.getHexString(ld.getPasswordHash());
		Set<APAddress> a = ap.getAddresses();
		for (APAddress temp : a) {
		    temp.setAddressLine1(
			    updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCompanyAddress());
		    temp.setZipCode(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getZipCode());
		    temp.setCountry(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCountry());
		    companyAddress = temp.getAddressLine1();
		    zipCode = temp.getZipCode();
		    country = temp.getCountry();
		    break;
		}
		LOGGER.info(
			"#Application Provider account details are updated successfully.new account details are here {}",
			ap);
	    } else {
		LOGGER.info(
			"#Since Application Provider is not present, Creating new card issuer with following param{}",
			updateAccountDetailsRequestDto);
		ApplicationProvider applicationProvider = buildApplicationProvider(updateAccountDetailsRequestDto);
		firstName = applicationProvider.getFirstName();
		lastName = applicationProvider.getLastName();
		email = applicationProvider.getEmail();
		companyName = applicationProvider.getCompanyName();
		ld.setUserId(String.valueOf(applicationProvider.getId()));
	    }
	} else {
	    LOGGER.info(
		    "#Since Application Provider is not present, Creating new application provider with following param{}",
		    updateAccountDetailsRequestDto);
	    ApplicationProvider applicationProvider = buildApplicationProvider(updateAccountDetailsRequestDto);
	    firstName = applicationProvider.getFirstName();
	    lastName = applicationProvider.getLastName();
	    email = applicationProvider.getEmail();
	    companyName = applicationProvider.getCompanyName();
	    ld.setUserId(String.valueOf(applicationProvider.getId()));
	}
	updateAccountDetailsResponseDto = ServiceResponseBuilder.buildUpdateAccountDetailsResponseDtoForSuccess(
		ldo.isPresent() ? ldo.get() : null, firstName, lastName, email, pd, companyName, companyAddress,
		zipCode, country, businessSegmentId, companyOrBusinessLogo, uploadYourTermsAndConditions);
	return updateAccountDetailsResponseDto;
    }

    private CardIssuer buildCardIssuer(UpdateAccountDetailsRequestDto updateAccountDetailsRequestDto) {
	LOGGER.info("inserting cardissuer details into DB");
	CardIssuer cardIssuer = CardIssuer.builder()
		.firstName(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getFirstName())
		.lastName(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getLastName())
		.email(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getEmail())
		.companyName(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCompanyName())
		.registrationStatusId((short) 2)
		// .companyAddress(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCompanyAddress())
		// .zipCode(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getZipCode())
		// .country(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCountry())
		.businessSegmentId(
			updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getBusinessSegment() != null
				? Short.parseShort(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest()
					.getBusinessSegment())
				: (short) 0)
		.companyOrBusinessLogo(
			updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCompanyOrBusinessLogo())
		.isActive("Y").build();
	CIAddress ciAddress = CIAddress.builder()
		.addressLine1(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCompanyAddress())
		.country(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCountry())
		.zipCode(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getZipCode()).isActive("Y")
		.build();
	byte[] tnc = updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getUploadYourTermsAndConditions();
	CardIssuerTnc cardIssuerTnc = CardIssuerTnc.builder().tnc(tnc).isActive("Y").build();
	cardIssuerTnc.setCardIssuer(cardIssuer);
	cardIssuer.setCardIssuerTnc(cardIssuerTnc);
	ciAddress.setCardIssuer(cardIssuer);
	cardIssuer.getAddresses().add(ciAddress);
	cardIssuerRepository.add(cardIssuer);
	cardIssuerTncRepository.add(cardIssuerTnc);
	ciAddressRepository.add(ciAddress);
	LOGGER.info("Cardissuer details inserted successfully");
	return cardIssuer;
    }

    private ApplicationProvider buildApplicationProvider(
	    UpdateAccountDetailsRequestDto updateAccountDetailsRequestDto) {
	LOGGER.info("inserting Application Provider details into DB");
	ApplicationProvider applicationProvider = ApplicationProvider.builder()
		.firstName(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getFirstName())
		.lastName(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getLastName())
		.email(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getEmail())
		.companyName(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCompanyName())
		.registrationStatusId((short) 2)
		.businessSegmentId(
			updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getBusinessSegment() != null
				? Short.parseShort(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest()
					.getBusinessSegment())
				: (short) 0)
		.companyOrBusinessLogo(
			updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCompanyOrBusinessLogo())
		.isActive("Y").build();
	APAddress apAddress = APAddress.builder()
		.addressLine1(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCompanyAddress())
		.country(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getCountry())
		.zipCode(updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getZipCode()).isActive("Y")
		.build();
	apAddress.setApplicationProvider(applicationProvider);
	byte[] tnc = updateAccountDetailsRequestDto.getUpdateAccountDetailsRequest().getUploadYourTermsAndConditions();
	ApplicationProviderTnc applicationProviderTnc = ApplicationProviderTnc.builder().tnc(tnc).isActive("Y").build();
	applicationProviderTnc.setApplicationProvider(applicationProvider);
	applicationProvider.getAddresses().add(apAddress);
	applicationProvider.setApplicationProviderTnc(applicationProviderTnc);
	applicationProviderRepository.add(applicationProvider);
	applicationProviderTncRepository.add(applicationProviderTnc);
	apAddress.setApplicationProvider(applicationProvider);
	apAddressRepository.add(apAddress);
	LOGGER.info("ApplicationProvider details inserted successfully");
	return applicationProvider;
    }

    public CountryResponseDto countries() {
	return ServiceResponseBuilder.buildCountries(countryRepository.getAllCountries());
    }

    public BusinessSegmentResponseDto businessSegments() {
	return ServiceResponseBuilder.buildBusinessSegments(businessSegmentRepository.getAllBusinessSegments());
    }

}
