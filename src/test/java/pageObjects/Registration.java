package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import utilities.CustomUtility;
import utilities.PopupHandle;

public class Registration extends BasePage {
	// public static WebDriver driver;

	public Registration(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[contains(text(),'Signup')]")
	private WebElement singup_login_Btn;
	@FindBy(how = How.XPATH, using = "//h2[text()='New User Signup!']")
	private WebElement newUserSingupText;
	@FindBy(css = "input[name='name']")
	private WebElement nameInput;
	@FindBy(css = "input[data-qa='signup-email']")
	private WebElement singupEmailInput;
	@FindBy(css = "button[data-qa='signup-button']")
	private WebElement signupBtn;
	@FindBy(xpath = "//*[text()='Enter Account Information']")
	private WebElement accInfoText;
	@FindBy(css = "div.clearfix input")
	private List<WebElement> titleRadio;
	@FindBy(id = "password")
	private WebElement passwordInput;
	@FindBy(id = "days")
	private WebElement daysList;
	@FindBy(id = "months")
	private WebElement monthsList;
	@FindBy(id = "years")
	private WebElement yearsList;
	@FindBy(id = "newsletter")
	private WebElement newsletterChkbox;
	@FindBy(id = "optin")
	private WebElement specialOffChkbox;
	@FindBy(id = "first_name")
	private WebElement firstNameTxtbox;
	@FindBy(id = "last_name")
	private WebElement lastNameTxtbox;
	@FindBy(id = "company")
	private WebElement companyTxtbox;
	@FindBy(id = "address1")
	private WebElement address_1_Txtbox;
	@FindBy(id = "address2")
	private WebElement address_2_Txtbox;
	@FindBy(id = "country")
	private WebElement countryDrpdwn;
	@FindBy(id = "state")
	private WebElement stateTxtbox;
	@FindBy(id = "city")
	private WebElement cityTxtbox;
	@FindBy(id = "zipcode")
	private WebElement zipcodeTxtbox;
	@FindBy(id = "mobile_number")
	private WebElement mobnoTxtbox;
	@FindBy(css = "button[data-qa='create-account']")
	private WebElement createAccBtn;
	@FindBy(xpath = "//*[text()='Account Created!']")
	private WebElement accCreateSuccessMsg;
	@FindBy(xpath = "//*[text()='Continue']")
	private WebElement continueBtn;
	@FindBy(css = "b:only-of-type")
	private WebElement loginUsername;
	@FindBy(partialLinkText = "Delete Account")
	private WebElement deleteAccBtn;
	@FindBy(xpath = "//*[text()='Account Deleted!']")
	private WebElement accDeletedMsg;
	@FindBy(linkText = "Continue")
	private WebElement continueBtn2;
	
	// Step 3: verify that home page is visible successfully
	public boolean verifyHomepageTitle() {
		boolean status = false;
		try {
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals("Automation Exercise")) {
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Home Page is not visible, as page is not a homepage");
			status = false;
		}
		return status;
	}

	// Step 4: click on singup / login button
	public void clickOnSignupLoginBtn() {
		singup_login_Btn.click();
	}

	// Step 5: Verify 'New User Signup!' is visible
	public boolean verifynewUserSingupVisible() {
		return newUserSingupText.isDisplayed();
	}

	// Step 6: Enter the username and Email Address
	public void enterUsername(String username, String email) {
		if (username != null && username.length() != 0) {
			nameInput.sendKeys(username);
		} else {
			System.out.println("The given input username is empty or null");
		}
		if (email != null && email.length() != 0) {
			singupEmailInput.sendKeys(email);
		} else {
			System.out.println("The given input email address is empty or null");
		}

	}

	// Step 7: Click on sing up button
	public void clickOnSignUp() {
		signupBtn.click();
	}

	// Step 8: Verify that 'ENTER ACCOUNT INFORMATION' is visible
	public boolean verifyEnterAccountInfoVisible() {
		return accInfoText.isDisplayed();
	}

	// Step 9: Fill details: Title, name, Password, Date of birth
	public void enterDetails(String titleOption, String name, String password, String day, String month, String year) {
		// Select user gender title
		try {
			for (int i = 0; i < titleRadio.size(); i++) {
				String option = titleRadio.get(i).getAttribute("value");
				if (option.equals(titleOption)) {
					CustomUtility.selectRadioOption(titleRadio.get(i));
				}
			}

			// enter name
			nameInput.clear();
			nameInput.sendKeys(name);

			// enter password
			passwordInput.sendKeys(password);

			// select day of birth
			CustomUtility.selectDropDownOption(daysList, "value", day);
			// select month of birth
			CustomUtility.selectDropDownOption(monthsList, "value", month);
			// select year of birth
			CustomUtility.selectDropDownOption(yearsList, "value", year);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Step 10: Select checkbox 'Sign up for our newsletter!'
	public void selectSignupCheckbox() {
		if (!newsletterChkbox.isSelected()) {
			CustomUtility.scrollToElementAndClick(newsletterChkbox);
		}
	}

	// Step 11: Select checkbox 'Receive special offers from our partners!'
	public void selectOffersCheckbox() {
		if (!specialOffChkbox.isSelected()) {
			CustomUtility.scrollToElementAndClick(specialOffChkbox);
		}
	}

	// Step 12: Fill Address info details: First name, Last name, Company, Address,
	// Address2, Country, State, City, Zipcode, Mobile Number
	public void fillAddressInfo(String fname, String lname, String company, String add1, String add2, String country,
			String state, String city, String zipcode, String mobileNo) {
		try {
			CustomUtility.sendkeys(firstNameTxtbox, fname);
			CustomUtility.sendkeys(lastNameTxtbox, lname);
			CustomUtility.sendkeys(companyTxtbox, company);
			CustomUtility.sendkeys(address_1_Txtbox, add1);
			CustomUtility.sendkeys(address_2_Txtbox, add2);
			CustomUtility.selectDropDownOption(countryDrpdwn, "value", country);
			CustomUtility.sendkeys(stateTxtbox, state);
			CustomUtility.sendkeys(cityTxtbox, city);
			CustomUtility.sendkeys(zipcodeTxtbox, zipcode);
			CustomUtility.sendkeys(mobnoTxtbox, mobileNo);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	// Step 13: Click 'Create Account button'
	public void clickOnCreateAccBtn() {
		CustomUtility.scrollToElementAndClick(createAccBtn);
	}

	// Step 14: Verify that 'ACCOUNT CREATED!' is visible
	public boolean verifyAccCreatedMsgisVisible() {
		try {
			return accCreateSuccessMsg.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
	}

	// Step 15: Click 'Continue' button
	public void clickOnContinueBtn() {
		continueBtn.click();
	}

	// Step 16: Verify that 'Logged in as username' is visible
	public boolean verifyLoggedInUsername(String username) {
		try {
			return loginUsername.isDisplayed();
		} catch (Exception e) {
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
	public PopupHandle displaypop(){
		return new PopupHandle(driver);
	}
}
