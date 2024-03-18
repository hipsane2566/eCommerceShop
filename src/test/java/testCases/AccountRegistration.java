package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Registration;
import testBase.BaseClass;

public class AccountRegistration extends BaseClass{
	Registration register;
	
	@Test(description = "Register User")
	public void testCase01() {
		boolean status;
		register = new Registration(getDriver());
		status = register.verifyHomepageTitle();
		if(status){
			Assert.assertTrue(status);
		}else{
			Assert.assertFalse(status);
		}
		register.clickOnSignupLoginBtn();
		status =  register.verifynewUserSingupVisible();
		if(status){
			Assert.assertTrue(status);
		}else{
			Assert.assertFalse(status);
		}
		register.enterUsername("Hitesh","Hitesh2024@gmail.com");
		register.clickOnSignUp();
		status = register.verifyEnterAccountInfoVisible();
		if(status){
			Assert.assertTrue(status);
		}else{
			Assert.assertFalse(status);
		}
		register.enterDetails("Mr", "Hitesh", "Pass@123","15", "10", "1996");
	}
	
	@Test(description = "Register User with existing email")
	public void testCase02() {
		register = new Registration(getDriver()); 
	}
}
