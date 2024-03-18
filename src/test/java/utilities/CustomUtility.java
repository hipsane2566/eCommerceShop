package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomUtility {
	static WebDriver driver; 
	static WebDriverWait wait;
	
	public static void visibilityOfElement(WebElement elem) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(elem));
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
}
