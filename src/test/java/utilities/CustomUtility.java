package utilities;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.BaseClass;

public class CustomUtility extends BaseClass{

	public static WebDriverWait waitForDuration(){
		return new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	public static void visibilityOfElement(WebElement elem) {
		waitForDuration().until(ExpectedConditions.visibilityOf(elem));
	}

	public static void selectRadioOption(WebElement elem){
		try{
			if(elem.isEnabled()){
			elem.click();
		}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("radio option is not enabled.");
		}	
	}

	/**
   * Select the drop down option using different method.
   *
   * <p>List of method that can be pass to select the option
   * <ul>
	*   <li>"value" to select the option by given value
	*   <li>"index" to select the option by give index number
	*   <li>"visibletext" to select the option by visibletext
	* </ul>
   *
   * @throws NoSuchElementException If no matching option elements are found  
   */
	public static void selectDropDownOption(WebElement elem, String method, String option){
		Select select = new Select(elem);
		switch(method){
			case "value":
				select.selectByValue(option);
				break;
			case "index":
				int index = Integer.parseInt(option);
				select.selectByIndex(index);
				break;
			case "visibletext":
				select.selectByVisibleText(option);
				break;
			default:
				System.out.println("Provide valid methods and option to select the dropdown option");
		}
		
	}

	public static void elementToBeClickable(WebElement elem){
		try{
			waitForDuration().until(ExpectedConditions.elementToBeClickable(elem)).click();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	public static void sendkeys(WebElement elem, String string){
		elem.clear();
		elem.sendKeys(string);
	}

	public static void scrollToElementAndClick(WebElement elem){
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", elem);
		elem.click();
	}

	public static String randomAlphanumeric(){
		String aplhabet = RandomStringUtils.randomAlphabetic(8);
		String numeric = RandomStringUtils.randomAlphanumeric(6);
		return aplhabet + "@" + numeric;
	}

}
