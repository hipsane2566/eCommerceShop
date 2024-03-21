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
		status = register.verifyHomepageTitle();
		if (status) {
			Assert.assertTrue(status, "Home page is visible ");
		} else {
			Assert.assertFalse(status, "Home page is not visible");
		}

		// Click on 'Signup / Login' button
		register.clickOnSignupLoginBtn();

		// Verify 'New User Signup!' is visible
		status = register.verifynewUserSingupVisible();
		if (status) {
			Assert.assertTrue(status, "New user singup text message is visible");
		} else {
			Assert.assertFalse(status, "New user singup text message is not visible");
		}

		// Enter name and email address
		register.enterUsername(username, "Hitesh2024@gmail.com");

		// Click 'Signup' button
		register.clickOnSignUp();

		// Verify that 'ENTER ACCOUNT INFORMATION' is visible
		status = register.verifyEnterAccountInfoVisible();
		if (status) {
			Assert.assertTrue(status, "Enter Account Information text message is visible");
		} else {
			Assert.assertFalse(status, "Enter Account Information text message is not visible");
		}

		// Fill details: Title, Name, Email, Password, Date of birth
		register.enterDetails("Mr", "Hitesh", "Pass@123", "15", "10", "1996");

		// Select checkbox 'Sign up for our newsletter!'
		register.selectSignupCheckbox();

		// Select checkbox 'Receive special offers from our partners!'
		register.selectOffersCheckbox();

		// Fill details: First name, Last name, Company, Address, Address2, Country,
		// State, City, Zipcode, Mobile Number
		register.fillAddressInfo(username, "Patiyal", "abc pvt ltd", "add 123 qwerty", "zxcv 4321 ghj, test", "India",
				"Pujab", "Mohali", "963258", "9632587410");

		// Click 'Create Account button'
		register.clickOnCreateAccBtn();

		// Verify that 'ACCOUNT CREATED!' is visible
		status = register.verifyAccCreatedMsgisVisible();
		if (status) {
			Assert.assertTrue(status, "Account is successfully created");
		}else{
			Assert.assertFalse(status, "Account creation is failed");
		}

		// Click 'Continue' button
		register.clickOnContinueBtn();
		// popup.close();

		// Verify that 'Logged in as username' is visible
		status = register.verifyLoggedInUsername(username);
		if(status){
			Assert.assertTrue(status, "User get Logged in and the username is visible");
		}else{
			Assert.assertFalse(status, "User login failed after user account creation");
		}

		// Click 'Delete Account' button
		register.clickOnDeleteAcc();
		// popup.close();

		// Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
		status = register.verifyAccDeletedMsg();
		if(status){
			Assert.assertTrue(status, "Account is successfully deleted");
		}else{
			Assert.assertFalse(status, "Account deletion is failed");
		}
		// popup.close();

		//click on continue button
		register.clickOnContinueBtn2();
	}

	@Test(description = "Register User with existing email")
	public void testCase02() {
		register = new Registration(getDriver());
	}
}
