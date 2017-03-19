package com.nxp.appxplorer.entity;

/**
 * Generate Fixed Entitys for Testing
 *
 */
public class TestDataGenerator {

    public static LoginType createLoginType(short loginTypeId) {
	LoginType loginType = LoginType.builder().name("admin").description("xxxxxx").build();
	loginType.setId(loginTypeId);
	return loginType;
    }

    public static LoginDetails generateLoginDetails() {
	LoginDetails loginDetails = LoginDetails.builder().accountTypeId((short) 1).loginTypeId((short) 1)
		.accountStatusId((short) 1).passwordHash(new byte[] { (byte) 0 }).salt(new byte[] { (byte) 0 })
		.passwordTypeId((short) 1).userName("gsrini.in@gmail.com").isActive("Y").build();
	return loginDetails;
    }

    public static LoginTokenDetails generateLoginTokenDetails() {
	LoginTokenDetails loginTokenDetails = LoginTokenDetails.builder().loginDetailsId(1L)
		.bearerToken("test-bearer-token").bearerTokenCreatedOn(System.currentTimeMillis())
		.loginOn(System.currentTimeMillis()).logoutOn(System.currentTimeMillis())
		.expiryInSeconds(System.currentTimeMillis()).reserve1("reverve1").reserve2("reverve2")
		.reserve3("reverve3").isActive("Y").build();
	return loginTokenDetails;
    }

    public static PasswordType createPassword() {
	PasswordType passwordType = PasswordType.builder().algorithmName("test").description("description")
		.iterations((short) 8192).desiredLength((short) 16).lengthOfSalt((short) 16).build();
	passwordType.setId((short) 1);
	return passwordType;
    }

    public static CardIssuer createCardIssuer() {
	CardIssuer cardIssuer = CardIssuer.builder().firstName("TestCardOne").lastName("TestCardOneLast")
		.email("TestCardOne@gmail.com").registrationStatusId((short) 1).registrationConfirmOn(1).isActive("Y")
		.build();
	return cardIssuer;
    }

    public static Card createCard(Long ciId) {
	Card card = Card.builder().cardName("TestCardOne").contactEmail("TestCardOne@gmail.com").isActive("Y").build();
	return card;
    }

    public static ApplicationProvider createApplicationProvider() {
	ApplicationProvider applicationProvider = ApplicationProvider.builder().firstName("TestCardOne")
		.lastName("TestCardOneLast").email("TestCardOne@gmail.com").registrationStatusId((short) 1)
		.registrationConfirmOn(1).isActive("Y").build();
	return applicationProvider;
    }

    public static Application createApplication(String applicationName) {
	Application application = Application.builder().providerName("TestAPPOne").applicationName(applicationName)
		.contactEmail("TestAPPOne@gmail.com").isActive("Y").build();
	return application;
    }

    public static BusinessSegment createBusinessSegment() {
	BusinessSegment businessSegment = BusinessSegment.builder().id((short) 18)
		.description("TEST - BusinessSegment Description").name("TEST - BusinessSegment Name")
		.priority((short) 1).isActive("Y").build();
	return businessSegment;
    }

    public static RegistrationStatus createRegistrationStatus() {
	RegistrationStatus registrationStatus = RegistrationStatus.builder().id((short) 18)
		.description("TEST - RegistrationStatus Description").name("TEST - RegistrationStatus Name")
		.isActive("Y").build();
	return registrationStatus;
    }

    public static SmartMediumSize createSmartMediumSize() {
	SmartMediumSize smartMediumSize = SmartMediumSize.builder().id((short) 18)
		.description("TEST - SmartMediumSize Description").name("TEST - Registration Status Name").isActive("Y")
		.build();
	return smartMediumSize;
    }

    public static SmartMediumType createSmartMediumType() {
	SmartMediumType smartMediumType = SmartMediumType.builder().id((short) 18)
		.description("TEST - SmartMediunType Description").name("TEST - SmartMediumType Name").isActive("Y")
		.build();
	return smartMediumType;
    }

    public static TechnologyUsed createTechnologyUsed() {
	TechnologyUsed technologyUsed = TechnologyUsed.builder().id((short) 18)
		.description("TEST - TechnologyUsed Description").name("TEST - TechnologyUsed Name").isActive("Y")
		.build();
	return technologyUsed;
    }

    public static Country createCountry(String ctryName) {
	Country country = Country.builder().id((short) 18).countryName(ctryName).alpha2ISOCountry("TC")
		.alpha3ISOCountry("TCS").isActive("Y").build();
	return country;
    }

    public static AddressType createAddressType(String addrTypeName) {
	AddressType addressType = AddressType.builder().id((short) 18).name(addrTypeName).description("TestDesc")
		.isActive("Y").build();
	return addressType;
    }

    public static APAddress createAPAddress(String apAddressName) {
	APAddress apAddress = APAddress.builder().addressTypeId((short) 1).addressLine1(apAddressName).isActive("Y")
		.build();
	return apAddress;
    }

    public static CIAddress createCIAddress(String ciAddressName) {
	CIAddress ciAddress = CIAddress.builder().addressTypeId((short) 1).addressLine1(ciAddressName).isActive("Y")
		.build();
	return ciAddress;
    }

    public static CardIssuerTnc createCardIssuerTnc() {
	CardIssuerTnc cardIssuerTnc = CardIssuerTnc.builder().tnc(new byte[] { (byte) 1, (byte) 2 }).isActive("Y")
		.build();
	return cardIssuerTnc;

    }

}
