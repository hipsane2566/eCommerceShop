package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PopupHandle {
    private final WebDriverWait wait;
    private static WebDriver driver;
    private final By frame = By.xpath("//*[contains(@name,'aswift')]");
    public PopupHandle(WebDriver driver){
        PopupHandle.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public Boolean isDisplayed() throws InterruptedException {
        try{
            WebElement popup = wait.until(ExpectedConditions.presenceOfElementLocated(frame));
            return popup.isDisplayed();
        }catch(Exception e){
            driver.switchTo().defaultContent();
            return false;
        }
    }

    public void close() throws InterruptedException {
        if(isDisplayed()){
            driver.switchTo().defaultContent();
            driver.navigate().refresh();
            }
        }
    }
