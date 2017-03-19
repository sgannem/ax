package com.nxp.appxplorer.services.model.utils;

import java.util.ArrayList;
import java.util.List;

import com.nxp.appxplorer.entity.BusinessSegment;
import com.nxp.appxplorer.entity.CardIssuer;
import com.nxp.appxplorer.entity.Country;
import com.nxp.appxplorer.entity.LoginDetails;
import com.nxp.appxplorer.services.model.beans.BusinessSegmentResponse;
import com.nxp.appxplorer.services.model.beans.BusinessSegmentResponseDto;
import com.nxp.appxplorer.services.model.beans.CardIssuerResponse;
import com.nxp.appxplorer.services.model.beans.CardIssuerResponseDto;
import com.nxp.appxplorer.services.model.beans.CountryResponse;
import com.nxp.appxplorer.services.model.beans.CountryResponseDto;
import com.nxp.appxplorer.services.model.beans.ErrorDetails;
import com.nxp.appxplorer.services.model.beans.LoginResponse;
import com.nxp.appxplorer.services.model.beans.LoginResponseDto;
import com.nxp.appxplorer.services.model.beans.LogoutResponse;
import com.nxp.appxplorer.services.model.beans.LogoutResponseDto;
import com.nxp.appxplorer.services.model.beans.SignupResponse;
import com.nxp.appxplorer.services.model.beans.SignupResponseDto;
import com.nxp.appxplorer.services.model.beans.UpdateAccountDetailsResponse;
import com.nxp.appxplorer.services.model.beans.UpdateAccountDetailsResponseDto;
import com.nxp.appxplorer.services.model.beans.ViewAccountDetailsResponse;
import com.nxp.appxplorer.services.model.beans.ViewAccountDetailsResponseDto;

/**
 * This class holds all service model utilities.
 * 
 * @author nxa30710
 *
 */
public class ServiceResponseBuilder {

