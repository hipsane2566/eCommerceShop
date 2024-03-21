package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Registration;
import testBase.BaseClass;
import utilities.PopupHandle;

public class AccountRegistration extends BaseClass {
	Registration register;

	@Test(description = "Register User")
	public void testCase01() throws InterruptedException {
		boolean status;
		String username = "Hitesh";
		register = new Registration(getDriver());
		PopupHandle popup = register.displaypop();

		// Verify that home page is visible successfully
		log().info("********** Test Case 01 is Started **********");
		log().info("********** Verify that home page is visible successfully? **********");
		status = register.verifyHomepageTitle();
		if (status) {
			Assert.assertTrue(status, "Home page is visible ");
			log().info("********** Home page is visible **********");
		} else {
			Assert.assertFalse(status, "Home page is not visible");
			log().info("********** Home page is not visible **********");
		}

		// Click on 'Signup / Login' button
		register.clickOnSignupLoginBtn();
		log().info("********** Clicked on Singup / Login button **********");

		// Verify 'New User Signup!' is visible
		status = register.verifynewUserSingupVisible();
		log().info("********** Verify New User Signup! text is visible? **********");
		if (status) {
			Assert.assertTrue(status, "New user singup text message is visible");
			log().info("********** New user singup text message is visible **********");
		} else {
			Assert.assertFalse(status, "New user singup text message is not visible");
			log().info("********** New user singup text message is not visible  **********");
		}

		// Enter name and email address
		register.enterUsername(username, "Hitesh2024@gmail.com");
		log().info("********** Username is entered **********");

		// Click 'Signup' button
		register.clickOnSignUp();
		log().info("********** Clicked on Singup button **********");

		// Verify that 'ENTER ACCOUNT INFORMATION' is visible
		status = register.verifyEnterAccountInfoVisible();
		log().info("********** Verify Enter Account information text is visible? **********");
		if (status) {
			Assert.assertTrue(status, "Enter Account Information text message is visible");
			log().info("********** Enter Account Information text message is visible **********");
		} else {
			Assert.assertFalse(status, "Enter Account Information text message is not visible");
			log().info("********** Enter Account Information text message is not visible **********");
		}

		// Fill details: Title, Name, Email, Password, Date of birth
		register.enterDetails("Mr", "Hitesh", "Pass@123", "15", "10", "1996");
		log().info("********** Given details such as 'Title', 'Name', 'Email', 'Password', 'DateOfBirth' is filled up **********");
		// log().error("********** Failed to fill up details **********");

		// Select checkbox 'Sign up for our newsletter!'
		register.selectSignupCheckbox();
		log().info("********** Clicked on singup for our newsletter checkbox **********");

		// Select checkbox 'Receive special offers from our partners!'
		register.selectOffersCheckbox();
		log().info("********** Clicked on receive special offers checkbox **********");

		// Fill details: First name, Last name, Company, Address, Address2, Country,
		// State, City, Zipcode, Mobile Number
		register.fillAddressInfo(username, "Patiyal", "abc pvt ltd", "add 123 qwerty", "zxcv 4321 ghj, test", "India",
				"Pujab", "Mohali", "963258", "9632587410");
		log().info("********** Given details are filled up successfully, First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number **********");
			
		// Click 'Create Account button'
		register.clickOnCreateAccBtn();
		log().info("********** Clicked on create account button **********");

		// Verify that 'ACCOUNT CREATED!' is visible
		status = register.verifyAccCreatedMsgisVisible();
		log().info("********** Verify account created text message is visible**********");
		if (status) {
			Assert.assertTrue(status, "Account is successfully created");
			log().info("********** Account is successfully created **********");
		}else{
			Assert.assertFalse(status, "Account creation is failed");
			log().info("********** Account creation is failed **********");
		}

		// Click 'Continue' button
		register.clickOnContinueBtn();
		log().info("********** Clicked on continue button **********");

		// Verify that 'Logged in as username' is visible
		status = register.verifyLoggedInUsername(username);
		log().info("********** Verify that logged in as username is visible **********");
		if(status){
			Assert.assertTrue(status, "User get Logged in and the username is visible");
			log().info("********** User get logged in successfullly and username is visible **********");
		}else{
			Assert.assertFalse(status, "User login is failed after user account creation");
			log().info("********** User login is failed after user account creation **********");
		}

		// Click 'Delete Account' button
		register.clickOnDeleteAcc();
		log().info("********** Clicked on delete account button **********");
		// popup.close();

		// Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
		status = register.verifyAccDeletedMsg();
		log().info("********** Verify that account deleted test message is visible **********");
		if(status){
			Assert.assertTrue(status, "Account is successfully deleted");
			log().info("********** Account is successfully deleted **********");
		}else{
			Assert.assertFalse(status, "Account deletion is failed");
			log().info("********** Account deletion is failed **********");
		}
		// popup.close();

		//click on continue button
		register.clickOnContinueBtn2();
		log().info("********** Clicked on continue button **********");
	}

	@Test(description = "Register User with existing email")
	public void testCase02() {
		register = new Registration(getDriver());
	}
}
