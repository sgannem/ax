package com.nxp.appxplorer.entity;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * Unit Tests for Creating and Removing Entities
 *
 */
public class EntityTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityTest.class);

    @Test
    public void testLoginType() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();

	LoginType loginType = LoginType.builder().name("test").description("description").build();
	loginType.setId((short) 1);
	System.out.println("#############################");
	System.out.println("#login type:" + loginType);
	LOGGER.info("#login Type:" + loginType.toString());
	entityManagerHelper.persist(loginType);
    }

    @Test
    public void testAccountType() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();
	AccountType accountType = AccountType.builder().name("test").description("description").build();
	accountType.setId((short) 1);
	System.out.println("#############################");
	System.out.println("#accountType:" + accountType);
	LOGGER.info("#accountType:" + accountType.toString());
	entityManagerHelper.persist(accountType);

    }

    @Test
    public void testPasswordType() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();
	PasswordType passwordType = PasswordType.builder().algorithmName("test").description("description")
		.iterations((short) 8192).desiredLength((short) 16).lengthOfSalt((short) 16).build();
	passwordType.setId((short) 1);
	System.out.println("#############################");
	System.out.println("#PasswordType:" + passwordType);
	LOGGER.info("#PasswordType:" + passwordType.toString());
	entityManagerHelper.persist(passwordType);
    }

    @Test
    public void testAccountStatus() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();
	AccountStatus accountStatus = AccountStatus.builder().name("test").description("description").build();
	accountStatus.setId((short) 1);
	System.out.println("#############################");
	System.out.println("#accountStatus:" + accountStatus);
	LOGGER.info("#PasswordType:" + accountStatus.toString());
	entityManagerHelper.persist(accountStatus);
    }

    @Test
    public void testUserRole() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();
	UserRole userRole = UserRole.builder().name("test").description("description").build();
	userRole.setId((short) 1);
	System.out.println("#############################");
	System.out.println("#UserRole:" + userRole);
	LOGGER.info("#PasswordType:" + userRole.toString());
	entityManagerHelper.persist(userRole);
    }

    @Test
    public void testUserPrivileges() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();
	UserPrivileges userPrivileges = UserPrivileges.builder().name("test").description("description").build();
	userPrivileges.setId((short) 1);
	System.out.println("#############################");
	System.out.println("#userPrivileges:" + userPrivileges);
	LOGGER.info("#userPrivileges:" + userPrivileges.toString());
	entityManagerHelper.persist(userPrivileges);
    }

    @Test
    public void testUserRolePrivileges() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();
	UserRolePrivileges userRolePrivileges = UserRolePrivileges.builder().roleId((short) 1).privilegeId((short) 1)
		.build();
	System.out.println("#############################");
	System.out.println("#UserRolePrivileges:" + userRolePrivileges);
	LOGGER.info("#UserRolePrivileges:" + userRolePrivileges.toString());
	entityManagerHelper.persist(userRolePrivileges);
    }

    @Test
    public void testLoginDetails() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();
	LoginDetails loginDetails = LoginDetails.builder().accountTypeId((short) 1).loginTypeId((short) 1)
		.roleId((short) 1).accountStatusId((short) 1).passwordTypeId((short) 1)
		.passwordHash(new byte[] { (byte) 0 }).userName("userTest").userId("userIdTest")
		.externalUserId("externalUserIdTest").build();
	System.out.println("#############################");
	System.out.println("#LoginDetails:" + loginDetails);
	LOGGER.info("#LoginDetails:" + loginDetails.toString());
	entityManagerHelper.persist(loginDetails);
    }

    @Test
    public void testLoginTokenDetails() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();
	LoginTokenDetails loginTokenDetails = LoginTokenDetails.builder().loginDetailsId(1L).bearerToken("xxxxxxxx")
		.bearerTokenCreatedOn(System.currentTimeMillis()).loginOn(System.currentTimeMillis())
		.logoutOn(System.currentTimeMillis()).expiryInSeconds(System.currentTimeMillis()).reserve1("reverve1")
		.reserve2("reverve2").reserve3("reverve3").isActive("Y").build();
	System.out.println("#############################");
	System.out.println("#loginTokenDetails:" + loginTokenDetails);
	LOGGER.info("#loginTokenDetails:" + loginTokenDetails.toString());
	entityManagerHelper.persist(loginTokenDetails);
    }

    // @Test
    public void test10_ApplicationProviderCreation() throws Exception {

	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();

	// start transaction
	entityManagerHelper.beginTransaction();

	// Save the Model object
	// final ApplicationProvider ap =
	// TestDataGenerator.generateApplicationProvider01();
	// entityManagerHelper.persist(ap);
	//
	// final Application application =
	// TestDataGenerator.generateApplication01(ap);
	// entityManagerHelper.persist(application);

	// Commit transaction
	entityManagerHelper.commitTransaction();
	// LOGGER.info("Application ID '{}'", application.getId());

	// Assert.assertNotEquals(2L, application.getId());
	// Assert.assertEquals("username1",
	// application.getApplicationProvider().getUser().getUsername());

	// start transaction
	entityManagerHelper.beginTransaction();

	// Delete the Model object
	// entityManagerHelper.remove(application);
	// entityManagerHelper.remove(ap);

	// Commit transaction
	entityManagerHelper.commitTransaction();

	entityManagerHelper.closeEntityManager();
    }

    // @Test
    public void test20_CardIssuerCreation() throws Exception {

	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();

	// final CardIssuer ci = TestDataGenerator.generateCardIssuer01();

	// start transaction
	entityManagerHelper.beginTransaction();

	// Save the Model object
	// entityManagerHelper.persist(ci);

	// Commit transaction
	entityManagerHelper.commitTransaction();
	// LOGGER.info("CardIssuer ID '{}'", ci.getId());

	// Assert.assertNotEquals(2L, ci.getId());
	// Assert.assertEquals("username1", ci.getUser().getUsername());
	// Assert.assertEquals(new byte[] { 0x01, 0x01, 0x01, 0x01 },
	// ci.getUser().getPasswordhash());

	// start transaction
	entityManagerHelper.beginTransaction();

	// delete the Model object
	// entityManagerHelper.remove(ci);

	// Commit transaction
	entityManagerHelper.commitTransaction();

	entityManagerHelper.closeEntityManager();
    }

    // @Test
    public void test30_ApprovedApplicationCreation() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();

	// start transaction
	entityManagerHelper.beginTransaction();

	// final ApplicationProvider ap =
	// TestDataGenerator.generateApplicationProvider01();
	// entityManagerHelper.persist(ap);
	//
	// final Application application =
	// TestDataGenerator.generateApplication01(ap);
	// entityManagerHelper.persist(application);
	//
	// // Save the Model object
	// final CardIssuer ci = TestDataGenerator.generateCardIssuer01();
	// entityManagerHelper.persist(ci);

	// Commit transaction
	entityManagerHelper.commitTransaction();
	// LOGGER.info("Application ID '{}'", application.getId());
	// LOGGER.info("CardIssuer ID '{}'", ci.getId());

	entityManagerHelper.beginTransaction();
	// final ApprovedApplication aa =
	// TestDataGenerator.generateApprovedApplication01(application, ci, 1);
	// entityManagerHelper.persist(aa);
	entityManagerHelper.commitTransaction();
	// LOGGER.info("ApprovedApplication '{}'", aa);

	// Assert.assertEquals(application.getId(),
	// aa.getApplication().getId());
	// Assert.assertEquals(ci.getId(), aa.getCardIssuer().getId());

	// start transaction
	entityManagerHelper.beginTransaction();

	// Delete the Model object
	// entityManagerHelper.remove(aa);
	// entityManagerHelper.remove(ci);
	// entityManagerHelper.remove(application);
	// entityManagerHelper.remove(ap);

	// Commit transaction
	entityManagerHelper.commitTransaction();

	entityManagerHelper.closeEntityManager();
    }

    // Business Segment
    @Test
    public void testBusinessSegment() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();
	BusinessSegment businessSegment = BusinessSegment.builder().id((short) 18).name("TestOne").priority((short) 1)
		.description("TestOneDescription").isActive("Y").build();
	System.out.println("# Business Segment - Insert Beggin #");
	System.out.println("#businessSegment:" + businessSegment);
	LOGGER.info("#businessSegment:" + businessSegment.toString());
	entityManagerHelper.persist(businessSegment);
	System.out.println("# Business Segment - Insert End #");
	entityManagerHelper.commitTransaction();
	entityManagerHelper.closeEntityManager();
    }

    // Registration Status
    @Test
    public void testRegistrationStatus() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();
	RegistrationStatus registrationStatus = RegistrationStatus.builder().id((short) 1).name("Approved")
		.description("Approved").isActive("Y").build();
	System.out.println("# Registration Status - Insert Beggin #");
	System.out.println("#registrationStatus:" + registrationStatus);
	LOGGER.info("#registrationStatus:" + registrationStatus.toString());
	entityManagerHelper.persist(registrationStatus);
	System.out.println("# Registration Status - Insert End #");
	entityManagerHelper.commitTransaction();
	entityManagerHelper.closeEntityManager();
    }

    // Smart Medium Size
    @Test
    public void testSmartMediumSize() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();
	SmartMediumSize smartMediumSize = SmartMediumSize.builder().id((short) 18).name("256").description("256")
		.isActive("Y").build();
	System.out.println("# Smart Medium Size - Insert Beggin #");
	System.out.println("#smartMediumSize:" + smartMediumSize);
	LOGGER.info("#smartMediumSize:" + smartMediumSize.toString());
	entityManagerHelper.persist(smartMediumSize);
	System.out.println("# Smart Medium Size - Insert End #");
	entityManagerHelper.commitTransaction();
	entityManagerHelper.closeEntityManager();
    }

    // Smart Medium Type
    @Test
    public void testSmartMediumType() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();
	SmartMediumType smartMediumType = SmartMediumType.builder().id((short) 18).name("256").description("256")
		.isActive("Y").build();
	System.out.println("# Smart Medium Type - Insert Beggin #");
	System.out.println("#smartMediumType:" + smartMediumType);
	LOGGER.info("#smartMediumType:" + smartMediumType.toString());
	entityManagerHelper.persist(smartMediumType);
	System.out.println("# Smart Medium Type - Insert End #");
	entityManagerHelper.commitTransaction();
	entityManagerHelper.closeEntityManager();
    }

    // Card Issuer
    @Test
    public void testCardIssuer() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	System.out.println(
		"#################################### [oneToOne]Creating Card Issuer ###################################");
	entityManagerHelper.beginTransaction();

	CardIssuer cardIssuer = CardIssuer.builder().firstName("TestCardOne").lastName("TestCardOneLast")
		.email("TestCardOne@gmail.com").registrationStatusId((short) 1).registrationConfirmOn(1).isActive("Y")
		.build();
	System.out.println("# Card Issuer - Insert Beggin #");
	// entityManagerHelper.persist(cardIssuer);
	// System.out.println("# Card Issuer - Insert End #");

	Set<Card> s = new HashSet<Card>();

	Card card = Card.builder().cardName("TestCardOne").contactEmail("TestCardOne@gmail.com").isActive("Y").build();

	s.add(card);

	/*
	 * System.out.println("# Card  - Insert Beggin #");
	 * System.out.println("#Card:" + card); LOGGER.info("#Card:" +
	 * card.toString()); entityManagerHelper.persist(card);
	 * System.out.println("# Card  - Insert End #");
	 */

	Card card2 = Card.builder().cardName("TestCardTwo").contactEmail("TestCardTwo@gmail.com").isActive("Y").build();

	/*
	 * System.out.println("# Card Two  - Insert Beggin #");
	 * System.out.println("#Card Two:" + card2); LOGGER.info("#Card Two :" +
	 * card2.toString()); entityManagerHelper.persist(card2);
	 * System.out.println("# Card Two - Insert End #");
	 */

	s.add(card2);

	cardIssuer.setCards(s);

	CardIssuerTnc ctnc = CardIssuerTnc.builder().isActive("Y").tnc(new byte[] { (byte) 1, (byte) 2 }).build();

	cardIssuer.setCardIssuerTnc(ctnc);

	System.out.println("#CardIssuer:" + cardIssuer);
	LOGGER.info("#CardIssuer:" + cardIssuer.toString());

	entityManagerHelper.persist(cardIssuer);
	entityManagerHelper.persist(ctnc);

	entityManagerHelper.commitTransaction();
	entityManagerHelper.closeEntityManager();
    }

    // Technology Used
    @Test
    public void testTechnologyUsed() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();
	TechnologyUsed technologyUsed = TechnologyUsed.builder().id((short) 18).name("256").description("256")
		.isActive("Y").build();
	System.out.println("# Technology Used - Insert Beggin #");
	System.out.println("#technologyUsed:" + technologyUsed);
	LOGGER.info("#technologyUsed:" + technologyUsed.toString());
	entityManagerHelper.persist(technologyUsed);
	System.out.println("# Technology Used - Insert End #");
	entityManagerHelper.commitTransaction();
	entityManagerHelper.closeEntityManager();
    }

    // Application Provider
    @Test
    public void testApplicationProvider() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();

	ApplicationProvider applicationProvider = ApplicationProvider.builder().firstName("TestCardOne")
		.lastName("TestCardOneLast").email("TestCardOne@gmail.com").registrationStatusId((short) 1)
		.registrationConfirmOn(1).companyOrBusinessLogo(new byte[] { (byte) 1 }).isActive("Y").build();
	System.out.println(
		"######################################### APPLICATION PROVIDER ###############################");
	System.out.println("# ApplicationProvider - Insert Beggin #");
	Set<Application> applications = applicationProvider.getApplications();
	byte[] promotionalLogo = new byte[] {};
	Application application1 = Application.builder().applicationId(Long.parseLong("010203")).applicationName("BMTC")
		.isActive("Y").build();
	Application application2 = Application.builder().applicationId(Long.parseLong("010204"))
		.applicationName("METRO").isActive("Y").build();
	Application application3 = Application.builder().applicationId(Long.parseLong("010205"))
		.applicationName("MICROPAYMENT").isActive("Y").build();
	Application application4 = Application.builder().applicationId(Long.parseLong("010206"))
		.applicationName("TOURIST").isActive("Y").build();
	Application application5 = Application.builder().applicationId(Long.parseLong("010207"))
		.applicationName("TRAVEL").isActive("Y").build();

	applications.add(application1);
	applications.add(application2);
	applications.add(application3);
	applications.add(application4);
	applications.add(application5);

	ApplicationProviderTnc aptnc = ApplicationProviderTnc.builder().isActive("Y")
		.tnc(new byte[] { (byte) 1, (byte) 2 }).build();

	applicationProvider.setApplicationProviderTnc(aptnc);

	LOGGER.info("#ApplicationProvider:" + applicationProvider.toString());
	System.out.println("#ApplicationProvider:" + applicationProvider);
	entityManagerHelper.persist(applicationProvider);
	entityManagerHelper.persist(aptnc);
	System.out.println(
		"######################################### END OF APPLICATION PROVIDER ###############################");
	entityManagerHelper.commitTransaction();
	entityManagerHelper.closeEntityManager();
    }

    // Country
    @Test
    public void testCountry() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();
	Country country = Country.builder().id((short) 250).countryName("CountryName").alpha2ISOCountry("CN")
		.alpha3ISOCountry("CNN").isActive("Y").build();
	System.out.println("# Country - Insert Beggin #");
	System.out.println("#country:" + country);
	LOGGER.info("#country:" + country.toString());
	entityManagerHelper.persist(country);
	System.out.println("# Country - Insert End #");
	entityManagerHelper.commitTransaction();
	entityManagerHelper.closeEntityManager();
    }

    // AddressType
    @Test
    public void testAddressType() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();
	AddressType addressType = AddressType.builder().id((short) 11).name("Company").description("Company")
		.isActive("Y").build();
	System.out.println("# AddressType - Insert Beggin #");
	System.out.println("#addressType:" + addressType);
	LOGGER.info("#addressType:" + addressType.toString());
	entityManagerHelper.persist(addressType);
	System.out.println("# AddressType - Insert End #");
	entityManagerHelper.commitTransaction();
	entityManagerHelper.closeEntityManager();
    }

    // CIAddress
    @Test
    public void testCIAddress() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();
	CardIssuer cardIssuer = CardIssuer.builder().firstName("TestCardIssuerdOne").lastName("TestCardIssuerLast")
		.email("TestCardIssuer@gmail.com").registrationStatusId((short) 1).registrationConfirmOn(1)
		.isActive("Y").build();
	System.out.println("########################### CARD ISSSUER ###############################");
	System.out.println("# Cards Issuer - Insert Beggin #");
	Set<Card> cards = cardIssuer.getCards();
	Card card1 = Card.builder().cardName("TestCardNameOne").contactEmail("TestCardNameOne@gmail.com").isActive("Y")
		.build();
	Card card2 = Card.builder().cardName("TestCardNameTwo").contactEmail("TestCardNameTwo@gmail.com").isActive("Y")
		.build();
	Card card3 = Card.builder().cardName("TestCardNameThree").contactEmail("TestCardNameThree@gmail.com")
		.isActive("Y").build();
	Card card4 = Card.builder().cardName("TestCardNameFour").contactEmail("TestCardNameFour@gmail.com")
		.isActive("Y").build();

	cards.add(card1);
	cards.add(card2);
	cards.add(card3);
	cards.add(card4);

	Set<CIAddress> ciAddresses = cardIssuer.getCiAddress();

	CIAddress ciAddress1 = CIAddress.builder().addressLine1("COMPANY addresss").addressTypeId((short) 1)
		.isActive("Y").build();
	CIAddress ciAddress2 = CIAddress.builder().addressLine1("BRANCH address").addressTypeId((short) 1).isActive("Y")
		.build();

	ciAddresses.add(ciAddress1);
	ciAddresses.add(ciAddress2);

	LOGGER.info("#Card Issuer:" + cardIssuer.toString());
	entityManagerHelper.persist(cardIssuer);
	System.out.println("#CardIssuer:" + cardIssuer);
	System.out.println("####################### END OF CARD ISSUER ###############################");
	System.out.println("# CIAddress - Insert End #");
	entityManagerHelper.commitTransaction();
	entityManagerHelper.closeEntityManager();
    }

    // APAddress
    @Test
    public void testAPAddress() throws Exception {
	final EntityManagerHelper entityManagerHelper = EntityManagerHelper.newInstance();
	entityManagerHelper.beginTransaction();
	ApplicationProvider applicationProvider = ApplicationProvider.builder().firstName("TestApplicationProviderdOne")
		.lastName("TestAppProviderLast").email("TestApplicationProvider@gmail.com")
		.registrationStatusId((short) 1).registrationConfirmOn(1).isActive("Y").build();
	System.out.println("############################### APPLICATION PROVIDER ###############################");
	System.out.println("# ApplicationProvider - Insert Beggin #");
	Set<Application> applications = applicationProvider.getApplications();
	Application application1 = Application.builder().applicationId(Long.parseLong("010203")).applicationName("BMTC")
		.isActive("Y").build();
	Application application2 = Application.builder().applicationId(Long.parseLong("010204"))
		.applicationName("METRO").isActive("Y").build();
	Application application3 = Application.builder().applicationId(Long.parseLong("010205"))
		.applicationName("MICROPAYMENT").isActive("Y").build();
	Application application4 = Application.builder().applicationId(Long.parseLong("010206"))
		.applicationName("TOURIST").isActive("Y").build();
	Application application5 = Application.builder().applicationId(Long.parseLong("010207"))
		.applicationName("TRAVEL").isActive("Y").build();
	applications.add(application1);
	applications.add(application2);
	applications.add(application3);
	applications.add(application4);
	applications.add(application5);

	Set<APAddress> apAddresses = applicationProvider.getApAddress();

	APAddress apAddress1 = APAddress.builder().addressLine1("COMPANY address").addressTypeId((short) 1)
		.isActive("Y").build();
	APAddress apAddress2 = APAddress.builder().addressLine1("BRANCH address").addressTypeId((short) 1).isActive("Y")
		.build();

	apAddresses.add(apAddress1);
	apAddresses.add(apAddress2);

	LOGGER.info("#ApplicationProvider:" + applicationProvider.toString());
	entityManagerHelper.persist(applicationProvider);
	System.out.println("#ApplicationProvider:" + applicationProvider);
	System.out.println(
		"######################################### END OF APPLICATION PROVIDER ###############################");
	System.out.println("# APAddress - Insert End #");
	entityManagerHelper.commitTransaction();
	entityManagerHelper.closeEntityManager();
    }

    private static byte[] loadImage() {
	// Get file from resources folder
	final URL resource = TestDataGenerator.class.getClassLoader().getResource("A.png");
	final File file = new File(resource.getFile());

	// Reading the Image First.
	final Path path = Paths.get(file.getPath());
	try {
	    return Files.readAllBytes(path);
	} catch (final IOException e) {
	    e.printStackTrace();
	    throw new RuntimeException("Image for Testing not found");
	}
    }

    public static void main(String[] args) {
	System.out.println(Arrays.toString(loadImage()));
    }

}
