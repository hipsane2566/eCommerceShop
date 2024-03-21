package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

public class BaseClass {
	protected static WebDriver driver;
	static Properties p;
	static Logger logging;

	public static WebDriver intitializeBrowser() throws IOException {
		p=new Properties();
		FileReader file = new FileReader("./src/test/resources/configure.properties");
		p.load(file);
			if(p.getProperty("os").equalsIgnoreCase("window")) {
				switch(p.getProperty("browser")) {
				case "chrome":
					//Setting up capabilities to run our test script
					ChromeOptions options = new ChromeOptions();
					//Add Adblocker extension to Chrome 
					options.addExtensions(new File("./src/test/resources/Adblock-all-advertisement-No-Ads-extension.crx"));
					options.addArguments("--disable-popup-blocking");
					options.addArguments("disable-infobars");
					// DesiredCapabilities capabilities = new DesiredCapabilities();
					//Using Chrome Options with Desired Capabilities Class
					// capabilities.setCapability(ChromeOptions.CAPABILITY, options);
					// capabilities.setPlatform(Platform.WIN11);
					// capabilities.setBrowserName("chrome");
					// options.merge(capabilities);
					driver = new ChromeDriver(options);
					break;
				case "edge":
					driver = new EdgeDriver();
					break;
				default:
					driver = null;
				}
		}
		driver.get(p.getProperty("Appurl"));
		driver.manage().deleteAllCookies();
		log();
		return driver;
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	@BeforeClass
	public static void setup() throws IOException {
		driver = intitializeBrowser();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}
	
	@AfterClass
	public static void teardown() {
		driver = getDriver();
		driver.quit();
	}
	
	public static Logger log() {
		logging = LogManager.getLogger();
		return logging;
	}
}
