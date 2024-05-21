package pageObjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.CustomUtility;

public class LoginPage extends BasePage{
	// public static WebDriver driver;
	public LoginPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath = "//*[contains(text(),'Login to')]")
	private WebElement loginText;
	@FindBy(css = "input[name='email']:nth-child(2)")
	private WebElement emailaddressTxtbox;
	@FindBy(name = "password")
	private WebElement passwordTxtBox;
	@FindBy(xpath = "//button[contains(text(),'Login')]")
	private WebElement loginBtn;
	@FindBy(xpath = "//*[contains(text(),' Logged in as')]")
	private WebElement LoggedInMsg;
	@FindBy(xpath = "//*[contains(text(),' Logged in as')]/b")
	private WebElement user_name;
	@FindBy(partialLinkText = "Delete Account")
	private WebElement deleteAccBtn;
	@FindBy(xpath = "//*[text()='Account Deleted!']")
	private WebElement accDeletedMsg;
	@FindBy(linkText = "Continue")
	private WebElement continueBtn2;
	@FindBy(css ="p[style='color: red;']")
	private WebElement errorMessage;

	public boolean verifyLoginToYourAccount(){
		try{
			if(loginText.isDisplayed()){
				return true;
			}else{
				return false;
			}
	}catch(NoSuchElementException e){
		String msg = e.getMessage();
		System.out.println(msg);
		return false;
	}
}

	public void enterEmailAddress(String emailId){
		emailaddressTxtbox.sendKeys(emailId);
	} 

	public void enterPassword(String password){
		passwordTxtBox.sendKeys(password);
	}
	public void clickOnLoginBtn(){
		loginBtn.click();
	}

	public boolean verifyLoggedIn(String username){
		try{
			String expectedResult = username;
			CustomUtility.visibilityOfElement(LoggedInMsg);
			String actualResult = user_name.getText();
			if(actualResult.equals(expectedResult)){
				return true;
			}else{
				return false;
			}
		}catch(NoSuchElementException e){
			System.out.println(e.getMessage());
			return false;
		}		
	}

	// Step 17: Click 'Delete Account' button
	public void clickOnDeleteAcc() {
		deleteAccBtn.click();
	}

	// Step 18: Verify that 'ACCOUNT DELETED!' is visible and click 'Continue'
	// button
	public boolean verifyAccDeletedMsg() {
		try {			
			return accDeletedMsg.isDisplayed();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public void clickOnContinueBtn2(){
		continueBtn2.click();
	}

	//Error message for invalid email and password"
	public boolean getErrorMessage(String expectedmessage){
		return errorMessage.getText().equals(expectedmessage);
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

