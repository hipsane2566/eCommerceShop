package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.Registration;
import testBase.BaseClass;
import utilities.DataProviders;


public class LoginTest extends BaseClass{
    Registration register;
    LoginPage lp;
    @Test(dataProvider = "LoginTestData", dataProviderClass = DataProviders.class,description = "Register User with existing email", groups ="Regression")
	public void testCase02(String username, String emailId, String password) {
		register = new Registration(getDriver());
		lp = new LoginPage(getDriver());
		Boolean status; 
		
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

		// Verify 'Login to your account' is visible
		log().info("********** Verify 'Login to your account' is visible **********");
		status = lp.verifyLoginToYourAccount();
		if (status){
			Assert.assertTrue(status, "********** Login to your account is visible **********");
			log().info("********** Login to your account is visible successfully **********");
		} else {
			Assert.assertTrue(status, "********** Login to your account is not visible **********");
			log().info("********** Login to your account is not visible **********");
		}

		//  Enter correct email address and password
		lp.enterEmailAddress(emailId);
		log().info("********** Enter email address **********");
		lp.enterPassword(password);
		log().info("********** Enter password **********");

		// click on login button
		lp.clickOnLoginBtn();
		log().info("********** Click on Login button *********");

		// Verify that 'Logged in as username' is visible
		log().info("********** Verify that 'Logged in as username' is visible **********");
		status = lp.verifyLoggedIn(username);
		if (status) {
			Assert.assertTrue(status, "********** 'Logged in as username' is visible **********");
			log().info("********** 'Logged in as username' is visible **********");
		}else {
			Assert.assertFalse(status, "********** 'Logged in as username' is not visible **********");
			log().info("********** 'Logged in as username' is not visible **********");
		}

	}

}
