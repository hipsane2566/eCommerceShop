package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import testBase.BaseClass;
import pageObjects.LoginPage;
import pageObjects.Registration;
import utilities.DataProviders;

public class LoginWithInvalidData extends BaseClass{
	Registration register;
    LoginPage lp;
	@Test(dataProvider = "InvalidLoginData",dataProviderClass = DataProviders.class, description="Login User with incorrect email and password",groups ="Regression")
	public void testCase03(String emailId, String password, String expectedMessage){
		register = new Registration(getDriver());
		lp = new LoginPage(getDriver());
		Boolean status; 
		
		// Verify that home page is visible successfully
		extentTest.info("********** Test Case 03 is Started **********");
		extentTest.info("********** Verify that home page is visible successfully? **********");
		status = register.verifyHomepageTitle();
		if (status) {
			Assert.assertTrue(status, "Home page is visible ");
			extentTest.pass("********** Home page is visible **********");
		} else {
			Assert.assertFalse(status, "Home page is not visible");
			Assert.assertFalse(status, "Test case 03 is failed");
			extentTest.fail("********** Home page is not visible **********");
		}

		// Click on 'Signup / Login' button
		register.clickOnSignupLoginBtn();
		extentTest.info("********** Clicked on Singup / Login button **********");

		// Verify 'Login to your account' is visible
		extentTest.info("********** Verify 'Login to your account' is visible **********");
		status = lp.verifyLoginToYourAccount();
		if (status){
			Assert.assertTrue(status, "********** Login to your account is visible **********");
			extentTest.pass("********** Login to your account is visible successfully **********");
		} else {
			Assert.assertTrue(status, "********** Login to your account is not visible **********");
			Assert.assertFalse(status, "Test case 03 is failed");
			extentTest.fail("********** Login to your account is not visible **********");
		}

		//  Enter correct email address and password
		lp.enterEmailAddress("Raj@gmail.com");
		extentTest.info("********** Enter email address **********");
		lp.enterPassword("Password@123");
		extentTest.info("********** Enter password **********");

		// click on login button
		lp.clickOnLoginBtn();
		extentTest.info("********** Click on Login button *********");

		// Verify error 'Your email or password is incorrect!' is visible
		status = lp.getErrorMessage("Your email or password is incorrect!");
		if (status){
			Assert.assertTrue(status, "********** Error Message is visible **********");
			extentTest.pass("********** Error Message is visible and not allow to login successfully **********");
		} else {
			Assert.assertTrue(status, "********** Error Message is not visible **********");
			Assert.assertFalse(status, "Test case 03 is failed");
			extentTest.fail("********** User is able to login with invalid user details **********");
		}

	}
}