    private static final char[] DIGITS_UPPER = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
	    'C', 'D', 'E', 'F' };

    private static final String STATUS_FAILED = "failed";
    private static final String STATUS_SUCCESS = "success";
    private static final String EMAIL = "email";
    private static final String CARD_ISSUER = "ci";
    private static final String APP_PROVIDER = "ap";

    private ServiceResponseBuilder() {
    }

    /**
     * 
     */
    public static SignupResponseDto buildSignupResponseDtoForError(String errorCode, String errorMessage) {
	ErrorDetails errorDetails = ErrorDetails.builder().errorCode(errorCode).errorDescription(errorMessage).build();
	return SignupResponseDto.builder().status(STATUS_FAILED).errorDetails(errorDetails).build();
    }

    /**
     * 
     */
    public static SignupResponseDto buildSignupResponseDtoForSuccess(String userName, String externalUserId) {
	SignupResponse signupResponse = SignupResponse.builder().userName(userName).externalUserId(externalUserId)
		.build();
	return SignupResponseDto.builder().status(STATUS_SUCCESS).signupResponse(signupResponse).build();
    }

    /**
     * @param signupDto
     * @return
     */
    public static short extractLoginType(String loginType) {
	return loginType.equalsIgnoreCase(EMAIL) ? (short) 1 : (short) -1;
    }

    /**
     * @param signupDto
     * @return
     */
    public static short extractAccountType(String accountType) {
	return accountType.equalsIgnoreCase(CARD_ISSUER) ? (short) 2
		: accountType.equalsIgnoreCase(APP_PROVIDER) ? (short) 3 : (short) -1;
    }

    /**
     * 
     */
    public static LoginResponseDto buildLoginResponseDtoForError(String errorCode, String errorMessage) {
	ErrorDetails errorDetails = ErrorDetails.builder().errorCode(errorCode).errorDescription(errorMessage).build();
	return LoginResponseDto.builder().status(STATUS_FAILED).errorDetails(errorDetails).build();
    }

    /**
     * 
     */
    public static LoginResponseDto buildLoginResponseDtoSuccess(String bearerToken, String externalUserId) {
	LoginResponse loginResponse = LoginResponse.builder().token(bearerToken).externalUserId(externalUserId).build();
	return LoginResponseDto.builder().status(STATUS_SUCCESS).loginResponse(loginResponse).build();
    }

    /**
     * @param errorCode
     * @param errorMessage
     * @return
     */
    public static ViewAccountDetailsResponseDto buildViewAccountDetailsDtoForError(String errorCode,
	    String errorMessage) {
	ErrorDetails errorDetails = ErrorDetails.builder().errorCode(errorCode).errorDescription(errorMessage).build();
	return ViewAccountDetailsResponseDto.builder().status(STATUS_FAILED).errorDetails(errorDetails).build();
    }

    /**
     * @param errorCode
     * @param errorMessage
     * @return
     */
    public static BusinessSegmentResponseDto buildBusinessSegmentResponseDtoForError(String errorCode,
	    String errorMessage) {
	ErrorDetails errorDetails = ErrorDetails.builder().errorCode(errorCode).errorDescription(errorMessage).build();
	return BusinessSegmentResponseDto.builder().status(STATUS_FAILED).errorDetails(errorDetails).build();
    }

    /**
     * @param errorCode
     * @param errorMessage
     * @return
     */
    public static CountryResponseDto buildCountryResponseDtoForError(String errorCode, String errorMessage) {
	ErrorDetails errorDetails = ErrorDetails.builder().errorCode(errorCode).errorDescription(errorMessage).build();
	return CountryResponseDto.builder().status(STATUS_FAILED).errorDetails(errorDetails).build();
    }

    /**
     * @param loginDetails
     * @return
     */
    public static ViewAccountDetailsResponseDto buildViewAccountDetailsDtoForSuccess(String firstName, String lastName,
	    String email, String password, String companyName, String companyAddress, String zipCode, String country,
	    short businessSegment, byte[] companyLogo, byte[] tnc) {
	ViewAccountDetailsResponse viewAccountDetailsResponse = ViewAccountDetailsResponse.builder()
		.firstName(firstName).lastName(lastName).email(email).password(password).companyName(companyName)
		.companyAddress(companyAddress).zipCode(zipCode).country(country)
		.businessSegment(String.valueOf(businessSegment)).companyOrBusinessLogo(companyLogo)
		.uploadYourTermsAndConditions(tnc).build();
	return ViewAccountDetailsResponseDto.builder().status(STATUS_SUCCESS)
		.viewAccountDetailsResponse(viewAccountDetailsResponse).build();
    }

    /**
     * @param errorCode
     * @param errorMessage
     * @return
     */
    public static CardIssuerResponseDto buildCardIssuerDtoForError(String errorCode, String errorMessage) {
	ErrorDetails errorDetails = ErrorDetails.builder().errorCode(errorCode).errorDescription(errorMessage).build();
	return CardIssuerResponseDto.builder().status(STATUS_FAILED).errorDetails(errorDetails).build();
    }

    /**
     * @param loginDetails
     * @return
     */
    public static CardIssuerResponseDto buildCardIssuerDtoForSuccess(List<CardIssuer> lCI) {

	List<CardIssuerResponse> cir = new ArrayList<CardIssuerResponse>();
	if (lCI != null && !lCI.isEmpty()) {
	    for (CardIssuer cardIssuer : lCI) {
		CardIssuerResponse cardIssuerResponse = CardIssuerResponse.builder().id(cardIssuer.getId())
			.cards(cardIssuer.getCards()).companyName(cardIssuer.getCompanyName())
			.email(cardIssuer.getEmail()).firstName(cardIssuer.getFirstName())
			.isActive(cardIssuer.getIsActive()).lastName(cardIssuer.getLastName())
			.registrationConfirmOn(cardIssuer.getRegistrationConfirmOn())
			.registrationStatusId(cardIssuer.getRegistrationStatusId()).build();

		cir.add(cardIssuerResponse);
	    }
	}

	return CardIssuerResponseDto.builder().status(STATUS_SUCCESS).cardIssuerResponse(cir).build();
    }

    /**
     * @param loginDetails
     * @return
     */
    public static UpdateAccountDetailsResponseDto buildUpdateAccountDetailsResponseDtoForSuccess(
	    LoginDetails loginDetails, String firstName, String lastName, String email, String password,
	    String companyName, String companyAddress, String zipCode, String country, short businessSegment,
	    byte[] companyLogo, byte[] tnc) {
	UpdateAccountDetailsResponse updateAccountDetailsResponse = UpdateAccountDetailsResponse.builder()
		.userName(loginDetails.getUserName()).firstName(firstName).lastName(lastName).email(email)
		.password(password).companyName(companyName).companyAddress(companyAddress).zipCode(zipCode)
		.country(country).businessSegment(String.valueOf(businessSegment)).companyOrBusinessLogo(companyLogo)
		.uploadYourTermsAndConditions(tnc).build();
	return UpdateAccountDetailsResponseDto.builder().status(STATUS_SUCCESS)
		.updateAccountDetailsResponse(updateAccountDetailsResponse).build();
    }

    /**
     * @param errorCode
     * @param errorMessage
     * @return
     */
    public static UpdateAccountDetailsResponseDto buildUpdateAccountDetailsResponseDtoForError(String errorCode,
	    String errorMessage) {
	ErrorDetails errorDetails = ErrorDetails.builder().errorCode(errorCode).errorDescription(errorMessage).build();
	return UpdateAccountDetailsResponseDto.builder().status(STATUS_FAILED).errorDetails(errorDetails).build();
    }

    /**
     * 
     */
    public static LogoutResponseDto buildLogoutResponseDtoForSuccess(String userName) {
	LogoutResponse logoutResponse = LogoutResponse.builder().userName(userName).build();
	return LogoutResponseDto.builder().status(STATUS_SUCCESS).logoutResponse(logoutResponse)
		.logoutResponse(logoutResponse).build();
    }

    /**
     * 
     */
    public static LogoutResponseDto buildLogoutResponseDtoForError(String errorCode, String errorMessage) {
	ErrorDetails errorDetails = ErrorDetails.builder().errorCode(errorCode).errorDescription(errorMessage).build();
	return LogoutResponseDto.builder().status(STATUS_FAILED).errorDetails(errorDetails).build();
    }

    /**
     * @param array
     * @return
     */
    public static String getHexString(byte... array) {
	int length = array.length;
	char[] out = new char[length << 1];
	int j = 0;

	for (int i = 0; i < length; ++i) {
	    out[j] = DIGITS_UPPER[(240 & array[i]) >>> 4];
	    out[j + 1] = DIGITS_UPPER[15 & array[i]];
	    j += 2;
	}

	return String.valueOf(out);
    }

    /**
     * @param allCountries
     * @return
     */
    public static CountryResponseDto buildCountries(List<Country> allCountries) {
	CountryResponseDto countryResponseDto;
	List<com.nxp.appxplorer.services.model.beans.Country> countries = new ArrayList<>();
	for (Country temp : allCountries) {
	    com.nxp.appxplorer.services.model.beans.Country country = com.nxp.appxplorer.services.model.beans.Country
		    .builder().countryName(temp.getCountryName()).twoCharIsoCountryCode(temp.getAlpha2ISOCountry())
		    .threeCharIsoCountryCode(temp.getAlpha3ISOCountry()).build();
	    countries.add(country);
	}
	CountryResponse countryResponse = CountryResponse.builder().countries(countries).build();
	countryResponseDto = CountryResponseDto.builder().status(STATUS_SUCCESS).countryResponse(countryResponse)
		.build();
	return countryResponseDto;
    }

    /**
     * @param allBusinessSegments
     * @return
     */
    public static BusinessSegmentResponseDto buildBusinessSegments(List<BusinessSegment> allBusinessSegments) {
	List<com.nxp.appxplorer.services.model.beans.BusinessSegment> businessSegments = new ArrayList<>();
	for (BusinessSegment temp : allBusinessSegments) {
	    com.nxp.appxplorer.services.model.beans.BusinessSegment businessSegment = com.nxp.appxplorer.services.model.beans.BusinessSegment
		    .builder().id(String.valueOf(temp.getId())).name(temp.getName()).build();
	    businessSegments.add(businessSegment);
	}
	BusinessSegmentResponse businessSegmentResponse = BusinessSegmentResponse.builder()
		.businessSegments(businessSegments).build();
	return BusinessSegmentResponseDto.builder().status(STATUS_SUCCESS)
		.businessSegmentResponse(businessSegmentResponse).build();
    }

}
