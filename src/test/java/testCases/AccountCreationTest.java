package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Registration;
import testBase.BaseClass;
// import utilities.PopupHandle;
import utilities.DataProviders;

public class AccountCreationTest extends BaseClass {
	Registration register;
	// final static String password = CustomUtility.randomAlphanumeric();
	// final static String emailId = "Hitesh2024@gmail.com";
	// final static String username = "Hitesh";

	@Test(dataProvider = "AccountCreationData", dataProviderClass = DataProviders.class ,description = "Register User", groups="Regression")
	// dataProvider = "RegisterationData", dataProviderClass = RegisterDataProvider.class
	public void testCase01(String username, String emailId, String password, String title, String name,
			String password1,String day, String month, String year, String firstName, String lastName,
			String comp, String add, String add2, String country, String state, String city, String zipcode,
			String mobno) throws InterruptedException {
		// String username, String emailId, String password
		boolean status;
		register = new Registration(getDriver());
		// PopupHandle popup = register.displaypop();

		// Verify that home page is visible successfully
		extentTest.info("********** Test Case 01 is Started **********");
		extentTest.info("********** Verify that home page is visible successfully? **********");
		status = register.verifyHomepageTitle();
		if (status) {
			Assert.assertTrue(status, "Home page is visible ");
			extentTest.pass("********** Home page is visible **********");
		} else {
			Assert.assertFalse(status, "Home page is not visible");
			extentTest.fail("********** Home page is not visible **********");
		}

		// Click on 'Signup / Login' button
		register.clickOnSignupLoginBtn();
		extentTest.info("********** Clicked on Singup / Login button **********");

		// Verify 'New User Signup!' is visible
		status = register.verifynewUserSingupVisible();
		extentTest.info("********** Verify New User Signup! text is visible? **********");
		if (status) {
			Assert.assertTrue(status, "New user singup text message is visible");
			extentTest.pass("********** New user singup text message is visible **********");
		} else {
			Assert.assertFalse(status, "New user singup text message is not visible");
			extentTest.fail("********** New user singup text message is not visible  **********");
		}

		// Enter name and email address
		register.enterUsername(username, emailId);
		extentTest.info("********** Username is entered **********");

		// Click 'Signup' button
		register.clickOnSignUp();
		extentTest.info("********** Clicked on Singup button **********");

		// Verify that 'ENTER ACCOUNT INFORMATION' is visible
		status = register.verifyEnterAccountInfoVisible();
		extentTest.info("********** Verify Enter Account information text is visible? **********");
		if (status) {
			Assert.assertTrue(status, "Enter Account Information text message is visible");
			extentTest.pass("********** Enter Account Information text message is visible **********");
		} else {
			Assert.assertFalse(status, "Enter Account Information text message is not visible");
			extentTest.fail("********** Enter Account Information text message is not visible **********");
		}

		// Fill details: Title, Name, Email, Password, Date of birth
		register.enterDetails(title, name, password1, day, month, year);
		extentTest.info("********** Given details such as 'Title', 'Name', 'Email', 'Password', 'DateOfBirth' is filled up **********");
		// extentTest.error("********** Failed to fill up details **********");

		// Select checkbox 'Sign up for our newsletter!'
		register.selectSignupCheckbox();
		extentTest.info("********** Clicked on singup for our newsletter checkbox **********");

		// Select checkbox 'Receive special offers from our partners!'
		register.selectOffersCheckbox();
		extentTest.info("********** Clicked on receive special offers checkbox **********");

		// Fill details: First name, Last name, Company, Address, Address2, Country,
		// State, City, Zipcode, Mobile Number
		register.fillAddressInfo(firstName, lastName, comp, add, add2, country,
				state, city, zipcode, mobno);
		extentTest.info("********** Given details are filled up successfully, First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number **********");
			
		// Click 'Create Account button'
		register.clickOnCreateAccBtn();
		extentTest.info("********** Clicked on create account button **********");

		// Verify that 'ACCOUNT CREATED!' is visible
		status = register.verifyAccCreatedMsgisVisible();
		extentTest.info("********** Verify account created text message is visible**********");
		if (status) {
			Assert.assertTrue(status, "Account is successfully created");
			extentTest.pass("********** Account is successfully created **********");
		}else{
			Assert.assertFalse(status, "Account creation is failed");
			extentTest.fail("********** Account creation is failed **********");
		}

		// Click 'Continue' button
		register.clickOnContinueBtn();
		extentTest.info("********** Clicked on continue button **********");

		// Verify that 'Logged in as username' is visible
		status = register.verifyLoggedInUsername(username);
		extentTest.info("********** Verify that logged in as username is visible **********");
		if(status){
			Assert.assertTrue(status, "User get Logged in and the username is visible");
			extentTest.pass("********** User get logged in successfullly and username is visible **********");
		}else{
			Assert.assertFalse(status, "User login is failed after user account creation");
			extentTest.fail("********** User login is failed after user account creation **********");
		}

		// Click 'Delete Account' button
		register.clickOnDeleteAcc();
		extentTest.info("********** Clicked on delete account button **********");
		// popup.close();

		// Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
		status = register.verifyAccDeletedMsg();
		extentTest.info("********** Verify that account deleted test message is visible **********");
		if(status){
			Assert.assertTrue(status, "Account is successfully deleted");
			extentTest.pass("********** Account is successfully deleted **********");
		}else{
			Assert.assertFalse(status, "Account deletion is failed");
			extentTest.fail("********** Account deletion is failed **********");
		}
		// popup.close();

		//click on continue button
		register.clickOnContinueBtn2();
		extentTest.info("********** Clicked on continue button **********");
	}

	}
