package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import utilities.CustomUtility;

public class Registration extends BasePage {

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


	//Step 3: verify that home page is visible successfully
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

	//Step 4: click on singup / login button
	public void clickOnSignupLoginBtn(){
		singup_login_Btn.click();
	}

	//Step 5: Verify 'New User Signup!' is visible
	public boolean verifynewUserSingupVisible() {
		return newUserSingupText.isDisplayed();
	}

	//Step 6: Enter the username and Email Address
	public void enterUsername(String username, String email) {
		if (username != null && username.length()!=0) {
			nameInput.sendKeys(username);
		} else {
			System.out.println("The given input username is empty or null");
		}
		if (email != null && email.length()!=0) {
			singupEmailInput.sendKeys(email);
		} else {
			System.out.println("The given input email address is empty or null");
		}
	}

	//Step 7: Click on sing up button
	public void clickOnSignUp() {
		signupBtn.click();
	}

	//Step 8: Verify that 'ENTER ACCOUNT INFORMATION' is visible
	public boolean verifyEnterAccountInfoVisible(){
		return accInfoText.isDisplayed();
	}

	//Step 9:  Fill details: Title, name,  Password, Date of birth
	public void enterDetails(String titleOption, String name, String password, String day, String month, String year){
		//Select user gender title 
		try{
			for(int i =0; i<titleRadio.size(); i++){
				String option = titleRadio.get(i).getAttribute("value");
				if(option.equals(titleOption)){
					CustomUtility.selectRadioOption(titleRadio.get(i));
				}
			}
		
		//enter name
		nameInput.clear();
		nameInput.sendKeys(name);

		//enter password
		passwordInput.sendKeys(password);
		
		//select day of birth
		CustomUtility.selectDropDownOption(daysList, "value", day);
		//select month of birth
		CustomUtility.selectDropDownOption(monthsList, "value", month);
		//select year of birth
		CustomUtility.selectDropDownOption(yearsList, "value", year);

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	

}
